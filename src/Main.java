//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();

        // UC1: Adding a contact
        Contact contact1 = new Contact("John", "Doe", "123 Main St", "Springfield", "Illinois", "62704", "1234567890", "john.doe@example.com");
        addressBook.addContact(contact1);
    }
}



