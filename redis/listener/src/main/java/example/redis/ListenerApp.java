package example.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Component;

@EnableScheduling
@SpringBootApplication
public class ListenerApp {
    public static void main(String[] args) {
        SpringApplication.run(ListenerApp.class, args);
    }

    @Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler();
    }

    @Component
    public static class CronTable {
        @Scheduled(cron = "0 0 0 1 * *")
        public void cronJob() {}
    }
}