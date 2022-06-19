# Desacoplamento dos dados sensiveis de um usuário no banco de dados
A ideia desse projeto e demonstrar uma maneira de modelar um banco de dados e uma aplicaçao que seja desacoplada os dados
sensiveis de um usuario, ou seja, esses dados sensiveis nao trafegam nas funcionalidades principais do sistema e esses dados 
nao estao no mesmo registro do usuario, assim podemos manipular as informacoes do usuario sem visualizar ou alterar os dados
sensiveis do mesmo.

### Exemplo criação de usuário
```
curl --location --request POST 'localhost:8080/usuario' \
--header 'Content-Type: application/json' \
--data-raw '{
"username": "username",
"dadosSensiveis": {
"cpf": "cpf",
"email": "email"
}
}'
```
### Exemplo visualização de usuário SEM dados sensiveis
```
curl --location --request GET 'localhost:8080/usuario/1'
```
### Exemplo visualização de usuário COM dados sensiveis
#### Em uma aplicaçao real essa funcionalidade seria utilizada somente para o usuario das informaçoes sensiveis
#### Necessitando uma autenticaçao e autorizaçao para acessar esse endpoint
```
curl --location --request GET 'localhost:8080/usuario/dados/1'
```
### Exemplo deleção dos dados sensíveis de um usuário
#### O usuario pode optar por deletar todos os dados sensiveis da aplicaçao sem danificar as funcionalidades restantes
```
curl --location --request DELETE 'localhost:8080/usuario/dados/1'
```
### Exemplo de atualização do registro do usuário com dados sensiveis
#### Depois de uma deleçao o usuario pode optar por alimentar novamente com os dados sensiveis
```
curl --location --request PUT 'localhost:8080/usuario' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": 1,
    "username": "username",
    "dadosSensiveis": {
        "cpf": "cpf",
        "email": "email"
    }
}'
```