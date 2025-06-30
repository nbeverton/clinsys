# 🏥 ClinSys – Sistema de Gestão para Consultórios Médicos

**ClinSys** é uma aplicação backend desenvolvida em **Java + Spring Boot**, com o objetivo de facilitar a gestão de pacientes e consultas em clínicas e consultórios.  
Este projeto também serve como portfólio para demonstrar minha habilidade em backend Java e arquitetura de sistemas.

---

## 🚀 Funcionalidades (em desenvolvimento)

- ✅ Cadastro de pacientes  
- ✅ Agendamento de consultas  
- 🔒 Autenticação e controle de acesso (Spring Security + JWT)  
- 📊 Dashboard com estatísticas (futuro)  
- 📬 Envio de notificações por e-mail ou WhatsApp (futuro)

---

## 🛠️ Tecnologias utilizadas

- Java 17  
- Spring Boot 3.5  
- Spring Data JPA  
- MySQL  
- Hibernate  
- Lombok  
- Maven  
- Swagger (documentação futura)  
- (Em breve: React ou React Native para o front-end)

---

## 💾 Estrutura do Projeto

src

└── main

└── java

└── com.nbeverton.clinsys

├── model

├── repository

├── controller

├── service

└── dto (futuro)


---

## 🧪 Como rodar o projeto localmente

1. Clone o repositório:
   ```bash
   git clone https://github.com/nbeverton/clinsys.git

2. Crie o banco de dados:
CREATE DATABASE clinsys_db;

3. Altere application.properties com suas credenciais do MySQL.

4. Rode a aplicação no IntelliJ ou via terminal:
./mvnw spring-boot:run

📂 Endpoints iniciais (em breve com Swagger)
| Método | Endpoint    | Descrição                |
| ------ | ----------- | ------------------------ |
| GET    | `/patients` | Lista todos os pacientes |
| POST   | `/patients` | Cria um novo paciente    |
| ...    | ...         | ...                      |

---

## 👨‍💻 Sobre o autor

Desenvolvido por Everton Barbosa – profissional em transição de carreira para tecnologia com formação em ADS, experiência em Java e paixão por backend.

📧 nbeverton@gmail.com


⭐ Este repositório está em constante evolução. Fique à vontade para enviar feedbacks, sugestões ou contribuir!
