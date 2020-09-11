package beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import java.io.Serializable;

@Stateful
@LocalBean
public class WebsocketPushBean implements Serializable {

    @Inject
    @Push(channel = "websocket")
    private PushContext pushContext;

    void pushUpdate() {
        System.out.println("Pushing notification to jsf");
        pushContext.send("update");
    }
}
