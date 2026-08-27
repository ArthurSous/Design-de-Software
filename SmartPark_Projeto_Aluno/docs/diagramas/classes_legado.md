# Visão parcial do legado

```mermaid
classDiagram
 class SmartParkService
 class ParkingSpot
 class Vehicle
 class ParkingSession
 class SensorLegacyApi
 class PaymentLegacyGateway
 SmartParkService --> ParkingSpot
 SmartParkService --> Vehicle
 SmartParkService --> ParkingSession
 SmartParkService --> SensorLegacyApi
 SmartParkService --> PaymentLegacyGateway
```
