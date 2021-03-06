## Variables
###########################################################################

JAVAC = javac
JAVAC_FLAGS = -sourcepath .

JAVA = java

# Name of generated .cup file for bnfc 2.8.1
CUPFILE = CPP/_cup.cup
# Older BNFC versions may need:
# CUPFILE = CPP/CPP.cup

# Choose one of the following definitions of variable tarassg3:

# GNU tar syntax (Linux)
tarassg3=tar --transform='s,^,assg3/,'

# BSD tar syntax (Mac OS X)
# tarassg3=tar -s',^,assg3/,'

# Note:
# The --transform option is only supported by GNU tar.
# BSD tar (Mac OS X) has a similar option -s.
# Name of GNU tar executable: tar on Linux, gtar on Mac (if installed).

###########################################################################

# List of goals not corresponding to file names.
.PHONY: bnfc sdist clean distclean vclean

# As first goal is default goal, this goal needs to remain first.
all: bnfc assg3.class

%.class: %.java
	${JAVAC} ${JAVAC_FLAGS} $<

bnfc:
	bnfc -java CPP.cf
	${JAVA} ${JAVA_FLAGS} JLex.Main CPP/Yylex
	${JAVA} ${JAVA_FLAGS} java_cup.Main -nopositions -expect 100 $(CUPFILE)
	mv sym.java parser.java CPP

# Rules for shipping the solution:

sdist: assg3.tar.gz

assg3.tar.gz: CPP.cf Makefile assg3 assg3.java Interpreter.java TypeChecker.java TypeException.java
	$(tarassg3) -czhf $@ $^

# Rules for cleaning generated files:

clean:
	 -rm -f CPP/Absyn/*.class CPP/*.class
	 -rm -f .dvi CPP.aux CPP.log CPP.ps  *.class

vclean: clean
	 -rm -f CPP/Absyn/*.java
	 -rmdir CPP/Absyn/
	 -rm -f CPP.tex CPP.dvi CPP.aux CPP.log CPP.ps
	 -rm -f CPP/Yylex $(CUPFILE) CPP/Yylex.java CPP/VisitSkel.java CPP/ComposVisitor.java CPP/AbstractVisitor.java CPP/FoldVisitor.java CPP/AllVisitor.java CPP/PrettyPrinter.java CPP/Skeleton.java CPP/Test.java CPP/sym.java CPP/parser.java CPP/*.class
	 -rmdir -p CPP/

distclean: vclean
	-rm -f assg3.tar.gz

# EOF
