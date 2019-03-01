all:
	happy -gca ParParser.y
	alex -g LexParser.x
	ghc --make TestParser.hs -o TestParser

clean:
	-rm -f *.log *.aux *.hi *.o *.dvi

distclean: clean
	-rm -f DocParser.* LexParser.* ParParser.* LayoutParser.* SkelParser.* PrintParser.* TestParser.* AbsParser.* TestParser ErrM.* SharedString.* ComposOp.* parser.dtd XMLParser.* Makefile
	
