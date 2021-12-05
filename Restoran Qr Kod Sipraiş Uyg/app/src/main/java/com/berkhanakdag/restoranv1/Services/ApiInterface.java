package com.berkhanakdag.restoranv1.Services;

import com.berkhanakdag.restoranv1.Model.CalisanModel;
import com.berkhanakdag.restoranv1.Model.HesapKapaModel;
import com.berkhanakdag.restoranv1.Model.HesapModel;
import com.berkhanakdag.restoranv1.Model.LoginModel;
import com.berkhanakdag.restoranv1.Model.MenuModel;
import com.berkhanakdag.restoranv1.Model.RestoranLoginModel;
import com.berkhanakdag.restoranv1.Model.RestoranModel;
import com.berkhanakdag.restoranv1.Model.SiparisVerModel;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("index.php?url=calisanlar/login")
    Call<LoginModel> LOGIN_MODEL_CALL(@Field("tc") String username,@Field("sifre") String password);

    @FormUrlEncoded
    @POST("index.php?url=restoranlar/get")
    Call<RestoranModel> RESTORAN_MODEL_CALL(@Field("id") String id);

    @FormUrlEncoded
    @POST("index.php?url=menuler/get")
    Call<MenuModel> MENU_MODEL_CALL(@Field("jwtCalisan") String jwtRestoran,@Field("menuId") String menuId);

    @FormUrlEncoded
    @POST("index.php?url=siparis/siparis")
    Call<SiparisVerModel> SIPARIS_VER_MODEL_CALL(@Field("jwtcalisan") String jwtCalisan, @Field("calisanFK") String calisanFK,@Field("menuler") String menuler,@Field("masaNo") String masaNo,@Field("restoranid") String restoranId);

    @FormUrlEncoded
    @POST("index.php?url=siparis/aktifMasa")
    Call<HesapModel> HESAP_MODEL_CALL(@Field("jwtcalisan") String jwtcalisan,@Field("masaNo") String masaNo);

    @FormUrlEncoded
    @POST("index.php?url=siparis/hesapode")
    Call<HesapKapaModel>HESAP_KAPA_MODEL_CALL(@Field("jwtcalisan") String jwtcalisan,@Field("masaNo") String masaNo);

    @FormUrlEncoded
    @POST("index.php?url=calisanlar/update")
    Call<CalisanModel> CALISAN_MODEL_CALL(@Field("jwt") String calisanJwt
            ,@Field("id") String calisanId
            ,@Field("islem") String calisanislem
            ,@Field("ad") String calisanAd
            ,@Field("soyad") String calisanSoyad
            ,@Field("mail") String calisanMail
            ,@Field("tel") String calisanTel
            ,@Field("adres") String calisanAdres);

    @FormUrlEncoded
    @POST("index.php?url=restoranlar/login")
    Observable<RestoranLoginModel> RESTORAN_LOGIN_MODEL_OBSERVABLE(@Field("restoranMail") String restoranMail,@Field("sifre") String restoranSifre);
}
