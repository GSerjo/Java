import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Created by Serjo on 03/10/16.
 */

@ServerEndpoint(value ="/websocket/echoa")
public class EchoEndpointAnnotation {

    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        RemoteEndpoint.Basic remoteEndpoint = session.getBasicRemote();
        session.addMessageHandler(new EchoMessageHandler(remoteEndpoint));

    }

    private static class EchoMessageHandler implements MessageHandler.Whole<String>{

        private RemoteEndpoint.Basic remoteEndpointBasic;

        private EchoMessageHandler(RemoteEndpoint.Basic remoteEndpointBasic){
            this.remoteEndpointBasic = remoteEndpointBasic;
        }

        @Override
        public void onMessage(String message) {
            try {
                if(remoteEndpointBasic != null){
                    remoteEndpointBasic.sendText("annotated:" + message);
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
