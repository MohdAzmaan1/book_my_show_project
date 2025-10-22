package com.example.BookMyShow.Config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://redis-19994.crce206.ap-south-1-1.ec2.redns.redis-cloud.com:19994")
                .setPassword("8FTaFPkvKAAr4nBF7b1SrEsP2b0GNoFp");
        return Redisson.create(config);
    }
}
