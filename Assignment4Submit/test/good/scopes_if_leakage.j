.class public scopes_if_leakage
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

  invokestatic scopes_if_leakage/main()I
  pop
  return

.end method


.method public static main()I
  .limit locals 7
  .limit stack 4

  nop
  

  ;; bool var = true;
  
  iconst_1  
  istore_0  

  ;; test if-condition (true)  
  iconst_1  
  ifeq L1  

  ;; int var = 1;
  
  iconst_1  
  istore_1  
  goto L0  

  ;; when (true) do:  
  L1:  

  ;; int var = 0;
  
  iconst_0  
  istore_2  

  ;; unless (true) do:  
  L0:  

  ;; var == true;
  
  iload_0  
  iconst_1  
  if_icmpeq L2  
  iconst_0  
  goto L3  
  L2:  
  iconst_1  
  L3:  
  pop  

  ;; bool var1 = var;
  
  iload_0  
  istore_3  

  ;; test if-condition (false)  
  iconst_0  
  ifeq L5  

  ;; int var = 1;
  
  iconst_1  
  istore 4  
  goto L4  

  ;; when (false) do:  
  L5:  

  ;; int var = 0;
  
  iconst_0  
  istore 5  

  ;; unless (false) do:  
  L4:  

  ;; var == true;
  
  iload_0  
  iconst_1  
  if_icmpeq L6  
  iconst_0  
  goto L7  
  L6:  
  iconst_1  
  L7:  
  pop  

  ;; bool var2 = var;
  
  iload_0  
  istore 6  

  ;; return 0;
  
  iconst_0  
  ireturn
  return

.end method
