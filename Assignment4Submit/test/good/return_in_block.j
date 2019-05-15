.class public return_in_block
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

  invokestatic return_in_block/main()I
  pop
  return

.end method


.method public static niam()I
  .limit locals 1
  .limit stack 3

  nop
  

  ;; return 99;
  
  bipush 99  
  ireturn  

  ;; printInt (1);
  
  iconst_1  
  invokestatic Runtime/printInt(I)V  
  return

.end method

.method public static main()I
  .limit locals 1
  .limit stack 3

  nop
  

  ;; printInt (niam ());
  
  invokestatic return_in_block/niam()I  
  invokestatic Runtime/printInt(I)V    

  ;; return 0;
  
  iconst_0  
  ireturn  

  ;; printInt (2);
  
  iconst_2  
  invokestatic Runtime/printInt(I)V  
  return

.end method
