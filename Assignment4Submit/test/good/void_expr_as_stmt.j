.class public void_expr_as_stmt
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

  invokestatic void_expr_as_stmt/main()I
  pop
  return

.end method


.method public static main()I
  .limit locals 1
  .limit stack 2

  nop
  

  ;; foo ();
  
  invokestatic void_expr_as_stmt/foo()V    

  ;; return 0;
  
  iconst_0  
  ireturn
  return

.end method

.method public static foo()V
  .limit locals 1
  .limit stack 2

  nop
  

  ;; printInt (1);
  
  iconst_1  
  invokestatic Runtime/printInt(I)V  
  return

.end method
