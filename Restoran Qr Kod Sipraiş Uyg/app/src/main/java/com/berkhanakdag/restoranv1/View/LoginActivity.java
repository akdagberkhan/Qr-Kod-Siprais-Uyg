package com.berkhanakdag.restoranv1.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.berkhanakdag.restoranv1.R;
import com.berkhanakdag.restoranv1.Services.ApiInterface;
import com.berkhanakdag.restoranv1.Services.RetrofitClient;
import com.berkhanakdag.restoranv1.Model.LoginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText edtLoginKulAd,edtLoginSifre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        edtLoginKulAd = findViewById(R.id.edtLoginKullaniciAd);
        edtLoginSifre = findViewById(R.id.edtLoginSifre);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginCheck();
            }
        });
    }

    private void loginCheck()
    {

        ApiInterface apiInterface= RetrofitClient.getRetrofit().create(ApiInterface.class);

        Call<LoginModel> loginModelCall = apiInterface.LOGIN_MODEL_CALL(edtLoginKulAd.getText().toString(),edtLoginSifre.getText().toString());

        loginModelCall.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                if(response.isSuccessful())
                {
                        if(response.body().getKulStatus().equals("1"))
                        {
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            intent.putExtra("kulId",response.body().getKulId());
                            intent.putExtra("kulAd",response.body().getKulAd());
                            intent.putExtra("kulSoyad",response.body().getKulSoyad());
                            intent.putExtra("kulUsername",response.body().getKulUsername());
                            intent.putExtra("kulMail",response.body().getKulMail());
                            intent.putExtra("kulTelefon",response.body().getKulTelefon());
                            intent.putExtra("kulRestoranFk",response.body().getKulRestoranFk());
                            intent.putExtra("kulJwt",response.body().getKuJjwt());
                            intent.putExtra("kulAdres",response.body().getKulAdres());

                            startActivity(intent);
                        }
                        else
                            {
                                Toast.makeText(LoginActivity.this, "Hatalı Kullanıcı Adı ya da Şifre! ", Toast.LENGTH_SHORT).show();
                            }
                }
                else
                    {
                        Toast.makeText(LoginActivity.this, "Hata", Toast.LENGTH_SHORT).show();
                    }

            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {

                t.printStackTrace();
            }
        });
    }
}