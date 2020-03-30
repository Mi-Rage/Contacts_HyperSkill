package contacts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PhoneBook {

    List<Element> storage = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void init() {

        while (true) {
            System.out.print("Enter action (add, remove, edit, count, info, exit): ");
            String choice = scanner.nextLine();
            switch (choice) {
                case ("add"):
                    addElement();
                    break;
                case ("remove"):
                    removeElements();
                    break;
                case ("edit"):
                    editElements();
                    break;
                case ("count"):
                    countElements();
                    break;
                case ("info"):
                    infoElements();
                    break;
                case ("exit"):
                    System.exit(0);
            }
        }
    }

    public void addElement() {
        System.out.print("Enter the type (person, organization): ");
        String type = scanner.nextLine();
        switch (type) {
            case ("person"):
                addPerson();
                break;
            case ("organization"):
                addCompany();
                break;
        }
    }

    public void addPerson() {
        Element person = new Person();
        person.setPerson(true);

        System.out.print("Enter the name: ");
        ((Person) person).setName(scanner.nextLine());
        System.out.print("Enter the surname: ");
        ((Person) person).setSurname(scanner.nextLine());
        System.out.print("Enter the birth date: ");
        String birthDay = scanner.nextLine();
        try {
            ((Person) person).setBirthDay(String.valueOf(LocalDate.parse(birthDay)));
        } catch (Exception e) {
            System.out.println("Bad birth date!");
            ((Person) person).setBirthDay("[no data]");
        }
        System.out.print("Enter the gender (M, F): ");
        String gender = scanner.nextLine();
        if (gender.equals("M") || gender.equals("F")) {
            ((Person) person).setGender(gender);
        } else {
            System.out.println("Bad gender!");
            ((Person) person).setGender("[no data]");
        }
        System.out.print("Enter the number: ");
        String number = scanner.nextLine();
        if (checkNumber(number)) {
            person.setPhone(number);
        } else {
            System.out.println("Wrong number format!");
            person.setPhone("[no number]");
        }

        person.setCreationDate(String.valueOf(LocalDateTime.now()));
        person.setLastEditDate(String.valueOf(LocalDateTime.now()));

        storage.add(person);
        System.out.println("A record added!");
        System.out.println();
    }

    public void addCompany() {
        Element company = new Company();
        company.setPerson(false);

        System.out.print("Enter the organization name: ");
        ((Company) company).setBrand(scanner.nextLine());
        System.out.print("Enter the address: ");
        ((Company) company).setAddress(scanner.nextLine());
        System.out.print("Enter the number: ");
        String number = scanner.nextLine();
        if (checkNumber(number)) {
            company.setPhone(number);
        } else {
            System.out.println("Wrong number format!");
            company.setPhone("[no number]");
        }

        company.setCreationDate(String.valueOf(LocalDateTime.now()));
        company.setLastEditDate(String.valueOf(LocalDateTime.now()));

        storage.add(company);
        System.out.println("A record added!");
        System.out.println();
    }

    private boolean checkNumber(String number) {
        final String regex = "^\\+?([\\da-zA-Z]+[\\s-]?)?(\\([\\da-zA-Z]{2,}(\\)[\\s-]|\\)$))?([\\da-zA-Z]{2,}[\\s-]?)*([\\da-zA-Z]{2,})?$";
        return number.matches(regex);
    }

    public void countElements() {
        System.out.println("The Phone Book has " + storage.size() + " records.");
        System.out.println();
    }

    public void listOfElements() {
        String output;
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).isPerson()) {
                output = ((Person) storage.get(i)).getName() + " " + ((Person) storage.get(i)).getSurname();
            } else {
                output = ((Company) storage.get(i)).getBrand();
            }
            System.out.println((i + 1) + ". " + output);
        }
    }

    public void infoElements() {
        listOfElements();

        System.out.print("Select a record: ");
        String selectedRecord = scanner.nextLine();
        int i = Integer.parseInt(selectedRecord) - 1;

        if (storage.get(i).isPerson()) {
            System.out.println("Name: " + ((Person) storage.get(i)).getName());
            System.out.println("Surname: " + ((Person) storage.get(i)).getSurname());
            System.out.println("Birth date: " + ((Person) storage.get(i)).getBirthDay());
            System.out.println("Gender: " + ((Person) storage.get(i)).getGender());
            System.out.println("Number: " + storage.get(i).getPhone());
            System.out.println("Time created: " + storage.get(i).getCreationDate().substring(0, 16));
            System.out.println("Time last edit: " + storage.get(i).getLastEditDate().substring(0, 16));
        } else {
            System.out.println("Organization name: " + ((Company) storage.get(i)).getBrand());
            System.out.println("Address: " + ((Company) storage.get(i)).getAddress());
            System.out.println("Number: " + storage.get(i).getPhone());
            System.out.println("Time created: " + storage.get(i).getCreationDate().substring(0, 16));
            System.out.println("Time last edit: " + storage.get(i).getLastEditDate().substring(0, 16));
        }
        System.out.println();
    }

    public void removeElements() {

        if (storage.size() == 0) {
            System.out.println("No records to remove!");
            return;
        }

        listOfElements();

        System.out.print("Select a record: ");
        int selectRec = scanner.nextInt();
        if (selectRec > storage.size()) {
            System.out.println("ERROR! Select record biggest then capacity");
            return;
        }

        storage.remove(selectRec - 1);
        System.out.println("The record removed!");
        System.out.println();
    }

    public void editElements() {
        if (storage.size() == 0) {
            System.out.println("No records to edit!");
            return;
        }

        listOfElements();

        System.out.print("Select a record: ");
        String selectedRecord = scanner.nextLine();
        int i = Integer.parseInt(selectedRecord) - 1;

        if (storage.get(i).isPerson()) {
            System.out.print("Select a field (name, surname, number): ");
            String field = scanner.nextLine();
            switch (field) {
                case ("name"):
                    System.out.print("Enter name: ");
                    ((Person) storage.get(i)).setName(scanner.nextLine());
                    break;
                case ("surname"):
                    System.out.print("Enter surname: ");
                    ((Person) storage.get(i)).setSurname(scanner.nextLine());
                    break;
                case ("birth"):
                    System.out.print("Enter birth: ");
                    String birthDay = scanner.nextLine();
                    try {
                        ((Person) storage.get(i)).setBirthDay(String.valueOf(LocalDate.parse(birthDay)));
                    } catch (Exception e) {
                        System.out.println("Bad birth date!");
                        ((Person) storage.get(i)).setBirthDay("[no data]");
                    }
                    break;
                case ("gender"):
                    System.out.print("Enter gender: ");
                    String gender = scanner.nextLine();
                    if (gender.equals("M") || gender.equals("F")) {
                        ((Person) storage.get(i)).setGender(gender);
                    } else {
                        System.out.println("Bad gender!");
                        ((Person) storage.get(i)).setGender("[no data]");
                    }
                    break;
                case ("number"):
                    System.out.print("Enter number: ");
                    String number = scanner.nextLine();
                    if (checkNumber(number)) {
                        storage.get(i).setPhone(number);
                    } else {
                        System.out.println("Wrong number format!");
                        storage.get(i).setPhone("[no number]");
                    }
                    break;
            }
        } else {
            System.out.print("Select a field (name, address, number): ");
            String field = scanner.nextLine();
            switch (field) {
                case ("name"):
                    System.out.print("Enter the organization name: ");
                    ((Company) storage.get(i)).setBrand(scanner.nextLine());
                    break;
                case ("address"):
                    System.out.print("Enter the address: ");
                    ((Company) storage.get(i)).setAddress(scanner.nextLine());
                    break;
                case ("number"):
                    System.out.print("Enter number: ");
                    String number = scanner.nextLine();
                    if (checkNumber(number)) {
                        storage.get(i).setPhone(number);
                    } else {
                        System.out.println("Wrong number format!");
                        storage.get(i).setPhone("[no number]");
                    }
                    break;
            }
        }
        storage.get(i).setLastEditDate(String.valueOf(LocalDateTime.now()));
        System.out.println("The record updated!");
        System.out.println();
    }
}
