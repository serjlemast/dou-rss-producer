package org.serjlemast.dourssproducer.infrastructure.redis;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.serjlemast.dourssproducer.domain.JobVacancy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class JobEventPublisher {

  @Value("${redis.stream.name}")
  private String streamName;

  @Value("${redis.stream.key}")
  private String streamKey;

  private final RedisTemplate<String, JobVacancy> redisTemplate;

  public void publish(JobVacancy job) {
    var record = MapRecord.create(streamName, Map.of(streamKey, job));

    RecordId recordId = redisTemplate.opsForStream().add(record);
    log.info("Published recordId {} to redis", recordId);
  }
}