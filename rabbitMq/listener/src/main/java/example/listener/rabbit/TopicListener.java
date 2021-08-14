package example.listener.rabbit;

import example.QueueNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TopicListener {

    @RabbitListener(queues = QueueNames.Topic1)
    public void topic1_1(String message) {
        log.info("topic1_1 message: {}", message);
    }

    @RabbitListener(queues = QueueNames.Topic1)
    public void topic1_2(String message) {
        log.info("topic1_2 message: {}", message);
    }

    @RabbitListener(queues = QueueNames.Topic2)
    public void topic2_1(String message) {
        log.info("topic2_1 message: {}", message);
    }

    @RabbitListener(queues = QueueNames.Topic2)
    public void topic2_2(String message) {
        log.info("topic2_2 message: {}", message);
    }
}
