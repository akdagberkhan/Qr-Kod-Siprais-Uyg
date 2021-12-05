package com.berkhanakdag.restoranv1.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HesapModel {

    @SerializedName("id")
    @Expose
    public String hesapId;
    @SerializedName("restoranFK")
    @Expose
    public String restoranFK;
    @SerializedName("calisanFK")
    @Expose
    public String calisanFK;
    @SerializedName("durum")
    @Expose
    public String durum;
    @SerializedName("tarih")
    @Expose
    public String tarih;
    @SerializedName("masaNo")
    @Expose
    public String masaNo;
    @SerializedName("hesap")
    @Expose
    public String hesap;

    @SerializedName("menuler")
    @Expose
    public List<HesapMenulerModel> hesapMenulerModels = null;

    public String getHesapId() {
        return hesapId;
    }

    public void setHesapId(String hesapId) {
        this.hesapId = hesapId;
    }

    public String getRestoranFK() {
        return restoranFK;
    }

    public void setRestoranFK(String restoranFK) {
        this.restoranFK = restoranFK;
    }

    public String getCalisanFK() {
        return calisanFK;
    }

    public void setCalisanFK(String calisanFK) {
        this.calisanFK = calisanFK;
    }

    public List<HesapMenulerModel> getHesapMenulerModels() {
        return hesapMenulerModels;
    }

    public void setHesapMenulerModels(List<HesapMenulerModel> hesapMenulerModels) {
        this.hesapMenulerModels = hesapMenulerModels;
    }
}
