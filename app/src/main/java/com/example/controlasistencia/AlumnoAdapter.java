package com.example.controlasistencia;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AlumnoAdapter extends RecyclerView.Adapter<AlumnoAdapter.AlumnoViewHolder> {
    ArrayList<AlumnoItem> alumnoItems;
    Context context;

    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener{
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public AlumnoAdapter(Context context, ArrayList<AlumnoItem> alumnoItems) {
        this.alumnoItems = alumnoItems;
        this.context = context;
    }

    public static class AlumnoViewHolder extends RecyclerView.ViewHolder{
        TextView matricula;
        TextView nombre;
        TextView estatus;
        CardView cardView;

        public AlumnoViewHolder(@NonNull View itemView,OnItemClickListener onItemClickListener ) {
            super(itemView);
            matricula = itemView.findViewById(R.id.matricula);
            nombre = itemView.findViewById(R.id.nombre);
            estatus = itemView.findViewById(R.id.estatus);
            cardView = itemView.findViewById(R.id.cardview);
            itemView.setOnClickListener(v->onItemClickListener.onClick(getAdapterPosition()));
        }
    }

    @NonNull
    @Override
    public AlumnoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.alumno_item,parent,false);
        return new AlumnoViewHolder(itemView,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AlumnoViewHolder holder, int position) {
       holder.matricula.setText(alumnoItems.get(position).getMatricula());
       holder.nombre.setText(alumnoItems.get(position).getNombre());
       holder.estatus.setText((alumnoItems.get(position).getEstatus()));
       holder.cardView.setCardBackgroundColor(getColor(position));

    }

    private int getColor(int position) {
        String estatus = alumnoItems.get(position).getEstatus();
        if (estatus.equals("P"))
            return Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context,R.color.presente)));
        else if (estatus.equals("F"))
            return Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context,R.color.falta)));
        return Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context,R.color.normal)));

    }

    @Override
    public int getItemCount() {
        return alumnoItems.size();
    }
}
