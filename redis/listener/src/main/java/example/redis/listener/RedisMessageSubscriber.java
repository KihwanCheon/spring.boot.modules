package example.redis.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RedisMessageSubscriber implements MessageListener {
    @Override
    public void onMessage(Message msg, byte[] pattern) {
        log.info("Message received: body-{} / channel-{} / pattern-{}",
                new String(msg.getBody()), new String(msg.getChannel()), new String(pattern));
    }
}
