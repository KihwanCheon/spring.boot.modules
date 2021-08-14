package example.listener.rabbit;

import example.QueueNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleListener {

    @RabbitListener(queues = QueueNames.Simple)
    public void simple_1(String message) {
        log.info("simple_1 message: {}", message);
    }

    @RabbitListener(queues = QueueNames.Simple)
    public void simple_2(String message) {
        log.info("simple_2 message: {}", message);
    }
}
