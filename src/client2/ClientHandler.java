package client2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String request;
            while ((request = input.readLine()) != null) {
                System.out.println("Received: " + request);

                // Example simple response
                if (request.equals("HELLO_SERVER")) {
                    output.println("Hello from Server!");
                } else {
                    output.println("Unknown command");
                }
            }
        } catch (IOException e) {
            System.out.println("Client disconnected or error occurred.");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}