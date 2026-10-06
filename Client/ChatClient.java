package ChatRoom.Client;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    private static String name = "localhost";
    private static int PORT = 5000;

    public void startClient() {
        try {
            Socket socket = new Socket(name, PORT);
            System.out.println("Connectes to server");
            //lien tuc doc du lieu tu server
            ClientListener clientListener = new ClientListener(socket);
            new Thread(clientListener).start();

            // lien tuc doc du lieu tu scanner
            OutputStream output = socket.getOutputStream();
            Scanner sc = new Scanner(System.in);
            while (true) {
                String message = sc.nextLine();
                output.write(message.getBytes());
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
