# 📚 Documentação da API - RU Digital

API REST completa para gerenciamento de restaurantes universitários, incluindo usuários, funcionários, unidades, refeições, consumíveis e sistema de tickets.

## 📑 Índice

- [Visão Geral](#visão-geral)
- [Fluxo de Uso](#fluxo-de-uso)
- [Endpoints](#endpoints)
  - [Usuários](#usuários)
  - [Funcionários](#funcionários)
  - [Unidades](#unidades)
  - [Refeições](#refeições)
  - [Consumíveis](#consumíveis)
  - [Tickets](#tickets)
- [Schemas de Objetos](#schemas-de-objetos)
- [Tratamento de Erros](#tratamento-de-erros)

---

## 🎯 Visão Geral

A API RU Digital permite:
- Gerenciamento de usuários (alunos) e funcionários
- Controle de unidades (restaurantes)
- Cadastro de refeições e consumíveis
- **Compra de tickets de refeição por alunos**
- **Consumo de tickets nas unidades**
- Controle de saldo dos usuários

**Base URL**: `http://localhost:8080/api`

---

## 🔄 Fluxo de Uso

### Fluxo de Compra de Ticket

```
1. Aluno adiciona saldo à conta
   POST /api/usuarios/{id}/saldo/adicionar?valor=1000

2. Aluno compra ticket de refeição
   POST /api/tickets/comprar
   {
     "usuarioId": 1,
     "tipoRefeicao": "ALMOCO",
     "unidadeId": 1,
     "funcionarioId": 2,
     "precoCentavos": 500
   }

3. Sistema valida saldo e cria ticket ATIVO
```

### Fluxo de Consumo de Ticket

```
1. Aluno apresenta ticket na unidade
   GET /api/tickets/usuario/{id}/ativos

2. Aluno seleciona consumíveis desejados
   GET /api/consumiveis

3. Aluno consome ticket
   POST /api/tickets/consumir
   {
     "ticketId": 1,
     "consumiveisIds": [1, 3, 5]
   }

4. Ticket marcado como CONSUMIDO
```

---

## 📍 Endpoints

### Usuários

#### Listar todos os usuários
```http
GET /api/usuarios
```

**Response 200:**
```json
[
  {
    "id": 1,
    "nome": "João Silva",
    "cpf": "12345678900",
    "email": "joao@email.com",
    "matricula": "2023001",
    "saldoCentavos": 5000
  }
]
```

#### Buscar usuário por ID
```http
GET /api/usuarios/{id}
```

**Response 200:**
```json
{
  "id": 1,
  "nome": "João Silva",
  "cpf": "12345678900",
  "email": "joao@email.com",
  "matricula": "2023001",
  "saldoCentavos": 5000
}
```

#### Buscar usuário por nome
```http
GET /api/usuarios/nome/{nome}
```

#### Criar usuário
```http
POST /api/usuarios
Content-Type: application/json

{
  "nome": "João Silva",
  "cpf": "12345678900",
  "email": "joao@email.com",
  "matricula": "2023001"
}
```

**Response 201:**
```json
{
  "id": 1,
  "nome": "João Silva",
  "cpf": "12345678900",
  "email": "joao@email.com",
  "matricula": "2023001",
  "saldoCentavos": 0
}
```

#### Atualizar usuário
```http
PUT /api/usuarios/{id}
Content-Type: application/json

{
  "nome": "João Silva Santos",
  "cpf": "12345678900",
  "email": "joao.santos@email.com",
  "matricula": "2023001",
  "saldoCentavos": 5000
}
```

#### Deletar usuário
```http
DELETE /api/usuarios/{id}
```

**Response 204:** No Content

#### Adicionar saldo
```http
POST /api/usuarios/{id}/saldo/adicionar?valor={valorEmCentavos}
```

**Exemplo:**
```http
POST /api/usuarios/1/saldo/adicionar?valor=1000
```

**Response 200:**
```json
{
  "id": 1,
  "nome": "João Silva",
  "saldoCentavos": 6000
}
```

#### Debitar saldo
```http
POST /api/usuarios/{id}/saldo/debitar?valor={valorEmCentavos}
```

#### Consultar saldo
```http
GET /api/usuarios/{id}/saldo
```

**Response 200:**
```json
5000
```

---

### Funcionários

#### Listar todos os funcionários
```http
GET /api/funcionarios
```

#### Buscar funcionário por ID
```http
GET /api/funcionarios/{id}
```

#### Buscar funcionário por CPF
```http
GET /api/funcionarios/cpf/{cpf}
```

#### Buscar funcionário por email
```http
GET /api/funcionarios/email/{email}
```

#### Buscar funcionário por matrícula
```http
GET /api/funcionarios/matricula/{matricula}
```

#### Criar funcionário
```http
POST /api/funcionarios
Content-Type: application/json

{
  "nome": "Maria Santos",
  "cpf": "98765432100",
  "email": "maria@email.com",
  "matricula": "FUNC001",
  "cargos": []
}
```

#### Atualizar funcionário
```http
PUT /api/funcionarios/{id}
```

#### Deletar funcionário
```http
DELETE /api/funcionarios/{id}
```

---

### Unidades

#### Listar todas as unidades
```http
GET /api/unidades
```

**Response 200:**
```json
[
  {
    "id": 1,
    "nome": "RU Central",
    "endereco": "Av. Universitária, 1000",
    "cronograma": "Segunda a Sexta: 11h-14h e 17h-20h"
  }
]
```

#### Buscar unidade por ID
```http
GET /api/unidades/{id}
```

#### Buscar unidade por nome
```http
GET /api/unidades/nome/{nome}
```

#### Buscar unidades (busca parcial)
```http
GET /api/unidades/search?nome={termo}
```

#### Criar unidade
```http
POST /api/unidades
Content-Type: application/json

{
  "nome": "RU Central",
  "endereco": "Av. Universitária, 1000",
  "cronograma": "Segunda a Sexta: 11h-14h e 17h-20h"
}
```

#### Atualizar unidade
```http
PUT /api/unidades/{id}
```

#### Deletar unidade
```http
DELETE /api/unidades/{id}
```

---

### Refeições

#### Listar todas as refeições
```http
GET /api/refeicoes
```

#### Buscar refeição por ID
```http
GET /api/refeicoes/{id}
```

#### Buscar refeições por tipo
```http
GET /api/refeicoes/tipo/{tipo}
```

**Tipos válidos:** `DESJEJUM`, `ALMOCO`, `JANTA`

#### Buscar refeições por unidade
```http
GET /api/refeicoes/unidade/{unidadeId}
```

#### Buscar refeições por usuário
```http
GET /api/refeicoes/usuario/{usuarioId}
```

#### Criar refeição
```http
POST /api/refeicoes
Content-Type: application/json

{
  "nome": "Almoço Completo",
  "tipo": "ALMOCO",
  "precoCentavos": 500,
  "unidade": {
    "id": 1
  },
  "consumiveis": []
}
```

#### Atualizar refeição
```http
PUT /api/refeicoes/{id}
```

#### Deletar refeição
```http
DELETE /api/refeicoes/{id}
```

---

### Consumíveis

#### Listar todos os consumíveis
```http
GET /api/consumiveis
```

**Response 200:**
```json
[
  {
    "id": 1,
    "nome": "Arroz Branco",
    "descricao": "Arroz branco cozido",
    "alergenos": ["gluten"],
    "estoqueKg": 50.0
  },
  {
    "id": 2,
    "nome": "Suco de Laranja",
    "descricao": "Suco natural de laranja",
    "alergenos": [],
    "estoqueLitros": 20.0
  }
]
```

#### Buscar consumível por ID
```http
GET /api/consumiveis/{id}
```

#### Buscar consumíveis por nome
```http
GET /api/consumiveis/search?nome={termo}
```

#### Criar consumível
```http
POST /api/consumiveis
Content-Type: application/json

{
  "nome": "Arroz Branco",
  "descricao": "Arroz branco cozido",
  "alergenos": ["gluten"],
  "estoqueKg": 50.0
}
```

**Nota:** Para bebidas, use `estoqueLitros` no lugar de `estoqueKg`.

#### Atualizar consumível
```http
PUT /api/consumiveis/{id}
```

#### Deletar consumível
```http
DELETE /api/consumiveis/{id}
```

---

### Tickets

#### 🎟️ Comprar ticket
```http
POST /api/tickets/comprar
Content-Type: application/json

{
  "usuarioId": 1,
  "tipoRefeicao": "ALMOCO",
  "unidadeId": 1,
  "funcionarioId": 2,
  "precoCentavos": 500
}
```

**Response 201:**
```json
{
  "id": 1,
  "usuario": {
    "id": 1,
    "nome": "João Silva"
  },
  "tipoRefeicao": "ALMOCO",
  "unidade": {
    "id": 1,
    "nome": "RU Central"
  },
  "precoCentavos": 500,
  "dataCompra": "2025-10-30T10:30:00",
  "dataValidade": "2025-11-29",
  "status": "ATIVO",
  "funcionarioVendedor": {
    "id": 2,
    "nome": "Maria Santos"
  },
  "dataConsumo": null,
  "consumiveis": []
}
```

**Erros possíveis:**
- `400 Bad Request`: Saldo insuficiente
- `404 Not Found`: Usuario, Unidade ou Funcionario não encontrado

#### 🍽️ Consumir ticket
```http
POST /api/tickets/consumir
Content-Type: application/json

{
  "ticketId": 1,
  "consumiveisIds": [1, 3, 5, 7]
}
```

**Response 200:**
```json
{
  "id": 1,
  "usuario": {
    "id": 1,
    "nome": "João Silva"
  },
  "tipoRefeicao": "ALMOCO",
  "status": "CONSUMIDO",
  "dataConsumo": "2025-10-30T12:15:00",
  "consumiveis": [
    {
      "id": 1,
      "nome": "Arroz Branco"
    },
    {
      "id": 3,
      "nome": "Feijão Preto"
    }
  ]
}
```

**Erros possíveis:**
- `400 Bad Request`: Ticket já consumido ou expirado
- `404 Not Found`: Ticket ou Consumível não encontrado

#### Listar todos os tickets
```http
GET /api/tickets
```

#### Buscar ticket por ID
```http
GET /api/tickets/{id}
```

#### Listar tickets de um usuário
```http
GET /api/tickets/usuario/{usuarioId}
```

#### Listar tickets ativos de um usuário
```http
GET /api/tickets/usuario/{usuarioId}/ativos
```

**Response 200:**
```json
[
  {
    "id": 1,
    "tipoRefeicao": "ALMOCO",
    "status": "ATIVO",
    "dataValidade": "2025-11-29"
  },
  {
    "id": 3,
    "tipoRefeicao": "JANTA",
    "status": "ATIVO",
    "dataValidade": "2025-11-30"
  }
]
```

#### Listar tickets por unidade
```http
GET /api/tickets/unidade/{unidadeId}
```

#### Listar tickets por status
```http
GET /api/tickets/status/{status}
```

**Status válidos:** `ATIVO`, `CONSUMIDO`, `EXPIRADO`

#### Verificar tickets expirados
```http
POST /api/tickets/verificar-expirados
```

---

## 📦 Schemas de Objetos

### Usuario
```json
{
  "id": Long,
  "nome": String,
  "cpf": String,
  "email": String,
  "matricula": String,
  "saldoCentavos": Long
}
```

### Funcionario
```json
{
  "id": Long,
  "nome": String,
  "cpf": String,
  "email": String,
  "matricula": String,
  "saldoCentavos": Long,
  "cargos": [
    {
      "id": Long,
      "unidade": { "id": Long },
      "salarioCentavos": Long
    }
  ]
}
```

### Unidade
```json
{
  "id": Long,
  "nome": String,
  "endereco": String,
  "cronograma": String
}
```

### Refeicao
```json
{
  "id": Long,
  "nome": String,
  "tipo": "DESJEJUM" | "ALMOCO" | "JANTA",
  "precoCentavos": Long,
  "dataHora": "ISO-8601",
  "unidade": { "id": Long },
  "usuario": { "id": Long },
  "consumiveis": [{ "id": Long }]
}
```

### Consumivel
```json
{
  "id": Long,
  "nome": String,
  "descricao": String,
  "alergenos": [String],
  "estoqueKg": Double,      // para Comida
  "estoqueLitros": Double   // para Bebida
}
```

### Ticket
```json
{
  "id": Long,
  "usuario": { "id": Long },
  "tipoRefeicao": "DESJEJUM" | "ALMOCO" | "JANTA",
  "unidade": { "id": Long },
  "precoCentavos": Long,
  "dataCompra": "ISO-8601",
  "dataValidade": "YYYY-MM-DD",
  "status": "ATIVO" | "CONSUMIDO" | "EXPIRADO",
  "funcionarioVendedor": { "id": Long },
  "dataConsumo": "ISO-8601",
  "consumiveis": [{ "id": Long }]
}
```

### ComprarTicketRequest
```json
{
  "usuarioId": Long,
  "tipoRefeicao": "DESJEJUM" | "ALMOCO" | "JANTA",
  "unidadeId": Long,
  "funcionarioId": Long,
  "precoCentavos": Long
}
```

### ConsumirTicketRequest
```json
{
  "ticketId": Long,
  "consumiveisIds": [Long]
}
```

---

## ⚠️ Tratamento de Erros

Todas as respostas de erro seguem o formato:

```json
{
  "timestamp": "2025-10-30T10:30:00",
  "status": 400,
  "error": "Saldo Insuficiente",
  "message": "Saldo insuficiente. Saldo atual: R$ 30.00, Valor necessário: R$ 50.00",
  "path": "/api/tickets/comprar"
}
```

### Códigos de Status HTTP

- `200 OK`: Requisição bem-sucedida
- `201 Created`: Recurso criado com sucesso
- `204 No Content`: Recurso deletado com sucesso
- `400 Bad Request`: Dados inválidos ou regra de negócio violada
- `404 Not Found`: Recurso não encontrado
- `500 Internal Server Error`: Erro interno do servidor

### Erros Comuns

#### Saldo Insuficiente
```json
{
  "status": 400,
  "error": "Saldo Insuficiente",
  "message": "Saldo insuficiente. Saldo atual: R$ 30.00, Valor necessário: R$ 50.00"
}
```

#### Ticket Inválido
```json
{
  "status": 400,
  "error": "Ticket Inválido",
  "message": "Ticket #1 inválido: Ticket já foi consumido ou está expirado"
}
```

#### Recurso Não Encontrado
```json
{
  "status": 404,
  "error": "Recurso Não Encontrado",
  "message": "Usuario com ID 99 não encontrado"
}
```

---

## 💡 Exemplos de Uso Completo

### Cenário: Aluno compra e consome almoço

**1. Criar aluno**
```bash
curl -X POST http://localhost:8080/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "cpf": "12345678900",
    "email": "joao@email.com",
    "matricula": "2023001"
  }'
```

**2. Adicionar saldo**
```bash
curl -X POST "http://localhost:8080/api/usuarios/1/saldo/adicionar?valor=5000"
```

**3. Comprar ticket de almoço**
```bash
curl -X POST http://localhost:8080/api/tickets/comprar \
  -H "Content-Type: application/json" \
  -d '{
    "usuarioId": 1,
    "tipoRefeicao": "ALMOCO",
    "unidadeId": 1,
    "funcionarioId": 2,
    "precoCentavos": 500
  }'
```

**4. Listar tickets ativos**
```bash
curl http://localhost:8080/api/tickets/usuario/1/ativos
```

**5. Consumir ticket**
```bash
curl -X POST http://localhost:8080/api/tickets/consumir \
  -H "Content-Type: application/json" \
  -d '{
    "ticketId": 1,
    "consumiveisIds": [1, 3, 5]
  }'
```

---

## 🔒 Observações de Segurança

- Valores monetários são sempre em **centavos** (Long) para evitar problemas com decimais
- Tickets têm validade de **30 dias** após a compra
- Tickets expirados não podem ser consumidos
- Saldo do usuário é validado antes de cada compra
- Transações de saldo são atômicas (com @Transactional)

---

## 📞 Suporte

Para dúvidas ou problemas com a API, entre em contato com a equipe de desenvolvimento.

**Versão da API**: 1.0.0  
**Última atualização**: 30/10/2025
