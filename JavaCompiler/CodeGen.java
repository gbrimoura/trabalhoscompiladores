class CodeGen{

    String geraCodigo (ArvoreSintatica arv)
    {
        int resultado = eval(arv);
        return String.valueOf(resultado);
    }

    int eval (ArvoreSintatica arv)
    {
        if (arv instanceof Num)
            return ((Num) arv).num;

        if (arv instanceof Soma)
            return eval(((Soma) arv).arg1) + eval(((Soma) arv).arg2);

        if (arv instanceof Mult)
            return eval(((Mult) arv).arg1) * eval(((Mult) arv).arg2);

        if (arv instanceof Sub)
            return eval(((Sub) arv).arg1) - eval(((Sub) arv).arg2);

        if (arv instanceof Div) {
            int right = eval(((Div) arv).arg2);
            if (right == 0)
                throw new RuntimeException("Divisão por zero");
            return eval(((Div) arv).arg1) / right;
        }

        throw new RuntimeException("Nó inválido na árvore sintática");
    }
}