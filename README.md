# In a galaxy far, far away

Para a execução do programa, é necessário utilizar o seguinte comando:

> java -jar resources-sharing-0.0.1-SNAPSHOT.jar

Existem cinco endpoints expostos nessa aplicação.

+ Para um Rebelde se juntar a aliança

```
POST localhost:8080/resources-sharing/join

{
    "nome": "Obi Wan Kinobi",
    "idade": 21,
    "genero": "Jedi",
    "arma": 1,
    "municao": 0,
    "agua": 2,
    "comida": 3,
    "localizacao": {
        "nome": "Aldebaran",
        "latitude": "12W50E",
        "longitude": "11C12E"
    }
}
 
RESPONSE: HTTP 201 (Created) 
```
+ Para um Rebelde migrar de base

```
PATCH localhost:8080/resources-sharing/migrate-base
 
{
    "nome": "Obi Wan Kinobi",
    "localizacao": {
        "nome": "Aldebaran",
        "latitude": "12W50E",
        "longitude": "11C12E"
    }
}

RESPONSE: HTTP 200 (OK) 
```

+ Para reportar um Rebelde com ações suspeitas 

```
PATCH localhost:8080/resources-sharing/report
 
{
    "nome": "Obi Wan Kinobi"
}

RESPONSE: HTTP 200 (OK) 
```

+ Para obter informações dos Rebeldes e seus Inventários

```
GET localhost:8080/resources-sharing/reports

RESPONSE: HTTP 200 (OK) 
{
    "traidores": 0.0,
    "rebeldes": 1.0,
    "arma": 1,
    "municao": 0,
    "agua": 0,
    "comida": 3,
    "pontos_perdidos": 0
}
```

+ Para negociar itens entre dois Rebeldes 

```
PUT localhost:8080/resources-sharing/trade

{
    "rebeldes": [{
        "nome": "Obi W Kinobi",
        "arma": 1,
        "municao": 0,
        "agua": 0,
        "comida": 0
    },
    {
        "nome": "Obi X Kinobi",
        "arma": 0,
        "municao": 0,
        "agua": 2,
        "comida": 0
    }]
}

RESPONSE: HTTP 200 (OK) 
```

