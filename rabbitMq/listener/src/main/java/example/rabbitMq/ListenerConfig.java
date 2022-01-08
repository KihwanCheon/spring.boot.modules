package example.rabbitMq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListenerConfig extends CommonConfig {

    @Bean Queue header1Queue() { return new Queue(QueueNames.Headers1, false, false, false, arguments); }
    @Bean Queue header2Queue() { return new Queue(QueueNames.Headers2, false, false, false, arguments); }

    @Bean
    Declarables declarable(@Qualifier("header1Queue") Queue header1Queue,
                           @Qualifier("header2Queue") Queue header2Queue) {

        HeadersExchange header1 = new HeadersExchange(ExchangeNames.Headers.Header1, true, false, arguments);
        HeadersExchange header2 = new HeadersExchange(ExchangeNames.Headers.Header2, true, false, arguments);

        return new Declarables(
                BindingBuilder.bind(header1Queue).to(header1).whereAll("color", "width").exist(),
                BindingBuilder.bind(header2Queue).to(header2).whereAny("color", "width").exist()
        );
    }
}
