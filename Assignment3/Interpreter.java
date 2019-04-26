import java.util.*;
import CPP.Absyn.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Interpreter {

  // signature
  private Map<String,DFun> sig;

  // environment
  private LinkedList<Map<String,Value>> env;

  private Scanner sc = new Scanner(System.in);

  // entrypoint
  public void interpret(Program p) {
    p.accept(new ProgramVisitor (), null);
    // throw new RuntimeException("Not yet an interpreter");
  }

  public class ProgramVisitor implements Program.Visitor<Void,Void>
  {
    public Void visit(CPP.Absyn.PDefs p, Void arg)
    {
      /* Add all definitions to the signature */
      sig = new TreeMap<String,DFun>();
      for (Def d: p.listdef_) {
        DFun d1 = (DFun) d;
        sig.put(d1.id_, d1);
        // d.accept(new DefVisitor(), null);
      }

      /* Find main function */
      DFun main = sig.get("main");
      if (main == null) throw new RuntimeException("Impossible: main function missing");

      // Initialize environment
      env = new LinkedList<Map<String,Value>> ();
      //System.out.println("Before decl"+env);
      env.push(new TreeMap<String,Value>());


      // Execute the function body
      try {
        for (Stm s: main.liststm_)
          s.accept(new StmVisitor(), null);
      } catch (ReturnException e) {}
      env.pop();
      //System.out.println("After end of Decl"+env);
      //throw new RuntimeException("Main function needs a return statement.");
      return null;
    }
  }
  public class DefVisitor implements Def.Visitor<Void,Void>
  {
    public Void visit(CPP.Absyn.DFun p, Void arg)
    { /* Code For DFun Goes Here */
      // p.type_.accept(new TypeVisitor<Void,Void>(), arg);
      //p.id_;

    // Execute the function body
      //TreeMap<String,Value> envCall = env.pop();
      //for (Arg x: p.listarg_){
      //envCall
      //}
      //env.add(tm);
    try{
      for(Stm statement : p.liststm_)
        statement.accept(new StmVisitor(),arg);
    } catch (ReturnException e){throw e;}
    return null;
    //}
  }
}

  ////////////////////////////// Statements //////////////////////////////

  public class StmVisitor implements Stm.Visitor<Void,Void>
  {
    public Void visit(CPP.Absyn.SExp p, Void arg)
    { /* Code For SExp Goes Here */
      Value v = p.exp_.accept(new ExpVisitor(), arg);
      return null;
    }
    public Void visit(CPP.Absyn.SDecls p, Void arg)
    { /* Code For SDecls Goes Here */
      // p.type_.accept(new TypeVisitor(), arg);
      for (String x: p.listid_)
      {
        newVar (x, null);
      }
      return null;
    }
    public Void visit(CPP.Absyn.SInit p, Void arg)
    { /* Code For SInit Goes Here */
      // p.type_.accept(new TypeVisitor(), arg);
      //p.id_;
      Value v = p.exp_.accept(new ExpVisitor(), arg);
      newVar (p.id_, v);
      return null;
    }
    public Void visit(CPP.Absyn.SReturn p, Void arg)// throws ReturnException
    { /* Code For SReturn Goes Here */
      Value v = p.exp_.accept(new ExpVisitor(), arg);
      throw new ReturnException(v);
    }
    public Void visit(CPP.Absyn.SWhile p, Void arg)
    { /* Code For SWhile Goes Here */
      //System.out.println("before while :"+env);
      env.push(new TreeMap<String,Value>());
      while(((VBool)p.exp_.accept(new ExpVisitor(), arg)).value)
      {
        //System.out.println("env in while iterations :" + env);
        env.push(new TreeMap<String,Value>());
        try{p.stm_.accept(new StmVisitor(), arg);}
        catch (ReturnException e){
          env.pop();
          env.pop();
          throw e;
        }
        env.pop();
        //System.out.println("env after while"+ env);
      }
      env.pop();
      //System.out.println("after while"+env);
      return null;
    }
    public Void visit(CPP.Absyn.SBlock p, Void arg)
    { /* Code For SBlock Goes Here */
    //System.out.println("before block" +env);
      env.push(new TreeMap<String,Value>());
      try{
        for(Stm x: p.liststm_)
        {
          x.accept(new StmVisitor(), arg);
        }
      } catch(ReturnException e){
        env.pop();
        //System.out.println("after block"+env);
        throw e;
      }
      env.pop();
      //System.out.println("after block"+env);

      return null;
    }
    public Void visit(CPP.Absyn.SIfElse p, Void arg)
    { /* Code For SIfElse Goes Here */
      //System.out.println("before if " +env);
      env.push(new TreeMap<String,Value>());
      if( ( (VBool) p.exp_.accept( new ExpVisitor(), arg) ).value )
      {
        //System.out.println("before if then"+ env);
        env.push(new TreeMap<String,Value>());
        try{p.stm_1.accept(new StmVisitor(), arg);}
        catch(ReturnException e) {

          env.pop();
          //System.out.println("after if then" +env);
          env.pop();
          //System.out.println("after if" +env);
          throw e;
        }
        env.pop();
        //System.out.println("after if then" +env);
      }
      else
      {
        //System.out.println("before else "+env);
        env.push(new TreeMap<String,Value>());
        try{p.stm_2.accept(new StmVisitor(), arg);}
        catch(ReturnException e){
          env.pop();
          //System.out.println("after else "+env);
          env.pop();
          //System.out.println("after if "+env);
          throw e;
        }
        env.pop();
        //System.out.println("after else "+env);
      }
      env.pop();
      //System.out.println("after if "+env);
      return null;
    }
  }

  ////////////////////////////// Expressions //////////////////////////////

  public class ExpVisitor implements Exp.Visitor<Value,Void>
  {
    public Value visit(CPP.Absyn.ETrue p, Void arg)
    {
      return new VBool(true);
    }
    public Value visit(CPP.Absyn.EFalse p, Void arg)
    {
      return new VBool(false);
    }
    public Value visit(CPP.Absyn.EInt p, Void arg)
    { /* Code For EInt Goes Here */
      return new VInt(p.integer_);
    }
    public Value visit(CPP.Absyn.EDouble p, Void arg)
    { /* Code For EDouble Goes Here */
      return new VDouble(p.double_);
    }
    public Value visit(CPP.Absyn.EId p, Void arg)
    {
      return lookupVar (p.id_);
    }
    public Value visit(CPP.Absyn.EApp p, Void arg)
    { /* Code For EApp Goes Here */
      if (p.id_.equals("printInt")) {
        VInt v = (VInt) p.listexp_.get(0).accept (new ExpVisitor(), null);
        System.out.println (v.value);
      } else if (p.id_.equals("printDouble")) {
        VDouble v = (VDouble) p.listexp_.get(0).accept (new ExpVisitor(), null);
        System.out.println (v.value);
      } else if (p.id_.equals("readInt")) {
          //System.out.println("Scanning for an int");


          int returnval = sc.nextInt();
          //System.out.println(returnval);
          //throw new ReturnException(
          return(new VInt(returnval));

      } else if (p.id_.equals("readDouble")) {
        //System.out.println("Scanning for an double");

          //Scanner sc = new Scanner(System.in);

          double returnval = sc.nextDouble();
          //System.out.println(returnval);
          //throw new ReturnException(/
          return(new VDouble(returnval));

      } else {

        DFun fun = sig.get(p.id_);
        if (fun == null) throw new RuntimeException("Impossible: function missing");
        // Initialize environment
        TreeMap<String,Value> tm = new TreeMap<String,Value>();
        Value returnVal = null;
        for (int i = 0; i<p.listexp_.size(); i++){
          tm.put(((ADecl)(fun.listarg_.get(i))).id_, p.listexp_.get(i).accept (new ExpVisitor(), null));
        }
        //System.out.println(tm);
        //System.out.println("before calling function "+env);
        env.push(tm);
        try {
          fun.accept(new DefVisitor(), null);
        } catch (ReturnException e){returnVal = e.getReturnVal();}
        env.pop();
        //System.out.println("after returning from function "+env);
        return returnVal;
      }
      // return new RuntimeException ("Function had no return statement");
      return new VVoid();
    }
    public Value visit(CPP.Absyn.EPostIncr p, Void arg)
    { /* Code For EPostIncr Goes Here */
      Value v  = lookupVar (p.id_);
      Value v1 = increment (v);
      assignVar (p.id_, v1);
      return v;
    }
    public Value visit(CPP.Absyn.EPostDecr p, Void arg)
    { /* Code For EPostIncr Goes Here */
      Value v  = lookupVar (p.id_);
      Value v1 = decrement (v);
      assignVar (p.id_, v1);
      return v;
    }
    public Value visit(CPP.Absyn.EPreIncr p, Void arg)
    { /* Code For EPreIncr Goes Here */
      Value v  = lookupVar (p.id_);
      Value v1 = increment (v);
      assignVar (p.id_, v1);
      return v1;
    }
    public Value visit(CPP.Absyn.EPreDecr p, Void arg)
    { /* Code For EPreDecr Goes Here */
      Value v  = lookupVar (p.id_);
      Value v1 = decrement (v);
      assignVar (p.id_, v1);
      return v1;
    }
    public Value visit(CPP.Absyn.ETimes p, Void arg)
    { /* Code For ETimes Goes Here */
      Value v1 = p.exp_1.accept(new ExpVisitor(), arg);
      Value v2 = p.exp_2.accept(new ExpVisitor(), arg);
      if ( v1 instanceof VInt ){
        return new VInt(((VInt)v1).value * ((VInt)v2).value);
      } else if ( v1 instanceof VDouble ) {
        return new VDouble(((VDouble)v1).value * ((VDouble)v2).value);
      }
      throw new TypeException ("Wrong given type for multiplication.");
    }
    public Value visit(CPP.Absyn.EDiv p, Void arg)
    { /* Code For EDiv Goes Here */
      Value v1 = p.exp_1.accept(new ExpVisitor(), arg);
      Value v2 = p.exp_2.accept(new ExpVisitor(), arg);
      if ( v1 instanceof VInt ){
        return new VInt(((VInt)v1).value / ((VInt)v2).value);
      } else if ( v1 instanceof VDouble ) {
        return new VDouble(((VDouble)v1).value / ((VDouble)v2).value);
      }
      throw new TypeException ("Wrong given type for division.");
    }
    public Value visit(CPP.Absyn.EPlus p, Void arg)
    { /* Code For EPlus Goes Here */
      Value v1 = p.exp_1.accept(new ExpVisitor(), arg);
      Value v2 = p.exp_2.accept(new ExpVisitor(), arg);
      if ( v1 instanceof VInt ){
        return new VInt(((VInt)v1).value.intValue() + ((VInt)v2).value.intValue());
      } else if ( v1 instanceof VDouble ) {
        return new VDouble(((VDouble)v1).value + ((VDouble)v2).value);
      }
      throw new TypeException ("Wrong given type for addition.");
    }
    public Value visit(CPP.Absyn.EMinus p, Void arg)
    { /* Code For EMinus Goes Here */
      Value v1 = p.exp_1.accept(new ExpVisitor(), arg);
      Value v2 = p.exp_2.accept(new ExpVisitor(), arg);
      if ( v1 instanceof VInt ){
        return new VInt(((VInt)v1).value - ((VInt)v2).value);
      } else if ( v1 instanceof VDouble ) {
        return new VDouble(((VDouble)v1).value - ((VDouble)v2).value);
      }
      throw new TypeException ("Wrong given type for substraction.");
    }
    public Value visit(CPP.Absyn.ELt p, Void arg)
    { /* Code For ELt Goes Here */
      Value v1 = p.exp_1.accept(new ExpVisitor(), arg);
      Value v2 = p.exp_2.accept(new ExpVisitor(), arg);

      if(v1 instanceof VInt) return new VBool(((VInt)v1).value.intValue() < ((VInt)v2).value.intValue());
      else if(v1 instanceof VDouble) return new VBool(((VDouble)v1).value.doubleValue() < ((VDouble)v2).value.doubleValue());
      throw new TypeException("type mismatch in less statement");
    }
    public Value visit(CPP.Absyn.EGt p, Void arg)
    { /* Code For EGt Goes Here */
      Value v1 = p.exp_1.accept(new ExpVisitor(), arg);
      Value v2 = p.exp_2.accept(new ExpVisitor(), arg);

      if(v1 instanceof VInt) return new VBool(((VInt)v1).value.intValue() > ((VInt)v2).value.intValue());
      else if(v1 instanceof VDouble) return new VBool(((VDouble)v1).value.doubleValue()  > ((VDouble)v2).value.doubleValue());
      throw new TypeException("type mismatch in greater statement");
    }
    public Value visit(CPP.Absyn.ELtEq p, Void arg)
    { /* Code For ELtEq Goes Here */
      Value v1 = p.exp_1.accept(new ExpVisitor(), arg);
      Value v2 = p.exp_2.accept(new ExpVisitor(), arg);

      if(v1 instanceof VInt) return new VBool(((VInt)v1).value.intValue() <= ((VInt)v2).value.intValue());
      else if(v1 instanceof VDouble) return new VBool(((VDouble)v1).value.doubleValue() <= ((VDouble)v2).value.doubleValue());
      throw new TypeException("type mismatch in less or equal statement");
    }
    public Value visit(CPP.Absyn.EGtEq p, Void arg)
    { /* Code For EGtEq Goes Here */
      Value v1 = p.exp_1.accept(new ExpVisitor(), arg);
      Value v2 = p.exp_2.accept(new ExpVisitor(), arg);
      if(v1 instanceof VInt) return new VBool(((VInt)v1).value.intValue() >= ((VInt)v2).value.intValue());
      else if(v1 instanceof VDouble) return new VBool(((VDouble)v1).value.doubleValue() >= ((VDouble)v2).value.doubleValue());
      throw new TypeException("type mismatch in greater or equal statement");
    }
    public Value visit(CPP.Absyn.EEq p, Void arg)
    { /* Code For EEq Goes Here */
      Value v1 = p.exp_1.accept(new ExpVisitor(), arg);
      Value v2 = p.exp_2.accept(new ExpVisitor(), arg);
      if(v1 instanceof VInt) return new VBool(((VInt)v1).value.equals(((VInt)v2).value));
      else if(v1 instanceof VDouble) return new VBool(((VDouble)v1).value.equals( ((VDouble)v2).value));
      else if(v1 instanceof VBool) return new VBool(((VBool)v1).value == ((VBool)v2).value);
      throw new TypeException("type mismatch in equal statement");
    }
    public Value visit(CPP.Absyn.ENEq p, Void arg)
    { /* Code For ENEq Goes Here */
      Value v1 = p.exp_1.accept(new ExpVisitor(), arg);
      Value v2 = p.exp_2.accept(new ExpVisitor(), arg);
      if(v1 instanceof VInt) return new VBool(!(((VInt)v1).value.equals(((VInt)v2).value)));
      else if(v1 instanceof VDouble) return new VBool(!( ( (VDouble) v1).value.equals( ((VDouble)v2).value) ) );
      else if(v1 instanceof VBool) return new VBool(((VBool)v1).value != ((VBool)v2).value);
      throw new TypeException("type mismatch in not equal statement");
    }
    public Value visit(CPP.Absyn.EAnd p, Void arg)
    { /* Code For EAnd Goes 1Here */
      Value v1 = p.exp_1.accept(new ExpVisitor(), arg);
      if(!((VBool)v1).value) return new VBool(false);
      Value v2 = p.exp_2.accept(new ExpVisitor(), arg);
      return new VBool(((VBool)v1).value && ((VBool)v2).value);
    }
    public Value visit(CPP.Absyn.EOr p, Void arg)
    { /* Code For EOr Goes Here */
      Value v1 = p.exp_1.accept(new ExpVisitor(), arg);
      if(((VBool)v1).value) return new VBool(true);
      Value v2 = p.exp_2.accept(new ExpVisitor(), arg);
      return new VBool(((VBool)v1).value || ((VBool)v2).value);
    }
    public Value visit(CPP.Absyn.EAss p, Void arg)
    { /* Code For EAss Goes Here */
      Value v = p.exp_.accept(new ExpVisitor(), arg);
      assignVar(p.id_, v);
      return v;
    }
  }

  public Value lookupVar (String x) {
    for (Map<String,Value> b : env) {
      Value v = b.get(x);
      if (v != null) return v;
    }
    throw new RuntimeException ("Unbound variable " + x);
  }

  public void assignVar (String x, Value v) {
    for (Map<String,Value> e : env) {
      if (e.containsKey(x)) { e.put(x,v); return; }
    }
    throw new RuntimeException ("Undeclared variable " + x);
  }

  public void newVar (String x, Value v) {
    Map<String,Value> b = env.peek();
    if(b.get(x) == null){
      b.put(x,v);
    } else throw new RuntimeException("Already declared variable.");
  }

  public Value increment (Value v) {
    if (v instanceof VInt) {
      return new VInt(((VInt)v).value + 1);
    } else if(v instanceof VDouble){
      return new VDouble(((VDouble)v).value + 1.0);
    }
    throw new TypeException ("Error during increment");
  }
  public Value decrement (Value v) {
    if (v instanceof VInt) {
      return new VInt(((VInt)v).value - 1);
    } else if(v instanceof VDouble){
      return new VDouble(((VDouble)v).value - 1.0);
    }
    throw new TypeException ("Error during increment");
  }
}
