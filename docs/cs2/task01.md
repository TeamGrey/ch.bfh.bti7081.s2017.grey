# Elaborate Use Cases

## Potential Users and common use cases
- People with diabetes (patients)
- Doctors (they get metrics from the PIP and supervise that it is working correctly)
- Technicians (they get hardware metrics, without personal information, to ensure the device isnt't malfunctioning)

## Use case diagram and system boundaries
- [use case diagram for editing](https://yuml.me/)
  Paste this 
```
[Patient]-(Wear PIP)
(Wear PIP)>(Go to doctor when malfunctioning)
(Wear PIP)>(Go to doctor for refills)
(Wear PIP)>(Go to technician when broken)
(Wear PIP)<(Alarm when patient should eat)
[Doctor]-(Administer higher dose)
[Doctor]-(Administer lower dose)
[Doctor]-(Refill Insulin)
(Administer higher dose)<(Monitor Injections)
(Administer lower dose)<(Monitor Injections)
(Refill Insulin)<(Monitor Insulin levels)
[Technician]-(Fix hardware)
(Fix hardware)<(Monitor hardware)
```
- ![diagram](http://yuml.me/af953538)

## Detailed use cases and scenarios
