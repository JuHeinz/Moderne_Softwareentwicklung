## Interne DSLs
Ein Beispiel für eine interne DSL als Fluent Interface können Sie in der Klasse
src/main/java/org/juheinz/entities/StaffDeployment.java finden. Die Klasse wird in src/main/java/org/juheinz/main/DeliverySimulator.java genutzt.


## Externe DSLs
In der Klasse src/main/java/org/juheinz/scanner/StaffDslTranslator finden sie eine einfache externe DSL die mit String Manipulation arbeitet.
Sie ermöglicht es Zustellern vor oder während der Liefertour Statusnachrichten an die Zentrale zu übermitteln und entsprechende Antworten zu erhalten.

Den Input und Output sehen sie in der Konsole.
