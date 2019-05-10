.class public core013
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

  invokestatic core013/main()I
  pop
  return

.end method


.method public static main()I
  .limit locals 1
  .limit stack 14

  nop
  

  ;; printBool (test (0 - 1) && test (0));
  
  iconst_0  
  iconst_1  
  isub  
  invokestatic core013/test(I)Z  
  ifeq L0  
  iconst_1  
  iconst_0  
  invokestatic core013/test(I)Z  
  if_icmpne L0  
  iconst_1  
  goto L1  
  L0:  
  iconst_0  
  L1:  
  invokestatic core013/printBool(Z)V    

  ;; printBool (test (0 - 2) || test (1));
  
  iconst_0  
  iconst_2  
  isub  
  invokestatic core013/test(I)Z  
  ifne L2  
  iconst_0  
  iconst_1  
  invokestatic core013/test(I)Z  
  if_icmpne L2  
  iconst_0  
  goto L3  
  L2:  
  iconst_1  
  L3:  
  invokestatic core013/printBool(Z)V    

  ;; printBool (test (3) && test (0 - 5) && true);
  
  iconst_3  
  invokestatic core013/test(I)Z  
  ifeq L6  
  iconst_1  
  iconst_0  
  iconst_5  
  isub  
  invokestatic core013/test(I)Z  
  if_icmpne L6  
  iconst_1  
  goto L7  
  L6:  
  iconst_0  
  L7:  
  ifeq L4  
  iconst_1  
  iconst_1  
  if_icmpne L4  
  iconst_1  
  goto L5  
  L4:  
  iconst_0  
  L5:  
  invokestatic core013/printBool(Z)V    

  ;; printBool (test (3) || test (0 - 5) && true);
  
  iconst_3  
  invokestatic core013/test(I)Z  
  ifne L8  
  iconst_0  
  iconst_0  
  iconst_5  
  isub  
  invokestatic core013/test(I)Z  
  ifeq L10  
  iconst_1  
  iconst_1  
  if_icmpne L10  
  iconst_1  
  goto L11  
  L10:  
  iconst_0  
  L11:  
  if_icmpne L8  
  iconst_0  
  goto L9  
  L8:  
  iconst_1  
  L9:  
  invokestatic core013/printBool(Z)V    

  ;; printBool (true);
  
  iconst_1  
  invokestatic core013/printBool(Z)V    

  ;; printBool (false);
  
  iconst_0  
  invokestatic core013/printBool(Z)V    

  ;; return 0;
  
  iconst_0  
  ireturn
  return

.end method

.method public static printBool(Z)V
  .limit locals 1
  .limit stack 3

  nop
  

  ;; test if-condition (b)  
  iload_0  
  ifeq L13  

  ;; printInt (1);
  
  iconst_1  
  invokestatic Runtime/printInt(I)V    
  goto L12  

  ;; when (b) do:  
  L13:  

  ;; printInt (0);
  
  iconst_0  
  invokestatic Runtime/printInt(I)V    

  ;; unless (b) do:  
  L12:
  return

.end method

.method public static test(I)Z
  .limit locals 1
  .limit stack 3

  nop
  

  ;; return i > 0;
  
  iload_0  
  iconst_0  
  if_icmpgt L14  
  iconst_0  
  goto L15  
  L14:  
  iconst_1  
  L15:  
  ireturn
  return

.end method
