.class public void_return_empty
.super java/lang/Object

.method public <init>()V
  .limit locals 1

  aload_0
  invokespecial java/lang/Object/<init>()V
  return

.end method

.method public static main([Ljava/lang/String;)V
  .limit locals 1
  .limit stack  1

  invokestatic void_return_empty/main()I
  pop
  return

.end method


.method public static empty()V
  .limit locals 1
  .limit stack 1

  nop

  return

.end method

.method public static main()I
  .limit locals 1
  .limit stack 2

  nop
  

  ;; return 0;
  
  iconst_0  
  ireturn
  return

.end method
