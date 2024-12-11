import java.util.ArrayList;
import java.util.List;

class AddressBook {
    private List<Contact> contacts = new ArrayList<>();

    // UC1: Add a contact
    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    // Display all contacts
    public void displayContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }
}
