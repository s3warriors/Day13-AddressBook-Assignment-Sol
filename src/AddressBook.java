import java.util.ArrayList;
import java.util.List;

class AddressBook {
    private List<Contact> contacts = new ArrayList<>();

    // UC1: Add a contact
    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    // UC2: Edit an existing contact
    public void editContact(String firstName, String lastName, Contact updatedContact) {
        for (Contact contact : contacts) {
            if (contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName)) {
                contact.setAddress(updatedContact.getAddress());
                contact.setCity(updatedContact.getCity());
                contact.setState(updatedContact.getState());
                contact.setZip(updatedContact.getZip());
                contact.setPhoneNumber(updatedContact.getPhoneNumber());
                contact.setEmail(updatedContact.getEmail());
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    // UC3: Delete a contact
    public void deleteContact(String firstName, String lastName) {
        contacts.removeIf(contact -> contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName));
    }
    // UC4: Add multiple contacts (Batch addition)
    public void addMultipleContacts(List<Contact> newContacts) {
        contacts.addAll(newContacts);
    }


    // UC5: Search contacts by city or state
    public List<Contact> searchByCityOrState(String location) {
        List<Contact> result = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getCity().equalsIgnoreCase(location) || contact.getState().equalsIgnoreCase(location)) {
                result.add(contact);
            }
        }
        return result;
    }



    // Display all contacts
    public void displayContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }
}
