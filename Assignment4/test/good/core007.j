.class public core007
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

  invokestatic core007/main()I
  pop
  return

.end method


.method public static main()I
  .limit locals 1
  .limit stack 3

  nop
  

  ;; int x = 7;
  
  bipush 7  
  istore_0  

  ;; printInt (x);
  
  iload_0  
  invokestatic Runtime/printInt(I)V    

  ;; return 0;
  
  iconst_0  
  ireturn
  return

.end method
