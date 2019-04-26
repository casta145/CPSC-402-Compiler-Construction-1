import java_cup.runtime.*;
import CPP.*;
import CPP.Absyn.*;
import java.io.*;

public class lab2 {
    public static void main(String args[]) {
        if (args.length != 1) {
            System.err.println("Usage: lab2 <SourceFile>");
            System.exit(1);
        }

        Yylex l = null;
        try {
            //System.out.println("1");
            l = new Yylex(new FileReader(args[0]));
            //System.out.println("2");
            parser p = new parser(l);
            //System.out.println("3");
            CPP.Absyn.Program parse_tree = p.pProgram();
            //System.out.println("4");
            new TypeChecker().typecheck(parse_tree);
            //System.out.println("5");
            new Interpreter().interpret(parse_tree);
            //System.out.println("6");

        } catch (TypeException e) {
            System.out.println("TYPE ERROR");
            System.err.println(e.toString());
            System.exit(1);
        } catch (RuntimeException e) {
            System.out.println("RUNTIME ERROR");
            System.err.println(e.toString());
            System.exit(-1);
        } catch (IOException e) {
            System.err.println(e.toString());
            System.exit(1);
        } catch (Throwable e) {
            System.out.println("SYNTAX ERROR");
            System.out.println("At line " + String.valueOf(l.line_num())
                       + ", near \"" + l.buff() + "\" :");
            System.out.println("     " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
