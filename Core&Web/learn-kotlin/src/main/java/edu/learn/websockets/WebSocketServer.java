package edu.learn.websockets;

import edu.learn.websockets.beans.Message;
import edu.learn.websockets.config.HttpSessionConfigurator;
import edu.learn.websockets.decoder.MessageDecoder;
import edu.learn.websockets.encoder.MessageEncoder;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executors;


@ServerEndpoint(
    value = "/actions/{username}",
    configurator = HttpSessionConfigurator.class,
    encoders = MessageEncoder.class,
    decoders = MessageDecoder.class
)
public class WebSocketServer {

    private static Set<WebSocketServer> notificationEndpoints = new CopyOnWriteArraySet<>();
    private static HashMap<String, String> users = new HashMap<>();
    private Session session;
    private HttpSession httpSession;
    private List<String> list = new ArrayList<>();
    @OnOpen
    public void open(@PathParam("username") String username, Session session,EndpointConfig config) throws IOException, EncodeException {
        this.session = session;
        this.httpSession = (HttpSession) config.getUserProperties().get(username);
        notificationEndpoints.add(this);
        users.put(session.getId(), username);

        String response = "User " + username + " | WebSocket session ID "+ session.getId() +" | HTTP session ID " + httpSession.getId();
        Message message = new Message();
        message.setFrom(username);
        message.setTo(username);
        message.setContent(response);
        session.getBasicRemote().sendObject(message);
    }

    @OnClose
    public void close(Session session) {
        System.out.println("Close session: " + session.getId());
    }

    @OnError
    public void onError(Throwable error) {
    }

    @OnMessage
    public void handleMessage(Message message, Session session) {
        System.out.println("Message : " + message);

        if(message.getContent().equalsIgnoreCase("all")){

            Message messageObject = new Message();
            messageObject.setFrom("bot");
            messageObject.setContent("hahahahahahahahahahahahah");
            try {
                session.getBasicRemote().sendObject(messageObject);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (EncodeException e) {
                e.printStackTrace();
            }

            //broadcast(messageObject);
        }
    }

    private static void broadcast(Message message) {

        notificationEndpoints.forEach(endpoint -> {
            synchronized (endpoint) {
                try {
                    endpoint.session.getBasicRemote().sendObject(message);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
