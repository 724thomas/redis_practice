package com.example.redis_practice;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
public class ExampleController {

    private final StringRedisTemplate stringRedisTemplate;

    public ExampleController(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @GetMapping("/set")
    public void set() {
        stringRedisTemplate.opsForValue().set("key", "value");
    }

    @GetMapping("/set")
    public void set(int duration) {
        stringRedisTemplate.opsForValue().set("key", "value", Duration.ofMinutes(duration));
    }

    @GetMapping("/setifabsent")
    public void setIfAbsent() {
        stringRedisTemplate.opsForValue().setIfAbsent("key", "value");
    }

    @GetMapping("/get")
    public String get() {
        return stringRedisTemplate.opsForValue().get("key");
    }
}
