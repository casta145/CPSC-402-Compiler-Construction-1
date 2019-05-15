.class public good15
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

  invokestatic good15/main()I
  pop
  return

.end method


.method public static main()I
  .limit locals 2
  .limit stack 20

  nop
  

  ;; int i = 1;
  
  iconst_1  
  istore_0  

  ;; printInt (i);
  
  iload_0  
  invokestatic Runtime/printInt(I)V    

  ;; true || i ++ != 45;
  
  iconst_1  
  ifne L0  
  iconst_0  
  iload_0  
  iload_0  
  iconst_1  
  iadd  
  istore_0  
  bipush 45  
  if_icmpne L2  
  iconst_0  
  goto L3  
  L2:  
  iconst_1  
  L3:  
  if_icmpne L0  
  iconst_0  
  goto L1  
  L0:  
  iconst_1  
  L1:  
  pop  

  ;; printInt (i);
  
  iload_0  
  invokestatic Runtime/printInt(I)V    

  ;; false || i ++ >= 0;
  
  iconst_0  
  ifne L4  
  iconst_0  
  iload_0  
  iload_0  
  iconst_1  
  iadd  
  istore_0  
  iconst_0  
  if_icmpge L6  
  iconst_0  
  goto L7  
  L6:  
  iconst_1  
  L7:  
  if_icmpne L4  
  iconst_0  
  goto L5  
  L4:  
  iconst_1  
  L5:  
  pop  

  ;; printInt (i);
  
  iload_0  
  invokestatic Runtime/printInt(I)V    

  ;; true && i ++ < 0;
  
  iconst_1  
  ifeq L8  
  iconst_1  
  iload_0  
  iload_0  
  iconst_1  
  iadd  
  istore_0  
  iconst_0  
  if_icmplt L10  
  iconst_0  
  goto L11  
  L10:  
  iconst_1  
  L11:  
  if_icmpne L8  
  iconst_1  
  goto L9  
  L8:  
  iconst_0  
  L9:  
  pop  

  ;; printInt (i);
  
  iload_0  
  invokestatic Runtime/printInt(I)V    

  ;; false && i ++ > 0;
  
  iconst_0  
  ifeq L12  
  iconst_1  
  iload_0  
  iload_0  
  iconst_1  
  iadd  
  istore_0  
  iconst_0  
  if_icmpgt L14  
  iconst_0  
  goto L15  
  L14:  
  iconst_1  
  L15:  
  if_icmpne L12  
  iconst_1  
  goto L13  
  L12:  
  iconst_0  
  L13:  
  pop  

  ;; printInt (i);
  
  iload_0  
  invokestatic Runtime/printInt(I)V    

  ;; int j = 0;
  
  iconst_0  
  istore_1  

  ;; test if-condition (34 < 6 && j < 0)  
  bipush 34  
  bipush 6  
  if_icmplt L20  
  iconst_0  
  goto L21  
  L20:  
  iconst_1  
  L21:  
  ifeq L18  
  iconst_1  
  iload_1  
  iconst_0  
  if_icmplt L22  
  iconst_0  
  goto L23  
  L22:  
  iconst_1  
  L23:  
  if_icmpne L18  
  iconst_1  
  goto L19  
  L18:  
  iconst_0  
  L19:  
  ifeq L17  

  ;; printInt (i);
  
  iload_0  
  invokestatic Runtime/printInt(I)V    
  goto L16  

  ;; when (34 < 6 && j < 0) do:  
  L17:  

  ;; printInt (42);
  
  bipush 42  
  invokestatic Runtime/printInt(I)V    

  ;; unless (34 < 6 && j < 0) do:  
  L16:  

  ;; return 0;
  
  iconst_0  
  ireturn
  return

.end method
