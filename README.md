# 💊 MedTrack – Controle de Medicamentos e Suplementos

**MedTrack** é um sistema Full Stack desenvolvido para ajudar usuários a controlarem o uso de medicamentos e suplementos. A aplicação permite cadastrar produtos com data de início, quantidade total e doses diárias, e envia **lembretes por e-mail antes do término** para garantir a reposição a tempo.

Foi criado para resolver uma necessidade pessoal e, ao mesmo tempo, colocar em prática os conhecimentos adquiridos com Java, Spring Boot e outras tecnologias web.

---

## 🚀 Funcionalidades

- Cadastro e autenticação de usuários com token JWT
- Cadastro de medicamentos/suplementos
- Cálculo automático da data de término com base nas doses
- Agendamento de envio de e-mail com aviso de reposição
- Interface simples e intuitiva
- Deploy full com backend e frontend online

---

## 🛠️ Tecnologias utilizadas

### 🔙 Backend
- Java + Spring Boot
- Spring Security (JWT)
- Spring Data JPA + Hibernate
- PostgreSQL
- JavaMailSender (SMTP Gmail)
- Agendamento de tarefas com `@Scheduled` (cronjob)
- Render (deploy)

### 🔜 Frontend
- React
- React Hook Form
- Axios
- Vite
- Vercel (deploy)

---

## 📦 Como rodar localmente

### Pré-requisitos
- Java 17+
- Node.js e npm
- PostgreSQL
- Conta Gmail (para envio de e-mail)
- IDE (recomendado: IntelliJ + VSCode)

### Clone o projeto
```bash
git clone https://github.com/seu-usuario/medtrack.git
```

### Inicie o backend
```bash
cd backend
./mvnw spring-boot:run
```

### Inicie o frontend
```bash
cd frontend
npm install
npm run dev
```

---

## 🔐 Variáveis de ambiente

### Backend (`application.properties`)
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/medtrack
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

spring.mail.username=SEU_EMAIL@gmail.com
spring.mail.password=SENHA_DE_APP

jwt.secret=SUA_CHAVE_SECRETA
```

### Frontend (`.env`)
```env
VITE_API_URL=http://localhost:8080
```

---

## 🌐 Deploy

- **Frontend (Vercel):** [https://medtrack-omega.vercel.app](https://medtrack-omega.vercel.app)
- **Backend (Render):** [https://medtrack-81w6.onrender.com](https://medtrack-81w6.onrender.com)

---

## 📚 Aprendizados

Durante o desenvolvimento do MedTrack, aprofundei meus conhecimentos em:

- Programação orientada a objetos (POO)
- Autenticação com JWT
- Integração com banco de dados usando Spring Data JPA
- Criação e consumo de APIs REST
- Agendamento de tarefas com cronjob
- Envio de e-mail com JavaMailSender
- Deploy em ambiente de produção
- Comunicação entre frontend e backend com Axios

---

## 💡 Motivação

Esse projeto nasceu de uma necessidade pessoal. Eu costumo usar suplementos e, como faço as compras pela internet, precisava controlar quando cada produto acabaria para conseguir repor a tempo. Antes, eu anotava essas datas e criava lembretes manuais. O MedTrack automatiza tudo isso, avisando por e-mail alguns dias antes do suplemento acabar.

Além de resolver meu problema, aproveitei para colocar em prática tudo o que aprendi com Java, Spring Boot e desenvolvimento web em geral.

---

## 📸 Demonstração

> ![Estoque](https://github.com/user-attachments/assets/65d8bdf1-8641-46c3-85c4-18a0b255cbb6)
> ![Home](https://github.com/user-attachments/assets/c62a4389-699a-4775-9a9e-badb5cd745b8)
> ![Cadastro](https://github.com/user-attachments/assets/37e90ed5-2105-495c-9d6e-b0cf77e930db)

---

## 👩‍💻 Autora

Desenvolvido por **Rhobertta Cardoso**

- [LinkedIn](https://www.linkedin.com/in/rhobertta-grasielle/)
