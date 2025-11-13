import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.*;

/*
   PUSH <n>    -> empilha inteiro n
   ADD         -> pop a, pop b, push (b + a)
   SUB         -> pop a, pop b, push (b - a)
   MULT          -> pop a, pop b, push (b * a)
   DIV         -> pop a, pop b, push (b / a)  (divisão inteira)
   PRINT       -> imprime topo sem removê-lo
*/

public class MaquinaPilha {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Uso: java MaquinaPilha arquivoDeEntrada");
            System.exit(1);
        }
        Path path = Paths.get(args[0]);
        Deque<Integer> stack = new ArrayDeque<>();
        List<String> lines;
        try {
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
            System.exit(2);
            return;
        }

        int lineNo = 0;
        try {
            for (String raw : lines) {
                lineNo++;
                String line = raw.trim();
                if (line.isEmpty() || line.startsWith("//")) continue;
                String[] parts = line.split("\\s+");
                String instr = parts[0].toUpperCase(Locale.ROOT);

                switch (instr) {
                    case "PUSH":
                        if (parts.length < 2) throw new RuntimeException("PUSH sem operando (linha " + lineNo + ")");
                        stack.push(Integer.parseInt(parts[1]));
                        break;
                    case "ADD":
                        need(stack, 2, lineNo);
                        int a1 = stack.pop();
                        int b1 = stack.pop();
                        stack.push(b1 + a1);
                        break;
                    case "SUB":
                        need(stack, 2, lineNo);
                        int a2 = stack.pop();
                        int b2 = stack.pop();
                        stack.push(b2 - a2);
                        break;
                    case "MULT":
                        need(stack, 2, lineNo);
                        int a3 = stack.pop();
                        int b3 = stack.pop();
                        stack.push(b3 * a3);
                        break;
                    case "DIV":
                        need(stack, 2, lineNo);
                        int denom = stack.pop();
                        int numer = stack.pop();
                        if (denom == 0) throw new ArithmeticException("Divisão por zero (linha " + lineNo + ")");
                        stack.push(numer / denom);
                        break;
                    case "PRINT":
                        if (stack.isEmpty()) System.out.println("null");
                        else System.out.println(stack.peek());
                        break;
                    default:
                        throw new RuntimeException("Instrução desconhecida '" + instr + "' (linha " + lineNo + ")");
                }
            }

        } catch (RuntimeException e) {
            System.err.println("Erro na execução: " + e.getMessage());
            System.exit(3);
        }
    }

    private static void need(Deque<Integer> s, int n, int line) {
        if (s.size() < n) throw new RuntimeException("Stack underflow (linha " + line + ")");
    }
}