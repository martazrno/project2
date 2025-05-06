package client1;

import java.io.IOException;
import java.util.Scanner;

public class ClientTest
{
  public static void main(String[] args) {
    try {
      Client client = new Client("172.20.10.5", 1234);
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