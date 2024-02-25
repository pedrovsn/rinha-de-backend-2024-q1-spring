# Rinha de Backend - 2024/Q1

Submissão em Java + Spring para a Rinha de Backend 2024/Q1

## Tecnologias Utilizadas

![Java](https://img.shields.io/badge/Java-GraalVM-orange)                                                              
![Spring](https://img.shields.io/badge/Spring-6DB33F.svg?&style=for-the-badge&logo=spring&logoColor=white)             
![Maven](https://img.shields.io/badge/Apache_Maven-C71A36.svg?&style=for-the-badge&logo=apache-maven&logoColor=white)  
![Nginx](https://img.shields.io/badge/nginx-009639.svg?&style=for-the-badge&logo=nginx&logoColor=white)                
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192.svg?&style=for-the-badge&logo=postgresql&logoColor=white) 
![Docker](https://img.shields.io/badge/Docker-2496ED.svg?&style=for-the-badge&logo=docker&logoColor=white)             
![ChatGPT](https://img.shields.io/badge/ChatGPT-OpenAI-blueviolet)

Configurações do NGINX by ChatGPT e do Postgres copiadas da submissão do [Gustavo Novaes](https://github.com/gustavonovaes/rinha-backend-2024-go/tree/main)

## Pré-requisitos

- GraalVM CE 17
- Maven 3.6
- Postgres 16
- NGINX 1.25

## Configuração


## Como Rodar

### Local
```bash
mvn spring-boot:run -Dspring.profiles.active=local
```

### Docker
```bash
docker-compose up -d
```

## Como executar o teste de carga

```bash 
./executar-teste-local.sh
```

TODO