package example.listener.rabbit;

import example.QueueNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HeadersListener {

    @RabbitListener(queues = QueueNames.Headers1)
    public void headers1_1(Message message) {
        log.info("headers1_1 message: {}", message);
    }

    @RabbitListener(queues = QueueNames.Headers1)
    public void headers1_2(Message message) {
        log.info("headers1_2 message: {}", message);
    }

    @RabbitListener(queues = QueueNames.Headers2)
    public void headers2_1(Message message) {
        log.info("headers2_1 message: {}", message);
    }

    @RabbitListener(queues = QueueNames.Headers2)
    public void headers_2(Message message) {
        log.info("headers2_2 message: {}", message);
    }
}
