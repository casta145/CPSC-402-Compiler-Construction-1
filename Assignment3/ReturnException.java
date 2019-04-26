class ReturnException extends RuntimeException{
    public Value returnValue;

    public ReturnException() {}

    public ReturnException (String message){
      super(message);
    }

    public ReturnException(Value v){
      this.returnValue = v;
    }

    public Value getReturnVal(){
      return this.returnValue;
    }
}
