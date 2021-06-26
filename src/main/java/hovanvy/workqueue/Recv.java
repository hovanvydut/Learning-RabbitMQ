package hovanvy.workqueue;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author hovanvydut
 * Created on 6/26/21
 */

// Consumer
public class Recv {
    private final static String QUEUE_NAME = "task_queue2";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        boolean durable = true;
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {

            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");

            try {
                doWork(message);
            } finally {
                System.out.println(" [x] Done");
//                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }

        };

//        boolean autoAcknowledgement = false;
        boolean autoAcknowledgement = true;
        channel.basicConsume(QUEUE_NAME, autoAcknowledgement, deliverCallback, consumerTag -> {
            System.out.println("hey hey hey");
        });
    }

    private static void doWork(String message) {
        try {
            for (char ch : message.toCharArray()) {
                if (ch == '.') {
                        Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
