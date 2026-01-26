# DOU RSS Producer

A Spring Boot application that collects job vacancies from DOU.ua RSS feeds, deduplicates them using Redis, and publishes new job postings to a Redis stream for further processing.

## Overview

This application periodically fetches job vacancy data from DOU.ua (a Ukrainian IT job board) RSS feeds and publishes new job postings to a Redis stream. It uses Redis sets to track processed jobs and avoid duplicate publications.

## Features

- **RSS Feed Processing**: Reads job vacancies from configurable RSS feeds
- **Deduplication**: Uses Redis sets to track processed job GUIDs
- **Event Publishing**: Publishes new jobs to Redis streams for downstream consumers
- **Scheduled Execution**: Configurable cron-based scheduling
- **Docker Support**: Containerized deployment with multi-stage builds
- **Virtual Threads**: Utilizes Spring Boot's virtual thread support for efficient concurrency

## Architecture

The application follows Clean Architecture principles with the following layers:

- **Domain**: `JobVacancy` entity representing job posting data
- **Application**: Use cases and scheduled jobs
- **Infrastructure**: External integrations (Redis, RSS feeds)

### Key Components

- `CollectJobsUseCase`: Core business logic for collecting and processing jobs
- `DailyJobCollector`: Scheduled job that triggers collection
- `RssFeedReader`: RSS feed parsing using Rome library
- `JobEventPublisher`: Redis stream publisher

## Configuration

The application supports configuration via environment variables:

| Variable | Default | Description |
|----------|---------|-------------|
| `PORT` | `8080` | Server port |
| `CRON_JOB` | `0 0 */6 * * *` | Cron expression for job collection (every 6 hours) |
| `RSS_URL` | DOU QA jobs feed | RSS feed URL to monitor |
| `REDIS_HOST` | `localhost` | Redis server host |
| `REDIS_PORT` | `6379` | Redis server port |
| `REDIS_USERNAME` | (empty) | Redis username |
| `REDIS_PASSWORD` | (empty) | Redis password |
| `REDIS_STREAM_NAME` | `stream:dou.jobs` | Redis stream name |
| `REDIS_STREAM_KEY` | `job` | Redis stream message key |
| `REDIS_SET_NAME` | `processed:jobs` | Redis set for tracking processed jobs |

## Prerequisites

- Java 25+
- Redis server
- Gradle (for building)

## Building

```bash
./gradlew clean build
```

## Running

### Local Development

1. Start Redis server
2. Set environment variables as needed
3. Run the application:

```bash
./gradlew bootRun
```

### Docker

Build and run with Docker:

```bash
docker build -t dou-rss-producer .
docker run -e REDIS_HOST=your-redis-host dou-rss-producer
```

## API Endpoints

The application exposes Spring Boot Actuator endpoints:

- `GET /actuator/health` - Health check
- `GET /actuator/info` - Application info

## Monitoring

The application includes Spring Boot Actuator for monitoring and health checks. Configure your monitoring system to check the `/actuator/health` endpoint.

## Dependencies

- Spring Boot 4.0.2
- Spring Data Redis
- Rome RSS library
- Lombok
- Spring Boot Actuator

## Development

### Project Structure

```
src/main/java/org/serjlemast/dourssproducer/
├── application/
│   ├── scheduler/
│   │   └── DailyJobCollector.java
│   └── usecase/
│       └── CollectJobsUseCase.java
├── domain/
│   └── JobVacancy.java
├── infrastructure/
│   ├── config/
│   │   ├── RedisSerializerConfiguration.java
│   │   ├── RedisTemplateConfiguration.java
│   │   └── SchedulerConfig.java
│   ├── redis/
│   │   └── JobEventPublisher.java
│   └── rss/
│       └── RssFeedReader.java
└── DouRssProducerApplication.java
```

### Testing

Run tests:

```bash
./gradlew test
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is licensed under the MIT License.</content>
<parameter name="filePath">/Users/dimitriimaksymov/IdeaProjects/dou-rss-producer/README.md