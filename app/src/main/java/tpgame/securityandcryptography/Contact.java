package tpgame.securityandcryptography;

import java.io.Serializable;

/**
 * Created by thienphuoc on 11/4/2016.
 */
public class Contact implements Serializable{

    private String name;
    private String phone;

    public Contact() {

    }
    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString(){
        return  this.name+"\n"+this.phone;
    }
}
