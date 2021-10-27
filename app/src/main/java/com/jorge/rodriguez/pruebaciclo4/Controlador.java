package com.jorge.rodriguez.pruebaciclo4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class Controlador {

    private Conectorbasededatos conectorbasededatos;
    private String NOMBRE_TABLA = "productos";

    public Controlador(Context contexto) {
        conectorbasededatos = new Conectorbasededatos(contexto);
    }


    public long nuevoProducto(Producto producto) {

        SQLiteDatabase baseDeDatos = conectorbasededatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("codigo", producto.getCodigo());
        valoresParaInsertar.put("nombre", producto.getNombre());
        valoresParaInsertar.put("descripcion", producto.getDescripcion());
        valoresParaInsertar.put("precio", producto.getPrecio());
        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }


    public ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        SQLiteDatabase baseDeDatos = conectorbasededatos.getReadableDatabase();

        String[] columnasAConsultar = {"codigo","nombre","descripcion", "precio", "id"};
        Cursor cursor = baseDeDatos.query(
                NOMBRE_TABLA,
                columnasAConsultar,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor == null) {

            return productos;

        }

        if (!cursor.moveToFirst()) return productos;


        do {

            String codigoDB = cursor.getString(0);
            String nombreDB = cursor.getString(1);
            String descripcionDB = cursor.getString(2);
            double precioBD = cursor.getInt(3);
            long idProducto = cursor.getLong(4);
            Producto productoBD = new Producto(codigoDB,nombreDB,descripcionDB,precioBD, idProducto);
            productos.add(productoBD);
        } while (cursor.moveToNext());


        cursor.close();
        return productos;
    }


    public Producto consultarProducto(String codigo){
        SQLiteDatabase baseDeDatos = conectorbasededatos.getWritableDatabase();
        String[] columnasAConsultar = {"codigo","nombre","descripcion", "precio", "id"};
        Cursor cursor = baseDeDatos.query(
                NOMBRE_TABLA,
                columnasAConsultar,
                "codigo = ?",
                new String[] {String.valueOf(codigo)},
                null,
                null,
                null
        );

        if (cursor != null   && cursor.moveToFirst()) {

           // cursor.moveToFirst();

            String codigoDB = cursor.getString(0);
            String nombreDB = cursor.getString(1);
            String descripcionDB = cursor.getString(2);
            double precioBD = cursor.getInt(3);
            long idProducto = cursor.getLong(4);
            Producto productoBD = new Producto(codigoDB, nombreDB, descripcionDB, precioBD, idProducto);

            return productoBD;

        }else {
            return null;




        }
    }


    public Producto consultarProductoxNombre(String nombre) {
        SQLiteDatabase baseDeDatos = conectorbasededatos.getWritableDatabase();
        String[] columnasAConsultar = {"codigo","nombre","descripcion", "precio", "id"};
        Cursor cursor = baseDeDatos.query(
                NOMBRE_TABLA,
                columnasAConsultar,
                "nombre = ?",
                new String[] {String.valueOf(nombre)},
                null,
                null,
                null
        );

        if (cursor != null   && cursor.moveToFirst()) {

            cursor.moveToFirst();

            String codigoDB = cursor.getString(0);
            String nombreDB = cursor.getString(1);
            String descripcionDB = cursor.getString(2);
            double precioBD = cursor.getInt(3);
            long idProducto = cursor.getLong(4);
            Producto productoBD = new Producto(codigoDB, nombreDB, descripcionDB, precioBD, idProducto);


            return productoBD;

           }else {
            return null;



        }
    }


    public int guardarCambios(Producto productoEditado) {
        SQLiteDatabase baseDeDatos = conectorbasededatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("codigo", productoEditado.getCodigo());
        valoresParaActualizar.put("nombre", productoEditado.getNombre());
        valoresParaActualizar.put("descripcion", productoEditado.getDescripcion());
        valoresParaActualizar.put("precio", productoEditado.getPrecio());

        String campoParaActualizar = "id = ?";

        String[] argumentosParaActualizar = {String.valueOf(productoEditado.getId())};
        return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }


    public int eliminarProducto(Producto producto) {

        SQLiteDatabase baseDeDatos = conectorbasededatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(producto.getId())};
        return baseDeDatos.delete(NOMBRE_TABLA, "id = ?", argumentos);
    }
}
