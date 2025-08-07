package model;

import java.io.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Prescription implements Serializable
{
  private transient SimpleStringProperty id;

  private transient SimpleStringProperty customerName;

  private transient SimpleStringProperty medicineName;

  public Prescription(String id, String customerName, String medicineName)
  {
    this.id = new SimpleStringProperty(id);
    this.customerName = new SimpleStringProperty(customerName);
    this.medicineName = new SimpleStringProperty(medicineName);
  }

  private void writeObject(ObjectOutputStream out) throws IOException
  {
    out.defaultWriteObject(); // For any non-transient fields (if added later)
    out.writeUTF(id.get());
    out.writeUTF(customerName.get());
    out.writeUTF(medicineName.get());
  }

  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
    in.defaultReadObject(); // For any non-transient fields (if added later)
    id = new SimpleStringProperty(in.readUTF());
    customerName = new SimpleStringProperty(in.readUTF());
    medicineName = new SimpleStringProperty(in.readUTF());
  }

  public StringProperty getId()
  {
    return id;
  }

  public StringProperty getCustomerName()
  {
    return customerName;
  }

  public StringProperty getMedicineName()
  {
    return medicineName;
  }
}
