package org.serjlemast.dourssproducer.application.usecase;

import lombok.RequiredArgsConstructor;
import org.serjlemast.dourssproducer.infrastructure.redis.JobEventPublisher;
import org.serjlemast.dourssproducer.infrastructure.rss.RssFeedReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CollectJobsUseCase {

    private final RssFeedReader rssFeedReader;
    private final JobEventPublisher publisher;
    private final StringRedisTemplate redis;

    @Value("${redis.set.name}")
    private String setName;

    public void collect() {
        rssFeedReader.read().forEach(job -> {
            Long added = redis.opsForSet()
                    .add(setName, job.getGuid());

            if (added != null && added == 1) {
                publisher.publish(job);
            }
        });
    }
}