# Activité Pratique N°2 — Mise en œuvre d'une architecture micro-services

**Module :** Systèmes Distribués Agentiques — Pr. Mohamed YOUSSFI
**Formation :** Master SDIA — ENSET Mohammedia — 2026-2027
**Réalisé par :** Taoufik KEHAL

## Objectif

Créer une application basée sur une architecture micro-services permettant de gérer des **factures**
contenant des **produits** et appartenant à un **client**, puis rendre cette architecture **agentique**
en y connectant un agent IA conversationnel via le protocole MCP.

## Architecture

```
          Clients : Angular, Discord, Telegram, agent IA
                              │
                              ▼
                   ┌─────────────────────┐        ┌──────────────────────┐
                   │   gateway-service   │◄──────►│  discovery-service   │
                   │  Spring Cloud       │        │  Eureka              │
                   │  Gateway (WebFlux)  │        └──────────────────────┘
                   └──────────┬──────────┘                   ▲
                              │ lb://                        │ enregistrement
          ┌───────────────────┼────────────────────┐         │
          ▼                   ▼                    ▼         │
   customer-service    inventory-service    billing-service ─┘
     (clients)           (produits)          (factures)
                                                 │ OpenFeign
                                                 └──► customer-service, inventory-service

          Tous les services lisent leur configuration depuis config-service (dépôt Git)
```

Chaque micro-service métier possède **sa propre base de données**. Le service de facturation ne stocke
que les identifiants du client et des produits, et récupère leurs détails auprès des services concernés
grâce à OpenFeign.

## Services

| Service | Port | Rôle |
|---|---|---|
| discovery-service | 8761 | Annuaire des services (Eureka) |
| gateway-service | 8888 | Point d'entrée unique, routage et répartition de charge |
| customer-service | 8081 | Gestion des clients |
| inventory-service | 8082 | Gestion des produits |
| billing-service | 8083 | Gestion des factures (OpenFeign) |
| config-service | 9999 | Configuration centralisée (Spring Cloud Config) |

## Travail demandé

1. Créer le micro-service `customer-service` qui permet de gérer les clients
2. Créer le micro-service `inventory-service` qui permet de gérer les produits
3. Créer la Gateway Spring Cloud Gateway
4. Configurer de manière statique le système de routage
5. Créer l'annuaire Eureka Discovery Service
6. Configurer de manière dynamique les routes de la Gateway
7. Créer le service de facturation `billing-service` en utilisant OpenFeign
8. Créer le service de configuration
9. Rendre l'architecture agentique en créant un agent IA conversationnel connecté aux micro-services via MCP
10. Créer des clients Discord et Telegram pour l'agent IA
11. Créer un client Angular

## Technologies

- Java 21, Spring Boot 4.1.2, Spring Cloud 2025.1.4
- Spring Data JPA, Spring Data REST, H2
- Spring Cloud Gateway (WebFlux), Netflix Eureka, OpenFeign, Spring Cloud Config
- Spring AI, Model Context Protocol (MCP)
- Angular
- Projet Maven multi-modules

## Lancement

Démarrer les services dans cet ordre :

1. `discovery-service`, puis vérifier l'annuaire sur http://localhost:8761
2. `config-service`
3. `customer-service`, `inventory-service`, `billing-service`
4. `gateway-service`

Chaque service se lance depuis son dossier avec :

```bash
./mvnw spring-boot:run
```

Les services sont ensuite accessibles via la Gateway, par exemple :
- http://localhost:8888/customers
- http://localhost:8888/products
- http://localhost:8888/customer-service/customers (route dynamique)

## Remarques techniques

- **Gateway réactive** : Spring Initializr propose par défaut la variante Spring MVC de la Gateway ;
  la variante WebFlux (serveur Netty) a été retenue, conformément au cours.
- **En-têtes `X-Forwarded`** : depuis la correction de la CVE-2025-41235, la Gateway ne les transmet plus par défaut.
  Ils sont réactivés avec `trusted-proxies`, et les services les lisent grâce à `server.forward-headers-strategy=framework`,
  pour que les liens HAL générés pointent vers la Gateway et non vers l'adresse interne des services.