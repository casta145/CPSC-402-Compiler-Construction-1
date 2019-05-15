.class public scopes_reuse_name
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

  invokestatic scopes_reuse_name/main()I
  pop
  return

.end method


.method public static main()I
  .limit locals 11
  .limit stack 12

  nop
  

  ;; int x = 0;
  
  iconst_0  
  istore_0  

  ;; test if-condition (true)  
  iconst_1  
  ifeq L1  

  ;; int x = 1;
  
  iconst_1  
  istore_1  
  goto L0  

  ;; when (true) do:  
  L1:  

  ;; unless (true) do:  
  L0:  

  ;; printInt (x);
  
  iload_0  
  invokestatic Runtime/printInt(I)V    

  ;; test if-condition (true)  
  iconst_1  
  ifeq L3  

  ;; int x;
  

  ;; Reserve address 2 for x. No code generated.  

  ;; x = 2;
  
  iconst_2  
  istore_2  
  iload_2  
  pop  
  goto L2  

  ;; when (true) do:  
  L3:  

  ;; unless (true) do:  
  L2:  

  ;; printInt (x);
  
  iload_0  
  invokestatic Runtime/printInt(I)V    

  ;; test if-condition (false)  
  iconst_0  
  ifeq L5  
  goto L4  

  ;; when (false) do:  
  L5:  

  ;; int x = 3;
  
  iconst_3  
  istore_3  

  ;; unless (false) do:  
  L4:  

  ;; printInt (x);
  
  iload_0  
  invokestatic Runtime/printInt(I)V    

  ;; test if-condition (false)  
  iconst_0  
  ifeq L7  
  goto L6  

  ;; when (false) do:  
  L7:  

  ;; int x;
  

  ;; Reserve address 4 for x. No code generated.  

  ;; x = 4;
  
  iconst_4  
  istore 4  
  iload 4  
  pop  

  ;; unless (false) do:  
  L6:  

  ;; printInt (x);
  
  iload_0  
  invokestatic Runtime/printInt(I)V    

  ;; int i = 0;
  
  iconst_0  
  istore 5  

  ;; test while-condition (i ++ < 1)  
  L8:  
  iload 5  
  iload 5  
  iconst_1  
  iadd  
  istore 5  
  iconst_1  
  if_icmplt L10  
  iconst_0  
  goto L11  
  L10:  
  iconst_1  
  L11:  
  ifeq L9  

  ;; while (i ++ < 1) do:  

  ;; int x = 5;
  
  iconst_5  
  istore 6  
  goto L8  
  L9:  

  ;; printInt (x);
  
  iload_0  
  invokestatic Runtime/printInt(I)V    

  ;; int i = 0;
  
  iconst_0  
  istore 7  

  ;; test while-condition (i ++ < 1)  
  L12:  
  iload 7  
  iload 7  
  iconst_1  
  iadd  
  istore 7  
  iconst_1  
  if_icmplt L14  
  iconst_0  
  goto L15  
  L14:  
  iconst_1  
  L15:  
  ifeq L13  

  ;; while (i ++ < 1) do:  

  ;; int x;
  

  ;; Reserve address 8 for x. No code generated.  

  ;; x = 6;
  
  bipush 6  
  istore 8  
  iload 8  
  pop  
  goto L12  
  L13:  

  ;; printInt (x);
  
  iload_0  
  invokestatic Runtime/printInt(I)V    

  ;; int x = 7;
  
  bipush 7  
  istore 9  

  ;; printInt (x);
  
  iload_0  
  invokestatic Runtime/printInt(I)V    

  ;; int x;
  

  ;; Reserve address 10 for x. No code generated.  

  ;; x = 8;
  
  bipush 8  
  istore 10  
  iload 10  
  pop  

  ;; printInt (x);
  
  iload_0  
  invokestatic Runtime/printInt(I)V    

  ;; return 0;
  
  iconst_0  
  ireturn
  return

.end method
