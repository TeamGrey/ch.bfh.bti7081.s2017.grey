# Elaborate Use Cases

## Potential Users and common use cases
- People with diabetes (patients)
- Doctors (they get metrics from the PIP and supervise that it is working correctly)
- Technicians (they get hardware metrics, without personal information, to ensure the device isnt't malfunctioning)

## Use case diagram and system boundaries
- [use case diagram for editing](https://yuml.me/edit)
  Paste this 
```
[Patient]-(Wear PIP)
[Doctor]-(Administer higher dose)
(Administer higher dose)<(Monitor Injections)
(Administer lower dose)<(Monitor Injections)
[Technician]-(Fix hardware)
(Fix hardware)<(Monitor hardware)
```
- ![diagram](http://yuml.me/e0ef32e7)

## Detailed use cases and scenarios
