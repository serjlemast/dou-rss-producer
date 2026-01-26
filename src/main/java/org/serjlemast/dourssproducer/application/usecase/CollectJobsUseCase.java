package org.serjlemast.dourssproducer.application.usecase;

import lombok.RequiredArgsConstructor;
import org.serjlemast.dourssproducer.infrastructure.redis.JobEventPublisher;
import org.serjlemast.dourssproducer.infrastructure.rss.RssFeedReader;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CollectJobsUseCase {

    private final RssFeedReader rssFeedReader;
    private final JobEventPublisher publisher;
    private final StringRedisTemplate redis;

    public void collect() {
        rssFeedReader.read().forEach(job -> {
            Long added = redis.opsForSet()
                    .add("processed:jobs", job.getGuid());

            if (added != null && added == 1) {
                publisher.publish(job);
            }
        });
    }
}