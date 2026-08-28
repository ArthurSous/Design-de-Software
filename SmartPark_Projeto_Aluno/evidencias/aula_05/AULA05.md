# Evidência — Aula 05

## Aula
Aula 05 — Planejamento técnico e backlog

## Problema observado
O SmartPark compila, mas contém problemas técnicos relevantes no fluxo de sensor, cobrança, pagamento, reserva, estado das vagas e publicação de eventos. Além disso, os requisitos e a arquitetura iniciais não possuem critérios de aceitação e decisões suficientemente rastreáveis.

## Alteração realizada
Foi criado um backlog técnico a partir da inspeção do código e da documentação existente.

Os achados foram classificados por risco:
- **P0:** falhas que podem causar entrada indevida, saída sem pagamento ou cobrança incorreta;
- **P1:** inconsistências de reserva, estado e eventos;
- **P2:** melhorias de extensibilidade e documentação.

Cada item recebeu critérios de aceitação verificáveis e uma evidência esperada.

## Critério de aceitação da Aula 05
A entrega é considerada concluída quando os problemas relevantes do legado estiverem convertidos em tarefas priorizadas, cada tarefa possuir critérios de aceitação objetivos e o trabalho estiver organizado para execução/revisão no GitHub Projects/Kanban.

## Evidências
- Issue: `Aula 05`
- Branch sugerida: `aula-05`
- Backlog: `docs/diagramsas/diagramas_aula_05/Auaula05_backlog.md`
- Issue preparada: `docs/diagramsas/diagramas_aula_05/issue_aula05.md`
- Evidência: `evidencias/aula_05/AULA05.md`
- Compilação/teste: `evidencias/aula_05/compilacao_aula05.txt`
- PR: criar no GitHub após subir a branch.

## Commits sugeridos
1. `docs: adiciona backlog tecnico da aula 05`
2. `docs: define criterios de aceitacao da aula 05`
3. `docs: registra evidencia da aula 05`

## Revisão equivalente
Antes de marcar a Issue como concluída, conferir se cada item possui critério de aceitação e evidência. Para itens que posteriormente envolverem código, exigir compilação/teste e revisão por PR.
