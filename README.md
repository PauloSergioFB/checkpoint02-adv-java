# Drone Mission Control API

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9-blue?style=for-the-badge&logo=apachemaven&logoColor=white)
![FIAP](https://img.shields.io/badge/FIAP-ED145B?style=for-the-badge)

---

## 💡 Sobre o Projeto

O **Drone Mission Control API** é uma aplicação desenvolvida em **Spring Boot** que implementa um sistema de gerenciamento de **drones e missões**.  

A API oferece as operações **CRUD básicas** (cadastrar, consultar, atualizar e remover drones e missões), além de **endpoints não-CRUD inspirados em cenários reais de uso** — como listagem de missões futuras, geração de relatórios de bateria, ranking de drones mais utilizados e busca de missões por localização.  

---

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.5**
- **Spring Data JPA**
- **H2 Database** (banco de dados local para testes)
- **Maven** (gerenciador de dependências)
- **Springdoc OpenAPI** (documentação Swagger UI)

---

## 🗂️ Entidades do Sistema

### **Drone**
Representa um drone cadastrado no sistema, que pode estar associado a várias missões (**relação 1:N**).  

**Atributos:**
- `id` *(UUID)* → Identificador único do drone, gerado automaticamente  
- `model` *(String, máx. 100)* → Modelo do drone (ex.: *DJI Mavic 3*)  
- `batteryCapacity` *(int)* → Capacidade da bateria em minutos  
- `status` *(String, máx. 20)* → Situação atual (`ativo`, `manutenção`, `fora de operação`)  
- `missions` *(List<Mission>)* → Lista de missões atribuídas ao drone  

---

### **Missão**
Representa uma missão atribuída a um drone específico.

**Atributos:**
- `id` *(UUID)* → Identificador único da missão, gerado automaticamente  
- `description` *(String)* → Texto livre com a descrição (ex.: *Inspeção de linha de transmissão*)  
- `location` *(String, máx. 155)* → Localização da missão (cidade ou coordenadas)  
- `scheduledData` *(LocalDate)* → Data prevista para execução da missão  
- `duration` *(int)* → Duração estimada da missão (em minutos)  
- `drone` *(Drone)* → Drone responsável pela missão (chave estrangeira)  

---

## 📌 Endpoints

### **Drones** (`/api/v1/drones`)

- `GET /api/v1/drones` → Lista todos os drones
- `GET /api/v1/drones/{id}` → Busca drone por ID
- `GET /api/v1/drones/{id}/missions` → Lista missões associadas a um drone
- `GET /api/v1/drones/{id}/future-missions` → Lista missões futuras de um drone
- `GET /api/v1/drones/{id}/battery-report` → Relatório de uso de bateria de um drone
- `GET /api/v1/drones/usage-ranking` → Exibe ranking de drones por uso de bateria
- `POST /api/v1/drones` → Cadastra um novo drone
- `PUT /api/v1/drones/{id}` → Atualiza informações de um drone
- `DELETE /api/v1/drones` → Remove todos os drones (se aplicável)

### **Missões** (`/api/v1/missions`)

- `GET /api/v1/missions` → Lista todas as missões
- `GET /api/v1/missions/{id}` → Busca missão por ID
- `GET /api/v1/missions/by-location` → Busca missões filtradas por localização
- `POST /api/v1/missions` → Cadastra uma nova missão

---

## 📋 Exemplos de Requisições (cURL)

### **Cadastrar Drones**

```bash
curl -X POST http://localhost:8080/api/v1/drones \
  -H "Content-Type: application/json" \
  -d '{
    "model": "DJI Mavic 3",
    "batteryCapacity": 4800,
    "status": "ativo"
  }'
```

```bash
curl -X POST http://localhost:8080/api/v1/drones \
  -H "Content-Type: application/json" \
  -d '{
    "model": "Parrot Anafi",
    "batteryCapacity": 2700,
    "status": "manutenção"
  }'
```

---

### **Cadastrar Missões**

```bash
curl -X POST http://localhost:8080/api/v1/missions \
  -H "Content-Type: application/json" \
  -d '{
    "description": "Inspeção de linha de transmissão",
    "location": "São Paulo",
    "scheduledData": "2025-10-15",
    "droneId": ${droneId}
  }'
```

```bash
curl -X POST http://localhost:8080/api/v1/missions \
  -H "Content-Type: application/json" \
  -d '{
    "description": "Entrega de suprimentos médicos",
    "location": "Rio de Janeiro",
    "scheduledData": "2025-10-20",
    "droneId": ${droneId}
  }'
```

```bash
curl -X POST http://localhost:8080/api/v1/missions \
  -H "Content-Type: application/json" \
  -d '{
    "description": "Monitoramento agrícola",
    "location": "Campinas",
    "scheduledData": "2025-10-25",
    "droneId": ${droneId}
  }'
```

```bash
curl -X POST http://localhost:8080/api/v1/missions \
  -H "Content-Type: application/json" \
  -d '{
    "description": "Mapeamento de área de risco",
    "location": "Belo Horizonte",
    "scheduledData": "2025-10-30",
    "droneId": ${droneId}
  }'
```

---


## 🧐 Justificativa para uso de GET nos Endpoints Não-CRUD

Os endpoints não-CRUD da API utilizam o método **HTTP GET** porque realizam apenas **consultas a dados já existentes** — como a listagem de missões futuras de um drone, geração de relatórios de uso de bateria, ranking de drones mais utilizados ou busca por localização.
Como não envolvem **criação, alteração ou exclusão de recursos**, não se justificaria o uso de métodos como `POST`, `PUT` ou `DELETE`.

---

## 🚀 Como Executar Localmente

### **Pré-requisitos**

- [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)  
- [Maven 3.9+](https://maven.apache.org/download.cgi)  

### **Instalação**


1. **Clonar o repositório**  
```bash
git clone https://github.com/PauloSergioFB/checkpoint02-adv-java.git
```

2. **Entrar na pasta do projeto**  
```bash
cd checkpoint02-adv-java
```

3. **Compilar o projeto**  
```bash
mvn clean install
```

### **Execução**

**Executar a aplicação**  
```bash
mvn spring-boot:run
```

O projeto iniciará em:  
👉 [http://localhost:8080](http://localhost:8080)

A documentação Swagger estará disponível em:  
👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 📐 Especificações Técnicas

- Arquitetura organizada em **camadas**:  
  - `domainmodel` → entidades e repositórios  
  - `service` → regras de negócio  
  - `presentation.controllers` → endpoints REST  
  - `infrastructure` → configuração do Swagger e handlers globais  

- Repositórios customizados (`DroneRepositoryCustom`, `MissionRepositoryImpl`, etc.) implementam consultas avançadas com QueryDSL.
- Uso de **DTOs** para transporte de dados (`DroneDTO`, `MissionDTO`, `BatteryUsageReportDTO`)  
- Tratamento centralizado de exceções com `GlobalExceptionHandler`

---

## 👥 Contribuidores

- [**@PauloSergioFB**](https://github.com/PauloSergioFB) — Desenvolvimento Backend