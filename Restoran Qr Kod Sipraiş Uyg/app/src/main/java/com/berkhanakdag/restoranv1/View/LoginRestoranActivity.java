package com.berkhanakdag.restoranv1.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.berkhanakdag.restoranv1.Model.RestoranLoginModel;
import com.berkhanakdag.restoranv1.R;
import com.berkhanakdag.restoranv1.Services.ApiInterface;
import com.berkhanakdag.restoranv1.Services.RetrofitClient;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.observers.BlockingBaseObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;

public class LoginRestoranActivity extends AppCompatActivity {

    private Button btnLoginRestoran;
    private EditText edtLoginRestoranMail,edtLoginRestoranSifre;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_restoran);

        btnLoginRestoran = findViewById(R.id.btnLoginRestoran);
        edtLoginRestoranMail = findViewById(R.id.edtLoginRestoranMail);
        edtLoginRestoranSifre = findViewById(R.id.edtLoginRestoranSifre);

        if(edtLoginRestoranSifre.equals("") || edtLoginRestoranMail.equals(""))
        {
            Toast.makeText(this, "Kullanıcı adı veya Şifre boş bırakılamaz!", Toast.LENGTH_SHORT).show();    
        }
        else
            {
                btnLoginRestoran.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       girisYap();
                    }

                });
            }

    }

    private void girisYap()
    {
        ApiInterface apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);
        /*
        Observable<RestoranLoginModel> restoranLoginModelObservable = apiInterface.RESTORAN_LOGIN_MODEL_OBSERVABLE(edtLoginRestoranMail.getText().toString(),edtLoginRestoranSifre.getText().toString());

        restoranLoginModelObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::responseResult,this::responseError);*/

        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(apiInterface.RESTORAN_LOGIN_MODEL_OBSERVABLE(edtLoginRestoranMail.getText().toString(),edtLoginRestoranSifre.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::responseResult,this::responseError));

    }

    private void responseError(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }

    private void responseResult(RestoranLoginModel restoranLoginModel) {

        Intent intent = new Intent(LoginRestoranActivity.this,RestoranPanelActivity.class);
        intent.putExtra("mailRestoran",restoranLoginModel.getRestoranMail());
        intent.putExtra("idRestoran",restoranLoginModel.getId());
        intent.putExtra("jwtRestoran",restoranLoginModel.getJwtrestoran());
        intent.putExtra("adRestoran",restoranLoginModel.getRestoranAd());
        intent.putExtra("masaRestoran",restoranLoginModel.getRestoranMasaSayisi());
        intent.putExtra("telefonRestoran",restoranLoginModel.getRestoranTelefon());
        startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}