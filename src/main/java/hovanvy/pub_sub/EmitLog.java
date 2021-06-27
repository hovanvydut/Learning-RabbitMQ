package hovanvy.pub_sub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author hovanvydut
 * Created on 6/27/21
 */

public class EmitLog {

    private static final String EXCHANGE_NAME = "logs-exchange";
    private static final String ROUTING_KEY = "";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

            for (int i = 1; i <= 10; i++) {
                String message = String.format("Task %d %s", i, generateDot(i));
                channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, message.getBytes("UTF-8"));
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
