# newMock 🛠️

![](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

Заглушка на Java Spring

На входе получает json с номером клиента (clientId) и номером счета(account)

```
{
	"clientId": "9050000000000000000",
	"account": "30500000000000000001"
	}
```

в ответ будет выдавать json с информацией по валюте счета(currency), балансу(balance) и максимальному лимиту(maxLimit).

```
{
    "rqUID": "58dgtf565j8547f64ke7",
    "clientId": "9050000000000000000",
    "account": "30500000000000000001",
    "currency": "EU",
    "balance": 404.57,
    "maxLimit": 1000
}
```

balance - любое рандомное число до максимального лимита.

Если номер клиента начинается с 8, то валюта счета (currency) доллар - US, максимальный лимит (maxLimit) - 2000.00

Если номер клиента начинается с 9, то валюта счета (currency) евро - EU, максимальный лимит (maxLimit) - 1000.00

Если начинается с любой другой цифры, то валюта счета (currency) рубль - RUB, максимальный лимит (maxLimit) - 10000.00
