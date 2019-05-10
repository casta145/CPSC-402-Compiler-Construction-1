.class public core103
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

  invokestatic core103/main()I
  pop
  return

.end method


.method public static main()I
  .limit locals 2
  .limit stack 7

  nop
  

  ;; int j = 4;
  
  iconst_4  
  istore_0  

  ;; test while-condition (j < 6)  
  L0:  
  iload_0  
  bipush 6  
  if_icmplt L2  
  iconst_0  
  goto L3  
  L2:  
  iconst_1  
  L3:  
  ifeq L1  

  ;; while (j < 6) do:  

  ;; int i = 0;
  
  iconst_0  
  istore_1  

  ;; i ++;
  
  iload_1  
  iload_1  
  iconst_1  
  iadd  
  istore_1  
  pop  

  ;; printInt (i);
  
  iload_1  
  invokestatic Runtime/printInt(I)V    

  ;; j ++;
  
  iload_0  
  iload_0  
  iconst_1  
  iadd  
  istore_0  
  pop  
  goto L0  
  L1:  

  ;; test if-condition (j < 7)  
  iload_0  
  bipush 7  
  if_icmplt L6  
  iconst_0  
  goto L7  
  L6:  
  iconst_1  
  L7:  
  ifeq L5  

  ;; j ++;
  
  iload_0  
  iload_0  
  iconst_1  
  iadd  
  istore_0  
  pop  
  goto L4  

  ;; when (j < 7) do:  
  L5:  

  ;; j --;
  
  iload_0  
  iload_0  
  iconst_1  
  isub  
  istore_0  
  pop  

  ;; unless (j < 7) do:  
  L4:  

  ;; printInt (j);
  
  iload_0  
  invokestatic Runtime/printInt(I)V    

  ;; return j;
  
  iload_0  
  ireturn
  return

.end method
