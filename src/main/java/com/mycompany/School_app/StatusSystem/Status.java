package com.mycompany.School_app.StatusSystem;

import java.util.Date;

public class Status {
    int errorCode;
    String statusMessage;
    Date statusDate;

    public Status(int errorCode, String statusMessage) {
        this.errorCode = errorCode;
        this.statusMessage = statusMessage;
        this.statusDate = new Date();
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getstatusMessage() {
        return statusMessage;
    }

    public void setstatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    @Override
    public String toString() {
        return "Status{" +
                "errorCode=" + errorCode +
                ", statusMessage='" + statusMessage + '\'' +
                ", statusDate=" + statusDate +
                '}';
    }
}
