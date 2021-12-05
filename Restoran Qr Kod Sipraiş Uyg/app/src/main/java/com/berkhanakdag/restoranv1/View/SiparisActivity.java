package com.berkhanakdag.restoranv1.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.berkhanakdag.restoranv1.Adapter.MenuAdapter;
import com.berkhanakdag.restoranv1.R;
import com.berkhanakdag.restoranv1.Services.ApiInterface;
import com.berkhanakdag.restoranv1.Services.RetrofitClient;
import com.berkhanakdag.restoranv1.Model.MenuModel;
import com.berkhanakdag.restoranv1.Model.SiparisVerModel;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SiparisActivity extends AppCompatActivity {

    ArrayList<String> menuIdArray;
    ArrayList<String> menuAd;
    ArrayList<String> menuFiyat;
    ArrayList<String> menuBilgi;
    ArrayList<String> menuFoto;
    ArrayList<String> menuAdet;
    RecyclerView recyclerView;
    MenuAdapter menuAdapter;
    EditText mAdet;
    String mId,mAd,mFiyat,mBilgi,mFoto;
    Button btnSiparisEkle,btnSiparisVer,btnSiparisIptal;
    TextView txtSiaprisMenuAd;

    private String calisanFk,calisanJwt,restoranId,masaNo;
    private int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siparis);

        Intent intent = getIntent();
        calisanFk = intent.getStringExtra("calisanFk");
        calisanJwt = intent.getStringExtra("calisanJwt");
        restoranId = intent.getStringExtra("restoranFk");
        masaNo = intent.getStringExtra("masaNo");

        recyclerView = findViewById(R.id.recyMenu);
        txtSiaprisMenuAd = findViewById(R.id.txtSiparisMenuAd);
        btnSiparisEkle = findViewById(R.id.btnSiparisEkle);
        btnSiparisIptal = findViewById(R.id.btnSiparisIptal);
        btnSiparisVer = findViewById(R.id.btnSiparisVer);
        mAdet = findViewById(R.id.edtSiparisAdet);
        menuIdArray = new ArrayList<>();
        menuAd = new ArrayList<>();
        menuFiyat = new ArrayList<>();
        menuBilgi = new ArrayList<>();
        menuFoto = new ArrayList<>();
        menuAdet = new ArrayList<>();
        qrBasla();

        btnSiparisEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                if(mAd.equals(""))
                {
                    Toast.makeText(SiparisActivity.this, "Lütfen QR okutun!", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        veriGonder();
                        menuAdapter.notifyDataSetChanged();
                    }
               
                
            }
        });
        btnSiparisVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(!menuAdapter.menuGonder.equals(""))
               {
                   ApiInterface apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);
                   Call<SiparisVerModel> siparisVerModelCall = apiInterface.SIPARIS_VER_MODEL_CALL(calisanJwt,calisanFk,menuAdapter.menuGonder,masaNo,restoranId);
                   
                   siparisVerModelCall.enqueue(new Callback<SiparisVerModel>() {
                       @Override
                       public void onResponse(Call<SiparisVerModel> call, Response<SiparisVerModel> response) {
                           
                           if(response.isSuccessful())
                           {
                               if(response.body().status.equals("1"))
                               {
                                   Toast.makeText(SiparisActivity.this, ""+response.body().message, Toast.LENGTH_SHORT).show();
                                   menuAd.clear();;
                                   menuAdet.clear();
                                   menuBilgi.clear();
                                   menuFiyat.clear();
                                   menuFoto.clear();
                                   menuIdArray.clear();
                                   menuAdapter.menuGonder="";
                                   menuAdapter.notifyDataSetChanged();
                               }
                               else
                                   {
                                       Toast.makeText(SiparisActivity.this, ""+response.body().message, Toast.LENGTH_SHORT).show();
                                   }

                           }
                       }

                       @Override
                       public void onFailure(Call<SiparisVerModel> call, Throwable t) {
                            t.printStackTrace();
                       }
                   });
               }
            }
        });
        btnSiparisIptal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuAdet.clear();
                menuAd.clear();
                menuBilgi.clear();
                menuFiyat.clear();
                menuFoto.clear();
                menuIdArray.clear();
                menuAdapter.menuGonder = "";
                recyclerView.setAdapter(null);
            }
        });



    }

    private void menuAl(String menuId)
    {


        ApiInterface apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);
        Call<MenuModel> menuModelCall = apiInterface.MENU_MODEL_CALL(calisanJwt,menuId);

        menuModelCall.enqueue(new Callback<MenuModel>() {
            @Override
            public void onResponse(Call<MenuModel> call, Response<MenuModel> response) {

                mId = response.body().menuId;
                mAd = response.body().menuAd;
                mFiyat = response.body().menuFiyat;
                mBilgi = response.body().menuBilgi;
                mFoto = response.body().menuFoto;

                txtSiaprisMenuAd.setText("Menü Ad: \n"+mAd);

            }
            @Override
            public void onFailure(Call<MenuModel> call, Throwable t) {

            }
        });
    }
    private void veriGonder()
    {
        menuIdArray.add(mId);
        menuAd.add(mAd);
        menuFiyat.add(mFiyat);
        menuBilgi.add(mBilgi);
        menuFoto.add(mFoto);
        menuAdet.add(mAdet.getText().toString());


        recyclerView.setLayoutManager(new LinearLayoutManager(SiparisActivity.this));
        recyclerView.setHasFixedSize(true);
        menuAdapter = new MenuAdapter(menuIdArray,menuAd,menuFiyat,menuBilgi,menuFoto,menuAdet,true);
        recyclerView.setAdapter(menuAdapter);

        txtSiaprisMenuAd.setText("Menü Ad: ");
        mId = "";
        mAd = "";
        mFiyat ="";
        mBilgi ="";
        mFoto ="";
    }


    //QR BAŞLA
    private void qrBasla()
    {
        REQUEST_CODE = 123;
        if (ContextCompat.checkSelfPermission(SiparisActivity.this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(SiparisActivity.this, new String[] {Manifest.permission.CAMERA}, REQUEST_CODE);
        } else {
            startScanning();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startScanning();
            } else {

                Toast.makeText(this, "Kamera izni reddedildi!", Toast.LENGTH_LONG).show();
            }
        }
    }
    private CodeScanner mCodeScanner;
    private void startScanning() {
        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        menuAl(result.getText());
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mCodeScanner != null) {
            mCodeScanner.startPreview();
        }
    }

    @Override
    protected void onPause() {
        if(mCodeScanner != null) {
            mCodeScanner.releaseResources();
        }
        super.onPause();
    }
    //QR SON

}