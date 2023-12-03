# Clean Code Checklist (Java)

## 0. Vor Projektbeginn
- Mit Kollegen Formatierungsregeln festlegen und in IDE importieren
- Mit Kollegen Namenskonventionen bestimmen und festhalten (z.B. Werden deutsche Fachbegriffe übersetzt? Wie?)

## 1. Beim Erstellen neuer Methoden/Klassen
- Access modifiers passend gewählt
- Javadoc vorhanden
- Variabelnnamen sprechend ("userRepository" statt "ur")
- Keine magic numbers verwenden, wenn Konstanten benötigt werden mit static final definieren

## 2. Bei Commit
### IDE Code Analysis laufen lassen und Warnungen beheben:
- Nur in einer Method benutzte Variable zu lokaler Variable machen
- Neuere Java Features verwenden, wenn vorgeschlagen


### Manuell checken
- Unvollständiger und nicht genutzter Code löschen oder mit TODO annotieren
- Code nach Regeln formatieren 
- Klassen auf sich wiederholenden Code überprüfen und evtl. abstrahieren 



## 3. Vor jedem Release
- Code Coverage Prüfung
- Package names sinnvoll benannt, Klassen sinnvoll in Packages?
- Dependency Check: Circular Dependencies? 
- Noch nicht erledigte TODOs in Tickets fassen
- 

