package org.serjlemast.dourssproducer.infrastructure.redis;

import lombok.RequiredArgsConstructor;
import org.serjlemast.dourssproducer.domain.JobVacancy;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class JobEventPublisher {

    private final StringRedisTemplate redis;

    public void publish(JobVacancy job) {
        Map<String, String> body = Map.of(
            "id", job.getGuid(),
            "title", job.getTitle(),
            "link", job.getLink(),
            "description", job.getDescription(),
            "publishedAt", job.getPublishedAt().toString()
        );

        MapRecord<String, String, String> record = MapRecord.create("stream:dou.jobs", body);

        redis.opsForStream()
            .add(record);
    }
}