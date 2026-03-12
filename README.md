# 🎟️ Ticket System API

Sistema backend para **venda de ingressos com pagamento via PIX**, utilizando integração com gateway de pagamento e confirmação automática via **webhook**.

O projeto foi desenvolvido como **estudo de arquitetura backend moderna**, utilizando **Java 21 + Spring Boot**, banco de dados relacional e infraestrutura conteinerizada.

---

# 📌 Objetivo do Projeto

Este sistema permite:

* Criar eventos
* Listar eventos disponíveis
* Realizar compra de ingressos
* Gerar cobrança PIX
* Receber confirmação de pagamento via webhook
* Gerar ingressos após pagamento confirmado

O foco do projeto é demonstrar conceitos importantes de backend:

* Integração com gateway de pagamento
* Arquitetura baseada em eventos
* Webhooks
* Persistência com banco relacional
* Estrutura de projeto organizada
* Infraestrutura com Docker

---

# 🧱 Arquitetura do Sistema

```
Frontend (Angular)
        |
        v
Spring Boot API
        |
        v
Gateway de Pagamento (PIX)
        |
        v
Webhook de Confirmação
        |
        v
PostgreSQL
```

Fluxo de pagamento:

```
Cliente compra ingresso
        |
API cria cobrança PIX
        |
Cliente paga o PIX
        |
Gateway envia webhook
        |
API confirma pagamento
        |
Ingresso é gerado
```

---

# 🛠️ Tecnologias Utilizadas

### Backend

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA
* Lombok

### Banco de dados

* PostgreSQL

### Infraestrutura

* Docker
* Docker Compose
* Redis (planejado para filas futuras)

### Ferramentas de teste

* Apidog / Postman

---

# 📁 Estrutura do Projeto

```
src/main/java/com/lazaro/ticketsystem

controller/
    EventController
    PaymentController
    WebhookController

service/
    EventService
    PaymentService
    TicketService

repository/
    EventRepository
    OrderRepository
    TicketRepository

model/
    Event
    Order
    Ticket

dto/
    CreatePixPaymentRequest
    PixPaymentResponse

config/
    AbacateClientConfig
```

---

# 🗄️ Modelagem do Banco

## Event

Representa um evento que possui ingressos disponíveis.

| Campo            | Tipo          |
| ---------------- | ------------- |
| id               | UUID          |
| name             | String        |
| description      | String        |
| price            | BigDecimal    |
| date             | LocalDateTime |
| availableTickets | Integer       |

---

## Order

Representa um pagamento realizado para um evento.

| Campo     | Tipo          |
| --------- | ------------- |
| id        | UUID          |
| eventId   | UUID          |
| paymentId | String        |
| status    | String        |
| createdAt | LocalDateTime |

Status possíveis:

```
PENDING
PAID
CANCELLED
```

---

## Ticket

Ingresso gerado após pagamento confirmado.

| Campo   | Tipo    |
| ------- | ------- |
| id      | UUID    |
| orderId | UUID    |
| qrCode  | String  |
| used    | Boolean |

---

# ⚙️ Configuração do Projeto

## application.properties

```
spring.datasource.url=jdbc:postgresql://localhost:5432/ticketdb
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8080

abacate.api-key=YOUR_API_KEY
```

---

# 🐳 Infraestrutura com Docker

Arquivo:

```
docker-compose.yml
```

```yaml
version: "3.8"

services:

  postgres:
    image: postgres:15
    container_name: ticket_postgres
    environment:
      POSTGRES_DB: ticketdb
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
    ports:
      - "5432:5432"

  redis:
    image: redis:7
    container_name: ticket_redis
    ports:
      - "6379:6379"
```

Subir infraestrutura:

```
docker compose up -d
```

---

# ▶️ Rodando o Projeto

### 1️⃣ Clonar repositório

```
git clone <repo-url>
```

---

### 2️⃣ Subir banco

```
docker compose up -d
```

---

### 3️⃣ Rodar aplicação

```
mvn spring-boot:run
```

ou

```
./mvnw spring-boot:run
```

---

# 📡 Endpoints da API

## Criar evento

```
POST /events
```

Body:

```json
{
  "name": "Show de Rock",
  "description": "Evento musical",
  "price": 50,
  "date": "2026-06-10T20:00:00",
  "availableTickets": 100
}
```

---

## Listar eventos

```
GET /events
```

---

## Criar pagamento PIX

```
POST /payments/pix
```

Body:

```json
{
  "eventId": "UUID_DO_EVENTO",
  "customerName": "Lazaro",
  "customerEmail": "lazaro@email.com"
}
```

Fluxo:

1. API cria cobrança PIX
2. Order é salva como **PENDING**
3. API retorna dados do pagamento

---

## Webhook de pagamento

```
POST /webhook/abacate
```

Quando o pagamento for confirmado:

```
Gateway -> Webhook -> API
```

A API:

1. Atualiza order para **PAID**
2. Gera ticket
3. Associa ticket ao pedido

---

# 🔐 Segurança (Planejado)

Ainda vou implementar:

* Validação de assinatura do webhook
* Idempotência de eventos
* Rate limit
* Autenticação JWT

---

# 🚀 Roadmap do Projeto

Próximas melhorias planejadas:

### Pagamentos

* Integração completa com PIX
* Confirmação automática via webhook
* Tratamento de erros do gateway

### Tickets

* Geração de QR Code
* Sistema de check-in de ingressos

### Arquitetura

* Filas com Redis
* Workers para processamento assíncrono
* Idempotência de pagamentos

### Frontend

* Interface Angular
* Página de pagamento
* Visualização de QR Code PIX

---

# 🎯 Objetivo de Aprendizado

Este projeto demonstra conceitos importantes utilizados em sistemas reais:

* Integração com APIs externas
* Processamento assíncrono
* Webhooks
* Arquitetura backend escalável
* Organização de código em camadas

---

# 👨‍💻 Autor

**Lázaro Vasconcelos**