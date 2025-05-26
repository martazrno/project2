package viewmodel;

import goodclient.RemoteDoctorClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import people.Customer;
import people.Doctor;

public class AddPrescriptionViewModel
{
  private final StringProperty customerName = new SimpleStringProperty();
  private final StringProperty medicineName = new SimpleStringProperty();

  private final ObservableList<String> customerList = FXCollections.observableArrayList();
  private final ObservableList<String> medicineList = FXCollections.observableArrayList();

  private final RemoteDoctorClient remoteClient;

  public AddPrescriptionViewModel()
  {
    remoteClient = new RemoteDoctorClient();

    customerList.setAll("Alice", "Bob", "Charlie");
    medicineList.setAll("Paracetamol", "Ibuprofen", "Amoxicillin");
  }

  public ObservableList<String> getCustomerList()
  {
    return customerList;
  }

  public ObservableList<String> getMedicineList()
  {
    return medicineList;
  }

  public StringProperty customerNameProperty() {
    return customerName;
  }

  public StringProperty medicineNameProperty() {
    return medicineName;
  }

  public void sendData()
  {
    remoteClient.createPrescription(customerName.get(), medicineName.get());
  }
}
