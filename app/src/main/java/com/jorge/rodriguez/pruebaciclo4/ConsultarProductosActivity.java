package com.jorge.rodriguez.pruebaciclo4;

import android.content.Intent;
import android.os.Bundle;
import android.service.controls.Control;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConsultarProductosActivity extends AppCompatActivity {

    private Button btnConsultarCodigo, btnConsultarNombre, btnCancelarConsulta;
    private EditText etBuscarCodigo, etBuscarNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultar_producto);
        btnConsultarCodigo = findViewById(R.id.btnConsultarCodigo);
        btnConsultarNombre = findViewById(R.id.btnConsultarNombre);
        btnCancelarConsulta = findViewById(R.id.btnCancelarConsulta);

        etBuscarCodigo = findViewById(R.id.etBuscarCodigo);
        etBuscarNombre = findViewById(R.id.etBuscarNombre);

        Controlador controlador = new Controlador(ConsultarProductosActivity.this);
        btnConsultarCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigoProducto =  etBuscarCodigo.getText().toString();


                Producto productoConsultado = controlador.consultarProducto(codigoProducto);

                if (productoConsultado==null){

                    Toast.makeText(ConsultarProductosActivity.this, "Error consultando producto. Intente de nuevo.", Toast.LENGTH_SHORT).show();


                }else {

                    Intent intent = new Intent(ConsultarProductosActivity.this, EditarProductoActivity.class);
                    intent.putExtra("idProducto", productoConsultado.getId());
                    intent.putExtra("codigoProducto", productoConsultado.getCodigo());
                    intent.putExtra("nombreProducto", productoConsultado.getNombre());
                    intent.putExtra("descripcionProducto", productoConsultado.getDescripcion());
                    intent.putExtra("precioProducto", productoConsultado.getPrecio());
                    startActivity(intent);
                }

            }


        });

        btnConsultarNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreproducto =  etBuscarNombre.getText().toString();


                Producto productoConsultado = controlador.consultarProductoxNombre(nombreproducto);

                if (productoConsultado==null){

                    Toast.makeText(ConsultarProductosActivity.this, "Error consultando producto. Intente de nuevo.", Toast.LENGTH_SHORT).show();


                }else {
                    Intent intent = new Intent(ConsultarProductosActivity.this, EditarProductoActivity.class);
                    intent.putExtra("idProducto", productoConsultado.getId());
                    intent.putExtra("codigoProducto", productoConsultado.getCodigo());
                    intent.putExtra("nombreProducto", productoConsultado.getNombre());
                    intent.putExtra("descripcionProducto", productoConsultado.getDescripcion());
                    intent.putExtra("precioProducto", productoConsultado.getPrecio());
                    startActivity(intent);
                }
            }

        });


        btnCancelarConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }

        });
    }





}
