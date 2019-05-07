import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

public class Send {

    public static void main(String[] argv) throws Exception {

        // Place your settings here
        final String USER_NAME = ;
        final String USER_PASSWORD = ;
        final String VIRTUAL_HOST = ;
        final String HOST = ;
        final int    PORT = ;

        final String EXCHANGE_NAME = ;
        final String ROUTING_KEY = ;
        /////////////////////////////

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(USER_NAME);
        factory.setPassword(USER_PASSWORD);
        factory.setVirtualHost(VIRTUAL_HOST);
        factory.setHost(HOST);
        factory.setPort(PORT);

        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();

        String message = "Привет";

        for (int i=1; i<100; i++) {
            byte[] messageBodyBytes = (message+i).getBytes();
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, messageBodyBytes);
        }
        channel.close();
        conn.close();

    }
}

