package hovanvy.workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author hovanvydut
 * Created on 6/26/21
 */

// Publisher
public class Send {
    private static final String QUEUE_NAME = "task_queue2";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            boolean durable = true;
            channel.queueDeclare(QUEUE_NAME, durable, false, false, null);

            System.out.println("Count down 10 --> 1");
            for (int i = 10; i >= 1; i--) {

                String message = String.format("Task %d %s", i, generateDot(i));

                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

                System.out.println(" [x] Sent '" + message + "'");
            }
        }
    }

    private static String generateDot(int times) {
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 1; i <= times; i++) {
            stringBuffer.append(".");
        }

        return stringBuffer.toString();
    }
}

