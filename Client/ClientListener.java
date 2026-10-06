package ChatRoom.Client;

import java.io.InputStream;
import java.net.Socket;

/**
 * ClientListener
 */
public class ClientListener implements Runnable {
    private Socket socket;
    private InputStream input;

    public ClientListener(Socket socket) {
        this.socket = socket;
        try {
            this.input = socket.getInputStream();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void run() {
        byte[] buffer = new byte[1024];
        int bytesread;
        try {
            while ((bytesread = input.read(buffer)) != -1) {
                String message = new String(buffer, 0, bytesread);
                System.out.println(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}