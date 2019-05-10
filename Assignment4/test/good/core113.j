.class public core113
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

  invokestatic core113/main()I
  pop
  return

.end method


.method public static main()I
  .limit locals 1
  .limit stack 4

  nop
  

  ;; printInt (f (45));
  
  bipush 45  
  invokestatic core113/f(I)I  
  invokestatic Runtime/printInt(I)V    

  ;; printInt (f (450));
  
  ldc 450  
  invokestatic core113/f(I)I  
  invokestatic Runtime/printInt(I)V    

  ;; return 0;
  
  iconst_0  
  ireturn
  return

.end method

.method public static f(I)I
  .limit locals 3
  .limit stack 3

  nop
  

  ;; int y;
  

  ;; Reserve address 1 for y. No code generated.  

  ;; test if-condition (x < 100)  
  iload_0  
  bipush 100  
  if_icmplt L2  
  iconst_0  
  goto L3  
  L2:  
  iconst_1  
  L3:  
  ifeq L1  

  ;; int x = 91;
  
  bipush 91  
  istore_2  

  ;; y = x;
  
  iload_2  
  istore_1  
  iload_1  
  pop  
  goto L0  

  ;; when (x < 100) do:  
  L1:  

  ;; y = x;
  
  iload_0  
  istore_1  
  iload_1  
  pop  

  ;; unless (x < 100) do:  
  L0:  

  ;; return y;
  
  iload_1  
  ireturn
  return

.end method
