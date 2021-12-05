package com.berkhanakdag.restoranv1.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestoranLoginModel {

    @SerializedName("restoranAd")
    @Expose
    private String restoranAd;
    @SerializedName("restoranTelefon")
    @Expose
    private String restoranTelefon;
    @SerializedName("restoranMail")
    @Expose
    private String restoranMail;
    @SerializedName("restoranMasaSayisi")
    @Expose
    private String restoranMasaSayisi;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("jwtrestoran")
    @Expose
    private String jwtrestoran;

    public RestoranLoginModel(String restoranAd, String restoranTelefon, String restoranMail, String restoranMasaSayisi, String id, String jwtrestoran) {
        this.restoranAd = restoranAd;
        this.restoranTelefon = restoranTelefon;
        this.restoranMail = restoranMail;
        this.restoranMasaSayisi = restoranMasaSayisi;
        this.id = id;
        this.jwtrestoran = jwtrestoran;
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

    public String getRestoranMasaSayisi() {
        return restoranMasaSayisi;
    }

    public void setRestoranMasaSayisi(String restoranMasaSayisi) {
        this.restoranMasaSayisi = restoranMasaSayisi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJwtrestoran() {
        return jwtrestoran;
    }

    public void setJwtrestoran(String jwtrestoran) {
        this.jwtrestoran = jwtrestoran;
    }
}
