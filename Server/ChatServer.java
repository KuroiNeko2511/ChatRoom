package ChatRoom.Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Server
 */
public class ChatServer {
    private static final int PORT = 5000;
    private List<ClientHandler> clients = new ArrayList<>();

    public void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server open port" + PORT);
            while (true) {
                Socket socketClient = serverSocket.accept();
                System.out.println("Ket noi voi" + socketClient.getInetAddress().getHostName());
                ClientHandler clientHandler = new ClientHandler(socketClient, System.currentTimeMillis() + "", this);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void broadcastMessage(String id, String message) {
        for (ClientHandler client : clients) {
            if (!(client.getId().equals(id)))
                client.sendMessage(id + ":" + message);
        }
    }
}