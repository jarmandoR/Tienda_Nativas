package com.example.tienda_nativas.Activities;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tienda_nativas.R;

import java.util.ArrayList;
import java.util.List;
public class InicioActivity extends AppCompatActivity {
    private RecyclerView recyclerFeatured;
    private FeaturedProductAdapter adapter;
    private List<Product> featuredProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio); // asegúrate de que tu layout sea correcto




        recyclerFeatured = findViewById(R.id.recyclerFeatured);

        // Crear lista de productos
        featuredProducts = new ArrayList<>();
        featuredProducts.add(new Product("Iphone 15Pro", "$120.00", R.drawable.dispositivos));
        featuredProducts.add(new Product("Reloj Casio", "$200.000", R.drawable.dispositivos));
        featuredProducts.add(new Product("Auriculares Sony", "$150.00", R.drawable.dispositivos));
        featuredProducts.add(new Product("Lenovo Pad11", "$800.000", R.drawable.dispositivos));

        // Configurar Adapter
        adapter = new FeaturedProductAdapter(featuredProducts);
        recyclerFeatured.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerFeatured.setAdapter(adapter);
    }
}
