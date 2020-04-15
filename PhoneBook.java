package contacts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


public class PhoneBook {

    List<Element> storage = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    String choice = "";

    public void init() {

        while (true) {
            System.out.print("[menu] Enter action (add, list, search, count, exit): ");
            choice = scanner.nextLine();
            switch (choice) {
                case ("add"):
                    addElement();
                    break;
                case ("list"):
                    listOfElements();
                    while (true){
                        if (choice.equals("menu")) {
                            break;
                        }
                        System.out.print("[list] Enter action ([number], back): ");
                        choice = scanner.nextLine();
                        if (choice.equals("back")) {
                            break;
                        } else {
                            int number = Integer.parseInt(choice) - 1;
                            storage.get(number).getAllFields();
                            System.out.println();
                            while (!choice.equals("menu")) {
                                System.out.print("[record] Enter action (edit, delete, menu): ");
                                choice = scanner.nextLine();
                                switch (choice) {
                                    case ("edit") :
                                        editElements(storage.get(number));
                                        break;
                                    case ("delete") :
                                        removeElements(number);
                                        choice = "menu";
                                        break;
                                    case ("menu") :
                                        System.out.println();
                                        break;
                                }
                            }
                        }

                    }
                    break;
                case("search"):
                    searchElement();
                    break;
                case ("edit"):
                    //editElements();
                    break;
                case ("count"):
                    countElements();
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
        if (storage.size() == 0) {
            System.out.println("We have no elements");
            System.out.println();
            choice = "menu";
            return;
        }
        String output;
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getClass() == Person.class) {
                output = storage.get(i).getField("name") + " " + storage.get(i).getField("surname");
            } else {
                output = storage.get(i).getField("name");
            }
            System.out.println((i + 1) + ". " + output);
        }
        System.out.println();
    }

    public void searchElement(){
        ArrayList<Integer> keyListSearch = new ArrayList<>();
        HashMap<Integer, String> resultSearch = new HashMap<>();

        System.out.print("Enter search query: ");
        String output;
        String query = scanner.nextLine();
        for (int i = 0; i < storage.size(); i++) {
            for(String each : storage.get(i).getAllItemField()) {
                if (each.toLowerCase().matches("(.*)" + query + "(.*)")) {
                    if (storage.get(i).getClass() == Person.class) {
                        output = storage.get(i).getField("name") + " " + storage.get(i).getField("surname");
                    } else {
                        output = storage.get(i).getField("name");
                    }
                    resultSearch.put(i,output);
                    keyListSearch.add(i);
                    //resultSearch.add(output);
                    break;
                }
            }

        }
        System.out.printf("Found %d results:\n", resultSearch.size());
        for(int i = 0; i < keyListSearch.size(); i++){
            System.out.println((i + 1) + ". " + resultSearch.get(keyListSearch.get(i)));
        }
        System.out.println();
    }

    public void infoElements() {
        listOfElements();

        System.out.print("Select a record: ");
        String selectedRecord = scanner.nextLine();
        int i = Integer.parseInt(selectedRecord) - 1;

        storage.get(i).getAllFields();

        System.out.println();
    }

    public void removeElements(int number) {
//
//        if (storage.size() == 0) {
//            System.out.println("No records to remove!");
//            return;
//        }
//
//        listOfElements();
//
//        System.out.print("Select a record: ");
//        int selectRec = scanner.nextInt();
//        if (selectRec > storage.size()) {
//            System.out.println("ERROR! Select record biggest then capacity");
//            return;
//        }

        storage.remove(number);
        System.out.println("The record removed!");
        System.out.println();
    }

    public void editElements(Element element) {

        if (element.getClass() == Person.class) {
            System.out.print("Select a field (name, surname, number): ");
            String field = scanner.nextLine();
            switch (field) {
                case ("name"):
                    System.out.print("Enter name: ");
                    ((Person) element).setName(scanner.nextLine());
                    break;
                case ("surname"):
                    System.out.print("Enter surname: ");
                    ((Person) element).setSurname(scanner.nextLine());
                    break;
                case ("birth"):
                    System.out.print("Enter birth: ");
                    String birthDay = scanner.nextLine();
                    try {
                        ((Person) element).setBirthDay(String.valueOf(LocalDate.parse(birthDay)));
                    } catch (Exception e) {
                        System.out.println("Bad birth date!");
                        ((Person) element).setBirthDay("[no data]");
                    }
                    break;
                case ("gender"):
                    System.out.print("Enter gender: ");
                    String gender = scanner.nextLine();
                    if (gender.equals("M") || gender.equals("F")) {
                        ((Person) element).setGender(gender);
                    } else {
                        System.out.println("Bad gender!");
                        ((Person) element).setGender("[no data]");
                    }
                    break;
                case ("number"):
                    System.out.print("Enter number: ");
                    String number = scanner.nextLine();
                    if (wrongNumber(number)) {
                        System.out.println("Wrong number format!");
                        element.setPhone("[no number]");
                    } else {
                        element.setPhone(number);
                    }

                    break;
            }
        } else {
            System.out.print("Select a field (name, address, number): ");
            String field = scanner.nextLine();
            switch (field) {
                case ("name"):
                    System.out.print("Enter the organization name: ");
                    ((Company) element).setBrand(scanner.nextLine());
                    break;
                case ("address"):
                    System.out.print("Enter the address: ");
                    ((Company) element).setAddress(scanner.nextLine());
                    break;
                case ("number"):
                    System.out.print("Enter number: ");
                    String phone = scanner.nextLine();
                    if (wrongNumber(phone)) {
                        System.out.println("Wrong number format!");
                        element.setPhone("[no number]");
                    } else {
                        element.setPhone(phone);
                    }
                    break;
            }
        }
        element.setLastEditDate(String.valueOf(LocalDateTime.now()));
        System.out.println("Saved");
        element.getAllFields();
        System.out.println();
    }
}
