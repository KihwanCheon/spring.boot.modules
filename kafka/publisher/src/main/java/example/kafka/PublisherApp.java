package example.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class PublisherApp implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(PublisherApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("run");
    }
}
