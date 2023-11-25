# Metriken Erfahrungen
Untersucht wurden zwei Projekte. A) "Pattern Hero": Ein Guitar Hero Clone in Java (Semesterprojekt aus Modul "Patterns und Frameworks) und B) ein Java-Mockup zu einem Package Tracking Service welches für diese Veranstaltung geschrieben wurde (in diesem Projekt unter "src").
Beide Programme sind recht kurz, ich wollte aber Projekte verwenden, in denen ich mich gut auskenne, weil ich sie selbst geschrieben habe. 
Als Tools wurden Metrics Reloaded als Plugin für IntelliJ und 

## Lines of code
Laut Script sollen Kommentare zwischen 30 % - 60 % der LOC (lines of code) ausmachen. 

### Metrics Reloaded Plugin für IntelliJ
Das Tracking-Projekt hat laut Metrics Reloaded 25 CLOC (Comment lines of code) von insgesamt 443 LOC. Das entspricht nur 5%. 
Allerdings gibt das Plugin auch die Javadoc lines of code an (13) hier ist auf erstem Blick unklar, ob diese auch zu den CLOC gezählt werden. Schaut man sich die Metric NCLOC (Non-comment lines of code) an, so sieht man, das JavaDoc wohl nicht zu den CLOC gezählt werden. 
![img.png](img.png)
Bei der Analyse von diesem Projekt musste ich darauf achten, einen richtigen Scope auszuwählen, der nur das Java Projekt (also den src Folder) und nicht auch Dokumente wie dieses beinhalten.  

Das Pattern Hero Projekt hat laut dem Plugin 133 CLOC und 54 JLOC von insgesamt 1577 LOC. Das entspricht einem Kommentaranteil von ca. 8%. 
![img_1.png](img_1.png)

Schade bei diesem Tool ist, dass man die Prozentzahl der jeweiligen Kategorien selbst ausrechnen muss und nicht sofort ersichtlich ist, wie die Kategorien genau zu Stande kamen. 

### Tool 2

### Fazit Lines of Code
Rechnet man die Anzahl der Kommentarzeilen aus, entsprechen beide Projekte also nicht dem Best-Practice Anteil, auch nicht, wenn man Javadoc als Kommentare zählt. Dies überrascht mich, denn im Vergleich zu meinen Kollegen auf der Arbeit habe ich das Gefühl, dass ich sehr viele Kommentare schreibe. Nun stellt sich heraus, dass ich mindestens vier mal so viele Kommentare schreiben muss, um der unteren Grenze der Empfehlung zu entsprechen.   