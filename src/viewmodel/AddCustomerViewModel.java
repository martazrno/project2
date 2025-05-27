package viewmodel;

import goodclient.RemotePharmacistClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Inventory;
import interfaces.InventoryManager;
import model.Medicine;
import people.Customer;
import people.Pharmacist;

public class AddCustomerViewModel
{
  private final StringProperty customerName = new SimpleStringProperty();
  private final RemotePharmacistClient remoteClient;

  public AddCustomerViewModel()
  {
    remoteClient = new RemotePharmacistClient();
  }

  public StringProperty customerNameProperty()
  {
    return customerName;
  }

  public String getCustomerName()
  {
    return customerName.get();
  }

  public void sendData()
  {
    String customer = customerName.get();

    if (customer != null)
    {
      remoteClient.addCustomer(customer);
    }
  }
}
