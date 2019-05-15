// Function types

import java.util.*;
import CPP.Absyn.*;

public class FunType {
  final Type returnType;
  final ListArg args;
  public FunType (Type t, ListArg l) {
    returnType = t;
    args = l;
  }
  public String toJVM() {
    String returnString = "(";
    for(Arg a : this.args){
      Type t = ((ADecl)a).type_;
      if(t.equals(new Type_int())){
        returnString += "I";
      }
      else if (t.equals(new Type_bool())){
        returnString += "Z";
      }
      else if (t.equals(new Type_void())){
        returnString += "V";
      }
      else{
        throw(new RuntimeException());
      }

    }
    returnString += ")";
    if(this.returnType.equals(new Type_int())){
      returnString += "I";
    }
    else if (this.returnType.equals(new Type_bool())){
      returnString += "Z";
    }
    else if (this.returnType.equals(new Type_void())){
      returnString += "V";
    }
    else{
      throw(new RuntimeException());
    }
    System.out.println(returnString);
    return(returnString);
  }
}
