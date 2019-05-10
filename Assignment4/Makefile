# Makefile for PLT lab 3 in JAVA

# Variables for file lists
###########################################################################

# Edit to add new java source files, if needed!
LABSRC    = assg4.java TypeException.java TypeChecker.java Compiler.java \
  PatchExp.java FunType.java Code.java Runtime.java

# No need to edit these:
PARSERSRC = $(wildcard CPP/Absyn/*.java CPP/*.java)
PARSEROBJ = $(PARSERSRC:.java=.class)
LABOBJ    = $(LABSRC:.java=.class)
# Inner classes:
# LABINNOBJ = $(wildcard $(LABSRC:.java=$$*.class))

# Variables for the programming environment (edit as needed)
###########################################################################

# Name of generated .cup file for bnfc 2.8.1
# CUPFILE = CPP/_cup.cup
# Older BNFC versions may need:
CUPFILE = CPP/CPP.cup

JAVAC       = javac
JAVAC_FLAGS = -sourcepath .
JAVA        = java

# No need to edit these:
javac       = ${JAVAC} ${JAVAC_FLAGS}
java        = ${JAVA}  ${JAVA_FLAGS}

# Default rule
###########################################################################

# List of goals not corresponding to file names.
.PHONY : default all test clean distclean vclean

# As the first goal is default goal, this goal needs to remain first.
default : assg4

# Build, test and ship
all : assg4 test sdist

# Rules for running tests
###########################################################################

# Name stem of C-- file to test compiler on.
test = fib

test : test/$(test).class Runtime.class
	java -cp test:. $(test)

%.class : %.cc assg4
	./assg4 $<

# Compiling the assg4 runner
###########################################################################

assg4 : assg4.hs assg4.class
	ghc --make -cpp $< -o $@
	touch $@

# Rules for compiling assg4 classes (modify or add as needed)
###########################################################################

TypeChecker.class : TypeChecker.java TypeException.class CPP/Test.class
	$(javac) $<

FunType.class Code.class : %.class : %.java CPP/Test.class
	$(javac) $<

Compiler.class : Compiler.java FunType.class Code.class CPP/Test.class
	$(javac) $<

assg4.class : assg4.java TypeChecker.class Compiler.class CPP/Test.class
	$(javac) $<


# Rules for creating the parser
###########################################################################

# Create parser source via bnfc (dependency CPP.cf needs to be first).
# Patch CPP/Absyn/Exp.java
CPP/Yylex $(CUPFILE) CPP/Test.java : CPP.cf
	bnfc --java $<
	cp -f PatchExp.java CPP/Absyn/Exp.java

# Create parser and move it to the correct location.
CPP/parser.java CPP/sym.java : $(CUPFILE)
	$(java) java_cup.Main -package CPP $<
	mv parser.java sym.java CPP/

# Create lexer.
CPP/Yylex.java : CPP/Yylex
	$(java) JLex.Main $<

# Compile lexer.
CPP/Yylex.class : CPP/Yylex.java CPP/sym.class
	$(javac) $<

# Create parser test.
CPP/Test.class : CPP/Test.java CPP/parser.class CPP/sym.class CPP/Yylex.class
	$(javac) $<


# Default rules
###########################################################################

%.class : %.java
	$(javac) $<


# Rules for shipping the solution
###########################################################################

sdist : assg4.tar.gz

tmpdir := $(shell mktemp -d)
assg4.tar.gz : CPP.cf Makefile assg4.hs $(LABSRC)
	mkdir $(tmpdir)/assg4
	cp $^ $(tmpdir)/assg4/
	tar -C $(tmpdir) -czhf $@ assg4

# Rules for cleaning generated files
###########################################################################

clean :
	-rm -f CPP/Absyn/*.class CPP/*.class
	-rm -f CPP.dvi CPP.aux CPP.log CPP.ps
	-rm -f $(LABOBJ)
	-rm -f assg4.hi assg4.o assg4.exe assg4
# Uncomment to also remove all .class files in current directory
#	-rm -f *.class


vclean : clean
	-rm -f $(PARSERSRC)
	-rm -f CPP/Absyn/*.bak CPP/*.bak
	-rmdir CPP/Absyn/
	-rm -f CPP.tex
	-rm -f CPP/Yylex $(CUPFILE)
	-rmdir -p CPP/

distclean : vclean
	-rm -f assg4.tar.gz

# Debugging the Makefile
###########################################################################

debug :
	echo $(LABINNOBJ)

# EOF
