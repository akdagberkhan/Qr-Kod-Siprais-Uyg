package com.berkhanakdag.restoranv1.Model;

import com.google.gson.annotations.SerializedName;

public class RestoranModel {

    @SerializedName("id")
    public String restoranId;
    @SerializedName("restoranAd")
    public String restoranAd;
    @SerializedName("restoranTelefon")
    public String restoranTelefon;
    @SerializedName("restoranMail")
    public String restoranMail;
    @SerializedName("restoranMasaSayisi")
    public String restoranMasaSayi;
    @SerializedName("password")
    public String restoranPass;
    @SerializedName("restoranFoto")
    public String restoranFoto;

    public RestoranModel(String restoranId, String restoranAd, String restoranTelefon, String restoranMail, String restoranMasaSayi, String restoranPass, String restoranFoto) {
        this.restoranId = restoranId;
        this.restoranAd = restoranAd;
        this.restoranTelefon = restoranTelefon;
        this.restoranMail = restoranMail;
        this.restoranMasaSayi = restoranMasaSayi;
        this.restoranPass = restoranPass;
        this.restoranFoto = restoranFoto;
    }

    public String getRestoranId() {
        return restoranId;
    }

    public void setRestoranId(String restoranId) {
        this.restoranId = restoranId;
    }

    public String getRestoranAd() {
        return restoranAd;
    }

    public void setRestoranAd(String restoranAd) {
        this.restoranAd = restoranAd;
    }

    public String getRestoranTelefon() {
        return restoranTelefon;
    }

    public void setRestoranTelefon(String restoranTelefon) {
        this.restoranTelefon = restoranTelefon;
    }

    public String getRestoranMail() {
        return restoranMail;
    }

    public void setRestoranMail(String restoranMail) {
        this.restoranMail = restoranMail;
    }

    public String getRestoranMasaSayi() {
        return restoranMasaSayi;
    }

    public void setRestoranMasaSayi(String restoranMasaSayi) {
        this.restoranMasaSayi = restoranMasaSayi;
    }

    public String getRestoranPass() {
        return restoranPass;
    }

    public void setRestoranPass(String restoranPass) {
        this.restoranPass = restoranPass;
    }

    public String getRestoranFoto() {
        return restoranFoto;
    }

    public void setRestoranFoto(String restoranFoto) {
        this.restoranFoto = restoranFoto;
    }

}
