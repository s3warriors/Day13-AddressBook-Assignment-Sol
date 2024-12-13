import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.PreparedStatement;

class MysqlCon {
    public static void main(String[] args) {
        try {
            // Load the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/addressbook1", "root", new Constants().getPassword());

            // SQL Insert Query
            String query = "INSERT INTO contacts (id, first_name, last_name, phone_number, email) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);

            // Contacts data
            Object[][] contacts = {
                    {35, "John", "Doe", "9876543210", "john.doe@example.com"},
                    {36, "Jane", "Smith", "8765432109", "jane.smith@example.com"},
                    {37, "Alice", "Johnson", "7654321098", "alice.j@example.com"},
                    {38, "Bob", "Brown", "6543210987", "bob.brown@example.com"},
                    {39, "Charlie", "Davis", "5432109876", "charlie.d@example.com"},
                    {40, "Daisy", "Wilson", "4321098765", "daisy.w@example.com"},
                    {41, "Eve", "Martinez", "3210987654", "eve.m@example.com"},
                    {42, "Frank", "White", "2109876543", "frank.w@example.com"},
                    {43, "Grace", "Lee", "1098765432", "grace.l@example.com"},
                    {44, "Hank", "Taylor", "9087654321", "hank.t@example.com"},
                    {45, "Ivy", "Moore", "8076543210", "ivy.m@example.com"},
                    {46, "Jack", "Clark", "7065432109", "jack.c@example.com"},
                    {47, "Kelly", "Lewis", "6054321098", "kelly.l@example.com"},
                    {48, "Liam", "Scott", "5043210987", "liam.s@example.com"},
                    {49, "Mia", "Adams", "4032109876", "mia.a@example.com"},
                    {50, "Noah", "Hill", "3021098765", "noah.h@example.com"},
                    {51, "Olivia", "King", "2010987654", "olivia.k@example.com"},
                    {52, "Paul", "Wright", "1009876543", "paul.w@example.com"},
                    {53, "Quinn", "Lopez", "9998765432", "quinn.l@example.com"},
                    {54, "Rachel", "Perez", "8887654321", "rachel.p@example.com"},
                    {55, "Sam", "Torres", "7776543210", "sam.t@example.com"},
                    {56, "Tina", "Murphy", "6665432109", "tina.m@example.com"},
                    {57, "Uma", "Patel", "5554321098", "uma.p@example.com"},
                    {58, "Victor", "Evans", "4443210987", "victor.e@example.com"},
                    {59, "Wendy", "Carter", "3332109876", "wendy.c@example.com"},
                    {60, "Xavier", "Rivera", "2221098765", "xavier.r@example.com"},
                    {61, "Yara", "Gomez", "1110987654", "yara.g@example.com"},
                    {62, "Zane", "Bryant", "0009876543", "zane.b@example.com"},
                    {63, "Amy", "Ross", "9876501234", "amy.r@example.com"},
                    {64, "Ben", "Nelson", "8765402345", "ben.n@example.com"}
            };

            // Loop through the contacts and execute insert
            for (Object[] contact : contacts) {
                pstmt.setInt(1, (Integer) contact[0]);           // id
                pstmt.setString(2, (String) contact[1]);         // first_name
                pstmt.setString(3, (String) contact[2]);         // last_name
                pstmt.setString(4, (String) contact[3]);         // phone_number
                pstmt.setString(5, (String) contact[4]);         // email
                pstmt.executeUpdate();
            }

            System.out.println("Contacts inserted successfully!");
            System.out.println("Contacts inserted successfully!");

// Display contents of the 'contacts' table
            Statement showContentStmt = con.createStatement();
            ResultSet contentRs = showContentStmt.executeQuery("SELECT distinct * FROM contacts");
            System.out.println("Contents of the 'contacts' table:");
            while (contentRs.next()) {
                System.out.println(
                        contentRs.getInt("id") + " | " +
                                contentRs.getString("first_name") + " | " +
                                contentRs.getString("last_name") + " | " +
                                contentRs.getString("phone_number") + " | " +
                                contentRs.getString("email")
                );
            }

// Close connection
            pstmt.close();
            showContentStmt.close();
            con.close();

        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }
}
