package example.listener.rabbit;

import example.QueueNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Argument;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Slf4j
@Component
public class SimpleQueueListener {

    @Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @RabbitListener(queuesToDeclare = {@Queue(name = QueueNames.Simple, durable = "false", autoDelete = "false",
            arguments = {@Argument(name = "x-message-ttl", value = "1000", type = "java.lang.Integer")
    })})
    public @interface DirectListenBinding {
    }

    @DirectListenBinding
    public void simple_1(String message) {
        log.info("simple_1 message: {}", message);
    }

    @DirectListenBinding
    public void simple_2(String message) {
        log.info("simple_2 message: {}", message);
    }
}
