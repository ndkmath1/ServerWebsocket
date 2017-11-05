package websocket.server;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import java.util.stream.IntStream;

public class WebSocketTest {

    private static int num = 50;

    public static void main(String[] args) {
        Server server = new Server();
        ServerConnector[] connectors = new ServerConnector[num];
        for (int i = 0; i < num; ++i) {
            ServerConnector c = new ServerConnector(server);
            if (i < 10) {
                c.setPort(Integer.parseInt("800" + i));
            } else {
                c.setPort(Integer.parseInt("80" + i));
            }
            connectors[i] = c;
        }
        server.setConnectors(connectors);
//        IntStream.range(0, 10).parallel()
        openSocketServer(server);
    }

    public static void openSocketServer(Server server) {
//        Server server = new Server(port);
        WebSocketHandler wsHandler = new WebSocketHandler() {
            @Override
            public void configure(WebSocketServletFactory factory) {
                factory.register(MyWebSocketHandler.class);
            }
        };
        server.setHandler(wsHandler);
        try {
            server.start();
            server.join();
        } catch (Exception e) {

        }
    }

}