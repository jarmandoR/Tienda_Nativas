package com.example.tienda_nativas.Activities;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.tienda_nativas.R;

import java.util.List;

public class ProductoAdapterg extends RecyclerView.Adapter<ProductoAdapterg.ProductoViewHolder> {

    private List<a_productog> productos;
    private final OnProductoClickListener listener;

    public interface OnProductoClickListener {
        void onEditar(a_productog producto);
        void onEliminar(a_productog producto);
    }

    public ProductoAdapterg(List<a_productog> productos, OnProductoClickListener listener) {
        this.productos = productos;
        this.listener = listener;
    }

    public void setProductos(List<a_productog> nuevosProductos) {
        this.productos = nuevosProductos;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_productog, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        a_productog producto = productos.get(position);
        holder.tvNombre.setText(producto.getNombre());
        holder.tvPrecio.setText("S/. " + producto.getPrecio());
        holder.tvCantidad.setText("Stock: " + producto.getCantidad());

        holder.btnEditar.setOnClickListener(v -> listener.onEditar(producto));
        holder.btnEliminar.setOnClickListener(v -> listener.onEliminar(producto));
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    static class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvPrecio, tvCantidad;
        ImageButton btnEditar, btnEliminar;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            tvCantidad = itemView.findViewById(R.id.tvCantidad);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}
