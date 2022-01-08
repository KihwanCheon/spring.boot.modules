package example.controller;

import example.QueueNames;
import lombok.Value;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static example.ExchangeNames.*;
import static example.ExchangeNames.Fanout.RoutingKey.*;
import static example.ExchangeNames.Topic.RoutingKey.*;

@RestController
@RequestMapping("pub")
public class PublishController {

    @Resource RabbitTemplate rabbit;

    @GetMapping(value = "simple/{message}")
    Object simple(@PathVariable String message) {
        rabbit.convertAndSend(QueueNames.Simple, message);
        return true;
    }
    @GetMapping(value = "topic/{id}/{message}")
    Object topic(@PathVariable int id, @PathVariable String message) {
        if (id == 1) {
            rabbit.convertAndSend(Topic.Topic1, T1, message);
        } else {
            rabbit.convertAndSend(Topic.Topic2, T2, message);
        }

        return true;
    }

    @GetMapping(value = "fanout/{id}/{message}")
    Object fanout(@PathVariable int id, @PathVariable String message) {
        if (id  == 1) {
            rabbit.convertAndSend(Fanout.Fanout1, F1, message);
        } else {
            rabbit.convertAndSend(Fanout.Fanout2, F1, message);
        }

        return true;
    }

    @Value
    static class Req {
        String color;
        Integer width;
    }

    @GetMapping(value = "headers")
    Object headers(Req req) {
        MessageProperties messageProperties = new MessageProperties();
        if (req.color != null)
            messageProperties.setHeader("color", req.color);
        if (req.width != null)
            messageProperties.setHeader("width", req.width);

        var msg = new SimpleMessageConverter().toMessage("", messageProperties);

        rabbit.convertAndSend(Headers.Header1, "", msg);
        rabbit.convertAndSend(Headers.Header2, "", msg);
        return true;
    }

}
