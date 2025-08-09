package people;
import database.DBconnect;

import java.io.Serializable;
import java.sql.*;
import java.util.UUID;

public class Customer implements Serializable
{
    private static final long serialVersionUID = -4537788144857287537L;
    private String name;
    private String id;

    // constructor
    public Customer(){}

    public Customer (String name){
        this.name = name;
        this.id = generateId();
    }


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

    private String generateId() {return "U" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();}

    //getters
    public String getId() {return id;}
    public String getName() {return name;}
}