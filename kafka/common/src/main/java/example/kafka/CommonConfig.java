package example.kafka;

import example.YamlPropertySourceFactory;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.util.backoff.FixedBackOff;

@PropertySource(value = "classpath:/common.yml", factory = YamlPropertySourceFactory.class)
public abstract class CommonConfig {

    @Bean
    public NewTopic topic() {
        return new NewTopic("topic1", 2, (short) 2);
    }

    @Bean
    public NewTopic dlt() {
        return new NewTopic("topic1.DLT", 2, (short) 2);
    }

    @Bean
    public RecordMessageConverter converter() {
        return new JsonMessageConverter();
    }

    @Bean
    public DefaultErrorHandler errorHandler(KafkaTemplate<Object, Object> template) {
        return new DefaultErrorHandler(
                new DeadLetterPublishingRecoverer(template), new FixedBackOff(1000L, 2));
    }
}
