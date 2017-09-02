package hello;

import org.omg.CORBA.TIMEOUT;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by tianhe on 2017/8/31.
 */
@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;
    private final ConfigurableApplicationContext context;

    public Runner(RabbitTemplate rabbitTemplate, Receiver receiver, ConfigurableApplicationContext context) {
        this.rabbitTemplate = rabbitTemplate;
        this.receiver = receiver;
        this.context = context;
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("send message.....");
        rabbitTemplate.convertAndSend(Application.queueName, "hello from rabbitMQ");
        receiver.getLatch().await(1000, TimeUnit.MICROSECONDS);
        context.close();
    }
}
