# SmartPark — Aula 05: Backlog técnico

## Objetivo
Analisar o legado antes de alterar o sistema e transformar os problemas encontrados em tarefas técnicas verificáveis, priorizadas por risco.

## Problemas observados no legado

| ID | Problema | Risco | Prioridade |
|---|---|---|---|
| BP-01 | A entrada abre a cancela mesmo quando o sensor pode indicar estado conflitante. | Entrada indevida/estado inconsistente da vaga. | P0 |
| BP-02 | A saída abre a cancela mesmo quando o pagamento falha. | Saída sem cobrança confirmada. | P0 |
| BP-03 | A duração da permanência é fixa em 95 minutos; entrada e saída não são calculadas. | Cobrança incorreta. | P0 |
| BP-04 | Reserva ignora ocupação, reserva existente e estado do sensor. | Dupla reserva ou reserva de vaga ocupada. | P1 |
| BP-05 | Ao liberar a vaga, reserva e placa não são limpas. | Estado incorreto da vaga após a saída. | P1 |
| BP-06 | `ParkingPublisher` mantém somente um observer, apesar de registrar dois. | Eventos deixam de chegar a um dos consumidores. | P1 |
| BP-07 | `PricingService` possui estratégia opcional, mas CAR e MOTORCYCLE usam regras fixas. | Dificuldade para trocar regras de preço. | P2 |
| BP-08 | Requisitos iniciais não possuem critérios de aceitação verificáveis. | Dificuldade para validar entregas. | P1 |
| BP-09 | Arquitetura inicial não registra fronteiras, contratos, requisitos arquiteturalmente significativos e trade-offs. | Decisões técnicas ficam sem rastreabilidade. | P2 |

## Backlog priorizado para GitHub Projects/Kanban

### P0 — Risco crítico

#### BP-01 — Validar sensor antes da entrada
**Descrição:** impedir a abertura da cancela e a ocupação da vaga quando o estado informado pelo sensor for incompatível com a entrada.

**Critérios de aceitação**
- Dado um sensor indicando `OCCUPIED`, ao tentar entrar, a sessão não é criada e a cancela não abre.
- Dado um sensor indicando `FREE`, a entrada pode prosseguir.
- O teste deve registrar o estado do sensor e o resultado da operação.

**Evidência:** teste automatizado + saída do teste/compilação.

#### BP-02 — Bloquear saída quando pagamento falhar
**Descrição:** a cancela de saída só pode abrir após pagamento aprovado.

**Critérios de aceitação**
- Pagamento aprovado => status `PAID` e cancela de saída aberta.
- Pagamento recusado/erro => status `PAYMENT_ERROR` e cancela de saída não abre.
- Em caso de falha, a vaga não deve ser liberada como se a saída tivesse sido concluída.

**Evidência:** teste automatizado + log da execução.

#### BP-03 — Calcular cobrança pelo tempo real
**Descrição:** substituir os 95 minutos fixos pelo cálculo entre `entryTime` e `exitTime`.

**Critérios de aceitação**
- Para uma entrada às 19:00 e saída às 20:35, o tempo calculado é 95 minutos.
- Alterando os horários, o valor muda de acordo com a nova duração.
- O valor final usado no pagamento é o mesmo valor calculado pela regra de preço.

**Evidência:** teste automatizado mostrando duração e valor.

### P1 — Alto

#### BP-04 — Validar reserva
**Critérios de aceitação**
- Não reservar vaga ocupada.
- Não reservar vaga já reservada.
- Não reservar vaga incompatível com o tipo solicitado.
- Reserva válida deve associar a placa à vaga.

**Evidência:** testes para cenários válidos e inválidos.

#### BP-05 — Limpar estado após saída
**Critérios de aceitação**
- Após uma saída concluída, `occupied=false`.
- A placa da vaga é removida.
- A reserva é removida quando a sessão consumiu aquela reserva.
- O estado final pode ser verificado pela execução.

**Evidência:** teste + saída do programa.

#### BP-06 — Corrigir publicação de eventos
**Critérios de aceitação**
- `DriverObserver` e `OperationsObserver` recebem o mesmo evento publicado.
- Adicionar um terceiro observer não substitui os anteriores.
- Um teste deve comprovar a quantidade de consumidores notificados.

**Evidência:** teste automatizado.

#### BP-08 — Definir critérios de aceitação dos requisitos
**Critérios de aceitação**
- Cada requisito prioritário deve possuir pelo menos um resultado observável.
- Os critérios devem ser objetivos e passíveis de teste.
- Os critérios devem estar vinculados às issues do backlog.

**Evidência:** este documento + issue `Aula 05`.

### P2 — Médio

#### BP-07 — Melhorar uso de Strategy no preço
**Critérios de aceitação**
- Regras de preço podem ser trocadas por estratégia sem alterar o fluxo principal.
- O comportamento atual continua coberto por testes.

**Evidência:** teste das estratégias + compilação.

#### BP-09 — Registrar decisões arquiteturais
**Critérios de aceitação**
- Documento identifica fronteiras principais.
- Integrações externas possuem contratos/responsabilidades descritos.
- Trade-offs das decisões relevantes estão registrados.

**Evidência:** Markdown/ADR atualizado.

## Organização sugerida no Kanban

1. **Backlog:** BP-01 a BP-09.
2. **A Fazer:** itens priorizados para a próxima execução.
3. **Em andamento:** somente uma tarefa por vez por integrante, quando possível.
4. **Em revisão:** tarefa concluída aguardando revisão/PR.
5. **Concluído:** critérios de aceitação atendidos e evidência anexada.

### Ordem recomendada
`BP-01 → BP-02 → BP-03 → BP-04 → BP-05 → BP-06 → BP-08 → BP-07 → BP-09`

## Definition of Done da Aula 05

Uma tarefa só pode ser marcada como concluída quando:
- o problema está descrito;
- a alteração ou planejamento está registrado;
- existem critérios de aceitação verificáveis;
- há evidência correspondente;
- o commit possui mensagem explicativa;
- a alteração foi revisada por PR ou revisão equivalente, quando aplicável.
