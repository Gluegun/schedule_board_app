package beans;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.io.Serializable;

@Named
public class TopicSubscriber implements MessageListener, Serializable {

    @Inject
    public WebsocketPushBean pushBean;

    @Override
    public void onMessage(Message message) {
        System.out.println("listen");
        pushBean.sendMessage();
    }
}