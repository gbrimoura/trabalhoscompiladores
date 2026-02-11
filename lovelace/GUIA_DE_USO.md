# GUIA DE USO - Compilador Lovelace

## ✅ Projeto Corrigido e Funcional!

### 📁 Estrutura do Projeto

```
lovelace/
├── Lovelace.jj           # Gramática JavaCC (fonte principal)
├── Lovelace.java         # Parser gerado pelo JavaCC
├── ast/                  # Classes da AST
│   ├── Prog.java
│   ├── Main.java
│   ├── Fun.java
│   ├── Comando.java
│   ├── Exp.java
│   └── ...
├── teste_simples.lov     # Exemplo de teste
├── exemplo1.lov          # Exemplo com strings (não suportado)
└── exemplo2.lov          # Exemplo com strings (não suportado)
```

---

## 🔧 Como Compilar o Projeto

### Passo 1: Gerar o Parser com JavaCC
```powershell
cd C:\codes\trabalhoscompiladores\lovelace
javacc Lovelace.jj
```

**Saída esperada:**
```
Parser generated successfully.
```

### Passo 2: Compilar os arquivos Java
```powershell
javac -encoding UTF-8 *.java ast/*.java
```

**Saída esperada:**
```
2 warnings (normais do JavaCC - pode ignorar)
```

---

## 🚀 Como Executar o Compilador

### Sintaxe:
```powershell
java Lovelace <arquivo.lov>
```

### Exemplo:
```powershell
java Lovelace teste_simples.lov
```

### Saída:
1. **Análise Léxica**: Mostra todos os tokens identificados
2. **Análise Sintática**: Constrói a AST
3. **Geração de Código**: Cria arquivo `.c` correspondente

```
Analise lexica concluida com sucesso!
Analise sintatica concluida com sucesso!
Codigo C gerado em: C:\...\teste_simples.c
```

---

## ⚙️ Testar o Código C Gerado

### Compilar com GCC:
```powershell
gcc teste_simples.c -o teste_simples.exe
```

### Executar:
```powershell
.\teste_simples.exe
```

---

## 📝 Sintaxe da Linguagem Lovelace

### ✅ Recursos Suportados

#### 1. Tipos
- `Float` - números reais
- `Bool` - booleanos (true/false)
- `Void` - sem retorno

#### 2. Funções
```lovelace
def Float soma(Float a, Float b)
begin
    let Float resultado;
    resultado := (a + b);
    return resultado;
end
```

#### 3. Main
```lovelace
main()
begin
    let Float x;
    x := 10;
    print x;
end
```

#### 4. Declaração de Variáveis
```lovelace
let Float x;
let Bool condicao;
```

#### 5. Atribuição
```lovelace
x := 5;
y := (x + 3);
```

#### 6. Operadores

**Aritméticos:** `+`, `-`, `*`, `/`
**Relacionais:** `==`, `<`, `>`, `<=`, `>=`
**Lógicos:** `&&`, `||`

#### 7. Estruturas de Controle

**If:**
```lovelace
if (x > 0) begin
    print x;
end;
```

**While:**
```lovelace
while (x < 10) begin
    x := (x + 1);
end;
```

#### 8. Entrada/Saída

**Leitura:**
```lovelace
x := read();
```

**Impressão:**
```lovelace
print x;
print (x + y);
```

#### 9. Chamadas de Função
```lovelace
resultado := soma(5, 3);
mostraValor(resultado);
```

---

## ❌ Limitações Conhecidas

### NÃO Suportado:
- ❌ Strings literais (`print "texto"`)
- ❌ Cláusula `else` (apenas `if` simples)
- ❌ Arrays
- ❌ Estruturas de dados complexas

**OBS:** Os arquivos `exemplo1.lov` e `exemplo2.lov` **NÃO FUNCIONAM** porque usam strings!

---

## 📊 Fluxo Completo de Teste

### 1. Criar arquivo Lovelace (.lov)
```lovelace
def Float quadrado(Float n)
begin
    let Float r;
    r := (n * n);
    return r;
end

main()
begin
    let Float x;
    let Float resultado;
    x := 4;
    resultado := quadrado(x);
    print resultado;
end
```

### 2. Compilar Lovelace → C
```powershell
java Lovelace meu_programa.lov
```

### 3. Compilar C → Executável
```powershell
gcc meu_programa.c -o meu_programa.exe
```

### 4. Executar
```powershell
.\meu_programa.exe
```

**Saída esperada:**
```
16.000000
```

---

## 🐛 Solução de Problemas

### Erro: "Parser generated successfully" mas não gera arquivos
**Solução:** Verifique se JavaCC está instalado corretamente

### Erro: "class file not found"
**Solução:** Compile novamente com `javac -encoding UTF-8 *.java ast/*.java`

### Erro no parsing: "Encountered unexpected token"
**Solução:** 
- Verifique se todas as instruções terminam com `;`
- Expressões complexas devem estar entre parênteses: `(a + b)`
- Não use strings literais

### Código C não compila
**Solução:** 
- Instale GCC (MinGW no Windows)
- Verifique se o arquivo `.c` foi gerado corretamente

---

## 📂 Arquivos Gerados

Após executar `java Lovelace programa.lov`:

- `programa.c` - Código C gerado
- `programa.exe` - Executável (após compilar com gcc)

---

## 🎯 Exemplo Completo Testado

**Arquivo: teste_simples.lov**
```lovelace
def Float soma(Float a, Float b)
begin
    let Float r;
    r := (a + b);
    return r;
end

main()
begin
    let Float x;
    let Float y;
    let Float resultado;
    
    x := 5;
    y := 3;
    resultado := soma(x, y);
    print resultado;
end
```

**Comandos:**
```powershell
# 1. Compilar gramática (apenas na primeira vez ou se modificar .jj)
javacc Lovelace.jj
javac -encoding UTF-8 *.java ast/*.java

# 2. Compilar programa Lovelace
java Lovelace teste_simples.lov

# 3. Compilar C gerado
gcc teste_simples.c -o teste_simples.exe

# 4. Executar
.\teste_simples.exe
```

**Saída:** `8.000000` ✅

---

## 📚 Recursos Implementados

✅ Análise léxica completa  
✅ Análise sintática com JavaCC  
✅ Construção de AST  
✅ Geração de código C funcional  
✅ Suporte a funções com parâmetros  
✅ Operadores aritméticos, relacionais e lógicos  
✅ Estruturas de controle (if, while)  
✅ Entrada/saída (read, print)  

---

## 💡 Dicas

1. **Sempre use parênteses em expressões:** `(a + b)` ao invés de `a + b`
2. **Não esqueça o ponto-e-vírgula:** Todos os comandos terminam com `;`
3. **Funções antes do main:** Declare todas as funções antes da função `main()`
4. **Teste incremental:** Comece com programas simples e vá adicionando complexidade

---

**Status: ✅ Totalmente Funcional**  
**Testado em:** Windows 11, JavaCC 4.1d1, Java 8+, GCC MinGW
