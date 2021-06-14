# Alelo Frota App
Seguindo os principios de arquitetura limpa à api foi construída com módulos , sendo estes:

- alelo-frota-core - Contém o core da aplicação totalmente desacoplada do outro módulo, com isso não ficamos preso a algum framework.
- alelo-frota-app - Contém à aplicação usando o Spring essa por sua vez usa o alelo-frota-core como depedência em seu pom.xml

## Docker
Aplicação totalmente rodando com containers (Spring e Mongo até o momento) Docker, para subir à aplicação simplesmente tenha o docker e docker-compose instalado e execute o seguinte comando: 
```sh
docker-compose up
```
a API subirá na porta 8080 juntamente com o MongoDB , fornecendo os endpoints com os verbos HTTP adequados para cada ação, conforme a especificação da API:

```sh
http://localhost:8080/alelo-frota-api/vehicle?page=1&limit=10 Lista veiculos paginados

http://localhost:8080/alelo-frota-api/vehicle?filter=ABC4852 Busca veículo pela placa

http://localhost:8080/alelo-frota-api/vehicle?filter=true Lista veículos pelo status

http://localhost:8080/alelo-frota-api/vehicle/:id Busca um veículo específico

http://localhost:8080/alelo-frota-api/vehicle Cria um novo veículo

http://localhost:8080/alelo-frota-api/vehicle/:id Atualiza um veículo

http://localhost:8080/alelo-frota-api/vehicle/:id Remove um veículo
```


