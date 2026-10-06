package ChatRoom.Server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * ClientHandler
 */
public class ClientHandler implements Runnable {
    private Socket mySocket;
    private ChatServer chatServer;
    private String id;
    private InputStream input;
    private OutputStream output;

    public ClientHandler(Socket mySocket, String id, ChatServer chatServer) {
        this.mySocket = mySocket;
        this.id = id;
        this.chatServer = chatServer;
        try {
            this.input = mySocket.getInputStream();
            this.output = mySocket.getOutputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        byte[] buffer = new byte[1024];
        int bytesread;
        try {
            while ((bytesread = input.read(buffer)) != -1) {
                String message = new String(buffer, 0, bytesread);
                chatServer.broadcastMessage(this.id, message);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void sendMessage(String message) {
        try {
            output.write(message.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

}