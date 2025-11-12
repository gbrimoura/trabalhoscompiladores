import java.io.*;

enum TokenType { NUM, SOMA, MULT, SUB, DIV,  APar, FPar, EOF }

class LexToken {
    String lexema;
    TokenType token;
    
    LexToken(String l, TokenType t) {
        lexema = l;
        token = t;
    }
}

class AnaliseLexica {
    BufferedReader arquivo;
    
    AnaliseLexica(String a) throws Exception {
        this.arquivo = new BufferedReader(new FileReader(a));
    }
    
    LexToken getNextToken() throws Exception {
        LexToken token;
        int eof = -1;
        char currchar;
        int currchar1;
        String currnum;
        
        do {
            currchar1 = arquivo.read();
            currchar = (char) currchar1;
        } while (currchar == '\n' || currchar == ' ' || currchar == '\t' || currchar == '\r');
        
        if (currchar1 != eof && currchar1 != 10) {
            if (currchar >= '0' && currchar <= '9') {
                currnum = "" + currchar;
                
                arquivo.mark(1);
                currchar1 = arquivo.read();
                currchar = (char) currchar1;
                
                while (currchar1 != eof && currchar >= '0' && currchar <= '9') {
                    currnum = currnum + currchar;
                    arquivo.mark(1);
                    currchar1 = arquivo.read();
                    currchar = (char) currchar1;
                }
                
                if (currchar1 != eof) {
                    arquivo.reset();
                }
                
                return new LexToken(currnum, TokenType.NUM);
            } else {
                switch (currchar) {
                    case '(':
                        return new LexToken(String.valueOf(currchar), TokenType.APar);
                    case ')':
                        return new LexToken(String.valueOf(currchar), TokenType.FPar);
                    case '+':
                        return new LexToken(String.valueOf(currchar), TokenType.SOMA);
                    case '*':
                        return new LexToken(String.valueOf(currchar), TokenType.MULT);
                    case '-':
                        return new LexToken(String.valueOf(currchar), TokenType.SUB);
                    case '/':
                        return new LexToken(String.valueOf(currchar), TokenType.DIV);
                    default:
                        throw new Exception("Caractere inválido: " + ((int) currchar));
                }
            }
        }
        
        arquivo.close();
        return new LexToken("", TokenType.EOF);
    }
}
