.class public ass_in_arith
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

  invokestatic ass_in_arith/main()I
  pop
  return

.end method


.method public static main()I
  .limit locals 2
  .limit stack 7

  nop
  

  ;; int x = 50;
  
  bipush 50  
  istore_0  

  ;; int y = x ++ + x --;
  
  iload_0  
  iload_0  
  iconst_1  
  iadd  
  istore_0  
  iload_0  
  iload_0  
  iconst_1  
  isub  
  istore_0  
  iadd  
  istore_1  

  ;; printInt (y);
  
  iload_1  
  invokestatic Runtime/printInt(I)V    

  ;; printInt (x);
  
  iload_0  
  invokestatic Runtime/printInt(I)V    

  ;; printInt ((x = 10) + x ++ + x);
  
  bipush 10  
  istore_0  
  iload_0  
  iload_0  
  iload_0  
  iconst_1  
  iadd  
  istore_0  
  iadd  
  iload_0  
  iadd  
  invokestatic Runtime/printInt(I)V    

  ;; printInt (x);
  
  iload_0  
  invokestatic Runtime/printInt(I)V    

  ;; return 0;
  
  iconst_0  
  ireturn
  return

.end method
