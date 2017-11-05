package websocket.server;

import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;

import java.io.IOException;

@WebSocket
public class MyWebSocketHandler {

    RemoteEndpoint remoteEndpoint;

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        System.out.println("Close: statusCode=" + statusCode + ", reason=" + reason);
    }

    @OnWebSocketError
    public void onError(Throwable t) {
        System.out.println("Error: " + t.getMessage());
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        System.out.println("Connect: " + session.getRemoteAddress().getAddress());
        this.remoteEndpoint = session.getRemote();
//        try {
//            session.getRemote().sendString("Hello Webbrowser");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @OnWebSocketMessage
    public void onMessage(String message) {
//        System.out.println("Message: " + message);
        try {
            remoteEndpoint.sendString("Hello" + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}