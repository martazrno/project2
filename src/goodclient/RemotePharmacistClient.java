package goodclient;

import model.Medicine;
import model.Prescription;
import people.Customer;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class RemotePharmacistClient
{
  private final String serverHost;
  private final int serverPort;

  public RemotePharmacistClient()
  {
    this("localhost", 8080);
  }

  public RemotePharmacistClient(String serverHost, int serverPort)
  {
    this.serverHost = serverHost;
    this.serverPort = serverPort;
  }

  public void addCustomer(String name)
  {
    try (Socket socket = new Socket(serverHost, serverPort);
        ObjectOutputStream out = new ObjectOutputStream(
            socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream()))
    {
      out.writeObject("ADD_CUSTOMER");
      out.writeObject(new Customer(name));
      out.flush();

      Object response = in.readObject();
      if (response instanceof String message)
      {
        System.out.println("Server response: " + message);
      }
    }
    catch (IOException | ClassNotFoundException e)
    {
      System.err.println("Failed to add customer:");
      e.printStackTrace();
    }
  }

  public void addMedicine(String name, boolean isPrescription, int quantity)
  {
    try (Socket socket = new Socket(serverHost, serverPort);
        ObjectOutputStream out = new ObjectOutputStream(
            socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream()))
    {
      out.writeObject("ADD_MEDICINE");
      out.writeObject(new Medicine(name, isPrescription, quantity));
      out.flush();

      Object response = in.readObject(); // Optional: handle a confirmation response
      if (response instanceof String message)
      {
        System.out.println("Server response: " + message);
      }
    }
    catch (IOException | ClassNotFoundException e)
    {
      System.err.println("Failed to add medicine:");
      e.printStackTrace();
    }
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

  public List<Medicine> getAllMedicine()
  {
    try (
        Socket socket = new Socket(serverHost, serverPort);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
    ) {
      out.writeObject("GET_MEDICINE");
      out.flush();
      Object response = in.readObject();

      if (response instanceof List<?> list) {
        return list.stream()
            .filter(Medicine.class::isInstance)
            .map(Medicine.class::cast)
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
