.class public core019
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

  invokestatic core019/main()I
  pop
  return

.end method


.method public static main()I
  .limit locals 4
  .limit stack 11

  nop
  

  ;; int i = 78;
  
  bipush 78  
  istore_0  

  ;; int i = 1;
  
  iconst_1  
  istore_1  

  ;; printInt (i);
  
  iload_1  
  invokestatic Runtime/printInt(I)V    

  ;; printInt (i);
  
  iload_0  
  invokestatic Runtime/printInt(I)V    

  ;; test while-condition (i > 76)  
  L0:  
  iload_0  
  bipush 76  
  if_icmpgt L2  
  iconst_0  
  goto L3  
  L2:  
  iconst_1  
  L3:  
  ifeq L1  

  ;; while (i > 76) do:  

  ;; i --;
  
  iload_0  
  iload_0  
  iconst_1  
  isub  
  istore_0  
  pop  

  ;; printInt (i);
  
  iload_0  
  invokestatic Runtime/printInt(I)V    

  ;; int i = 7;
  
  bipush 7  
  istore_2  

  ;; printInt (i);
  
  iload_2  
  invokestatic Runtime/printInt(I)V    
  goto L0  
  L1:  

  ;; printInt (i);
  
  iload_0  
  invokestatic Runtime/printInt(I)V    

  ;; test if-condition (i > 4)  
  iload_0  
  iconst_4  
  if_icmpgt L6  
  iconst_0  
  goto L7  
  L6:  
  iconst_1  
  L7:  
  ifeq L5  

  ;; int i = 4;
  
  iconst_4  
  istore_3  

  ;; printInt (i);
  
  iload_3  
  invokestatic Runtime/printInt(I)V    
  goto L4  

  ;; when (i > 4) do:  
  L5:  

  ;; unless (i > 4) do:  
  L4:  

  ;; printInt (i);
  
  iload_0  
  invokestatic Runtime/printInt(I)V    

  ;; return 0;
  
  iconst_0  
  ireturn
  return

.end method
