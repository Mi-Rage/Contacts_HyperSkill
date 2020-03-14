package contacts;

import java.util.Scanner;

public class PhoneBook {
    Person person = new Person();
    Scanner scanner = new Scanner(System.in);

    public void init() {
        System.out.println("Enter the name of the person:");
        person.setName(scanner.next());
        System.out.println("Enter the surname of the person:");
        person.setSurname(scanner.next());
        System.out.println("Enter the number:");
        person.setPhone(scanner.next());

        System.out.println("A record created!\n" +
                "A Phone Book with a single record created!");
    }
}
