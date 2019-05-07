import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Recv {



    public static void main(String[] argv) throws Exception {

        // Place your settings here
        final String USER_NAME = ;
        final String USER_PASSWORD = ;
        final String VIRTUAL_HOST = ;
        final String HOST = ;
        final int    PORT = ;

        final String EXCHANGE_NAME = ;

        /////////////////////////////

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(USER_NAME);
        factory.setPassword(USER_PASSWORD);
        factory.setVirtualHost(VIRTUAL_HOST);
        factory.setHost(HOST);
        factory.setPort(PORT);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(EXCHANGE_NAME, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(EXCHANGE_NAME, true, deliverCallback, consumerTag -> { });
    }
}