package com.jorge.rodriguez.pruebaciclo4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Producto> listaDeProductos;
    private RecyclerView recyclerView;
    private Adaptadorproductos adaptadorproductos;
    private Controlador controlador;


    private FloatingActionButton fabAgregarProducto, fabConsultarProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerViewproductos);
        fabAgregarProducto = findViewById(R.id.fabAgregarProducto);
        fabConsultarProducto = findViewById(R.id.fabConsultarProducto);

        controlador = new Controlador(MainActivity.this);

        listaDeProductos = new ArrayList<>();
        adaptadorproductos = new Adaptadorproductos(listaDeProductos);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptadorproductos);


        refrescarListaDeProductos();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Producto productoSeleccionado = listaDeProductos.get(position);
                Intent intent = new Intent(MainActivity.this, EditarProductoActivity.class);
                intent.putExtra("idProducto", productoSeleccionado.getId());
                intent.putExtra("codigoProducto", productoSeleccionado.getCodigo());
                intent.putExtra("nombreProducto", productoSeleccionado.getNombre());
                intent.putExtra("descripcionProducto", productoSeleccionado.getDescripcion());
                intent.putExtra("precioProducto", productoSeleccionado.getPrecio());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                final Producto productoParaEliminar = listaDeProductos.get(position);
                AlertDialog dialog = new AlertDialog
                        .Builder(MainActivity.this)
                        .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                controlador.eliminarProducto(productoParaEliminar);
                                refrescarListaDeProductos();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Confirmar")
                        .setMessage("¿Eliminar el Producto " + productoParaEliminar.getNombre() + "?")
                        .create();
                dialog.show();

            }
        }));




        fabAgregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AgregarProductoActivity.class);
                startActivity(intent);
            }
        });

        fabConsultarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ConsultarProductosActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        refrescarListaDeProductos();
    }

    public void refrescarListaDeProductos() {

        if (adaptadorproductos == null) return;
        listaDeProductos = controlador.obtenerProductos();
        adaptadorproductos.setListaDeProductos(listaDeProductos);
        adaptadorproductos.notifyDataSetChanged();
    }



}