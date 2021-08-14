package example.listener.rabbit;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static example.ExchangeNames.*;

/**
 * https://docs.spring.io/spring-amqp/reference/html/#meta-annotation-driven
 */
@Slf4j
@Component
public class FanoutListener {

    @Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = Fanout.Fanout1, type = ExchangeTypes.FANOUT)))
    public @interface Fanout1Listener {
    }

    @Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = Fanout.Fanout2, type = ExchangeTypes.FANOUT)))
    public @interface Fanout2Listener {
    }

    @Fanout1Listener
    public void fanout1_1(String message) {
        log.info("fanout1_1 message: {}", message);
    }

    @Fanout1Listener
    public void fanout1_2(String message) {
        log.info("fanout1_2 message: {}", message);
    }

    @Fanout2Listener
    public void fanout2_1(String message) {
        log.info("fanout2_1 message: {}", message);
    }

    @Fanout2Listener
    public void fanout2_2(String message) {
        log.info("fanout2_2 message: {}", message);
    }

    @RabbitListener
    public void fanoutAnonymous(String message) {
        log.info("fanoutAnonymous message: {}", message);
    }
}
