package com.example.controlasistencia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;


public class AlumnoActivity extends AppCompatActivity {

    Toolbar toolbar;
    private String claseNombre;
    private String materiaNombre;
    private int position;
    private RecyclerView recyclerView;
    private AlumnoAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<AlumnoItem> alumnoItems = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno);

        Intent intent = getIntent();
        claseNombre = intent.getStringExtra("claseNombre");
        materiaNombre = intent.getStringExtra("materiaNombre");
        position = intent.getIntExtra("position",-1);

        setToolbar();
        recyclerView = findViewById(R.id.alumno_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new AlumnoAdapter(this,alumnoItems);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(position1 -> cambiarEstatus(position));

    }

    private void cambiarEstatus(int position) {
        String estatus = alumnoItems.get(position).getEstatus();
        if (estatus.equals("P")) estatus = "F";
        else estatus = "P";

        alumnoItems.get(position).setEstatus(estatus);
        adapter.notifyItemChanged(position);
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        TextView titulo = toolbar.findViewById(R.id.bar_titulo);
        TextView subtitulo = toolbar.findViewById(R.id.bar_subtitulo);
        ImageButton regresar = toolbar.findViewById(R.id.back);
        ImageButton guardar = toolbar.findViewById(R.id.guardar);

        titulo.setText(claseNombre);
        subtitulo.setText(materiaNombre);
        toolbar.inflateMenu(R.menu.alumno_menu);
        toolbar.setOnMenuItemClickListener(menuItem->onMenuItemClick(menuItem));

    }

    private boolean onMenuItemClick(MenuItem menuItem) {
        if(menuItem.getItemId() == R.id.anadir_alumno){
            showAnadirAlumnoDialog();
        }
        return true;
    }

    private void showAnadirAlumnoDialog() {
        MyDialog dialog = new MyDialog();
        dialog.show(getSupportFragmentManager(),MyDialog.ALUMNO_ADD_DIALOG);
        dialog.setListener((matricula,nombre)->anadirAlumno(matricula,nombre));
    }

    private void anadirAlumno(String matricula, String nombre) {
        alumnoItems.add(new AlumnoItem(matricula,nombre));
        adapter.notifyDataSetChanged();

    }


}