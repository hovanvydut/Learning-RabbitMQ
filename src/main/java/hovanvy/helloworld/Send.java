package hovanvy.helloworld;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author hovanvydut
 * Created on 6/26/21
 */

// Publisher
public class Send {
    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            for (int i = 1; i <= 100; i++) {
                // delay 100ms
                Thread.sleep(100);

                String message = "Hello Vy " + i;
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

                System.out.println(" [x] Sent '" + message + "'");
            }
        }
    }
}

