package contacts;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PhoneBook {

    List<Person> storage = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void init() {

        while (true) {
            System.out.print("Enter action (add, remove, edit, count, list, exit): ");
            String choice = scanner.nextLine();
            switch (choice) {
                case ("add"):
                    addPerson();
                    break;
                case ("remove"):
                    listPerson();
                    removePerson();
                    break;
                case ("edit"):
                    listPerson();
                    editPerson();
                    break;
                case ("count"):
                    countPerson();
                    break;
                case ("list"):
                    listPerson();
                    break;
                case ("exit"):
                    System.exit(0);
            }
        }
    }

    public void addPerson() {
        Person person = new Person();
        System.out.print("Enter the name of the person: ");
        person.setName(scanner.nextLine());
        System.out.print("Enter the surname of the person: ");
        person.setSurname(scanner.nextLine());
        System.out.print("Enter the number: ");
        String number = scanner.nextLine();
        if (checkNumber(number)) {
            person.setPhone(number);
        } else {
            System.out.println("Wrong number format!");
            person.setPhone("[no number]");
        }
        storage.add(person);
        System.out.println("A record created!");
    }

    private boolean checkNumber(String number) {
        final String regex = "^\\+?([\\da-zA-Z]+[\\s-]?)?(\\([\\da-zA-Z]{2,}(\\)[\\s-]|\\)$))?([\\da-zA-Z]{2,}[\\s-]?)*([\\da-zA-Z]{2,})?$";
        return number.matches(regex);
    }

    public void countPerson() {
        System.out.println("The Phone Book has " + storage.size() + " records.");
    }

    public void listPerson() {
        for (int i = 0; i < storage.size(); i++) {
            System.out.println((i + 1) + ". "
                    + storage.get(i).getName() + " "
                    + storage.get(i).getSurname() + ", "
                    + storage.get(i).getPhone());
        }
    }

    public void removePerson(){
        if (storage.size() == 0) {
            System.out.println("No records to remove!");
            return;
        }
        System.out.print("Select a record: ");
        int selectRec = scanner.nextInt();
        if (selectRec > storage.size()) {
            System.out.println("ERROR! Select record biggest then capacity");
            return;
        }
        storage.remove(selectRec - 1);
        System.out.println("The record removed!");
    }

    public void editPerson() {
        if (storage.size() == 0) {
            System.out.println("No records to edit!");
            return;
        }
        System.out.print("Select a record: ");
        int record = Integer.parseInt(scanner.nextLine());
        if (record > storage.size()) {
            System.out.println("ERROR! record has bigeer!");
            return;
        }
        System.out.print("Select a field (name, surname, number): ");
//        scanner.nextLine();
        String field = scanner.nextLine();
        switch (field) {
            case ("name") :
                System.out.print("Enter name: ");
                storage.get(record-1).setName(scanner.nextLine());
                break;
            case ("surname") :
                System.out.print("Enter surname: ");
                storage.get(record-1).setSurname(scanner.nextLine());
                break;
            case ("number") :
                System.out.print("Enter number: ");
                String number = scanner.nextLine();
                if (checkNumber(number)) {
                    storage.get(record-1).setPhone(number);
                } else {
                    System.out.println("Wrong number format!");
                    storage.get(record-1).setPhone("[no number]");
                }
                break;
        }
        System.out.println("The record updated!");
    }
}
