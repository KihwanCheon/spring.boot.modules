package example.redis.controller;

import example.redis.publisher.RedisMessagePublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("pub")
public class PublishController {

    @Resource RedisMessagePublisher publisher;

    @GetMapping(value = "redis/{message}")
    Object pubRedis(@PathVariable String message) {
        publisher.publish(message);
        return true;
    }
}
