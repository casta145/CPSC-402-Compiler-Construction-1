.class public good11
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

  invokestatic good11/main()I
  pop
  return

.end method


.method public static main()I
  .limit locals 3
  .limit stack 4

  nop
  

  ;; int sum = 0;
  
  iconst_0  
  istore_0  

  ;; int num = 0;
  
  iconst_0  
  istore_1  

  ;; int x;
  

  ;; Reserve address 2 for x. No code generated.  

  ;; test while-condition ((x = readInt ()) != 0)  
  L0:  
  invokestatic Runtime/readInt()I  
  istore_2  
  iload_2  
  iconst_0  
  if_icmpne L2  
  iconst_0  
  goto L3  
  L2:  
  iconst_1  
  L3:  
  ifeq L1  

  ;; while ((x = readInt ()) != 0) do:  

  ;; sum = sum + x;
  
  iload_0  
  iload_2  
  iadd  
  istore_0  
  iload_0  
  pop  

  ;; num ++;
  
  iload_1  
  iload_1  
  iconst_1  
  iadd  
  istore_1  
  pop  
  goto L0  
  L1:  

  ;; printInt (sum / num);
  
  iload_0  
  iload_1  
  idiv  
  invokestatic Runtime/printInt(I)V    

  ;; return 0;
  
  iconst_0  
  ireturn
  return

.end method
