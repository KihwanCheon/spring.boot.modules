package example.listener.rabbit;

import example.ExchangeNames;
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

@Slf4j
@Component
public class TopicListener {

    @Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            key = ExchangeNames.Topic.RoutingKey.T1,
            exchange = @Exchange(type = ExchangeTypes.TOPIC, name = ExchangeNames.Topic.Topic1)))
    public @interface Topic1T1ListenerBinding {
    }

    @Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            key = ExchangeNames.Topic.RoutingKey.T2,
            exchange = @Exchange(type = ExchangeTypes.TOPIC, name = ExchangeNames.Topic.Topic1)))
    public @interface Topic1T2ListenerBinding {
    }

    @Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            key = ExchangeNames.Topic.RoutingKey.T1,
            exchange = @Exchange(type = ExchangeTypes.TOPIC, name = ExchangeNames.Topic.Topic2)))
    public @interface Topic2T1ListenerBinding {
    }

    @Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            key = ExchangeNames.Topic.RoutingKey.T2,
            exchange = @Exchange(type = ExchangeTypes.TOPIC, name = ExchangeNames.Topic.Topic2)))
    public @interface Topic2T2ListenerBinding {
    }

    @Topic1T1ListenerBinding
    public void topic1_1(String message) {
        log.info("topic1_1 message: {}", message);
    }

    @Topic1T2ListenerBinding
    public void topic1_2(String message) {
        log.info("topic1_2 message: {}", message);
    }

    @Topic2T1ListenerBinding
    public void topic2_1(String message) {
        log.info("topic2_1 message: {}", message);
    }

    @Topic2T2ListenerBinding
    public void topic2_2(String message) {
        log.info("topic2_2 message: {}", message);
    }
}
