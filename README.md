# 🏥 ClinSys – Sistema de Gestão para Profissionais da Saúde

O ClinSys é uma aplicação desenvolvida para facilitar a rotina de profissionais da saúde que gerenciam seus próprios pacientes e consultas, como médicos, psicólogos, fisioterapeutas, nutricionistas e terapeutas.

O sistema foi criado inicialmente como uma aplicação backend com Java 17 e Spring Boot 3.5, oferecendo segurança, organização e escalabilidade para o gerenciamento de dados sensíveis.
Com a conclusão da primeira etapa (backend), o projeto segue agora para a segunda fase: o desenvolvimento do frontend web, utilizando Bootstrap para criar uma interface simples, responsiva e amigável.

---

## 🚀 Funcionalidades

### Backend
- ✅ Cadastro de pacientes
- ✅ Agendamento de consultas com status de pagamento (pago ou pendente)
- ✅ Autenticação com Spring Security + JWT
- ✅ Registro e login de usuários com criptografia de senha
- ✅ Proteção de rotas e autorização baseada em tokens
- ✅ Criptografia de senhas com BCrypt
- ✅ Notificações por e-mail ou WhatsApp
- ✅ Estrutura em camadas com DTOs, validações e tratamento global de erros

### Frontend
- Interface web responsiva com Bootstrap
- Listagem e cadastro de pacientes
- Agendamento e gerenciamento de consultas
- Autenticação e login de usuários
- Consumo da API backend via REST
- Painel administrativo com estatísticas e relatórios

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

- HTML5
- CSS3
- JavaScript
- Bootstrap 5
- Axios (consumo de API)

---

## 💾 Estrutura do Projeto

src

 └── main
 
     └── java
     
         └── com.nbeverton.clinsys
         
             ├── model        # Entidades do sistema
             
             ├── repository   # Interfaces JPA para persistência
             
             ├── service      # Regras de negócio
             
             ├── controller   # Pontos de entrada da API (REST)
             
             ├── dto          # Objetos de transferência de dados
             
             ├── security     # Configuração de autenticação/autorização
             
             └── config       # Configurações gerais do sistema



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
