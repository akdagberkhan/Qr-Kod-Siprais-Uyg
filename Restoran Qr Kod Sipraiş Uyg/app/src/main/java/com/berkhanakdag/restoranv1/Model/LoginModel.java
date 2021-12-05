package com.berkhanakdag.restoranv1.Model;

import com.google.gson.annotations.SerializedName;

public class LoginModel {

    @SerializedName("id")
    public String kulId;
    @SerializedName("ad")
    public String kulAd;
    @SerializedName("soyad")
    public String kulSoyad;
    @SerializedName("tc")
    public String kulUsername;
    @SerializedName("mail")
    public String kulMail;
    @SerializedName("telefon")
    public String kulTelefon;
    @SerializedName("restoranFK")
    public String kulRestoranFk;
    @SerializedName("status")
    public String kulStatus;
    @SerializedName("jwt")
    public String kuJjwt;
    @SerializedName("adres")
    public String kulAdres="";


    public LoginModel(String kulId, String kulAd, String kulSoyad, String kulUsername, String kulMail, String kulTelefon, String kulStatus, String kuJjwt, String kulRestoranFk, String kulAdres) {
        this.kulId = kulId;
        this.kulAd = kulAd;
        this.kulSoyad = kulSoyad;
        this.kulUsername = kulUsername;
        this.kulMail = kulMail;
        this.kulTelefon = kulTelefon;
        this.kulStatus = kulStatus;
        this.kuJjwt = kuJjwt;
        this.kulRestoranFk = kulRestoranFk;
        this.kulAdres = kulAdres;
    }

    public String getKulId() {
        return kulId;
    }

    public void setKulId(String kulId) {
        this.kulId = kulId;
    }

    public String getKulAd() {
        return kulAd;
    }

    public void setKulAd(String kulAd) {
        this.kulAd = kulAd;
    }

    public String getKulSoyad() {
        return kulSoyad;
    }

    public void setKulSoyad(String kulSoyad) {
        this.kulSoyad = kulSoyad;
    }

    public String getKulUsername() {
        return kulUsername;
    }

    public void setKulUsername(String kulUsername) {
        this.kulUsername = kulUsername;
    }

    public String getKulMail() {
        return kulMail;
    }

    public void setKulMail(String kulMail) {
        this.kulMail = kulMail;
    }

    public String getKulTelefon() {
        return kulTelefon;
    }

    public void setKulTelefon(String kulTelefon) {
        this.kulTelefon = kulTelefon;
    }

    public String getKulStatus() {
        return kulStatus;
    }

    public void setKulStatus(String kulStatus) {
        this.kulStatus = kulStatus;
    }

    public String getKuJjwt() {
        return kuJjwt;
    }

    public void setKuJjwt(String kuJjwt) {
        this.kuJjwt = kuJjwt;
    }

    public String getKulRestoranFk() {
        return kulRestoranFk;
    }

    public void setKulRestoranFk(String kulRestoranFk) {
        this.kulRestoranFk = kulRestoranFk;
    }

    public String getKulAdres() {
        return kulAdres;
    }

    public void setKulAdres(String kulAdres) {
        this.kulAdres = kulAdres;
    }
}
