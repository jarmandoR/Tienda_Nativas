package com.example.tienda_nativas.Activities;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tienda_nativas.R;

import java.util.List;
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItemList;

    public CartAdapter(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        CartItem item = cartItemList.get(position);
        holder.nombreProducto.setText(item.getNombre());
        holder.precioProducto.setText("$" + item.getPrecio());
        holder.imagenProducto.setImageResource(item.getImagenResourceId());
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imagenProducto;
        TextView nombreProducto, precioProducto;

        public CartViewHolder(View itemView) {
            super(itemView);
            imagenProducto = itemView.findViewById(R.id.imageProducto);
            nombreProducto = itemView.findViewById(R.id.textNombreProducto);
            precioProducto = itemView.findViewById(R.id.textPrecioProducto);
        }
    }
}