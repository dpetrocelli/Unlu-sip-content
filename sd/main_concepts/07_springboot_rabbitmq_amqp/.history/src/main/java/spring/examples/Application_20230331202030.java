package spring.examples;

//import lombok.RequiredArgsConstructor;
import spring.examples.controllers.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@RequiredArgsConstructor
public class Application implements CommandLineRunner {
    // private final DefaultExchangeProducer defaultExchangeProducer;
    // private final DirectExchangeProducer directExchangeProducer;
    // private final FanoutExchangeProducer fanoutExchangeProducer;
    // private final TopicExchangeProducer topicExchangeProducer;
    // private final HeadersExchangeProducer headersExchangeProducer;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        // defaultExchangeProducer.sendMessage();
        // directExchangeProducer.sendMessage();
        // fanoutExchangeProducer.sendMessage();
        // topicExchangeProducer.sendMessage();
        // headersExchangeProducer.sendMessage();
       

       
        
    }

}
