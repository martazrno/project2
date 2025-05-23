package viewmodel;

import model.Inventory;
import interfaces.InventoryManager;
import model.Medicine;
import people.Customer;
import people.Pharmacist;

public class AddCustomerViewModel
{
  private String customerName;
  private String customerId;

  private Pharmacist pharmacist;

  public void setCustomerName(String customerName)
  {
    this.customerName = customerName;
  }

  public void setCustomerId(String customerId)
  {
    this.customerId = customerId;
  }

  public String getCustomerName()
  {
    return customerName;
  }

  public String getCustomerId()
  {
    return customerId;
  }

  public void sendData()
  {
    InventoryManager inventory = new Inventory();
    pharmacist = new Pharmacist("pharmacist", inventory);

    pharmacist.addCustomer(new Customer(customerName));

  }
}
