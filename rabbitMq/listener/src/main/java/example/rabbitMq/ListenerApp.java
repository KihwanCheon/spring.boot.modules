package example.rabbitMq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "example")
public class ListenerApp {
    public static void main(String[] args) {
        SpringApplication.run(ListenerApp.class, args);
    }
}
