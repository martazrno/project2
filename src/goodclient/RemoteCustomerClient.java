package goodclient;

import model.Prescription;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class RemoteCustomerClient
{
  private final String serverHost;
  private final int serverPort;

  public RemoteCustomerClient()
  {
    this("localhost", 8080);
  }

  public RemoteCustomerClient(String serverHost, int serverPort)
  {
    this.serverHost = serverHost;
    this.serverPort = serverPort;
  }

  public List<Prescription> getAllPrescriptions()
  {
    try (
        Socket socket = new Socket(serverHost, serverPort);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
    ) {
      out.writeObject("GET_PRESCRIPTIONS");
      out.flush();
      Object response = in.readObject();

      if (response instanceof List<?> list) {
        return list.stream()
            .filter(Prescription.class::isInstance)
            .map(Prescription.class::cast)
            .toList();
      } else {
        System.err.println("Unexpected response type from server: " + response.getClass());
      }
    } catch (IOException | ClassNotFoundException e) {
      System.err.println("Error communicating with server:");
      e.printStackTrace();
    }
    return List.of();
  }
}
