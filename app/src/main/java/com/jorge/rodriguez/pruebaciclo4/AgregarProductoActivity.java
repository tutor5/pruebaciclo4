package com.jorge.rodriguez.pruebaciclo4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AgregarProductoActivity extends AppCompatActivity {

    private Button btnAgregarProducto, btnCancelarNuevoproducto;
    private EditText etNombre, etPrecio, etCodigo, etDescripcion;
    private Controlador controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_agregar_producto);

        etCodigo = findViewById(R.id.codigo);
        etNombre = findViewById(R.id.nombre);
        etDescripcion = findViewById(R.id.descripcion);
        etPrecio = findViewById(R.id.precio);
        btnAgregarProducto = findViewById(R.id.btGuardar);
        btnCancelarNuevoproducto = findViewById(R.id.btCancelar);

        controlador = new Controlador(AgregarProductoActivity.this);


        btnAgregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                etCodigo.setError(null);
                etNombre.setError(null);
                etDescripcion.setError(null);
                etPrecio.setError(null);
                String codigo = etCodigo.getText().toString(),
                        nombre = etNombre.getText().toString(),
                        descripcion = etDescripcion.getText().toString(),
                        preciostring = etPrecio.getText().toString();
                if ("".equals(codigo)) {
                    etCodigo.setError("Escribe el nombre de la mascota");
                    etCodigo.requestFocus();
                    return;
                }
                if ("".equals(nombre)) {
                    etNombre.setError("Escribe el nombre de la mascota");
                    etNombre.requestFocus();
                    return;
                }
                if ("".equals(descripcion)) {
                    etDescripcion.setError("Escribe el nombre de la mascota");
                    etDescripcion.requestFocus();
                    return;
                }
                if ("".equals(preciostring)) {
                    etPrecio.setError("Escribe la edad de la mascota");
                    etPrecio.requestFocus();
                    return;
                }

                double precio;
                try {
                    precio = Double.parseDouble(etPrecio.getText().toString());
                } catch (NumberFormatException e) {
                    etPrecio.setError("Escribe un número");
                    etPrecio.requestFocus();
                    return;
                }

                Producto nuevoproducto = new Producto(codigo, nombre, descripcion, precio);
                long id = controlador.nuevoProducto(nuevoproducto);
                if (id == -1) {

                    Toast.makeText(AgregarProductoActivity.this, "Error al guardar. Intenta de nuevo", Toast.LENGTH_SHORT).show();
                } else {

                    finish();
                }
            }
        });


        btnCancelarNuevoproducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



}
