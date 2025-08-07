package people;
import database.DBconnect;

import java.io.Serializable;
import java.sql.*;


public class Customer extends User implements Serializable
{
    private static final long serialVersionUID = -4537788144857287537L;
    // constructor
    public Customer(){super();}

    public Customer (String name){super(name);}


    // methods
    public void viewMyPrescriptions() {
        String sql = """
        SELECT m.name FROM prescriptions p
        JOIN medicines m ON p.medicine_id = m.medicine_id WHERE p.customer_id = ?;
        """;

        try (Connection connection = DBconnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, this.getId());
            ResultSet rs = statement.executeQuery();
            boolean found = false;
            System.out.println("Your prescribed medicines: ");

            while (rs.next()) {
                found = true;
                String name = rs.getString("name");
                System.out.println("- " + name);}
            if (!found) {System.out.println("No prescriptions found.");}}

        catch (SQLException e)
        {
            System.out.println("Error fetching prescriptions: " + e.getMessage());
        }
    }
}