package com.berkhanakdag.restoranv1.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.berkhanakdag.restoranv1.R;
import com.berkhanakdag.restoranv1.Services.ApiInterface;
import com.berkhanakdag.restoranv1.Services.RetrofitClient;
import com.berkhanakdag.restoranv1.Model.CalisanModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilActivity extends AppCompatActivity {

    private Button btnGuncelle;
    private TextView txtAdSoyad,txtMail;
    private EditText edtAd,edtSoyad,edtTc,edtMail,edtTelefon,edtAdres;
    private String ad,soyad,tc,mail,telefon,adres="",restoranFk,calisanJwt,calisanId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        btnGuncelle = findViewById(R.id.btnCalisanGuncelle);
        Intent intent = getIntent();
        txtAdSoyad = findViewById(R.id.txtProfilAdSoyad);
        txtMail = findViewById(R.id.txtProfilMail);
        edtAd = findViewById(R.id.edtGuncelAd);
        edtSoyad = findViewById(R.id.edtGundelSoyad);
        edtAdres = findViewById(R.id.edtGuncelAdres);
        edtMail = findViewById(R.id.edtGuncelMail);
        edtTc = findViewById(R.id.edtGuncelTC);
        edtTelefon = findViewById(R.id.edtGuncelTelefon);

        ad = intent.getStringExtra("kulAd");
        soyad = intent.getStringExtra("kulSoyad");
        tc = intent.getStringExtra("kulTc");
        mail = intent.getStringExtra("kulMail");
        telefon = intent.getStringExtra("kulTelefon");
        adres = intent.getStringExtra("kulAdres");
        restoranFk = intent.getStringExtra("kulRestoranFk");
        calisanJwt = intent.getStringExtra("kulJwt");
        calisanId = intent.getStringExtra("kulId");

        txtAdSoyad.setText(intent.getStringExtra("kulAd")+" "+intent.getStringExtra("kulSoyad"));
        txtMail.setText(intent.getStringExtra("kulMail"));

        edtAd.setText(ad);
        edtSoyad.setText(soyad);
        edtTelefon.setText(telefon);
        edtTc.setText(tc);
        edtMail.setText(mail);
        edtAdres.setText(adres);


        btnGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ApiInterface apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);
                Call<CalisanModel> calisanModelCall = apiInterface.CALISAN_MODEL_CALL(
                        calisanJwt,
                        calisanId,
                        "genel",
                        edtAd.getText().toString(),
                        edtSoyad.getText().toString(),
                        edtMail.getText().toString(),
                        edtTelefon.getText().toString(),
                        edtAdres.getText().toString());
                calisanModelCall.enqueue(new Callback<CalisanModel>() {
                    @Override
                    public void onResponse(Call<CalisanModel> call, Response<CalisanModel> response) {

                        Toast.makeText(ProfilActivity.this, ""+response.body(), Toast.LENGTH_SHORT).show();
                        if(response.isSuccessful())
                        {
                            if(response.body().status.equals("1"))
                            {
                                Toast.makeText(ProfilActivity.this, ""+response.body().message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<CalisanModel> call, Throwable t) {
                        System.out.println("Hata:");
                        t.printStackTrace();
                    }
                });
            }
        });
    }
}