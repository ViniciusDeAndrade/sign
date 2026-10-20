# sign

Demo Spring Boot (Kotlin) com autenticação JWT, clientes e recuperação de senha.

## Stack

- Spring Boot 3.5, Java 17, Kotlin 2.2
- Spring Data JPA, Security, Validation, Mail, Thymeleaf
- springdoc-openapi
- Testes unitários: Kotest + MockK

## Como rodar

Na raiz do repositório:

```bash
./gradlew bootRun
```

Perfil recomendado: `dev`.

- PowerShell: `$env:SPRING_PROFILES_ACTIVE="dev"`
- JVM: `-Dspring.profiles.active=dev`

Variáveis úteis:

| Variável | Uso |
|---|---|
| `MYSQL_HOST`, `MYSQL_USER`, `MYSQL_PASSWORD` | banco |
| `JWT_SECRET` | chave HMAC (mín. 32 caracteres) |
| `MAIL_HOST`, `MAIL_PORT`, `MAIL_USERNAME`, `MAIL_PASSWORD` | SMTP (Mailtrap no `dev`) |

OpenAPI: http://localhost:8080/swagger-ui/index.html

## Testes

```bash
./gradlew test
```
