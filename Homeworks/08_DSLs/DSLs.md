## Interne DSL
Ein Beispiel für eine interne DSL als Fluent Interface können Sie in der Klasse
[src/main/java/org/juheinz/entities/StaffDeployment.java](https://github.com/JuHeinz/Moderne_Softwareentwicklung/blob/877e423f61195dd08a5ac013e37c9b19b44a9280/src/main/java/org/juheinz/entities/StaffDeployment.java) finden.
Die Klasse wird in [src/main/java/org/juheinz/main/DeliverySimulator.java](https://github.com/JuHeinz/Moderne_Softwareentwicklung/blob/877e423f61195dd08a5ac013e37c9b19b44a9280/src/main/java/org/juheinz/main/DeliverySimulator.java) genutzt.

## Externe DSL
In der Klasse [src/main/java/org/juheinz/scanner/StaffDslTranslator.java](https://github.com/JuHeinz/Moderne_Softwareentwicklung/blob/877e423f61195dd08a5ac013e37c9b19b44a9280/src/main/java/org/juheinz/scanner/StaffDslTranslator.java) finden Sie eine einfache externe DSL mit Parser,
die mit String Manipulation arbeitet.
Sie ermöglicht es Zustellern vor oder während der Liefertour Statusnachrichten an die Zentrale zu übermitteln und entsprechende Antworten zu erhalten.

Beispieleingaben finden Sie unter [src/main/java/org/juheinz/scanner/StaffDslListener](https://github.com/JuHeinz/Moderne_Softwareentwicklung/blob/877e423f61195dd08a5ac013e37c9b19b44a9280/src/main/java/org/juheinz/scanner/StaffDslListener.java).

Den Input und Output können Sie zur Runtime in der Konsole einsehen.
