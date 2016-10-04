import javax.websocket.*;
import java.io.IOException;

/**
 * Created by Serjo on 03/10/16.
 */
public class EchoEndpoint extends Endpoint {
    @Override
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
                    remoteEndpointBasic.sendText(message);
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
