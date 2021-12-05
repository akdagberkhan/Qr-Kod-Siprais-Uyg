package com.berkhanakdag.restoranv1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.berkhanakdag.restoranv1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuHolder>{

    ArrayList<String> menuId;
    ArrayList<String> menuAd;
    ArrayList<String> menuFiyat;
    ArrayList<String> menuBilgi;
    ArrayList<String> menuFoto;
    ArrayList<String> menuAdet;
    private Boolean tiklama;
    private Context mContext;
    public String menuGonder ="";

    public MenuAdapter(ArrayList<String> menuId, ArrayList<String> menuAd, ArrayList<String> menuFiyat, ArrayList<String> menuBilgi, ArrayList<String> menuFoto,ArrayList<String> menuAdet,Boolean tiklama) {
        this.menuId = menuId;
        this.menuAd = menuAd;
        this.menuFiyat = menuFiyat;
        this.menuBilgi = menuBilgi;
        this.menuFoto = menuFoto;
        this.menuAdet = menuAdet;
        this.tiklama = tiklama;
    }


    @NonNull
    @Override
    public MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_siparis,parent,false);
        mContext = parent.getContext();
        return new MenuHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuHolder holder, int position) {

        holder.siparisAdet.setText("Adet : "+menuAdet.get(position));
        holder.siparisAd.setText("Menü "+menuId.get(position)+" :"+menuAd.get(position));
        holder.siparisFiyat.setText("Fiyat : "+menuFiyat.get(position)+" TL");
        Picasso.get().load("http://192.168.1.6/restoranapi/"+menuFoto.get(position)).resize(72,72).into(holder.siparisImg);
        menuGonder += ","+menuId.get(position)+"x"+menuAdet.get(position);


    }

    @Override
    public int getItemCount() {
        return menuId.size();
    }


    public class MenuHolder extends RecyclerView.ViewHolder {


        TextView siparisFiyat,siparisAd,siparisAdet;
        ImageView siparisImg;
        public MenuHolder(@NonNull View itemView) {
            super(itemView);


            siparisAdet = itemView.findViewById(R.id.txtSiparisAdet);
            siparisFiyat = itemView.findViewById(R.id.txtSiparisFiyat);
            siparisAd = itemView.findViewById(R.id.txtSiparisAd);
            siparisImg = itemView.findViewById(R.id.imgSiapris);
            if(tiklama.equals(true))
            {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION)
                        {
                            menuId.remove(pos);
                            menuFoto.remove(pos);
                            menuFiyat.remove(pos);
                            menuBilgi.remove(pos);
                            menuAd.remove(pos);
                            menuAdet.remove(pos);
                            menuGonder = "";
                            notifyDataSetChanged();

                        }
                    }
                });
            }

        }
    }


}
