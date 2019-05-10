.class public scopes
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

  invokestatic scopes/main()I
  pop
  return

.end method


.method public static f()I
  .limit locals 2
  .limit stack 4

  nop
  

  ;; int n = 2;
  
  iconst_2  
  istore_0  

  ;; test if-condition (n < 3)  
  iload_0  
  iconst_3  
  if_icmplt L2  
  iconst_0  
  goto L3  
  L2:  
  iconst_1  
  L3:  
  ifeq L1  

  ;; int n = 3;
  
  iconst_3  
  istore_1  

  ;; return n;
  
  iload_1  
  ireturn  
  goto L0  

  ;; when (n < 3) do:  
  L1:  

  ;; unless (n < 3) do:  
  L0:  

  ;; return n;
  
  iload_0  
  ireturn
  return

.end method

.method public static main()I
  .limit locals 1
  .limit stack 4

  nop
  

  ;; int n = 1;
  
  iconst_1  
  istore_0  

  ;; printInt (n);
  
  iload_0  
  invokestatic Runtime/printInt(I)V    

  ;; printInt (f ());
  
  invokestatic scopes/f()I  
  invokestatic Runtime/printInt(I)V    

  ;; printInt (n);
  
  iload_0  
  invokestatic Runtime/printInt(I)V    

  ;; return 0;
  
  iconst_0  
  ireturn
  return

.end method
