.class public core109
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

  invokestatic core109/main()I
  pop
  return

.end method


.method public static main()I
  .limit locals 1
  .limit stack 3

  nop
  

  ;; int j = 4;
  
  iconst_4  
  istore_0  

  ;; printInt (j);
  
  iload_0  
  invokestatic Runtime/printInt(I)V    

  ;; return j;
  
  iload_0  
  ireturn
  return

.end method
