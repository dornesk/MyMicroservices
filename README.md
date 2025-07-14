# Pet Project: Kafka + REST + 3 БД

## Техническое задание
Собрать демо-систему из 3 микросервисов с взаимодействием через Kafka и REST API.

### Цель
- Потрогать Kafka как шину событий
- Пощупать взаимодействие REST между сервисами
- Освоить работу с PostgreSQL, Redis, MongoDB
- Развернуть всё через Docker Compose

### Стек технологий
- Spring Boot (SLF4J для логов)
- Docker, Docker Compose
- Kafka, Kafka UI (Redpanda Console)
- MongoDB, Redis, PostgreSQL
- Liquibase (для миграций PostgreSQL)

### Микросервисы
#### Service A
- Слушает Kafka topic `ui.in`
- Сохраняет сообщение в MongoDB
- Вызывает REST API Service B (`POST /api/process`)

#### Service B
- Принимает REST-запросы от Service A
- Если `type == important`, сохраняет в Redis (TTL=5 мин)
- Вызывает Service C по REST (`POST /api/save`)

#### Service C
- Принимает REST-запросы от Service B
- Сохраняет сообщение в PostgreSQL
- Публикует сообщение в Kafka (topic `c.out`)

### Kafka Topics
| Topic    | Publisher   | Consumer         |
|----------|-------------|------------------|
| ui.in    | UI          | Service A        |
| c.out    | Service C   | UI (для просмотра)|

### Инструкция по запуску
1. Установить Docker и Docker Compose
2. Запустить проект:
```bash
docker-compose up --build
```
3. Адрес Kafka UI: http://localhost:8080
4. Отправить тестовое сообщение в Kafka через Kafka UI (`ui.in`)
5. Проверить обработку сообщений через Kafka UI (`c.out`) и базы данных

### Состав проекта
- `docker-compose.yml` — инфраструктура (Kafka, базы, UI)
- `service-a/`, `service-b/`, `service-c/` — код микросервисов
- `Dockerfile` в каждом сервисе