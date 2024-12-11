import java.util.Arrays;
import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();

        // UC1: Adding a contact
        Contact contact1 = new Contact("John", "Doe", "123 Main St", "Springfield", "Illinois", "62704", "1234567890", "john.doe@example.com");
        addressBook.addContact(contact1);

        // UC2: Editing the contact
        Contact updatedContact = new Contact("John", "Doe", "456 Elm St", "Springfield", "Illinois", "62704", "9876543210", "john.updated@example.com");
        addressBook.editContact("John", "Doe", updatedContact);

        // UC3: Deleting a contact
        addressBook.deleteContact("John", "Doe");
        // UC4: Adding multiple contacts
        Contact contact2 = new Contact("Jane", "Smith", "789 Oak St", "Chicago", "Illinois", "60616", "1112223333", "jane.smith@example.com");
        Contact contact3 = new Contact("Emily", "Johnson", "321 Pine St", "Peoria", "Illinois", "61615", "4445556666", "emily.j@example.com");
        addressBook.addMultipleContacts(Arrays.asList(contact2, contact3));

        // UC5: Search contacts by city or state
        List<Contact> searchResults = addressBook.searchByCityOrState("Chicago");
        System.out.println("Contacts in Chicago: " + searchResults);

        // UC6: View contacts by city or state
        Map<String, List<Contact>> viewMap = addressBook.viewContactsByCityOrState();
        System.out.println("Contacts grouped by city/state: " + viewMap);

    }
}



