package model;

import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Prescription implements Serializable
{
  private final SimpleStringProperty id;

  private final SimpleStringProperty customerName;

  private final SimpleStringProperty medicineName;

  public Prescription(String id, String customerName, String medicineName)
  {
    this.id = new SimpleStringProperty(id);
    this.customerName = new SimpleStringProperty(customerName);
    this.medicineName = new SimpleStringProperty(medicineName);
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
