package contacts;

import java.time.LocalDateTime;

public class Company extends Element {
    private String brand;
    private String address;

    public Company(String brand, String address, String phone, String creationDate, String lastEditDate) {
        super(phone, creationDate, lastEditDate);
        this.brand = brand;
        this.address = address;
    }

    @Override
    public void getAllFields(){
        System.out.println("Organization name: " + this.getBrand());
        System.out.println("Address: " + this.getAddress());
        System.out.println("Number: " + this.getPhone());
        System.out.println("Time created: " + this.getCreationDate().substring(0, 16));
        System.out.println("Time last edit: " + this.getLastEditDate().substring(0, 16));
    }

    @Override
    public void changeField(String field, String volume) {
        super.changeField(field, volume);
        switch (field){
            case ("name") :
                this.setBrand(volume);
                break;
            case ("address") :
                this.setAddress(volume);
                break;
            case ("number") :
                this.setPhone(volume);
                break;
        }
        this.setLastEditDate(String.valueOf(LocalDateTime.now()));
    }

    @Override
    public String getField(String field) {
        switch (field) {
            case ("name") :
                return this.getBrand();
            case ("address") :
                return this.getAddress();
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
