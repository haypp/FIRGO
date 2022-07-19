package com.reyjunaaf.firgo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class UangAdaptor extends RecyclerView.Adapter<UangAdaptor.ViewHolder> {

    private ArrayList<UangModel> UangModels;
    private Context context;

    public UangAdaptor(ArrayList<UangModel> UangModels, Context context) {
        this.UangModels = UangModels;
        this.context = context;
    }

    public void filterList(ArrayList<UangModel> filteredList){
        UangModels = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UangAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_uang,parent,false);
        return new UangAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UangAdaptor.ViewHolder holder, int position) {
        UangModel UangModel = UangModels.get(position);
        holder.tvName.setText(UangModel.getNama());
        holder.tvNote.setText(UangModel.getnote());
        holder.tvAngka.setText("Rp. "+UangModel.getJumlah());
        holder.tvDate.setText(UangModel.getDate());
        if (UangModel.getJenis().equals("+")){
            holder.ivImage.setImageResource(R.drawable.input);
        }else {
            holder.ivImage.setImageResource(R.drawable.output);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Detail.class);
                intent.putExtra("id",UangModel.getId());
                intent.putExtra("nama", UangModel.getNama());
                intent.putExtra("jumlah", UangModel.getJumlah());
                intent.putExtra("date", UangModel.getDate());
                intent.putExtra("jenis", UangModel.getJenis());
                intent.putExtra("note", UangModel.getnote());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return UangModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvName,tvAngka,tvData, tvDate, tvNote,tvId;
        private ImageView ivImage;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAngka = itemView.findViewById(R.id.tvAngka);
            tvData = itemView.findViewById(R.id.tvDate);
            ivImage = itemView.findViewById(R.id.ivImage);
            cardView = itemView.findViewById(R.id.cardView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvNote = itemView.findViewById(R.id.tvNote);
            tvId = itemView.findViewById(R.id.TVid);
        }
    }
}

