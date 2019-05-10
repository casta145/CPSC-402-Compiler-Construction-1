import java.util.*;
import CPP.Absyn.*;

public class Compiler
{
  // The output of the compiler is a list of strings.
  LinkedList<String> output;

  // Signature mapping function names to their JVM name and type.
  Map<String,Fun> sig;

  // Context mapping variable identifiers to their type and address.
  LinkedList<Map<String,CxtEntry>> cxt;

  // Next free address for local variable;
  int nextLocal = 0;

  // Number of locals needed for current function
  int limitLocals = 0;

  // Maximum stack size needed for current function
  int limitStack = 0;

  // Current stack size
  int currentStack = 0;

  // Global counter to get next label;
  int nextLabel = 0;

  // Variable information
  public class CxtEntry {
    final Type    type;
    final Integer addr;
    CxtEntry (Type t, Integer a) { type = t; addr = a; }
  }

  // Share type constants
  public final Type BOOL   = new Type_bool  ();
  public final Type INT    = new Type_int   ();
  public final Type DOUBLE = new Type_double();
  public final Type VOID   = new Type_void  ();

  // Compile C-- AST to a .j source file (returned as String).
  // name should be just the class name without file extension.
  public String compile(String name, CPP.Absyn.Program p) {
    // Initialize output
    output = new LinkedList();

    // Output boilerplate
    this.output.add(".class public " + name + "\n");
    this.output.add(".super java/lang/Object\n");
    this.output.add("\n");
    this.output.add(".method public <init>()V\n");
    this.output.add("  .limit locals 1\n");
    this.output.add("\n");
    this.output.add("  aload_0\n");
    this.output.add("  invokespecial java/lang/Object/<init>()V\n");
    this.output.add("  return\n");
    this.output.add("\n");
    this.output.add(".end method\n");
    this.output.add("\n");
    this.output.add(".method public static main([Ljava/lang/String;)V\n");
    this.output.add("  .limit locals 1\n");
    this.output.add("  .limit stack  1\n");
    this.output.add("\n");
    this.output.add("  invokestatic " + name + "/main()I\n");
    this.output.add("  pop\n");
    this.output.add("  return\n");
    this.output.add("\n");
    this.output.add(".end method\n");
    this.output.add("\n");

    // Create signature
    sig = new TreeMap();

    // Built-in functions
    ListArg intArg = new ListArg();
    intArg.add (new ADecl(INT , "x"));
    sig.put("printInt",    new Fun ("Runtime/printInt"   , new FunType (VOID  , intArg)));
    sig.put("readInt",    new Fun ("Runtime/readInt"   , new FunType (INT  , new ListArg())));

    // User-defined functions
    for (Def d: ((PDefs)p).listdef_) {
      DFun def = (DFun)d;
      sig.put(def.id_,
        new Fun(name + "/" + def.id_, new FunType(def.type_, def.listarg_)));
    }

    // Run compiler
    p.accept(new ProgramVisitor(), null);

    // Concatenate strings in output to .j file content.
    StringBuilder jtext = new StringBuilder();
    for (String s: output) {
      jtext.append(s);
    }
    return jtext.toString();
  }

  // Compile program

  public class ProgramVisitor implements Program.Visitor<Void,Void>
  {
    public Void visit(CPP.Absyn.PDefs p, Void arg)
    {
      for (Def def: p.listdef_)
        def.accept(new DefVisitor(), null);
      return null;
    }
  }

  // Compile function definition.

  public class DefVisitor implements Def.Visitor<Void,Void>
  {
    public Void visit(CPP.Absyn.DFun p, Void arg)
    {
      // reset state for new function
      cxt = new LinkedList();
      cxt.add(new TreeMap());
      nextLocal    = 0;
      limitLocals  = 0;
      limitStack   = 0;
      currentStack = 0;

      // save output so far and reset output;
      LinkedList<String> savedOutput = output;
      output = new LinkedList();

      // Compile function

      // Add function parameters to context
      for (Arg x: p.listarg_)
        x.accept (new ArgVisitor(), null);
      for (Stm s: p.liststm_)
        s.accept (new StmVisitor(), null);

      // add new Output to old output
      LinkedList<String> newOutput = output;
      output = savedOutput;

      Fun f = new Fun(p.id_, new FunType(p.type_, p.listarg_));
      output.add("\n.method public static " + f.toJVM() + "\n");
      output.add("  .limit locals " + (limitLocals+1) + "\n");
      output.add("  .limit stack " + (limitStack+1) + "\n\n");
      output.add("  nop\n");
      for (String s: newOutput) {
        output.add("  " + s);
      }
      output.add("\n  return");
      output.add("\n\n.end method\n");
      return null;
    }
  }

  // "Compiling" a function argument means adding it to the context.

  public class ArgVisitor implements Arg.Visitor<Void,Void>
  {
    public Void visit(CPP.Absyn.ADecl p, Void arg)
    {
      newVar (p.id_, p.type_);
      return null;
    }
  }

  // Compile single statement.

  public class StmVisitor implements Stm.Visitor<Void,Void>
  {
    // e;
    public Void visit(CPP.Absyn.SExp p, Void arg)
    {
      emit (new Comment(CPP.PrettyPrinter.print(p)));
      p.exp_.accept (new ExpVisitor(), arg);
      Type t = p.exp_.getType();
      emit (new Pop(t));
      if (!t.equals(new Type_void())) decrStack();
      return null;
    }

    // int x,y,z;
    public Void visit(CPP.Absyn.SDecls p, Void arg)
    {
      emit (new Comment(CPP.PrettyPrinter.print(p)));
      // for (String x: p.listid_) p.type_
      for(String id: p.listid_){
        limitLocals++;
        int addr = newVar(id, p.type_);
        emit (new Comment("Reserve address " + addr + " for " + id + ". No code generated."));
      }
      return null;
    }

    // int x = e;
    public Void visit(CPP.Absyn.SInit p, Void arg)
    {
      // p.type_ p.id_ p.exp_
      emit (new Comment(CPP.PrettyPrinter.print(p)));
      p.exp_.accept (new ExpVisitor(), arg);
      int addr = newVar (p.id_, p.type_);
      emit (new Store (p.type_, addr));
      decrStack();
      return null;
    }

    // return e;
    public Void visit(CPP.Absyn.SReturn p, Void arg)
    {
      // p.exp_getType()
      emit (new Comment(CPP.PrettyPrinter.print(p)));
      p.exp_.accept (new ExpVisitor(), arg);
      emit (new Return(p.exp_.getType()));
      return null;
    }

    // while (e) s
    public Void visit(CPP.Absyn.SWhile p, Void arg)
    {
      // p.exp_ p.stm_
      emit (new Comment("test while-condition (" + CPP.PrettyPrinter.print(p.exp_) + ")"));
      Label start = new Label (nextLabel++);
      Label done  = new Label (nextLabel++);
      emit (new Target(start));
      p.exp_.accept (new ExpVisitor(), arg);
      emit (new IfZ(done));
      decrStack();
      emit (new Comment("while (" + CPP.PrettyPrinter.print(p.exp_) + ") do:"));
      newBlock();
      p.stm_.accept (this, arg);
      popBlock();
      emit (new Goto(start));
      emit (new Target(done));
      return null;
    }

    // { ss }
    public Void visit(CPP.Absyn.SBlock p, Void arg)
    {
      newBlock();
      for (Stm s: p.liststm_) s.accept(this, arg);
      popBlock();
      return null;
    }

    // if (e) s else s'
    public Void visit(CPP.Absyn.SIfElse p, Void arg)
    {
      // p.exp_ p.stm_1 p.stm_2
      emit (new Comment("test if-condition (" + CPP.PrettyPrinter.print(p.exp_) + ")"));
      Label TRUE = new Label (nextLabel++);
      Label FALSE  = new Label (nextLabel++);

      p.exp_.accept (new ExpVisitor(), arg);
      emit (new IfZ(FALSE));
      decrStack();

      newBlock();
      p.stm_1.accept (new StmVisitor(), arg);
      popBlock();
      emit (new Goto(TRUE));

      emit (new Comment("when (" + CPP.PrettyPrinter.print(p.exp_) + ") do:"));
      emit (new Target(FALSE));
      newBlock();
      p.stm_2.accept (new StmVisitor(), arg);
      popBlock();

      emit (new Comment("unless (" + CPP.PrettyPrinter.print(p.exp_) + ") do:"));
      emit(new Target(TRUE));
      return null;
    }
  }

  public class ExpVisitor implements Exp.Visitor<Void,Void>
  {
    // true
    public Void visit(CPP.Absyn.ETrue p, Void arg)
    {
      emit (new IConst (1));
      incrStack();
      return null;
    }

    // false
    public Void visit(CPP.Absyn.EFalse p, Void arg)
    {
      emit (new IConst (0));
      incrStack();
      return null;
    }

    // 5
    public Void visit(CPP.Absyn.EInt p, Void arg)
    {
      emit (new IConst (p.integer_));
      incrStack();
      return null;
    }

    // 3.14
    public Void visit(CPP.Absyn.EDouble p, Void arg)
    {
      throw new RuntimeException ("TODO: compile " + CPP.PrettyPrinter.print(p));
    }

    // x
    public Void visit(CPP.Absyn.EId p, Void arg)
    {
      CxtEntry ce = lookupVar(p.id_);
      emit (new Load (ce.type, ce.addr));
      incrStack();
      return null;
    }

    // f (e_1, ..., e_n)
    public Void visit(CPP.Absyn.EApp p, Void arg)
    {
      // p.id_
      for (Exp e: p.listexp_) e.accept (new ExpVisitor(), arg);
      Fun f = sig.get(p.id_);
      emit (new Call(f));
      return null;
    }

    // x++
    public Void visit(CPP.Absyn.EPostIncr p, Void arg)
    {
      //emit (new IConst (p.integer_));
      //p.integer_ = p.integer_ + 1;
      //return null;

      CxtEntry ce = lookupVar(p.id_);
      emit (new Load (ce.type, ce.addr));
      incrStack();
      emit (new Load (ce.type, ce.addr));
      incrStack();
      emit (new IConst (1));
      incrStack();
      emit (new Add(ce.type));
      decrStack();
      emit (new Store (p.getType(), ce.addr));
      decrStack();
      return null;
    }

    // x--
    public Void visit(CPP.Absyn.EPostDecr p, Void arg)
    {
      CxtEntry ce = lookupVar(p.id_);
      emit (new Load (ce.type, ce.addr));
      incrStack();
      emit (new Load (ce.type, ce.addr));
      incrStack();
      emit (new IConst (1));
      incrStack();
      emit (new Sub(ce.type));
      decrStack();
      emit (new Store (p.getType(), ce.addr));
      decrStack();
      return null;
    }

    // ++x
    public Void visit(CPP.Absyn.EPreIncr p, Void arg)
    {
      //p.integer_ = p.integer_ + 1;
      //emit (new IConst (p.integer_));
      //return null;
      CxtEntry ce = lookupVar(p.id_);
      emit (new Load (ce.type, ce.addr));
      incrStack();
      emit (new IConst (1));
      incrStack();
      emit (new Add(ce.type));
      decrStack();
      emit (new Store (p.getType(), ce.addr));
      decrStack();
      emit (new Load (ce.type, ce.addr));
      incrStack();
      return null;
    }

    // --x
    public Void visit(CPP.Absyn.EPreDecr p, Void arg)
    {
      //p.integer_ = p.integer_ - 1;
      //emit (new IConst (p.integer_));
      //return null;

      CxtEntry ce = lookupVar(p.id_);
      emit (new Load (ce.type, ce.addr));
      incrStack();
      emit (new IConst (1));
      incrStack();
      emit (new Sub(ce.type));
      decrStack();
      emit (new Store (p.getType(), ce.addr));
      decrStack();
      emit (new Load (ce.type, ce.addr));
      incrStack();
      return null;
    }

    // e * e'
    public Void visit(CPP.Absyn.ETimes p, Void arg)
    {
      // p.exp_1 p.exp_2 p.getType()
      p.exp_1.accept (this, arg);
      p.exp_2.accept (this, arg);
      emit (new Mul(p.getType()));
      decrStack();
      return null;
    }

    // e / e'
    public Void visit(CPP.Absyn.EDiv p, Void arg)
    {
      // p.exp_1 p.exp_2 p.getType()
      p.exp_1.accept (this, arg);
      p.exp_2.accept (this, arg);
      emit (new Div(p.getType()));
      decrStack();
      return null;
    }

    //  e + e'
    public Void visit(CPP.Absyn.EPlus p, Void arg)
    {
      // p.exp_1 p.exp_2 p.getType()
      p.exp_1.accept (this, arg);
      p.exp_2.accept (this, arg);
      emit (new Add(p.getType()));
      decrStack();
      return null;
    }

    // e - e'
    public Void visit(CPP.Absyn.EMinus p, Void arg)
    {
      // p.exp_1 p.exp_2 p.getType()
      p.exp_1.accept (this, arg);
      p.exp_2.accept (this, arg);
      emit (new Sub(p.getType()));
      decrStack();
      return null;
    }

    // e < e'
    public Void visit(CPP.Absyn.ELt p, Void arg)
    {
      // p.exp_1 p.exp_2
      p.exp_1.accept(new ExpVisitor(), arg);
      p.exp_2.accept(new ExpVisitor(), arg);
      Label yes  = new Label (nextLabel++);
      Label done = new Label (nextLabel++);
      emit (new IfLt(p.getType(), yes));
      decrStack();
      decrStack();
      emit (new IConst(0));
      incrStack();
      emit (new Goto(done));
      emit (new Target(yes));
      emit (new IConst(1));
      incrStack();
      emit (new Target(done));
      return null;
    }

    // e > e'
    public Void visit(CPP.Absyn.EGt p, Void arg)
    {
      // p.exp_1 p.exp_2
      p.exp_1.accept(new ExpVisitor(), arg);
      p.exp_2.accept(new ExpVisitor(), arg);
      Label yes  = new Label (nextLabel++);
      Label done = new Label (nextLabel++);
      emit (new IfGt(p.getType(), yes));
      decrStack();
      decrStack();
      emit (new IConst(0));
      incrStack();
      emit (new Goto(done));
      emit (new Target(yes));
      emit (new IConst(1));
      incrStack();
      emit (new Target(done));
      return null;
    }

    // e <= e'
    public Void visit(CPP.Absyn.ELtEq p, Void arg)
    {
      // p.exp_1 p.exp_2
      p.exp_1.accept(new ExpVisitor(), arg);
      p.exp_2.accept(new ExpVisitor(), arg);
      Label yes  = new Label (nextLabel++);
      Label done = new Label (nextLabel++);
      emit (new IfLtEq(p.getType(), yes));
      decrStack();
      decrStack();
      emit (new IConst(0));
      incrStack();
      emit (new Goto(done));
      emit (new Target(yes));
      emit (new IConst(1));
      incrStack();
      emit (new Target(done));
      return null;
    }

    // e >= e'
    public Void visit(CPP.Absyn.EGtEq p, Void arg)
    {
      // p.exp_1 p.exp_2
      p.exp_1.accept(new ExpVisitor(), arg);
      p.exp_2.accept(new ExpVisitor(), arg);
      Label yes  = new Label (nextLabel++);
      Label done = new Label (nextLabel++);
      emit (new IfGtEq(p.getType(), yes));
      decrStack();
      decrStack();
      emit (new IConst(0));
      incrStack();
      emit (new Goto(done));
      emit (new Target(yes));
      emit (new IConst(1));
      incrStack();
      emit (new Target(done));
      return null;
    }

    // e == e'
    public Void visit(CPP.Absyn.EEq p, Void arg)
    {
      // p.exp_1 p.exp_2
      p.exp_1.accept(new ExpVisitor(), arg);
      p.exp_2.accept(new ExpVisitor(), arg);
      Label yes  = new Label (nextLabel++);
      Label done = new Label (nextLabel++);
      // If both are equals, we go to "yes"
      emit (new IfEq(p.getType(), yes));
      decrStack();
      decrStack();
      // Otherwise, we say it is false
      emit (new IConst(0));
      incrStack();
      // ... and we go to "done"
      emit (new Goto(done));

      emit (new Target(yes));
      // If it is "yes", we say it is TRUE, and we leave.
      emit (new IConst(1));
      incrStack();

      emit (new Target(done));
      return null;
    }

    // e != e'
    public Void visit(CPP.Absyn.ENEq p, Void arg)
    {
      // p.exp_1 p.exp_2
      p.exp_1.accept(new ExpVisitor(), arg);
      p.exp_2.accept(new ExpVisitor(), arg);
      Label yes  = new Label (nextLabel++);
      Label done = new Label (nextLabel++);
      // If both are non equals, we go to "yes"
      emit (new IfNe(p.getType(), yes));
      decrStack();
      decrStack();
      // Otherwise, we say it is false
      emit (new IConst(0));
      incrStack();
      // ... and we go to "done"
      emit (new Goto(done));

      emit (new Target(yes));
      // If it is "yes", we say it is TRUE, and we leave.
      emit (new IConst(1));
      incrStack();

      emit (new Target(done));
      return null;
    }

    // e && e'
    public Void visit(CPP.Absyn.EAnd p, Void arg)
    {
      // p.exp_1 p.exp_2
      Label no  = new Label (nextLabel++);
      Label done = new Label (nextLabel++);
      p.exp_1.accept(new ExpVisitor(), arg);

      // If first is FALSE, we go to "done".
      emit (new IfZ(no));
      decrStack();
      // Otherwise we put it is TRUE.
      emit (new IConst(1));
      incrStack();
      // Then we visit the second.
      p.exp_2.accept(new ExpVisitor(), arg);
      // Finally, we check if not equal (=FALSE, then).
      // If it is the case we go to "no".
      emit (new IfNe(p.getType(), no));
      decrStack();
      decrStack();
      //Otherwise we put the global exp is TRUE and we go to "done"
      emit (new IConst(1));
      incrStack();
      emit (new Goto(done));

      emit (new Target(no));
      emit (new IConst(0));
      incrStack();

      emit (new Target(done));
      return null;
    }

    // e || e'
    public Void visit(CPP.Absyn.EOr p, Void arg)
    {
      // p.exp_1 p.exp_2
      Label yes  = new Label (nextLabel++);
      Label done = new Label (nextLabel++);
      p.exp_1.accept(new ExpVisitor(), arg);

      // If first is TRUE, we go to "yes".
      emit (new IfNZ(yes));
      decrStack();
      // Otherwise we put it is false.
      emit (new IConst(0));
      incrStack();
      // Then we visit the second.
      p.exp_2.accept(new ExpVisitor(), arg);
      // Finally, we check if not equal (=TRUE, then).
      // If it is the case we go to "yes".
      emit (new IfNe(p.getType(), yes));
      decrStack();
      decrStack();
      //Otherwise we put the global exp is false and we go to "done"
      emit (new IConst(0));
      incrStack();
      emit (new Goto(done));

      emit (new Target(yes));
      emit (new IConst(1));
      incrStack();

      emit (new Target(done));
      return null;
    }

    // x = e
    public Void visit(CPP.Absyn.EAss p, Void arg)
    {
      // p.id_ ce.type ce.addr
      p.exp_.accept (this, arg);
      CxtEntry ce = lookupVar (p.id_);
      emit (new Store (ce.type, ce.addr));
      decrStack();
      emit (new Load (ce.type, ce.addr));
      incrStack();
      return null;
    }
  }

  void newBlock() {
    cxt.add(0, new TreeMap());
  }

  CxtEntry lookupVar(String id){
    for(Map<String, CxtEntry> m: this.cxt){
      CxtEntry v = m.get(id);
      if (v != null) return v;
    } throw new RuntimeException ("lookup var failed for variable " + id);
  }

  int newVar(String id, Type t){
    Map<String,CxtEntry> c = cxt.peek();
    if(c.get(id) == null){
      int addr = incrLocals();
      CxtEntry ce = new CxtEntry(t, addr);
      c.put(id,ce);
      return addr;
    } else throw new RuntimeException("Already declared variable.");
  }

  void popBlock() {
    cxt.remove(0);
  }
  void emit(Code s){
    this.output.add( s.accept(new CodeToJVM()) );
  }

  int incrLocals(){
    int numLocals = this.nextLocal++;
    if(this.limitLocals < numLocals) this.limitLocals++;
    return numLocals;
  }

  void incrStack(){
    this.currentStack++;
    if(this.limitStack < this.currentStack) this.limitStack++;
  }

  void decrStack(){
    this.currentStack--;
  }
}
