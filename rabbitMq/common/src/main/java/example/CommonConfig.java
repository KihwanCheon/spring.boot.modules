package example;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@PropertySource(value = "classpath:/common.yml", factory = YamlPropertySourceFactory.class)
public abstract class CommonConfig {

    @Value("${rabbitmq.host:}") private String hostname;
    @Value("${rabbitmq.port:5672}") private int port;
    @Value("${rabbitmq.username:}") private String username;
    @Value("${rabbitmq.password:}") private String password;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(hostname, port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    Map<String, Object> arguments = Map.of("x-message-ttl", 1000);
}
