# ai-chat-middleware

En Spring Boot-applikation som fungerar som en middleware mellan en användare och en AI-modell via OpenRouter. Användaren skickar en fråga med en vald personlighet och applikationen hanterar konversationshistorik och vidarebefordrar anropet till AI:n.

## Förutsättningar

- Java 21
- Maven
- Ett konto på [OpenRouter](https://openrouter.ai) och en API-nyckel

## Kom igång

### 1. Klona projektet

```bash
git clone https://github.com/lindaeskilsson/ai-chat-middleware.git
cd ai-chat-middleware
```

### 2. Konfigurera miljövariabler

Kopiera exempelfilen och fyll i dina egna värden:

```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

Öppna `application.properties` och fyll i:

```properties
openrouter.api.key=DIN-NYCKEL-HÄR
openrouter.api.url=https://openrouter.ai/api/v1/chat/completions
openrouter.model=MODELL-HÄR
```

### 3. Starta applikationen

På macOS/Linux:
```bash
./mvnw spring-boot:run
```

På Windows:
```bash
mvnw.cmd spring-boot:run
```

Applikationen startar på `http://localhost:8080`. Det finns ingen startsida – appen är ett REST API och används via insomnia eller Postman mot endpointen nedan.

## Använda API:et

Skicka en POST-request till `/api/v1/chat`:

```bash
curl -X POST http://localhost:8080/api/v1/chat \
  -H "Content-Type: application/json" \
  -d '{"personality":"pirate","message":"Hej! Vad heter du?","sessionId":"123"}'
```

### Request body

| Fält | Typ | Beskrivning | Obligatorisk |
|---|---|---|---|
| personality | String | Styr AI:ns personlighet | Ja |
| message | String | Användarens fråga | Ja |
| sessionId | String | Håller isär konversationer | Nej |

### Tillgängliga personligheter

| Personlighet | Beskrivning |
|---|---|
| `helper` | Vänlig och hjälpsam assistent |
| `pirate` | Svarar som en pirat |
| `coder` | Expert på programmering |

### Exempelsvar

```json
{
  "reply": "Arrr! Jag är kapten av haven, matey!"
}
```

### Testa minnet

Skicka två meddelanden med samma `sessionId` för att testa konversationshistoriken:

```bash
curl -X POST http://localhost:8080/api/v1/chat \
  -H "Content-Type: application/json" \
  -d '{"personality":"helper","message":"Mitt namn är Linda.","sessionId":"abc"}'

curl -X POST http://localhost:8080/api/v1/chat \
  -H "Content-Type: application/json" \
  -d '{"personality":"helper","message":"Vad heter jag?","sessionId":"abc"}'
```

AI:n ska komma ihåg att du heter Linda i det andra svaret.
