# ADR-0002 – Mapa de Contexto

## A Fila da Cantina

**Aplicação:**
https://campus-cantina-order-app-wzzkkr.sticklight.app

---

## Alunos

- Arthur Sousa
- Erick
- Pedro Herinque Ochoa
- Danielle
- José Renato

---

# 1. Participantes

| Participante | Papel |
|--------------|-------|
| **Aluno** | Reserva o prato do dia e realiza a retirada. |
| **Equipe da cozinha** | Prepara os pratos conforme as reservas. |
| **Atendente/Caixa** | Entrega pedidos e recebe pagamento, se presencial. |
| **Coordenação** | Define regras e acompanha o funcionamento. |

---

# 2. O que já existe hoje

- A cantina física continua funcionando normalmente.
- O preparo dos alimentos continua sendo realizado pela cozinha.
- Atualmente o pagamento é realizado presencialmente no caixa.
- Existe apenas uma pessoa no caixa.
- Existem apenas duas pessoas na cozinha.
- O aluno retira a refeição presencialmente.

---

# 3. Restrições

- Não foi definido se o pagamento será pelo aplicativo ou presencial.
- Não existe regra para alunos que reservam e não comparecem.
- O sistema deve funcionar sem aumentar a quantidade de funcionários.
- O objetivo principal é reduzir as filas.

---

# 4. Contexto Geral

```text
               Coordenação
                    │
                    ▼
      ┌────────────────────────┐
      │ Aplicativo de Reserva  │
      └────────────────────────┘
            ▲              ▲
            │              │
         Aluno    Equipe da Cozinha
            │
            ▼
      Cantina / Caixa
```

---

# 5. Operacional Atual

Atualmente os alunos chegam à cantina, aguardam na fila, realizam o pagamento no caixa e recebem a refeição preparada pela cozinha.

Como há apenas um caixa e duas pessoas na cozinha, formam-se filas nos horários de maior movimento.

O aplicativo permitirá reservar o prato antecipadamente para reduzir o tempo de espera.

---

# 6. Decisões em aberto

1. O pagamento será realizado pelo aplicativo ou presencialmente?
2. Como tratar reservas não utilizadas?
3. Existirá horário limite para reservas?
4. Será permitido cancelar reservas?

---