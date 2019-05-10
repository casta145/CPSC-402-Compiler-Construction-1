.class public cmp_bool
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

  invokestatic cmp_bool/main()I
  pop
  return

.end method


.method public static printBool(Z)V
  .limit locals 1
  .limit stack 3

  nop
  

  ;; test if-condition (b)  
  iload_0  
  ifeq L1  

  ;; printInt (1);
  
  iconst_1  
  invokestatic Runtime/printInt(I)V    
  goto L0  

  ;; when (b) do:  
  L1:  

  ;; printInt (0);
  
  iconst_0  
  invokestatic Runtime/printInt(I)V    

  ;; unless (b) do:  
  L0:
  return

.end method

.method public static main()I
  .limit locals 1
  .limit stack 8

  nop
  

  ;; printBool (true == true);
  
  iconst_1  
  iconst_1  
  if_icmpeq L2  
  iconst_0  
  goto L3  
  L2:  
  iconst_1  
  L3:  
  invokestatic cmp_bool/printBool(Z)V    

  ;; printBool (true == false);
  
  iconst_1  
  iconst_0  
  if_icmpeq L4  
  iconst_0  
  goto L5  
  L4:  
  iconst_1  
  L5:  
  invokestatic cmp_bool/printBool(Z)V    

  ;; printBool (true != false);
  
  iconst_1  
  iconst_0  
  if_icmpne L6  
  iconst_0  
  goto L7  
  L6:  
  iconst_1  
  L7:  
  invokestatic cmp_bool/printBool(Z)V    

  ;; return 0;
  
  iconst_0  
  ireturn
  return

.end method
