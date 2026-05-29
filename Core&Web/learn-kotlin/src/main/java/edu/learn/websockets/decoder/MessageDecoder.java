package edu.learn.websockets.decoder;



import com.fasterxml.jackson.databind.ObjectMapper;
import edu.learn.websockets.beans.Message;

import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.IOException;

public class MessageDecoder implements Decoder.Text<Message> {

    private static ObjectMapper gson = new ObjectMapper();

    @Override
    public Message decode(String s) {
        Message decodedString = null;
        try {
            decodedString = gson.readValue(s, Message.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return decodedString;
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        // Custom initialization logic
    }

    @Override
    public void destroy() {
        // Close resources
    }
}
