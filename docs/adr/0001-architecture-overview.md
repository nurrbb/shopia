# ADR-001: Hexagonal Mimari ve 6 Servisli Yapı

## Durum
Kabul edildi

## Karar
- **6 Servis ve Hexagonal Mimari:** Catalog, Identity, Cart, Order, Payment ve Notification servisleri domain katmanı framework bağımsız kalacak şekilde tasarlanır.
- **Eureka ve Config Server Yok:** Servis keşfi Docker DNS ile, yapılandırma ortam değişkenleri (environment variables) ile yönetilir.
- **Outbox ve Saga:** Sipariş ve ödeme süreçlerinde veri tutarlılığı için Transactional Outbox ve event tabanlı Saga (choreography) kullanılır.

## Sonuçlar
- İş kuralları framework'ten bağımsız olarak test edilebilir ve servisler birbirine sıkı bağımlı (tightly coupled) hale gelmez.
- Ekstra katman (port/adapter) yapısı ve dağıtık akış takibi için merkezi gözlemlenebilirlik (observability) kurulumu gerekir.
