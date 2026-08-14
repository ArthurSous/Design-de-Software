# SmartPark — Projeto Semestral

O SmartPark simula um estacionamento inteligente com vagas, veículos, sensores, entradas, saídas, reservas, cobrança e diferentes meios de pagamento.

O projeto inicial **compila e executa**, porém representa um legado propositalmente imperfeito. O aluno deverá analisar e evoluir o sistema progressivamente conforme os conteúdos apresentados em aula.

## Escopo inicial
- vagas e setores;
- veículos;
- sensores de ocupação;
- entrada e saída;
- reserva de vaga;
- cálculo de cobrança;
- pagamento;
- notificações;
- integração com sensores e gateway externo.

## Execução
Requer Java 17.

```bash
javac -d out $(find src/main/java -name "*.java")
java -cp out br.edu.smartpark.Main
```

As atividades estão em `atividades/README_SmartPark_AulaXX.md`.

> A existência de classes com nomes de padrões não significa que o padrão esteja corretamente aplicado. O aluno deve analisar problema, necessidade, comportamento e consequências.
