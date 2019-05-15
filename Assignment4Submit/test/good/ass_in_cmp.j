.class public ass_in_cmp
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

  invokestatic ass_in_cmp/main()I
  pop
  return

.end method


.method public static main()I
  .limit locals 1
  .limit stack 5

  nop
  

  ;; int x = 5;
  
  iconst_5  
  istore_0  

  ;; test if-condition (++ x == x ++)  
  iload_0  
  iconst_1  
  iadd  
  istore_0  
  iload_0  
  iload_0  
  iload_0  
  iconst_1  
  iadd  
  istore_0  
  if_icmpeq L2  
  iconst_0  
  goto L3  
  L2:  
  iconst_1  
  L3:  
  ifeq L1  

  ;; printInt (x);
  
  iload_0  
  invokestatic Runtime/printInt(I)V    
  goto L0  

  ;; when (++ x == x ++) do:  
  L1:  

  ;; printInt (0);
  
  iconst_0  
  invokestatic Runtime/printInt(I)V    

  ;; unless (++ x == x ++) do:  
  L0:  

  ;; return 0;
  
  iconst_0  
  ireturn
  return

.end method
