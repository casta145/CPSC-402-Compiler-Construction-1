.class public boolean
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

  invokestatic boolean/main()I
  pop
  return

.end method


.method public static ff()Z
  .limit locals 1
  .limit stack 3

  nop
  

  ;; printInt (0);
  
  iconst_0  
  invokestatic Runtime/printInt(I)V    

  ;; return false;
  
  iconst_0  
  ireturn
  return

.end method

.method public static tt()Z
  .limit locals 1
  .limit stack 3

  nop
  

  ;; printInt (1);
  
  iconst_1  
  invokestatic Runtime/printInt(I)V    

  ;; return true;
  
  iconst_1  
  ireturn
  return

.end method

.method public static main()I
  .limit locals 6
  .limit stack 2

  nop
  

  ;; bool t = true;
  
  iconst_1  
  istore_0  

  ;; bool f = false;
  
  iconst_0  
  istore_1  

  ;; bool silent1 = f && ff ();
  
  iload_1  
  ifeq L0  
  iconst_1  
  invokestatic boolean/ff()Z  
  if_icmpne L0  
  iconst_1  
  goto L1  
  L0:  
  iconst_0  
  L1:  
  istore_2  

  ;; bool silent2 = t || ff ();
  
  iload_0  
  ifne L2  
  iconst_0  
  invokestatic boolean/ff()Z  
  if_icmpne L2  
  iconst_0  
  goto L3  
  L2:  
  iconst_1  
  L3:  
  istore_3  

  ;; bool noisy1 = t && tt ();
  
  iload_0  
  ifeq L4  
  iconst_1  
  invokestatic boolean/tt()Z  
  if_icmpne L4  
  iconst_1  
  goto L5  
  L4:  
  iconst_0  
  L5:  
  istore 4  

  ;; bool noisy2 = f || tt ();
  
  iload_1  
  ifne L6  
  iconst_0  
  invokestatic boolean/tt()Z  
  if_icmpne L6  
  iconst_0  
  goto L7  
  L6:  
  iconst_1  
  L7:  
  istore 5  

  ;; return 0;
  
  iconst_0  
  ireturn
  return

.end method
