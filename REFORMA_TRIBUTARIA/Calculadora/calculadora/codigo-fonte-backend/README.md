## Execução do projeto

./mvnw spring-boot:run -Dspring-boot.run.profiles=offline

ou

java -jar api-regime-geral.jar --spring.profiles.active=offline

## Execução dos Testes

./mvnw clean verify

Para um relatório da cobertura de testes, abrir o seguinte arquivo no navegador:

target/site/jacoco/index.html

## Carga com o Flyway

bash migrate.sh