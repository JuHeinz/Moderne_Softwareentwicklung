# Microservices
Dies ist ein Beispiel für einen simplen Microservice.

## Funktionsweise
### A) Route Microservice
Der Python Microservice für die Routenführung berechtet die (Luft-)Route zwischen zwei GPS Koordinaten.

### B) Parcel Tracking App
Diese Java Applikation managed die Verfolgung von Paketen.
Sie greift auf den Route Microservice via java.net.http Library zu um (fliegenden) Paketzustellern Navigation zu geben.

### C) API
Der Microservice und Parcel Tracking App kommunizieren via FastAPI (HTTP) miteinander. 

## Microservice starten
1. Python installieren
2. Uvicorn und FastAPI via pip installieren
3. In command prompt zu diesem Directory navigieren
4. `uvicorn microservice:app --reload`
 
Der Microservice läuft auf einem von uvicorn erzeugten Server auf dem lokalen Gerät.
Eine Übersicht der Methoden kann unter http://127.0.0.1:8000/docs# eingesehen werden.



