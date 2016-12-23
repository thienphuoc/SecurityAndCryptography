package tpgame.securityandcryptography.data;

import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;

/**
 * Created by thienphuoc on 11/14/2016.
 */
public class SMSModel implements Serializable {

    private String phonenumber;
    private   String timeStamp;
    private  String body;

    public SMSModel(String phonenumber, String timeStamp, String body) {
        this.phonenumber = phonenumber;
        this.timeStamp = timeStamp;
        this.body = body;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
