package contacts;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneBook {
    Person person = new Person();
    List<Person> storage = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void init() {

        while (true) {
            System.out.print("Enter action (add, remove, edit, count, list, exit): ");
            String choice = scanner.next();
            switch (choice) {
                case ("add"):
                    addPerson();
                    break;
                case ("remove"):
                    //do remove
                    break;
                case ("edit"):
                    //do edit
                    break;
                case ("count"):
                    countPerson();
                    break;
                case ("list"):
                    //do list
                    break;
                case ("exit"):
                    System.exit(0);
            }
        }

    }

    public void addPerson(){

        System.out.println("Enter the name of the person:");
        person.setName(scanner.next());
        System.out.println("Enter the surname of the person:");
        person.setSurname(scanner.next());
        System.out.println("Enter the number:");
        person.setPhone(scanner.next());
        storage.add(person);

        System.out.println("A record created!\n" +
                "A Phone Book with a single record created!");
    }

    public void countPerson() {
        System.out.println("The Phone Book has "+ storage.size() + " records.");
    }
}
