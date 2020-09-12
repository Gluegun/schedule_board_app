package beans;

import javax.ejb.Startup;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@Startup
public class WebsocketPushBean implements Serializable {

    @Inject
    @Push(channel = "websocket")
    public PushContext pushContext;

    public void sendMessage() {
        System.out.println("update");
        pushContext.send("update");
    }
}