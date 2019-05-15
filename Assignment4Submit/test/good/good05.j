.class public good05
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

  invokestatic good05/main()I
  pop
  return

.end method


.method public static main()I
  .limit locals 4
  .limit stack 5

  nop
  

  ;; int lo, hi, mx;
  

  ;; Reserve address 0 for lo. No code generated.  

  ;; Reserve address 1 for hi. No code generated.  

  ;; Reserve address 2 for mx. No code generated.  

  ;; lo = 1;
  
  iconst_1  
  istore_0  
  iload_0  
  pop  

  ;; hi = lo;
  
  iload_0  
  istore_1  
  iload_1  
  pop  

  ;; mx = readInt ();
  
  invokestatic Runtime/readInt()I  
  istore_2  
  iload_2  
  pop  

  ;; printInt (lo);
  
  iload_0  
  invokestatic Runtime/printInt(I)V    

  ;; test while-condition (hi < mx)  
  L0:  
  iload_1  
  iload_2  
  if_icmplt L2  
  iconst_0  
  goto L3  
  L2:  
  iconst_1  
  L3:  
  ifeq L1  

  ;; while (hi < mx) do:  

  ;; printInt (hi);
  
  iload_1  
  invokestatic Runtime/printInt(I)V    

  ;; hi = lo + hi;
  
  iload_0  
  iload_1  
  iadd  
  istore_1  
  iload_1  
  pop  

  ;; lo = hi - lo;
  
  iload_1  
  iload_0  
  isub  
  istore_0  
  iload_0  
  pop  
  goto L0  
  L1:  

  ;; return 0;
  
  iconst_0  
  ireturn
  return

.end method
