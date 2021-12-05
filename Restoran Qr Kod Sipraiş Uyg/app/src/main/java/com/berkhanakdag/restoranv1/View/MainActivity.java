package com.berkhanakdag.restoranv1.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.berkhanakdag.restoranv1.Adapter.RestoranAdapter;
import com.berkhanakdag.restoranv1.R;
import com.berkhanakdag.restoranv1.Services.ApiInterface;
import com.berkhanakdag.restoranv1.Services.RetrofitClient;
import com.berkhanakdag.restoranv1.Model.RestoranModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RestoranAdapter restoranAdapter;

    private String restoranFK,calisanjwt,calisanFk,calisanMail,calisanTelefon,calisanTc,calisanAd,calisanSoyad,calisanAdres;
    private int masaSayisi;

    private ArrayList<String> calisan;
    private ArrayList<String> restoran;
    private ArrayList<String> jwtcalisan;
    private ArrayList<String> masaS;

    private Button btnProfil,btnRpanel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       calisan = new ArrayList<>();
       restoran=new ArrayList<>();
       masaS=new ArrayList<>();
        jwtcalisan = new ArrayList<>();

        btnProfil = findViewById(R.id.btnMainProfil);
        btnRpanel = findViewById(R.id.btnRpanel);
        Intent intent= getIntent();
        restoranFK = intent.getStringExtra("kulRestoranFk");
        calisanFk = intent.getStringExtra("kulId");
        calisanjwt = intent.getStringExtra("kulJwt");
        calisanMail = intent.getStringExtra("kulMail");
        calisanTelefon = intent.getStringExtra("kulTelefon");
        calisanTc = intent.getStringExtra("kulUsername");
        calisanAd = intent.getStringExtra("kulAd");
        calisanSoyad = intent.getStringExtra("kulSoyad");
        calisanAdres = intent.getStringExtra("kulAdres");
        restoranlar();

        RecyclerView recyclerView=findViewById(R.id.recycMasalar);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setHasFixedSize(true);
        restoranAdapter = new RestoranAdapter(calisan,restoran,masaS,jwtcalisan);
        recyclerView.setAdapter(restoranAdapter);
        restoranAdapter.notifyDataSetChanged();


        btnProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this,ProfilActivity.class);

                intent1.putExtra("kulRestoranFk",restoranFK);
                intent1.putExtra("kulId",calisanFk);
                intent1.putExtra("kulJwt",calisanjwt);
                intent1.putExtra("kulMail",calisanMail);
                intent1.putExtra("kulTc",calisanTc);
                intent1.putExtra("kulTelefon",calisanTelefon);
                intent1.putExtra("kulAd",calisanAd);
                intent1.putExtra("kulSoyad",calisanSoyad);
                intent1.putExtra("kulAdres",calisanAdres);
                startActivity(intent1);
            }
        });

        btnRpanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginRestoranActivity.class));
            }
        });
    }

    private void restoranlar()
    {
        ApiInterface apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);
        Call<RestoranModel> restoranListCall = apiInterface.RESTORAN_MODEL_CALL(restoranFK);

        restoranListCall.enqueue(new Callback<RestoranModel>() {
            @Override
            public void onResponse(Call<RestoranModel> call, Response<RestoranModel> response) {

                if(response.isSuccessful())
                {
                        masaSayisi =Integer.valueOf(response.body().restoranMasaSayi);
                        calisan.add(calisanFk);
                        restoran.add(restoranFK);
                        jwtcalisan.add(calisanjwt);
                        //jwtRestoran.add(response.body().)
                        for(int i = 1; i <= masaSayisi;i++)
                        {
                            masaS.add(String.valueOf(i));
                            restoranAdapter.notifyDataSetChanged();
                        }

                }
            }

            @Override
            public void onFailure(Call<RestoranModel> call, Throwable t) {
                System.out.println("Hata:");
                t.printStackTrace();
            }
        });
    }
}