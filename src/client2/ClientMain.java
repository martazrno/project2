package client2;

import java.io.IOException;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        try {
            Client client = new Client("localhost", 1234);
            Scanner scanner = new Scanner(System.in);

            System.out.println("Connected to server. Type messages:");
            while (true) {
                System.out.print("> ");
                String message = scanner.nextLine();

                client.sendRequest(message);

                String response = client.getResponse();
                System.out.println("Server said: " + response);

                if (message.equalsIgnoreCase("exit")) {
                    break; // Type 'exit' to leave
                }
            }

            client.close();
            System.out.println("Connection closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}