package com.berkhanakdag.restoranv1.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.berkhanakdag.restoranv1.Adapter.MenuAdapter;
import com.berkhanakdag.restoranv1.R;
import com.berkhanakdag.restoranv1.Services.ApiInterface;
import com.berkhanakdag.restoranv1.Services.RetrofitClient;
import com.berkhanakdag.restoranv1.Model.HesapKapaModel;
import com.berkhanakdag.restoranv1.Model.HesapModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HesapActivity extends AppCompatActivity {

    private Button btnHesapAl;
    private TextView txtHesapMasaNo,txtHesapUcret;
    private RecyclerView recyclerView;
    private MenuAdapter menuAdapter;
    private ArrayList<String> menuId;
    private ArrayList<String> menuAd;
    private ArrayList<String> menuFiyat;
    private ArrayList<String> menuBilgi;
    private ArrayList<String> menuFoto;
    private ArrayList<String> menuAdet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hesap);

        menuAd = new ArrayList<>();
        menuId = new ArrayList<>();
        menuFiyat= new ArrayList<>();
        menuBilgi= new ArrayList<>();
        menuFoto= new ArrayList<>();
        menuAdet= new ArrayList<>();
        txtHesapMasaNo = findViewById(R.id.txtHesapMasaNo);
        btnHesapAl = findViewById(R.id.btnHesapAl);
        txtHesapUcret = findViewById(R.id.txtHesapUcret);
        recyclerView = findViewById(R.id.recycHesap);
     

        Intent intent = getIntent();
        String calisanJwt = intent.getStringExtra("calisanJwt");
        String masaNo = intent.getStringExtra("masaNo");
        txtHesapMasaNo.setText(masaNo);
        siparisListesi(calisanJwt,masaNo);

        btnHesapAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hesapKapat(calisanJwt,masaNo);
            }
        });
    }

    private void siparisListesi(String calisanJwt, String masaNo) {

        ApiInterface apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);
        Call<HesapModel> hesapModelCall = apiInterface.HESAP_MODEL_CALL(calisanJwt,masaNo);

        hesapModelCall.enqueue(new Callback<HesapModel>() {
            @Override
            public void onResponse(Call<HesapModel> call, Response<HesapModel> response) {

                txtHesapUcret.setText(response.body().hesap+" TL");
                for(int i = 0 ; i < response.body().getHesapMenulerModels().size();i++)
                {
                    menuId.add(response.body().getHesapMenulerModels().get(i).menuId);
                    menuAd.add(response.body().getHesapMenulerModels().get(i).menuAd);
                    menuFiyat.add(response.body().getHesapMenulerModels().get(i).menuFiyat);
                    menuBilgi.add(response.body().getHesapMenulerModels().get(i).menuBilgi);
                    menuFoto.add(response.body().getHesapMenulerModels().get(i).menuFoto);
                    menuAdet.add(response.body().getHesapMenulerModels().get(i).adet);
                    listele();
                }
            }

            @Override
            public void onFailure(Call<HesapModel> call, Throwable t) {

            }
        });
    }

    private void listele()
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(HesapActivity.this));
        recyclerView.setHasFixedSize(true);
        menuAdapter = new MenuAdapter(menuId,menuAd,menuFiyat,menuBilgi,menuFoto,menuAdet,false);
        recyclerView.setAdapter(menuAdapter);
        menuAdapter.notifyDataSetChanged();
        recyclerView.setClickable(false);
        recyclerView.setEnabled(false);
    }

    private void hesapKapat(String calisanJwt, String masaNo)
    {
        ApiInterface apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);
        Call<HesapKapaModel> hesapKapaModelCall = apiInterface.HESAP_KAPA_MODEL_CALL(calisanJwt,masaNo);
        hesapKapaModelCall.enqueue(new Callback<HesapKapaModel>() {
            @Override
            public void onResponse(Call<HesapKapaModel> call, Response<HesapKapaModel> response) {

                if(response.isSuccessful())
                {
                    if(response.body().status.equals("1")){
                        Toast.makeText(HesapActivity.this, ""+response.body().message, Toast.LENGTH_SHORT).show();

                        menuAd.clear();
                        menuAdet.clear();
                        menuBilgi.clear();
                        menuFiyat.clear();
                        menuFoto.clear();
                        menuId.clear();
                        txtHesapMasaNo.setText("00");
                        txtHesapUcret.setText("00 TL");
                        menuAdapter.notifyDataSetChanged();
                    }


                }
            }

            @Override
            public void onFailure(Call<HesapKapaModel> call, Throwable t) {

            }
        });
    }
}