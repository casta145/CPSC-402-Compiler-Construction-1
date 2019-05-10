.class public core017
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

  invokestatic core017/main()I
  pop
  return

.end method


.method public static main()I
  .limit locals 1
  .limit stack 26

  nop
  

  ;; int x = 4;
  
  iconst_4  
  istore_0  

  ;; test if-condition (3 <= x && 4 != 2 && true)  
  iconst_3  
  iload_0  
  if_icmple L6  
  iconst_0  
  goto L7  
  L6:  
  iconst_1  
  L7:  
  ifeq L4  
  iconst_1  
  iconst_4  
  iconst_2  
  if_icmpne L8  
  iconst_0  
  goto L9  
  L8:  
  iconst_1  
  L9:  
  if_icmpne L4  
  iconst_1  
  goto L5  
  L4:  
  iconst_0  
  L5:  
  ifeq L2  
  iconst_1  
  iconst_1  
  if_icmpne L2  
  iconst_1  
  goto L3  
  L2:  
  iconst_0  
  L3:  
  ifeq L1  

  ;; printBool (true);
  
  iconst_1  
  invokestatic core017/printBool(Z)V    
  goto L0  

  ;; when (3 <= x && 4 != 2 && true) do:  
  L1:  

  ;; unless (3 <= x && 4 != 2 && true) do:  
  L0:  

  ;; printBool (eq_bool (true, true) || dontCallMe (1));
  
  iconst_1  
  iconst_1  
  invokestatic core017/eq_bool(ZZ)Z  
  ifne L10  
  iconst_0  
  iconst_1  
  invokestatic core017/dontCallMe(I)Z  
  if_icmpne L10  
  iconst_0  
  goto L11  
  L10:  
  iconst_1  
  L11:  
  invokestatic core017/printBool(Z)V    

  ;; printBool (4 > 50 && dontCallMe (2));
  
  iconst_4  
  bipush 50  
  if_icmpgt L14  
  iconst_0  
  goto L15  
  L14:  
  iconst_1  
  L15:  
  ifeq L12  
  iconst_1  
  iconst_2  
  invokestatic core017/dontCallMe(I)Z  
  if_icmpne L12  
  iconst_1  
  goto L13  
  L12:  
  iconst_0  
  L13:  
  invokestatic core017/printBool(Z)V    

  ;; printBool (4 == x && eq_bool (true, false) && true);
  
  iconst_4  
  iload_0  
  if_icmpeq L20  
  iconst_0  
  goto L21  
  L20:  
  iconst_1  
  L21:  
  ifeq L18  
  iconst_1  
  iconst_1  
  iconst_0  
  invokestatic core017/eq_bool(ZZ)Z  
  if_icmpne L18  
  iconst_1  
  goto L19  
  L18:  
  iconst_0  
  L19:  
  ifeq L16  
  iconst_1  
  iconst_1  
  if_icmpne L16  
  iconst_1  
  goto L17  
  L16:  
  iconst_0  
  L17:  
  invokestatic core017/printBool(Z)V    

  ;; printBool (implies (false, false));
  
  iconst_0  
  iconst_0  
  invokestatic core017/implies(ZZ)Z  
  invokestatic core017/printBool(Z)V    

  ;; printBool (implies (false, true));
  
  iconst_0  
  iconst_1  
  invokestatic core017/implies(ZZ)Z  
  invokestatic core017/printBool(Z)V    

  ;; printBool (implies (true, false));
  
  iconst_1  
  iconst_0  
  invokestatic core017/implies(ZZ)Z  
  invokestatic core017/printBool(Z)V    

  ;; printBool (implies (true, true));
  
  iconst_1  
  iconst_1  
  invokestatic core017/implies(ZZ)Z  
  invokestatic core017/printBool(Z)V    

  ;; return 0;
  
  iconst_0  
  ireturn
  return

.end method

.method public static dontCallMe(I)Z
  .limit locals 1
  .limit stack 3

  nop
  

  ;; printInt (x);
  
  iload_0  
  invokestatic Runtime/printInt(I)V    

  ;; return true;
  
  iconst_1  
  ireturn
  return

.end method

.method public static printBool(Z)V
  .limit locals 1
  .limit stack 3

  nop
  

  ;; test if-condition (b)  
  iload_0  
  ifeq L23  

  ;; printInt (1);
  
  iconst_1  
  invokestatic Runtime/printInt(I)V    
  goto L22  

  ;; when (b) do:  
  L23:  

  ;; printInt (0);
  
  iconst_0  
  invokestatic Runtime/printInt(I)V    

  ;; unless (b) do:  
  L22:
  return

.end method

.method public static implies(ZZ)Z
  .limit locals 2
  .limit stack 4

  nop
  

  ;; return not (x) || eq_bool (x, y);
  
  iload_0  
  invokestatic core017/not(Z)Z  
  ifne L24  
  iconst_0  
  iload_0  
  iload_1  
  invokestatic core017/eq_bool(ZZ)Z  
  if_icmpne L24  
  iconst_0  
  goto L25  
  L24:  
  iconst_1  
  L25:  
  ireturn
  return

.end method

.method public static not(Z)Z
  .limit locals 2
  .limit stack 2

  nop
  

  ;; bool r;
  

  ;; Reserve address 1 for r. No code generated.  

  ;; test if-condition (x)  
  iload_0  
  ifeq L27  

  ;; r = false;
  
  iconst_0  
  istore_1  
  iload_1  
  pop  
  goto L26  

  ;; when (x) do:  
  L27:  

  ;; r = true;
  
  iconst_1  
  istore_1  
  iload_1  
  pop  

  ;; unless (x) do:  
  L26:  

  ;; return r;
  
  iload_1  
  ireturn
  return

.end method

.method public static eq_bool(ZZ)Z
  .limit locals 3
  .limit stack 2

  nop
  

  ;; bool r;
  

  ;; Reserve address 2 for r. No code generated.  

  ;; test if-condition (x)  
  iload_0  
  ifeq L29  

  ;; r = y;
  
  iload_1  
  istore_2  
  iload_2  
  pop  
  goto L28  

  ;; when (x) do:  
  L29:  

  ;; r = not (y);
  
  iload_1  
  invokestatic core017/not(Z)Z  
  istore_2  
  iload_2  
  pop  

  ;; unless (x) do:  
  L28:  

  ;; return r;
  
  iload_2  
  ireturn
  return

.end method
