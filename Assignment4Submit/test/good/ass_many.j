.class public ass_many
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

  invokestatic ass_many/main()I
  pop
  return

.end method


.method public static main()I
  .limit locals 4
  .limit stack 3

  nop
  

  ;; int x, j, i;
  

  ;; Reserve address 0 for x. No code generated.  

  ;; Reserve address 1 for j. No code generated.  

  ;; Reserve address 2 for i. No code generated.  

  ;; j = i = 6;
  
  bipush 6  
  istore_2  
  iload_2  
  istore_1  
  iload_1  
  pop  

  ;; printInt (i + j);
  
  iload_2  
  iload_1  
  iadd  
  invokestatic Runtime/printInt(I)V    

  ;; return 0;
  
  iconst_0  
  ireturn
  return

.end method
