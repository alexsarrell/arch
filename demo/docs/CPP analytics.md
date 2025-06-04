# CPP analytics

## Document Description
В данном документе представлены модели аналитики компонента CPP

## List of Models

### IncomingCallEvent - Ивент аналитики, отправляемый по звонку
#### Current Version: 1.0.0

| Property Name | Type | Description | Required | Example Value | Version |
|---------------|------|-------------|----------|---------------|---------|
| sessionId | String | ID сессии звонка | true |  | 1.0.0 |
| activeVoice | String | Выбранный голос ассистента | false |  | 1.0.0 |
| offerId | String | ID оффера | false |  | 1.0.0 |
| callStartTime | Instant |  | true |  | 1.0.0 |
| callEndTime | Instant |  | true |  | 1.0.0 |
| playedAudio | List&lt;String&gt; |  | true |  | 1.0.0 |
| hangUpType | String |  | true |  | 1.0.0 |
| callType | String |  | true |  | 1.0.0 |
| callDuration | CallDuration |  | true |  | 1.0.0 |
| featureInfo | Map&lt;String, Any&gt; |  | false |  | 1.0.0 |

### JSON Example
```json
{ 
    "sessionId": null,
    "activeVoice": null,
    "offerId": null,
    "callStartTime": null,
    "callEndTime": null,
    "playedAudio": null,
    "hangUpType": null,
    "callType": null,
    "callDuration": null,
    "featureInfo": null
}
```

### CallDuration - Класс для хранения длительности звонка
#### Current Version: 1.0.0

| Property Name | Type | Description | Required | Example Value | Version |
|---------------|------|-------------|----------|---------------|---------|
| billingCallDurationSec | Int |  | false |  | 1.0.0 |
| headerDurationMs | Long |  | true |  | 1.0.0 |
| audioDurationMs | Long |  | true |  | 1.0.0 |
| platformDurationMs | Long |  | true |  | 1.0.0 |

### JSON Example
```json
{ 
    "billingCallDurationSec": null,
    "headerDurationMs": null,
    "audioDurationMs": null,
    "platformDurationMs": null
}
```
