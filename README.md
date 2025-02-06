
# RM Sistemas - Backend cópia do ifood

Este repositório contém o código fonte do backend do sistema "ifome", uma singela cópia do ifood

## Ferramentas utilizadas e versões

**Linguagem de programação:** Java 21

**Java JDK:** eclipse-temurin

**Gerenciador de dependências:** Maven 3.9.9

**Banco de dados:** Postgresql 17.2, gerenciado pelo [Neon database](http://neon.tech)


## Execução Localmente

Clone o repositório

```bash
  git clone https://github.com/Arthurlevicoelho/api-clone-ifood.git
```

Vá para o diretório do projeto

```bash
  cd api-clone-ifood
```

Crie um arquivo .env e insira as variáveis de ambiente de acordo com o .env.example

```env
## LOCAL DATABASE
# DB_HOST=localhost:5432/database_name
# DB_USERNAME=your_database_username
# DB_PASSWORD=your_database_password

## CLOUD DATABASE
# DB_HOST=databse_host (without jdbc:postgresql://)
# DB_USERNAME=database_username
# DB_PASSWORD=database_password

## OTHER OPTIONS
# HIKARI_CONNECTION_TIMEOUT=30000
# HIKARI_IDLE_TIMEOUT=600000
# HIKARI_MAX_LIFETIME=1800000

# HIKARI_MAXIMUM_POOL_SIZE=10
# HIKARI_MINIMUM_IDLE=5
# SECRET_KEY=very-secret-key
```

Inicie o projeto com o maven

```bash
  mvn spring-boot:run
```

Ou Docker

```bash
  docker compose up -d --build
```

