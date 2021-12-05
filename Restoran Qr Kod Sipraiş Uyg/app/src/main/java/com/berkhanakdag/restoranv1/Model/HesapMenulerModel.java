package com.berkhanakdag.restoranv1.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HesapMenulerModel {

    @SerializedName("id")
    @Expose
    public String menuId;
    @SerializedName("menuAd")
    @Expose
    public String menuAd;
    @SerializedName("menuFiyat")
    @Expose
    public String menuFiyat;
    @SerializedName("menuBilgi")
    @Expose
    public String menuBilgi;
    @SerializedName("menuFoto")
    @Expose
    public String menuFoto;
    @SerializedName("menuQR")
    @Expose
    public String menuQR;
    @SerializedName("restoranFK")
    @Expose
    public String restoranFK;
    @SerializedName("adet")
    @Expose
    public String adet;
}
