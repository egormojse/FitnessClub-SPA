package ega.spring.FitnessClub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // Метод для получения всех сессионных ключей
    @GetMapping("/all")
    public Set<String> getAllSessionKeys() {
        // Возвращает все ключи сессий
        return redisTemplate.keys("spring:session:sessions:*");
    }

    // Метод для получения времени жизни конкретной сессии
    @GetMapping("/ttl/{sessionId}")
    public String getSessionTTL(@PathVariable String sessionId) {
        String key = "spring:session:sessions:" + sessionId;

        Long ttl = redisTemplate.getExpire(key);  // Получить TTL для ключа

        if (ttl == null) {
            return "Session not found or has expired";
        }
        return ttl > 0 ? "Time to live for session " + sessionId + " is " + ttl + " seconds" : "Session is persistent or already expired";
    }
}
