import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

class AddressBook {
    private Connection con;

    // Constructor: Establish database connection
    public AddressBook() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/addressbook", "root", new Constants().getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UC1: Add a contact to the database
    public void addContact(Contact contact) {
        String query = "INSERT INTO contacts (first_name, last_name, address, city, state, zip, phone_number, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, contact.getFirstName());
            stmt.setString(2, contact.getLastName());
            stmt.setString(3, contact.getAddress());
            stmt.setString(4, contact.getCity());
            stmt.setString(5, contact.getState());
            stmt.setString(6, contact.getZip());
            stmt.setString(7, contact.getPhoneNumber());
            stmt.setString(8, contact.getEmail());
            stmt.executeUpdate();
            System.out.println("Contact added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UC2: Edit an existing contact
    public void editContact(String firstName, String lastName, Contact updatedContact) {
        String query = "UPDATE contacts SET address = ?, city = ?, state = ?, zip = ?, phone_number = ?, email = ? " +
                "WHERE first_name = ? AND last_name = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, updatedContact.getAddress());
            stmt.setString(2, updatedContact.getCity());
            stmt.setString(3, updatedContact.getState());
            stmt.setString(4, updatedContact.getZip());
            stmt.setString(5, updatedContact.getPhoneNumber());
            stmt.setString(6, updatedContact.getEmail());
            stmt.setString(7, firstName);
            stmt.setString(8, lastName);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Contact updated successfully.");
            } else {
                System.out.println("Contact not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UC3: Delete a contact
    public void deleteContact(String firstName, String lastName) {
        String query = "DELETE FROM contacts WHERE first_name = ? AND last_name = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Contact deleted successfully.");
            } else {
                System.out.println("Contact not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UC4: Add multiple contacts (batch addition)
    public void addMultipleContacts(List<Contact> contacts) {
        contacts.forEach(this::addContact);
    }

    // UC5 & UC8: Search persons by city or state
    public void searchByCityOrState(String cityOrState) {
        String query = "SELECT * FROM contacts WHERE city = ? OR state = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, cityOrState);
            stmt.setString(2, cityOrState);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(
                        "Name: " + rs.getString("first_name") + " " + rs.getString("last_name") +
                                ", City: " + rs.getString("city") +
                                ", State: " + rs.getString("state")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UC6 & UC9: View persons by city or state
    public void viewByCityOrState() {
        String query = "SELECT city, state, GROUP_CONCAT(CONCAT(first_name, ' ', last_name)) AS persons " +
                "FROM contacts GROUP BY city, state";
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println(
                        "City: " + rs.getString("city") +
                                ", State: " + rs.getString("state") +
                                ", Persons: " + rs.getString("persons")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UC7: Ensure no duplicate entries
    public void addContactWithCheck(Contact contact) {
        String checkQuery = "SELECT COUNT(*) FROM contacts WHERE first_name = ? AND last_name = ?";
        try (PreparedStatement checkStmt = con.prepareStatement(checkQuery)) {
            checkStmt.setString(1, contact.getFirstName());
            checkStmt.setString(2, contact.getLastName());
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                System.out.println("Duplicate contact found. Cannot add.");
            } else {
                addContact(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UC10: Count persons by city or state
    public void countByCityOrState() {
        String query = "SELECT city, state, COUNT(*) as count FROM contacts GROUP BY city, state";
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println(
                        "City: " + rs.getString("city") +
                                ", State: " + rs.getString("state") +
                                ", Count: " + rs.getInt("count")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UC11: Sort entries alphabetically by name, city, state, or zip
    public void sortContacts(String sortBy) {
        String query = "SELECT * FROM contacts ORDER BY " + sortBy;
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println(
                        "Name: " + rs.getString("first_name") + " " + rs.getString("last_name") +
                                ", City: " + rs.getString("city") +
                                ", State: " + rs.getString("state") +
                                ", ZIP: " + rs.getString("zip")
                );
            }
        } catch (SQLException e) {
            System.out.println("Invalid column name to sort by.");
            e.printStackTrace();
        }
    }

    // UC12: Display all contacts
    public void displayContacts() {
        String query = "SELECT * FROM contacts";
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println(
                        "Name: " + rs.getString("first_name") + " " + rs.getString("last_name") +
                                ", Address: " + rs.getString("address") +
                                ", City: " + rs.getString("city") +
                                ", State: " + rs.getString("state") +
                                ", ZIP: " + rs.getString("zip") +
                                ", Phone: " + rs.getString("phone_number") +
                                ", Email: " + rs.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();

        // Add a new contact
        addressBook.addContact(new Contact("John", "Doe", "123 Street", "New York", "NY", "10001", "1234567890", "john.doe@example.com"));
        addressBook.addContact(new Contact("satveer", "swami", "123 Street", "HMH", "RAJ", "10001", "12454567890", "ss@example.com"));

        // Edit a contact
        addressBook.editContact("John", "Doe", new Contact("John", "Doe", "456 Avenue", "Los Angeles", "CA", "90001", "0987654321", "john.updated@example.com"));

        // Display all contacts
        addressBook.displayContacts();

        // Search by city/state
        addressBook.searchByCityOrState("Los Angeles");

        // Count by city/state
        addressBook.countByCityOrState();

        // Delete a contact
        addressBook.deleteContact("John", "Doe");
    }
}
