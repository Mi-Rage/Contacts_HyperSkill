package contacts;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Person extends Element {
    private String name;
    private String surname;
    private String birthDay;
    private String gender;

    public Person(String name, String surname, String birthDay, String gender,
                  String phone, String creationDate, String lastEditDate) {
        super(phone, creationDate, lastEditDate);
        this.name = name;
        this.surname = surname;
        this.birthDay = birthDay;
        this.gender = gender;
    }

    @Override
    public void getAllFields() {
        System.out.println("Name: " + this.getName());
        System.out.println("Surname: " + this.getSurname());
        System.out.println("Birth date: " + this.getBirthDay());
        System.out.println("Gender: " + this.getGender());
        System.out.println("Number: " + this.getPhone());
        System.out.println("Time created: " + this.getCreationDate().substring(0, 16));
        System.out.println("Time last edit: " + this.getLastEditDate().substring(0, 16));
    }

    @Override
    public ArrayList<String> getAllItemField() {
        ArrayList<String> items = new ArrayList<>();
        items.add(this.getName());
        items.add(this.getSurname());
        items.add(this.getBirthDay());
        items.add(this.getGender());
        items.add(this.getPhone());
        items.add(this.getCreationDate().substring(0, 16));
        items.add(this.getLastEditDate().substring(0, 16));
        return items;
    }

    @Override
    public void changeField(String field, String volume) {
        switch (field) {
            case ("name"):
                this.setName(volume);
                break;
            case ("surname"):
                this.setSurname(volume);
                break;
            case ("birth"):
                this.setBirthDay(volume);
                break;
            case ("gender"):
                this.setGender(volume);
                break;
            case ("number"):
                this.setPhone(volume);
                break;
        }
        this.setLastEditDate(String.valueOf(LocalDateTime.now()));
    }

    @Override
    public String getField(String field) {
        switch (field) {
            case ("name"):
                return this.getName();
            case ("surname"):
                return this.getSurname();
            case ("birth"):
                return this.getBirthDay();
            case ("gender"):
                return this.getGender();
            case "phone":
                return this.getPhone();
            case "creationDate":
                return this.getCreationDate();
            case "lastEditDate":
                return this.getLastEditDate();

            default:
                return null;
        }
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
