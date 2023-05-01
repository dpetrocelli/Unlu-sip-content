package spring.examples.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueuesConfiguration {
    public static final String QUEUE_TASKS_DEFAULT = "q.tasks.default";
    
    @Bean
    public Queue queueTasksDefault() {
        return new Queue(QUEUE_TASKS_DEFAULT);
    }

   

}
