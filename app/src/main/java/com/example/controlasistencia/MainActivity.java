package com.example.controlasistencia;


import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView recyclerView;
    ClaseAdapter claseAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ClaseItem> claseItems = new ArrayList<>();
    Toolbar toolbar;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab_main);
        fab.setOnClickListener(view -> showDialog());

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        claseAdapter = new ClaseAdapter(this,claseItems);
        recyclerView.setAdapter(claseAdapter);
        claseAdapter.setOnItemClickListener(position -> gotoItemActivity(position));

        setToolbar();


    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        TextView titulo = toolbar.findViewById(R.id.bar_titulo);
        TextView subtitulo = toolbar.findViewById(R.id.bar_subtitulo);
        ImageButton regresar = toolbar.findViewById(R.id.back);
        ImageButton guardar = toolbar.findViewById(R.id.guardar);

        titulo.setText("Aplicación para Control de Asistencia");
        subtitulo.setVisibility(View.GONE);
        regresar.setVisibility(View.INVISIBLE);
        guardar.setVisibility(View.INVISIBLE);


    }

    private void gotoItemActivity(int position) {
        Intent intent = new Intent(this, AlumnoActivity.class);

        intent.putExtra("claseNombre", claseItems.get(position).getClaseNombre());
        intent.putExtra("materiaNombre",claseItems.get(position).getMateriaNombre());
        intent.putExtra("position",position);
        startActivity(intent);
    }

    private void showDialog() {
        MyDialog dialog = new MyDialog();
        dialog.show(getSupportFragmentManager(), MyDialog.CLASE_ADD_DIALOG);
        dialog.setListener((claseNombre,materiaNombre)->anadirClase(claseNombre,materiaNombre));

    }

    private void anadirClase(String claseNombre, String materiaNombre) {
        claseItems.add(new ClaseItem(claseNombre,materiaNombre));
        claseAdapter.notifyDataSetChanged();
    }
}