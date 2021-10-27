package com.jorge.rodriguez.pruebaciclo4;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Conectorbasededatos extends SQLiteOpenHelper {

    private static final String NOMBRE_BASE_DE_DATOS = "proyecto",
            NOMBRE_TABLA_PRODUCTOS = "productos";
    private static final int VERSION_BASE_DE_DATOS = 1;

    public Conectorbasededatos(Context context) {
        super(context, NOMBRE_BASE_DE_DATOS, null, VERSION_BASE_DE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(id integer primary key autoincrement,codigo int, nombre text, descripcion text, precio double)", NOMBRE_TABLA_PRODUCTOS));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
