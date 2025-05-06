package viewmodel;

import people.Customer;
import people.Doctor;

public class AddPrescriptionViewModel
{
  private String customerName;
  private String medicineName;

  private Doctor doctor;

  public void setCustomerName(String customerName)
  {
    this.customerName = customerName;
  }

  public void setMedicineName(String medicineName)
  {
    this.medicineName = medicineName;
  }

  public String getCustomerName()
  {
    return customerName;
  }

  public String getMedicineName()
  {
    return medicineName;
  }

  public void sendData()
  {
    doctor = new Doctor("doctor", "1");

    doctor.createPrescription(customerName, medicineName);
  }
}
