package hello;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * Created by tianhe on 2017/8/31.
 */
@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message){
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch(){
        return  latch;
    }
}
