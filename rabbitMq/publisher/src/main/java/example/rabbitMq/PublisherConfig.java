package example.rabbitMq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class PublisherConfig extends CommonConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    Map<String, Object> arguments = Map.of("x-message-ttl", 1000);

    @Bean
    Declarables exchange() {
        TopicExchange topic1 = new TopicExchange(ExchangeNames.Topic.Topic1, true, false, arguments);
        TopicExchange topic2 = new TopicExchange(ExchangeNames.Topic.Topic2, true, false, arguments);

        FanoutExchange fan1 = new FanoutExchange(ExchangeNames.Fanout.Fanout1, true, false, arguments);
        FanoutExchange fan2 = new FanoutExchange(ExchangeNames.Fanout.Fanout2, true, false, arguments);

        HeadersExchange header1 = new HeadersExchange(ExchangeNames.Headers.Header1, true, false, arguments);
        HeadersExchange header2 = new HeadersExchange(ExchangeNames.Headers.Header2, true, false, arguments);

        return new Declarables(topic1, topic2, fan1, fan2, header1, header2);
    }
}
