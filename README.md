# Architektura Mikroserwisowa

System oparty o architekture mikroserwisowa uruchamiany za pomoca Docker i Docker Compose.

## Architektura systemu

```
Frontend (HTML/Nginx :3000)
    |
    v
Gateway (Spring Cloud Gateway :8080)
    |
    v  (Eureka Service Discovery :8761)
    |
Product Service (Spring Boot :8081)
    |
    v
PostgreSQL (:5432)
```

## Komponenty

| Komponent | Technologia | Port | Opis |
|-----------|------------|------|------|
| **PostgreSQL** | PostgreSQL 16 | 5432 | Baza danych z tabela `products` |
| **Discovery Server** | Spring Cloud Netflix Eureka | 8761 | Service Discovery |
| **Product Service** | Spring Boot + Spring Data JPA | 8081 | Mikroserwis biznesowy (CRUD produktow) |
| **Gateway** | Spring Cloud Gateway | 8080 | API Gateway |
| **Frontend** | HTML + JavaScript + Nginx | 3000 | Interfejs uzytkownika |

## Wymagania

- Docker
- Docker Compose

## Uruchomienie

```bash
cd architektura
docker compose up --build
```

Pierwsze uruchomienie moze potrwac kilka minut (pobieranie zaleznosci Maven).

## Uzywanie

1. Otworz przegladarke: **http://localhost:3000**
2. Zaloguj sie danymi:
   - **Login:** `admin`
   - **Haslo:** `admin123`
3. Po zalogowaniu zobaczysz tabele z produktami pobranymi z bazy PostgreSQL

## Eureka Dashboard

Panel Eureka Service Discovery: **http://localhost:8761**

## Endpointy API

- `GET /api/products` — lista wszystkich produktow
- `GET /api/products/{id}` — produkt po ID (`findById`)
- `POST /api/products` — dodanie nowego produktu

Wszystkie endpointy wymagaja uwierzytelnienia Basic Auth (`admin:admin123`).

## Dane testowe

Przy pierwszym uruchomieniu do bazy zostanie zaladowanych 5 przykladowych produktow:

| Nazwa | Cena | Ilosc |
|-------|------|-------|
| Laptop Dell XPS 15 | 5499.99 PLN | 10 |
| iPhone 15 Pro | 5999.00 PLN | 25 |
| Samsung Galaxy S24 | 4299.00 PLN | 30 |
| Sony WH-1000XM5 | 1499.00 PLN | 50 |
| Logitech MX Master 3S | 449.99 PLN | 100 |

## Struktura projektu

```
architektura/
├── docker-compose.yml
├── README.md
├── discovery-server/          # Eureka Server
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/
├── product-service/           # Mikroserwis biznesowy
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/
├── gateway/                   # API Gateway
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/
└── frontend/                  # Frontend HTML
    ├── Dockerfile
    ├── nginx.conf
    └── index.html
```

## Technologie

- **Java 17** + Spring Boot 3.3.5
- **Spring Cloud** 2023.0.3 (Eureka, Gateway)
- **Spring Data JPA** + Hibernate
- **Spring Security** (Basic Auth)
- **PostgreSQL 16**
- **Docker** + Docker Compose
- **Nginx** (serwer frontendu)
