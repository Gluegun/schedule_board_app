package beans;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSConsumer {

    public void consume() {

        ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_BIND_URL);
        try {
            Connection connection = factory.createConnection();
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("railways");
            MessageConsumer consumer = session.createConsumer(topic);
            TopicSubscriber listener = new TopicSubscriber();
            consumer.setMessageListener(listener);
            connection.start();
        } catch (JMSException exp) {
            exp.printStackTrace();
        }
    }
}
