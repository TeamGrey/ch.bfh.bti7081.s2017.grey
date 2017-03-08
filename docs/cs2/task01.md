# Elaborate Use Cases

## Potential Users and common use cases
- People with diabetes (patients)
- Doctors (they get metrics from the PIP and supervise that it is working correctly)
- Technicians (they get hardware metrics, without personal information, to ensure the device isnt't malfunctioning)

## Use case diagram and system boundaries
- [use case diagram for editing](http://yuml.me/edit/f6351879)
  Paste this 
```
[Patient]-(Wear PIP)
(Wear PIP)>(Monitor)
(Monitor)>(Alarm)
(Monitor)>(Inject)
(Monitor)>(Log)
(Alarm)<(Alarm when malfunction)
(Alarm)<(Alarm when supply low)
(Alarm)<(Alarm when user should eat)
[Doctor]-(Maintain)
(Maintain)<(Adjust parameters)
(Maintain)<(Refill insulin)
[Technician]-(Maintain hardware)
(Maintain hardware)<(fix hardware)
(Maintain hardware)<(check logs)
```
![diagram](http://yuml.me/f6351879)

## Detailed use cases and scenarios
