package com.berkhanakdag.restoranv1.Model;

import com.google.gson.annotations.SerializedName;

public class MenuModel {

    @SerializedName("id")
    public String menuId;
    @SerializedName("menuAd")
    public String menuAd;
    @SerializedName("menuFiyat")
    public String menuFiyat;
    @SerializedName("menuBilgi")
    public String menuBilgi;
    @SerializedName("menuFoto")
    public String menuFoto;

    public MenuModel(String menuId, String menuAd, String menuFiyat, String menuBilgi, String menuFoto) {
        this.menuId = menuId;
        this.menuAd = menuAd;
        this.menuFiyat = menuFiyat;
        this.menuFoto = menuFoto;
        this.menuBilgi = menuBilgi;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuAd() {
        return menuAd;
    }

    public void setMenuAd(String menuAd) {
        this.menuAd = menuAd;
    }

    public String getMenuFiyat() {
        return menuFiyat;
    }

    public void setMenuFiyat(String menuFiyat) {
        this.menuFiyat = menuFiyat;
    }

    public String getMenuFoto() {
        return menuFoto;
    }

    public void setMenuFoto(String menuFoto) {
        this.menuFoto = menuFoto;
    }

    public String getMenuBilgi() {
        return menuBilgi;
    }

    public void setMenuBilgi(String menuBilgi) {
        this.menuBilgi = menuBilgi;
    }
}
