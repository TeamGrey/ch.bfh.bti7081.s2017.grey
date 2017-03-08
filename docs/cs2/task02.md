# Activity diagrams

## Activity diagram for use case *\#2 Alarm*

[use case diagram for editing](http://yuml.me/815e895d)
  Paste this
```
(start)->(Monitor)->[Alarm data]->(Evaluate data)-><a>[System error]->(Alarm for system error)->(end),<a>[Low blood sugar]->(Alarm for low blood sugar)->(end),<a>[Low insulin level]->(Alarm for insulin refill)->(end)
```
![diagram](http://yuml.me/815e895d)

## Activity diagram for use case *\#2 Maintain* Hardware

Paste this

```
(start)->(Wechsel zu Maintenance-Mode)->(Logs auslesen)->(Logs auswerten)-><a>,<a>[ok]->(Logs reseten)->(Wechseln in den Normal-User-Modus)->(end),<a>[Technischer Fehler entdeckt]->(PIP Ausschalten)->(Hardware auswechseln/fixen)->(PIP einschalten)->(PIP checkenen auf Funktionstuechtigkeit)->(PIP freigeben)->(Logs reseten)
```

![diagram](http://yuml.me/bfdaf3ff)