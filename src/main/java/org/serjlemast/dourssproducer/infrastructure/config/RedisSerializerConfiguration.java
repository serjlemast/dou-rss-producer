package org.serjlemast.dourssproducer.infrastructure.config;

import org.serjlemast.dourssproducer.domain.JobVacancy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.*;

@Configuration
public class RedisSerializerConfiguration {

  @Bean
  public StringRedisSerializer stringRedisSerializer() {
    return new StringRedisSerializer();
  }

  @Bean
  public RedisSerializer<JobVacancy> jobRedisSerializer() {
    return new JacksonJsonRedisSerializer<>(JobVacancy.class);
  }
}
