.class public ineq_inclusive
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

  invokestatic ineq_inclusive/main()I
  pop
  return

.end method


.method public static main()I
  .limit locals 1
  .limit stack 14

  nop
  

  ;; test if-condition (0 >= 0)  
  iconst_0  
  iconst_0  
  if_icmpge L2  
  iconst_0  
  goto L3  
  L2:  
  iconst_1  
  L3:  
  ifeq L1  

  ;; printInt (0);
  
  iconst_0  
  invokestatic Runtime/printInt(I)V    
  goto L0  

  ;; when (0 >= 0) do:  
  L1:  

  ;; printInt (1);
  
  iconst_1  
  invokestatic Runtime/printInt(I)V    

  ;; unless (0 >= 0) do:  
  L0:  

  ;; test if-condition (1 >= 1)  
  iconst_1  
  iconst_1  
  if_icmpge L6  
  iconst_0  
  goto L7  
  L6:  
  iconst_1  
  L7:  
  ifeq L5  

  ;; printInt (2);
  
  iconst_2  
  invokestatic Runtime/printInt(I)V    
  goto L4  

  ;; when (1 >= 1) do:  
  L5:  

  ;; printInt (3);
  
  iconst_3  
  invokestatic Runtime/printInt(I)V    

  ;; unless (1 >= 1) do:  
  L4:  

  ;; test if-condition (2 <= 2)  
  iconst_2  
  iconst_2  
  if_icmple L10  
  iconst_0  
  goto L11  
  L10:  
  iconst_1  
  L11:  
  ifeq L9  

  ;; printInt (4);
  
  iconst_4  
  invokestatic Runtime/printInt(I)V    
  goto L8  

  ;; when (2 <= 2) do:  
  L9:  

  ;; printInt (5);
  
  iconst_5  
  invokestatic Runtime/printInt(I)V    

  ;; unless (2 <= 2) do:  
  L8:  

  ;; test if-condition (0 <= 0)  
  iconst_0  
  iconst_0  
  if_icmple L14  
  iconst_0  
  goto L15  
  L14:  
  iconst_1  
  L15:  
  ifeq L13  

  ;; printInt (6);
  
  bipush 6  
  invokestatic Runtime/printInt(I)V    
  goto L12  

  ;; when (0 <= 0) do:  
  L13:  

  ;; printInt (7);
  
  bipush 7  
  invokestatic Runtime/printInt(I)V    

  ;; unless (0 <= 0) do:  
  L12:  

  ;; return 0;
  
  iconst_0  
  ireturn
  return

.end method
