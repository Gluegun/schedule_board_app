package beans;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.Message;
import javax.jms.MessageListener;

@Named
@ApplicationScoped
public class TopicSubscriber implements MessageListener {

    @Inject
    private WebsocketPushBean pushBean;

    @Override
    public void onMessage(Message message) {
        System.out.println("Received message from railways");
        pushBean.pushUpdate();
    }
}