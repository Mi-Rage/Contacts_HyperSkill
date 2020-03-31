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

        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter the birth date: ");
        String inputDay = scanner.nextLine();
        String birthDay = "[no data]";
        try {
            birthDay = String.valueOf(LocalDate.parse(inputDay));
        } catch (Exception e) {
            System.out.println("Bad birth date!");
        }
        System.out.print("Enter the gender (M, F): ");
        String gender = scanner.nextLine();
        gender = checkGender(gender);
        System.out.print("Enter the number: ");
        String phone = scanner.nextLine();
        if (wrongNumber(phone)) {
            System.out.println("Wrong number format!");
            phone = "[no number]";
        }

        String creationDate= String.valueOf(LocalDateTime.now());
        String lastEditDate = String.valueOf(LocalDateTime.now());

        Element element = new Person(name, surname, birthDay, gender, phone, creationDate, lastEditDate);
        storage.add(element);

        System.out.println("A record added!");
        System.out.println();
    }

    public void addCompany() {
        System.out.print("Enter the organization name: ");
        String brand = scanner.nextLine();
        System.out.print("Enter the address: ");
        String address = scanner.nextLine();
        System.out.print("Enter the number: ");
        String phone = scanner.nextLine();
        if (wrongNumber(phone)) {
            System.out.println("Wrong number format!");
            phone = "[no number]";
        }

        String creationDate= String.valueOf(LocalDateTime.now());
        String lastEditDate = String.valueOf(LocalDateTime.now());

        Element element = new Company(brand, address, phone, creationDate,lastEditDate);
        storage.add(element);

        System.out.println("A record added!");
        System.out.println();
    }

    protected boolean wrongNumber(String phone){
        final String regex = "^\\+?([\\da-zA-Z]+[\\s-]?)?(\\([\\da-zA-Z]{2,}(\\)[\\s-]|\\)$))?([\\da-zA-Z]{2,}[\\s-]?)*([\\da-zA-Z]{2,})?$";
        return !phone.matches(regex);
    }

    private String checkGender(String gender) {
        if (!gender.equals("M") && !gender.equals("F")) {
            System.out.println("Bad gender!");
            return "[no data]";
        } else {
            return gender;
        }
    }

    public void countElements() {
        System.out.println("The Phone Book has " + storage.size() + " records.");
        System.out.println();
    }

    public void listOfElements() {
        String output;
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getClass() == Person.class) {
                output = storage.get(i).getField("name") + " " + storage.get(i).getField("surname");
            } else {
                output = storage.get(i).getField("name");
            }
            System.out.println((i + 1) + ". " + output);
        }
    }

    public void infoElements() {
        listOfElements();

        System.out.print("Select a record: ");
        String selectedRecord = scanner.nextLine();
        int i = Integer.parseInt(selectedRecord) - 1;

        storage.get(i).getAllFields();

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

        if (storage.get(i).getClass()== Person.class) {
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

                    break;
            }
        }
        storage.get(i).setLastEditDate(String.valueOf(LocalDateTime.now()));
        System.out.println("The record updated!");
        System.out.println();
    }
}
