package com.berkhanakdag.restoranv1.Model;

import com.google.gson.annotations.SerializedName;

public class SiparisVerModel {

    @SerializedName("status")
    public String status;
    @SerializedName("message")
    public String message;

    public SiparisVerModel(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
