package goodserver;

import interfaces.InventoryManager;
import model.Inventory;
import model.Medicine;
import model.Prescription;
import people.Customer;
import people.Doctor;
import people.Pharmacist;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MultiServer
{
  private static final List<Prescription> prescriptions = new ArrayList<>();
  private static final List<Medicine> medicine = new ArrayList<>();
  private static final List<Customer> customers = new ArrayList<>();

  private static final Doctor doctor = new Doctor("guy");
  private static final Pharmacist pharmacist = new Pharmacist("dude", new Inventory());
  private static final Customer customer = new Customer("bro");

  public static void main(String[] args)
  {
    try (ServerSocket serverSocket = new ServerSocket(8080))
    {
      System.out.println("Server started on port 8080...");

      while (true)
      {
        Socket clientSocket = serverSocket.accept();
        new Thread(() -> handleClient(clientSocket)).start();
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  private static void handleClient(Socket socket)
  {
    try (
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream()))
    {
      Object request = in.readObject();

      if (request instanceof String command)
      {
        switch (command)
        {
          case "GET_PRESCRIPTIONS" -> out.writeObject(doctor.getAllPrescriptionsFromDB());

          case "GET_MEDICINE" -> out.writeObject(pharmacist.getInventory().getAllMedicines());

          case "GET_CUSTOMERS" -> out.writeObject(pharmacist.getAllCustomers());

          case "CREATE_PRESCRIPTION" -> {
            String customerName = (String) in.readObject();
            String medicineName = (String) in.readObject();
            doctor.createPrescription(customerName, medicineName);
            out.writeObject("Prescription added for " + customerName);
          }

          case "ADD_MEDICINE" -> {
            Object obj = in.readObject();
            if (obj instanceof Medicine med)
            {
              pharmacist.addToInventory(med);
              out.writeObject("Medicine added: " + med.getName());
            }
            else
            {
              out.writeObject("Invalid object for ADD_MEDICINE");
            }
          }

          case "ADD_CUSTOMER" -> {
            Object obj = in.readObject();
            if (obj instanceof Customer customer)
            {
              pharmacist.addCustomer(customer);
              out.writeObject("Customer added: " + customer.getName());
            }
            else
            {
              out.writeObject("Invalid object for ADD_CUSTOMER");
            }
          }

          default -> out.writeObject("Unknown command: " + command);
        }

        out.flush();
      }
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }
}

