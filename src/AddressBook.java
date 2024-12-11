import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class AddressBook {
    private List<Contact> contacts = new ArrayList<>();

    // UC1: Add a contact
//    public void addContact(Contact contact) {
//        contacts.add(contact);
//    }

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
//    public List<Contact> searchByCityOrState(String location) {
//        List<Contact> result = new ArrayList<>();
//        for (Contact contact : contacts) {
//            if (contact.getCity().equalsIgnoreCase(location) || contact.getState().equalsIgnoreCase(location)) {
//                result.add(contact);
//            }
//        }
//        return result;
//    }


    // UC6: View contacts by city or state
    public Map<String, List<Contact>> viewContactsByCityOrState() {
        Map<String, List<Contact>> locationMap = new HashMap<>();
        for (Contact contact : contacts) {
            locationMap.computeIfAbsent(contact.getCity(), k -> new ArrayList<>()).add(contact);
            locationMap.computeIfAbsent(contact.getState(), k -> new ArrayList<>()).add(contact);
        }
        return locationMap;
    }
    // UC7: Ensure no duplicate entries
    public void addContact(Contact contact) {
        boolean isDuplicate = contacts.stream()
                .anyMatch(existingContact -> existingContact.getFirstName().equals(contact.getFirstName()) &&
                        existingContact.getLastName().equals(contact.getLastName()));
        if (!isDuplicate) {
            contacts.add(contact);
        } else {
            System.out.println("Duplicate contact found. Cannot add.");
        }
    }
    // UC8: Search persons by city or state
    public List<Contact> searchByCityOrState(String cityOrState) {
        return contacts.stream()
                .filter(contact -> contact.getCity().equalsIgnoreCase(cityOrState) ||
                        contact.getState().equalsIgnoreCase(cityOrState))
                .toList();
    }

    // UC9: View persons by city or state
    public Map<String, List<Contact>> viewByCityOrState() {
        Map<String, List<Contact>> cityStateMap = new HashMap<>();
        contacts.forEach(contact -> {
            String key = contact.getCity() + ", " + contact.getState();
            cityStateMap.computeIfAbsent(key, k -> new ArrayList<>()).add(contact);
        });
        return cityStateMap;
    }


    // UC10: Count persons by city or state
    public Map<String, Long> countByCityOrState() {
        return contacts.stream()
                .collect(Collectors.groupingBy(contact -> contact.getCity() + ", " + contact.getState(), Collectors.counting()));
    }
    // Display all contacts
    public void displayContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }
}
