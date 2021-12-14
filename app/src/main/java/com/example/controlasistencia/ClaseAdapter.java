package com.example.controlasistencia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClaseAdapter extends RecyclerView.Adapter<ClaseAdapter.ClassViewHolder> {
    ArrayList<ClaseItem> claseItems;
    Context context;

    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener{
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public ClaseAdapter(Context context, ArrayList<ClaseItem> claseItems) {
        this.claseItems = claseItems;
        this.context = context;
    }

    public static class ClassViewHolder extends RecyclerView.ViewHolder{
        TextView claseNombre;
        TextView materiaNombre;
        public ClassViewHolder(@NonNull View itemView,OnItemClickListener onItemClickListener ) {
            super(itemView);
            claseNombre = itemView.findViewById(R.id.clase_tv);
            materiaNombre = itemView.findViewById(R.id.materia_tv);
            itemView.setOnClickListener(v->onItemClickListener.onClick(getAdapterPosition()));
        }
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.clase_item,parent,false);
        return new ClassViewHolder(itemView,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
       holder.claseNombre.setText(claseItems.get(position).getClaseNombre());
       holder.materiaNombre.setText(claseItems.get(position).getMateriaNombre());

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
