package com.jorge.rodriguez.pruebaciclo4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptadorproductos extends RecyclerView.Adapter<Adaptadorproductos.MyViewHolder>{

    private List<Producto> listaDeProductos;

    public void setListaDeProductos(List<Producto> listaDeProductos) {
        this.listaDeProductos = listaDeProductos;
    }

    public Adaptadorproductos(List<Producto> productos) {
        this.listaDeProductos = productos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View filaProducto = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.producto, viewGroup, false);
        return new MyViewHolder(filaProducto);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Producto producto = listaDeProductos.get(i);


        String codigoProducto = producto.getCodigo();
        String nombreProducto = producto.getNombre();
        String descripcionProducto = producto.getDescripcion();
        double precioProducto = producto.getPrecio();

        myViewHolder.codigo.setText(codigoProducto);
        myViewHolder.nombre.setText(nombreProducto);
        myViewHolder.descripcion.setText(descripcionProducto);
        myViewHolder.precio.setText(String.valueOf(precioProducto));
    }

    @Override
    public int getItemCount() {
        return listaDeProductos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView codigo,nombre,descripcion,precio;

        MyViewHolder(View itemView) {
            super(itemView);
            this.codigo = itemView.findViewById(R.id.textView);
            this.nombre = itemView.findViewById(R.id.textView2);
            this.descripcion = itemView.findViewById(R.id.textView3);
            this.precio = itemView.findViewById(R.id.textView4);
        }
    }

}
