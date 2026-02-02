package org.serjlemast.dourssproducer.infrastructure.config;

import org.serjlemast.dourssproducer.domain.Item;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisTemplateConfiguration {

  @Bean
  public RedisTemplate<String, Item> redisTemplate(
      RedisConnectionFactory connectionFactory,
      StringRedisSerializer stringRedisSerializer,
      RedisSerializer<Item> jobRedisSerializer) {

    var template = new RedisTemplate<String, Item>();
    template.setConnectionFactory(connectionFactory);

    template.setKeySerializer(stringRedisSerializer);
    template.setValueSerializer(stringRedisSerializer);

    template.setHashKeySerializer(stringRedisSerializer);
    template.setHashValueSerializer(jobRedisSerializer);

    template.afterPropertiesSet();

    return template;
  }
}
