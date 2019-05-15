.class public return_in_if_right
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

  invokestatic return_in_if_right/main()I
  pop
  return

.end method


.method public static g()I
  .limit locals 1
  .limit stack 4

  nop
  

  ;; test if-condition (false)  
  iconst_0  
  ifeq L1  

  ;; return 12;
  
  bipush 12  
  ireturn  
  goto L0  

  ;; when (false) do:  
  L1:  

  ;; return 11;
  
  bipush 11  
  ireturn  

  ;; unless (false) do:  
  L0:  

  ;; return 10;
  
  bipush 10  
  ireturn
  return

.end method

.method public static main()I
  .limit locals 1
  .limit stack 2

  nop
  

  ;; printInt (g ());
  
  invokestatic return_in_if_right/g()I  
  invokestatic Runtime/printInt(I)V    

  ;; return 0;
  
  iconst_0  
  ireturn
  return

.end method
