package example;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static example.ExchangeNames.Topic.RoutingKey.*;

@Configuration
public class ListenerConfig extends  CommonConfig {

    @Bean Queue simpleQueue() { return new Queue(QueueNames.Simple, false, false, false, arguments); }

    @Bean Queue topic1Queue() { return new Queue(QueueNames.Topic1, false, false, false, arguments); }
    @Bean Queue topic2Queue() { return new Queue(QueueNames.Topic2, false, false, false, arguments); }

    @Bean Queue header1Queue() { return new Queue(QueueNames.Headers1, false, false, false, arguments); }
    @Bean Queue header2Queue() { return new Queue(QueueNames.Headers2, false, false, false, arguments); }

    @Bean
    Declarables declarable(@Qualifier("topic1Queue") Queue topic1Queue,
                           @Qualifier("topic2Queue") Queue topic2Queue,
                           @Qualifier("header1Queue") Queue header1Queue,
                           @Qualifier("header2Queue") Queue header2Queue) {

        TopicExchange topic1 = new TopicExchange(ExchangeNames.Topic.Topic1, true, false, arguments);
        TopicExchange topic2 = new TopicExchange(ExchangeNames.Topic.Topic2, true, false, arguments);

        FanoutExchange fan1 = new FanoutExchange(ExchangeNames.Fanout.Fanout1, true, false, arguments);
        FanoutExchange fan2 = new FanoutExchange(ExchangeNames.Fanout.Fanout2, true, false, arguments);

        HeadersExchange header1 = new HeadersExchange(ExchangeNames.Headers.Header1, true, false, arguments);
        HeadersExchange header2 = new HeadersExchange(ExchangeNames.Headers.Header2, true, false, arguments);

        return new Declarables(
                topic1Queue, topic2Queue, header1Queue, header2Queue,
                BindingBuilder.bind(topic1Queue).to(topic1).with(T1),
                BindingBuilder.bind(topic2Queue).to(topic2).with(T2),
                BindingBuilder.bind(header1Queue).to(header1).whereAll("h1", "h2").exist(),
                BindingBuilder.bind(header2Queue).to(header2).where("h1").exists()
        );
    }
}
