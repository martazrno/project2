package database;
import java.sql.Connection;
import java.sql.SQLException;

public class database_main {
    public static void main(String[] args) {

        try (Connection conn = DBconnect.getConnection()) {System.out.println("Connected successfully!");}
        catch (SQLException e) {
            System.out.println("Connection failed:");
            e.printStackTrace();

        }}}