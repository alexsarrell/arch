# model-generator

```yaml
schemas:
  AnalyticsDocument:
    description: Class for analytics holding
    type: data
    properties:
      name:
        type: String
        description: Name of the event
        since: 1.0.0
      accountId:
        type: String
        format: uuid
        description: ID of the event
        since: 1.0.0
      metadata:
        required: true
        type: String
        description: Metadata of the event
        default: emptyMap<String, Any>
        since: 1.0.0
    version: 2.0.0
  
  AnalyticsEvent:
    description: Base analytics class
    type: data
    properties: {}
    version: 2.0.0

  IncomingCallEvent:
    description: Class for call analytics
    type: class
    properties: {}
    version: 2.0.0
```

### TODO
Придумать, как будем конфигурировать аннотации