# 🏥 ClinSys – Sistema de Gestão para Profissionais da Saúde

**ClinSys** é uma aplicação backend desenvolvida com **Java + Spring Boot**, com o objetivo de facilitar a gestão de pacientes e consultas em clínicas e consultórios. É voltado para profissionais independentes da área de saúde e que fazem a própria gestão de pacientes.
Este projeto também serve como portfólio para demonstrar minha habilidade em backend Java, arquitetura de software e segurança com tokens JWT.

---

## 🚀 Funcionalidades

- ✅ Cadastro de pacientes
- ✅ Agendamento de consultas com status de pagamento (pago ou pendente)
- ✅ Autenticação com Spring Security + JWT
- ✅ Registro e login de usuários com criptografia de senha
- ✅ Proteção de rotas e autorização baseada em tokens
- 📊 Dashboard com estatísticas (em desenvolvimento)
- 📬 Notificações por e-mail ou WhatsApp (futuro)
- 🌐 Frontend em React ou React Native (futuro)

---

## 🔒 Segurança

- Implementação completa de autenticação e autorização via JWT
- Senhas criptografadas com BCrypt
- Endpoints protegidos com Spring Security
- Filtro de autenticação personalizado para validar tokens
- DTOs e validações com `@Valid` e handler global de erros

---

## 🛠️ Tecnologias utilizadas

- Java 17  
- Spring Boot 3.5  
- Spring Security  
- JWT (jjwt)  
- Spring Data JPA  
- Hibernate  
- MySQL  
- Lombok  
- Maven  
- Swagger (documentação futura)  
- (Em breve: React ou React Native)

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

├── dto

├── security

└── config



---

## 🧪 Como rodar o projeto localmente

1. Clone o repositório:
   ```bash
   git clone https://github.com/nbeverton/clinsys.git

2. Crie o banco de dados:
CREATE DATABASE clinsys_db;

3. Altere application.properties com suas credenciais MySQL e chave JWT.

4. Rode a aplicação no IntelliJ ou via terminal:
./mvnw spring-boot:run

--

📂 Endpoints disponíveis
| Método | Endpoint    | Descrição                |
| ------ | ----------- | ------------------------ |
| GET    | `/auth/register` | Cria um novo usuário |
| POST   | `/auth/login` | Autentica e gera JWT |
| ...    | ...         | ...                      |

--

| Método | Endpoint    | Descrição                |
| ------ | ----------- | ------------------------ |
| GET    | `/patients` | Lista todos os pacientes |
| POST   | `/patients` | Cria um novo paciente    |

--

| Método | Endpoint        | Descrição                |
| ------ | --------------- | ------------------------ |
| GET    | `/appointments` | Lista todas as consultas |
| POST   | `/appointments` | Cria uma nova consulta   |

Para acessar rotas protegidas, utilize o token JWT no header:
Authorization: Bearer <token>

---

## 👨‍💻 Sobre o autor

Desenvolvido por Everton Barbosa – profissional em transição de carreira para tecnologia com formação em Análise e Desenvolvimento de Sistemas, foco em backend Java e experiência com tecnologias modernas como Spring Boot, JWT e MySQL.

📧 nbeverton@gmail.com
📌 LinkedIn: linkedin.com/in/evertonbarbosa-dev/


⭐ Este repositório está em constante evolução. Fique à vontade para enviar feedbacks, sugestões ou contribuir!
