package com.example.redis_practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/set")
    public ResponseEntity<Void> setValue(@RequestParam String key, @RequestParam String value) {
        stringRedisTemplate.opsForValue().set(key, value);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    public ResponseEntity<String> getValue(@RequestParam String key) {
        String value = stringRedisTemplate.opsForValue().get(key);
        return ResponseEntity.ok(value);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateValue(@RequestParam String key, @RequestParam String value) {
        Boolean exists = stringRedisTemplate.hasKey(key);
        if (exists) {
            stringRedisTemplate.opsForValue().set(key, value);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteKey(@RequestParam String key) {
        stringRedisTemplate.delete(key);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/keys")
    public ResponseEntity<Set<String>> getAllKeys() {
        Set<String> keys = stringRedisTemplate.keys("*");
        return ResponseEntity.ok(keys);
    }

    @GetMapping("/values")
    public ResponseEntity<List<String>> getAllValues() {
        Set<String> keys = stringRedisTemplate.keys("*");
        List<String> values = stringRedisTemplate.opsForValue().multiGet(keys);
        return ResponseEntity.ok(values);
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> keyExists(@RequestParam String key) {
        Boolean exists = stringRedisTemplate.hasKey(key);
        return ResponseEntity.ok(exists);
    }
}