# Issue — Aula 05

## Objetivo
Transformar os achados do legado do SmartPark em um backlog técnico priorizado, com critérios de aceitação verificáveis e evidências de validação.

## Achados principais
- Entrada não valida adequadamente o estado do sensor antes de abrir a cancela.
- Saída abre a cancela mesmo com pagamento com erro.
- Cobrança usa duração fixa de 95 minutos.
- Reserva não verifica ocupação/reserva existente.
- Estado da vaga não é completamente limpo após a saída.
- Publisher mantém apenas um observer.
- Requisitos e arquitetura ainda não possuem critérios/decisões suficientemente rastreáveis.

## Prioridade
**P0:** sensor, pagamento e cálculo da cobrança.  
**P1:** reserva, limpeza de estado, eventos e critérios de aceitação.  
**P2:** Strategy de preço e documentação arquitetural.

## Entregáveis
- [x] Backlog técnico priorizado.
- [x] Critérios de aceitação.
- [x] Definition of Done.
- [x] Registro de evidência de compilação.
- [x] Branch sugerida: `aula-05`.
- [ ] Criar a Issue no GitHub com este conteúdo.
- [ ] Criar branch `aula-05`.
- [ ] Fazer commits explicativos.
- [ ] Abrir PR ou realizar revisão equivalente.

## Observação
A Aula 05 é principalmente de planejamento técnico. Os itens do backlog acima descrevem correções futuras; eles não devem ser considerados implementados apenas porque foram registrados.
