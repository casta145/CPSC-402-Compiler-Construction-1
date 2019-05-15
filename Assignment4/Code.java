// Code.java
// Originally created by github.com/andreasabel/java-adt

import CPP.Absyn.*;

class Fun {
    public String id;
    public FunType funType;
    public Fun (String id, FunType funType) {
        this.id = id;
        this.funType = funType;
    }
    public String toJVM() {
      String tojvm = funType.toJVM();
      return this.id + tojvm;
    }
}

class Label {
    public int label;
    public Label (int label) {
        this.label = label;
    }
    public String toJVM() {
      return "L" + label;
    }
}

abstract class Code {
    public abstract <R> R accept (CodeVisitor<R> v);
}

class Comment extends Code {
  public String comment;
  public Comment (String c) { comment = c; }
  public <R> R accept (CodeVisitor<R> v) {
    return v.visit(this);
  }
}

class Store extends Code {
    public Type type;
    public Integer addr;
    public Store (Type type, Integer addr) {
        this.type = type;
        this.addr = addr;
    }
    public <R> R accept (CodeVisitor<R> v) {
        return v.visit (this);
    }
}

class Load extends Code {
    public Type type;
    public Integer addr;
    public Load (Type type, Integer addr) {
        this.type = type;
        this.addr = addr;
    }
    public <R> R accept (CodeVisitor<R> v) {
        return v.visit (this);
    }
}

class IConst extends Code {
    public Integer immed;
    public IConst (Integer immed) {
        this.immed = immed;
    }
    public <R> R accept (CodeVisitor<R> v) {
        return v.visit (this);
    }
}

class Pop extends Code {
    public Type type;
    public Pop (Type type) {
        this.type = type;
    }
    public <R> R accept (CodeVisitor<R> v) {
        return v.visit (this);
    }
}

class Return extends Code {
    public Type type;
    public Return (Type type) {
        this.type = type;
    }
    public <R> R accept (CodeVisitor<R> v) {
        return v.visit (this);
    }
}

class Call extends Code {
    public Fun fun;
    public Call (Fun fun) {
        this.fun = fun;
    }
    public <R> R accept (CodeVisitor<R> v) {
        return v.visit (this);
    }
}

class Target extends Code {
    public Label label;
    public Target (Label label) {
        this.label = label;
    }
    public <R> R accept (CodeVisitor<R> v) {
        return v.visit (this);
    }
}

class Goto extends Code {
    public Label label;
    public Goto (Label label) {
        this.label = label;
    }
    public <R> R accept (CodeVisitor<R> v) {
        return v.visit (this);
    }
}

class IfZ extends Code {
    public Label label;
    public IfZ (Label label) {
        this.label = label;
    }
    public <R> R accept (CodeVisitor<R> v) {
        return v.visit (this);
    }
}

class IfNZ extends Code {
    public Label label;
    public IfNZ (Label label) {
        this.label = label;
    }
    public <R> R accept (CodeVisitor<R> v) {
        return v.visit (this);
    }
}

class IfLt extends Code {
    public Type type;
    public Label label;
    public IfLt (Type type, Label label) {
        this.type = type;
        this.label = label;
    }
    public <R> R accept (CodeVisitor<R> v) {
        return v.visit (this);
    }
}

class IfGt extends Code {
    public Type type;
    public Label label;
    public IfGt (Type type, Label label) {
        this.type = type;
        this.label = label;
    }
    public <R> R accept (CodeVisitor<R> v) {
        return v.visit (this);
    }
}

class IfLtEq extends Code {
    public Type type;
    public Label label;
    public IfLtEq (Type type, Label label) {
        this.type = type;
        this.label = label;
    }
    public <R> R accept (CodeVisitor<R> v) {
        return v.visit (this);
    }
}

class IfGtEq extends Code {
    public Type type;
    public Label label;
    public IfGtEq (Type type, Label label) {
        this.type = type;
        this.label = label;
    }
    public <R> R accept (CodeVisitor<R> v) {
        return v.visit (this);
    }
}

class IfEq extends Code {
    public Type type;
    public Label label;
    public IfEq (Type type, Label label) {
        this.type = type;
        this.label = label;
    }
    public <R> R accept (CodeVisitor<R> v) {
        return v.visit (this);
    }
}

class IfNe extends Code {
    public Type type;
    public Label label;
    public IfNe (Type type, Label label) {
        this.type = type;
        this.label = label;
    }
    public <R> R accept (CodeVisitor<R> v) {
        return v.visit (this);
    }
}


class Add extends Code {
    public Type type;
    public Add (Type type) {
        this.type = type;
    }
    public <R> R accept (CodeVisitor<R> v) {
        return v.visit (this);
    }
}


class Mul extends Code {
    public Type type;
    public Mul (Type type) {
        this.type = type;
    }
    public <R> R accept (CodeVisitor<R> v) {
        return v.visit (this);
    }
}


class Div extends Code {
    public Type type;
    public Div (Type type) {
        this.type = type;
    }
    public <R> R accept (CodeVisitor<R> v) {
        return v.visit (this);
    }
}


class Sub extends Code {
    public Type type;
    public Sub (Type type) {
        this.type = type;
    }
    public <R> R accept (CodeVisitor<R> v) {
        return v.visit (this);
    }
}

interface CodeVisitor<R> {
    public R visit (Comment c);
    public R visit (Store c);
    public R visit (Load c);
    public R visit (IConst c);
    public R visit (Pop c);
    public R visit (Return c);
    public R visit (Call c);
    public R visit (Target c);
    public R visit (Goto c);
    public R visit (IfZ c);
    public R visit (IfNZ c);
    public R visit (IfLt c);
    public R visit (IfGt c);
    public R visit (IfLtEq c);
    public R visit (IfGtEq c);
    public R visit (IfEq c);
    public R visit (IfNe c);
    public R visit (Add c);
    public R visit (Mul c);
    public R visit (Div c);
    public R visit (Sub c);
}

class CodeToJVM implements CodeVisitor<String> {

  public String visit (Comment c) { return "\n\n  ;; " + c.comment; }

  public String visit(Store c){
    if (c.addr > 3) return "\n  istore " + c.addr;
    else return "\n  istore_" + c.addr;
  }

  public String visit(Load c){
    if (c.addr > 3) return "\n  iload " + c.addr;
    else return "\n  iload_" + c.addr;
  }

  public String visit(IConst c){
    if (c.immed > 127) return   "\n  ldc "+c.immed;
    else if (c.immed > 5) return "\n  bipush " + c.immed;
    else return "\n  iconst_" + c.immed;
  }

  public String visit(Pop c){
    String rtrn = "";
    if(!c.type.equals(new Type_void())) rtrn += "\n  pop";
    return rtrn;
  }

  public String visit(Return c){ return "\n  ireturn"; }

  public String visit(Call c){ return "\n  invokestatic " + c.fun.toJVM(); }

  public String visit(Target c){ return "\n  " + c.label.toJVM() + ":"; }

  public String visit(Goto c){ return "\n  goto " + c.label.toJVM(); }

  public String visit(IfZ c){ return "\n  ifeq " + c.label.toJVM(); }

  public String visit(IfNZ c){ return "\n  ifne " + c.label.toJVM(); }

  public String visit(IfLt c){ return "\n  if_icmplt " + c.label.toJVM(); }

  public String visit(IfGt c){ return "\n  if_icmpgt " + c.label.toJVM(); }

  public String visit(IfLtEq c){ return "\n  if_icmple " + c.label.toJVM(); }

  public String visit(IfGtEq c){ return "\n  if_icmpge " + c.label.toJVM(); }

  public String visit(IfEq c){ return "\n  if_icmpeq " + c.label.toJVM(); }

  public String visit(IfNe c){ return "\n  if_icmpne " + c.label.toJVM(); }

  public String visit (Add c){ return "\n  iadd"; }

  public String visit (Mul c){ return "\n  imul"; }

  public String visit (Div c){ return "\n  idiv"; }

  public String visit (Sub c){ return "\n  isub"; }

}
