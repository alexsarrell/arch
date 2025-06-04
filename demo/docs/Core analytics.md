# Core analytics

## Document Description
В данном документе представлены основные абстрактные модели аналитики

## List of Models

### AnalyticsDocument - Class for analytics holding
#### Current Version: 2.0.0

| Property Name | Type | Description | Required | Example Value | Version |
|---------------|------|-------------|----------|---------------|---------|
| name | String | Name of the event | true | helloWorld | 1.0.0 |
| accountId | UUID | ID of the event | true |  | 1.0.0 |
| metadata | Map&lt;String, Any&gt; | Metadata of the event | true |  | 1.0.0 |
| event | AnalyticsEvent | Body of analytics event | true |  | 1.0.0 |

### JSON Example
```json
{ 
    "name": "helloWorld",
    "accountId": null,
    "metadata": null,
    "event": null
}
```

### AnalyticsEvent - Abstract event class example
#### Current Version: 1.0.0

| Property Name | Type | Description | Required | Example Value | Version |
|---------------|------|-------------|----------|---------------|---------|
| sessionId | UUID | ID of a session | true |  | 1.0.0 |

### JSON Example
```json
{ 
    "sessionId": null
}
```

### ChildEvent - Child event class example
#### Current Version: 1.0.0

| Property Name | Type | Description | Required | Example Value | Version |
|---------------|------|-------------|----------|---------------|---------|
| childProperty | Instant | Example child property description | true |  | 1.0.0 |

### JSON Example
```json
{ 
    "childProperty": null
}
```
