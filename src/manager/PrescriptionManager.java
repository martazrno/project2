package manager;
import model.Prescription;

public interface PrescriptionManager {
    void addPrescription(Prescription prescription);
    void cancelPrescription(Prescription prescription);
    void viewPrescriptions();
}
