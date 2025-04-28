package com.example.tienda_nativas.Activities;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tienda_nativas.R;

import java.util.List;
public class FeaturedProductAdapter extends RecyclerView.Adapter<FeaturedProductAdapter.ProductViewHolder>{
    private List<Product> productList;

    public FeaturedProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView txtProductName, txtProductPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtProductPrice = itemView.findViewById(R.id.txtProductPrice);
        }
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_featured_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.imgProduct.setImageResource(product.getImageResId());
        holder.txtProductName.setText(product.getName());
        holder.txtProductPrice.setText(product.getPrice());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

}
