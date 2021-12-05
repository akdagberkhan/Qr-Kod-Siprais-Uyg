package com.berkhanakdag.restoranv1.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.berkhanakdag.restoranv1.R;
import com.berkhanakdag.restoranv1.View.Fragment.PanelCalisanlarFragment;
import com.berkhanakdag.restoranv1.View.Fragment.PanelProfilFragment;
import com.berkhanakdag.restoranv1.View.Fragment.PanelSifreFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RestoranPanelActivity extends AppCompatActivity {

    private TextView txtPanelBaslik;
    private BottomNavigationView bottomNavigationView;
    private Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restoran_panel);

        txtPanelBaslik = findViewById(R.id.txtRpanelBaslik);

        bottomNavigationView = findViewById(R.id.rpanelNavView);

        bottomNavigationView.setOnNavigationItemSelectedListener(navItemSelect);

        getSupportFragmentManager().beginTransaction().replace(R.id.rpanelFrame,new PanelProfilFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navItemSelect= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()){

                case R.id.nav_profil:
                    fragment= new PanelProfilFragment();
                    break;
                case R.id.nav_sifre:
                    fragment= new PanelSifreFragment();
                    break;
                case R.id.nav_calisanlar:
                    fragment= new PanelCalisanlarFragment();
                    break;
                default:
                    break;
            }

            if(fragment!=null){
                getSupportFragmentManager().beginTransaction().replace(R.id.rpanelFrame,fragment).commit();
            }
            return true;
        }
    };
}