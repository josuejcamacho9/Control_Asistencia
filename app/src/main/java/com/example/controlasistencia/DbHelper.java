package com.example.controlasistencia;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;

    //tabla Clase
    private static final String CLASE_NOMBRE_TABLA = "TABLA_CLASE";
    private static final String C_ID = "_CID";
    private static final String CLASE_NOMBRE_KEY = "NOMBRE_CLASE";
    private static final String MATERIA_NOMBRE_KEY = "MATERIA";

    private static final String CREATE_TABLA_CLASE =
            "CREATE TABLE " + CLASE_NOMBRE_TABLA + "(" +
                    CLASE_NOMBRE_KEY + " TEXT NOT NULL," +
                    MATERIA_NOMBRE_KEY + " TEXT NOT NULL," +
                    "UNIQUE (" + CLASE_NOMBRE_KEY + "," + MATERIA_NOMBRE_KEY + ")" +
                    ");";

    private static final String DROP_TABLA_CLASE = "DROP TABLE IF EXISTS " + CLASE_NOMBRE_TABLA;
    private static final String SELECT_TABLA_CLASE = "SELECT * FROM " + CLASE_NOMBRE_TABLA;

    //Tabla Alumno

    private static final String ALUMNO_NOMBRE_TABLA = "TABLA_ALUMNO";
    private static final String S_ID = "_SID";
    private static final  String ALUMNO_NOMBRE_KEY = "ALUMNO_NOMBRE";
    private static final String ALUMNO_MATRICULA_KEY = "MATRICULA";

    private static  final String CREATE_TABLA_ALUMNO =
            "CREATE TABLE " + ALUMNO_NOMBRE_TABLA +
                    "( "+
                    S_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                    C_ID + " INTEGER NOT NULL, "+
                    ALUMNO_NOMBRE_KEY + " TEXT NOT NULL, "+
                    ALUMNO_MATRICULA_KEY + " INTEGER, " +
                    " FOREIGN KEY ( "+C_ID+") REFERENCES " + CLASE_NOMBRE_TABLA + "("+C_ID+")"+
                    ");";

    private static final String DROP_TABLA_ALUMNO = "DROP TABLE IF EXISTS "+ ALUMNO_NOMBRE_TABLA;
    private static final String SELECT_ALUMNO_TABLA = "SELECT * FROM " + ALUMNO_NOMBRE_TABLA;


    //TABLA ESTATUS
    private static final String ESTATUS_NOMBRE_TABLA = "TABLA_ESTATUS";
    private static final String ESTATUS_ID = "_ESTATUS_ID";
    private static final  String FECHA_KEY = "ESTATUS_FECHA";
    private static final String ESTATUS_KEY = "ESTATUS";

    private static  final String CREATE_TABLA_ESTATUS =
            "CREATE TABLE " + ESTATUS_NOMBRE_TABLA +
                    "( "+
                    ESTATUS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                    S_ID + " INTEGER NOT NULL, "+
                    FECHA_KEY + " DATE NOT NULL, "+
                    ESTATUS_KEY + "TEXT NOT NULL, " +
                    "UNIQUE (" + S_ID+ "," + FECHA_KEY+"),"+
                    " FOREIGN KEY ( "+S_ID+") REFERENCES " + ALUMNO_NOMBRE_TABLA + "("+S_ID+")"+
                    ");";

    private static final String DROP_TABLA_ESTATUS = "DROP TABLE IF EXISTS "+ ESTATUS_NOMBRE_TABLA;
    private static final String SELECT_ESTATUS_TABLA = "SELECT * FROM " + ESTATUS_NOMBRE_TABLA;



    public DbHelper(@Nullable Context context) {
        super(context, "Asistencia.db", null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
      sqLiteDatabase.execSQL(CREATE_TABLA_CLASE);
        sqLiteDatabase.execSQL(CREATE_TABLA_ALUMNO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try{
            sqLiteDatabase.execSQL(DROP_TABLA_CLASE);
            sqLiteDatabase.execSQL(DROP_TABLA_ALUMNO);

        }catch (SQLException e){
            e.printStackTrace();
        }


    }
}
