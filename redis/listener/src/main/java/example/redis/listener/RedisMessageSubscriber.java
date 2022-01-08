package example.redis.listener;

import example.redis.Msg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RedisMessageSubscriber implements MessageListener {
    final JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();

    @Override
    public void onMessage(Message msg, byte[] pattern) {
        Msg body = deserialize(msg.getBody());
        log.info("Message received: body-{} / channel-{} / pattern-{}",
                body, new String(msg.getChannel()), new String(pattern));
    }

    @SuppressWarnings("unchecked")
    <T> T deserialize(byte[] b) {
        return (T) serializer.deserialize(b);
    }
}
