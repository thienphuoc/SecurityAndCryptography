package tpgame.securityandcryptography.data;

import java.io.Serializable;

/**
 * Created by thienphuoc on 11/14/2016.
 */
public class CallLogHistory implements Serializable {
    private String phName ;
    private  String phNum ;
    private String callType;
    private String callDate ;
    private  String callDuration ;

    public CallLogHistory(){

    }

    public CallLogHistory(String phName, String phNum, String callType, String callDate, String callDuration) {
        this.phName = phName;
        this.phNum = phNum;
        this.callType = callType;
        this.callDate = callDate;
        this.callDuration = callDuration;
    }

    public String getPhName() {
        return phName;
    }

    public void setPhName(String phName) {
        this.phName = phName;
    }

    public String getPhNum() {
        return phNum;
    }

    public void setPhNum(String phNum) {
        this.phNum = phNum;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getCallDate() {
        return callDate;
    }

    public void setCallDate(String callDate) {
        this.callDate = callDate;
    }


    public String getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(String callDuration) {
        this.callDuration = callDuration;
    }
}