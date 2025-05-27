package goodclient;

import model.Medicine;
import model.Prescription;
import people.Customer;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class RemoteDoctorClient
{
  private final String serverHost;
  private final int serverPort;

  public RemoteDoctorClient()
  {
    this("localhost", 8080);
  }

  public RemoteDoctorClient(String serverHost, int serverPort)
  {
    this.serverHost = serverHost;
    this.serverPort = serverPort;
  }

  public void createPrescription(String customerName, String medicineName)
  {
    try (
        Socket socket = new Socket(serverHost, serverPort);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
    ) {
      out.writeObject("CREATE_PRESCRIPTION");
      out.flush();

      out.writeObject(customerName);
      out.writeObject(medicineName);
      out.flush();

      Object response = in.readObject();
      if (response instanceof String serverMsg) {
        System.out.println("Server response: " + serverMsg);
      }
    } catch (IOException | ClassNotFoundException e) {
      System.err.println("Error communicating with server:");
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

  public List<Customer> getAllCustomers()
  {
    try (
        Socket socket = new Socket(serverHost, serverPort);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
    ) {
      out.writeObject("GET_CUSTOMERS");
      out.flush();
      Object response = in.readObject();

      if (response instanceof List<?> list) {
        return list.stream()
            .filter(Customer.class::isInstance)
            .map(Customer.class::cast)
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
