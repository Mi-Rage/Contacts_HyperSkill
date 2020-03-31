package contacts;

import java.util.ArrayList;

public abstract class Element {
    private String phone;
    private String creationDate;
    private String lastEditDate;

    public Element(String phone, String creationDate, String lastEditDate) {
        this.phone = phone;
        this.creationDate = creationDate;
        this.lastEditDate = lastEditDate;
    }

    public void getAllFields(){
    }

    public ArrayList<String> getAllItemField(){
        return null;
    }

    public void changeField(String field, String volume){

    }

    public String getField(String field){
        switch (field) {
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



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(String lastEditDate) {
        this.lastEditDate = lastEditDate;
    }
}
