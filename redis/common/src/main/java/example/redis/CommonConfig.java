package example.redis;

import example.YamlPropertySourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.listener.ChannelTopic;

@PropertySource(value = "classpath:/common.yml", factory = YamlPropertySourceFactory.class)
public abstract class CommonConfig {

    @Bean
    ChannelTopic topic() { return new ChannelTopic("topic"); }
}
