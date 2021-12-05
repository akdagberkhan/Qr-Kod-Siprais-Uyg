package com.berkhanakdag.restoranv1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.berkhanakdag.restoranv1.View.HesapActivity;
import com.berkhanakdag.restoranv1.R;
import com.berkhanakdag.restoranv1.View.SiparisActivity;

import java.util.ArrayList;

public class RestoranAdapter extends RecyclerView.Adapter<RestoranAdapter.RestoranHolder> {

    private ArrayList<String> calisanFk;
    private ArrayList<String> restoranFk;
    private ArrayList<String> masaSayisi;
    private ArrayList<String> calisanJwt;
    private Context mContext;

    public RestoranAdapter(ArrayList<String> calisanFk, ArrayList<String> restoranFk, ArrayList<String> masaSayisi,ArrayList<String> calisanJwt) {
        this.calisanFk = calisanFk;
        this.restoranFk = restoranFk;
        this.masaSayisi = masaSayisi;
        this.calisanJwt = calisanJwt;
    }

    @NonNull
    @Override
    public RestoranHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_masalar,parent,false);
        mContext = parent.getContext();

        return new RestoranHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestoranHolder holder, int position) {

        holder.masaSayisiTxt.setText(masaSayisi.get(position));

        holder.siparisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SiparisActivity.class);
                intent.putExtra("calisanFk",calisanFk.get(0));
                intent.putExtra("restoranFk",restoranFk.get(0));
                intent.putExtra("calisanJwt",calisanJwt.get(0));
                String x = String.valueOf(position+1);
                intent.putExtra("masaNo",x);

                mContext.startActivity(intent);
            }
        });

        holder.hesapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, HesapActivity.class);
                intent.putExtra("calisanJwt",calisanJwt.get(0));
                String x = String.valueOf(position+1);
                intent.putExtra("masaNo",x);

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return masaSayisi.size();
    }

    public class RestoranHolder extends RecyclerView.ViewHolder {

        Button hesapBtn,siparisBtn;
        TextView masaSayisiTxt;

        public RestoranHolder(@NonNull View itemView) {
            super(itemView);

            hesapBtn = itemView.findViewById(R.id.btnCardHesap);
            siparisBtn = itemView.findViewById(R.id.btnCardSiparis);
            masaSayisiTxt = itemView.findViewById(R.id.txtCardMasaNo);

        }
    }
}
