package com.jorge.rodriguez.pruebaciclo4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditarProductoActivity extends AppCompatActivity {
    private EditText etEditarNombre, etEditarCodigo, etEditarDescripcion, etEditarPrecio;
    private Button btnGuardarCambios, btnCancelarEdicion;
    private Producto producto;
    private Controlador controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_editar_producto);

        Bundle extras = getIntent().getExtras();

        if (extras == null) {
            finish();
            return;
        }

        controlador = new Controlador(EditarProductoActivity.this);

        long idProducto = extras.getLong("idProducto");
        String codigoProducto = extras.getString("codigoProducto");
        String nombreProducto = extras.getString("nombreProducto");
        String descripcionProducto = extras.getString("descripcionProducto");
        double precioProducto = extras.getDouble("precioProducto");
        producto = new Producto(codigoProducto, nombreProducto, descripcionProducto, precioProducto, idProducto);



        etEditarCodigo = findViewById(R.id.etEditarCodigo);
        etEditarNombre = findViewById(R.id.etEditarNombre);
        etEditarDescripcion = findViewById(R.id.etEditarDescripcion);
        etEditarPrecio = findViewById(R.id.etEditarPrecio);
        btnCancelarEdicion = findViewById(R.id.btnCancelar);
        btnGuardarCambios = findViewById(R.id.btnGuardar);



        etEditarPrecio.setText(String.valueOf(producto.getPrecio()));
        etEditarCodigo.setText(producto.getCodigo());
        etEditarNombre.setText(producto.getNombre());
        etEditarDescripcion.setText(producto.getDescripcion());

        btnCancelarEdicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                etEditarNombre.setError(null);
                etEditarCodigo.setError(null);
                etEditarDescripcion.setError(null);
                etEditarPrecio.setError(null);

                String nuevoCodigo = etEditarCodigo.getText().toString();
                String nuevoNombre = etEditarNombre.getText().toString();
                String nuevaDescripcion = etEditarDescripcion.getText().toString();
                String nuevo2Precio = etEditarPrecio.getText().toString();
                if (nuevoCodigo.isEmpty()) {
                    etEditarCodigo.setError("Escribe el codigo");
                    etEditarCodigo.requestFocus();
                    return;
                }
                if (nuevoNombre.isEmpty()) {
                    etEditarNombre.setError("Escribe el nombre");
                    etEditarNombre.requestFocus();
                    return;
                }
                if (nuevaDescripcion.isEmpty()) {
                    etEditarDescripcion.setError("Escribe la descripcion");
                    etEditarDescripcion.requestFocus();
                    return;
                }
                if (nuevo2Precio.isEmpty()) {
                    etEditarPrecio.setError("Escribe el precio");
                    etEditarPrecio.requestFocus();
                    return;
                }

                int nuevoPrecio;
                try {
                    nuevoPrecio = Integer.parseInt(nuevo2Precio);
                } catch (NumberFormatException e) {
                    etEditarPrecio.setError("Escribe un número");
                    etEditarPrecio.requestFocus();
                    return;
                }

                Producto productoCambios = new Producto(nuevoCodigo, nuevoNombre, nuevaDescripcion,nuevoPrecio, producto.getId());
                int filasModificadas = controlador.guardarCambios(productoCambios);
                if (filasModificadas != 1) {

                    Toast.makeText(EditarProductoActivity.this, "Error guardando cambios. Intente de nuevo.", Toast.LENGTH_SHORT).show();
                } else {

                    finish();
                }
            }
        });
    }



}
