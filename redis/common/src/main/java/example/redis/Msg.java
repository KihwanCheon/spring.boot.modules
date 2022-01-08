package example.redis;

import lombok.Value;

import java.io.Serializable;

@Value
public class Msg implements Serializable {
    String header;
    String content;
}
