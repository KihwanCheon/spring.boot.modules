package example.kafka.controller;

import example.kafka.Foo1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("pub")
public class PublishController {
    @Resource private KafkaTemplate<Object, Object> template;

    @GetMapping(value = "kafka/{message}")
    Object pubKafka(@PathVariable String message) {
        template.send("topic1", new Foo1(message));
        return true;
    }

    @KafkaListener(id = "dltGroup", topics = "topic1.DLT")
    public void dltListen(Foo1 in) {
        log.info("Received from DLT: " + in);
    }
}
