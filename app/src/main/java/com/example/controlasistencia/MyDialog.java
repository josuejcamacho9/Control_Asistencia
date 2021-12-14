package com.example.controlasistencia;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;


public class MyDialog extends DialogFragment {
    public static final String CLASE_ADD_DIALOG="anadirClase";
    public static final String ALUMNO_ADD_DIALOG="anadirAlumno";

    private OnClickListener listener;
    public interface OnClickListener{
        void onClick(String text1,String text2);
    }

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = null;
        if(getTag().equals(CLASE_ADD_DIALOG)) dialog=getAnadirClaseDialog();
        if (getTag().equals(ALUMNO_ADD_DIALOG))dialog = getAnadirAlumnoDialog();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        return dialog;
    }

    private Dialog getAnadirAlumnoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog,null);
        builder.setView(view);

        TextView titulo = view.findViewById(R.id.tituloDialog);
        titulo.setText("Añadir ALumno");

        EditText matricula_edt = view.findViewById(R.id.est01);
        EditText nombre_edt = view.findViewById(R.id.edt02);


        matricula_edt.setHint("Matricula");
        nombre_edt.setHint("Nombre");
        Button cancelar = view.findViewById(R.id.cancelar_btn);
        Button anadir = view.findViewById(R.id.anadir_btn);

        cancelar.setOnClickListener(v-> dismiss() );
        anadir.setOnClickListener(v-> {
            String matricula = matricula_edt.getText().toString();
            String nombre = nombre_edt.getText().toString();
            matricula_edt.setText(String.valueOf(Integer.parseInt(matricula)+1));
            nombre_edt.setText("");
            listener.onClick(matricula,nombre);
        });
        return  builder.create();
    }



    private Dialog getAnadirClaseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog,null);
        builder.setView(view);

        TextView titulo = view.findViewById(R.id.tituloDialog);
        titulo.setText("Añadir Clase");

        EditText clase_edt = view.findViewById(R.id.est01);
        EditText materia_edt = view.findViewById(R.id.edt02);


        clase_edt.setHint("Clase");
        materia_edt.setHint("Materia");
        Button cancelar = view.findViewById(R.id.cancelar_btn);
        Button anadir = view.findViewById(R.id.anadir_btn);

        cancelar.setOnClickListener(v-> dismiss() );
        anadir.setOnClickListener(v-> {
            String claseNombre = clase_edt.getText().toString();
            String materiaNombre = materia_edt.getText().toString();
            listener.onClick(claseNombre,materiaNombre);
            dismiss();
        });
        return  builder.create();
    }
}
