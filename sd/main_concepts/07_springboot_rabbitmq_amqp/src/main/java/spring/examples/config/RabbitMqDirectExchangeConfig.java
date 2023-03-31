package spring.examples.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqDirectExchangeConfig {
    public static final String EXCHANGE_TASKS_DIRECT = "x.tasks.direct";
    
    @Bean
    public DirectExchange exchangeTasksDirect() {
        return new DirectExchange(EXCHANGE_TASKS_DIRECT);
    }

    @Bean
    public Declarables directExchangeBindings(
            DirectExchange exchangeTasksDirect,
            Queue queueTaxiNormalSmall,
            Queue queueTaxiEcoSmall,
            Queue queueTaxiNormalLarge,
            Queue queueTaxiEcoLarge) {
        return new Declarables(
                BindingBuilder.bind(queueTaxiNormalSmall).to(exchangeTasksDirect).with(EXCHANGE_TASKS_DIRECT)
        );
    }


}
