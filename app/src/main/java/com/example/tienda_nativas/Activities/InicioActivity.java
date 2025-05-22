package com.example.tienda_nativas.Activities;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
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
        setContentView(R.layout.activity_inicio);




        recyclerFeatured = findViewById(R.id.recyclerFeatured);

        // Crear lista de productos
        featuredProducts = new ArrayList<>();
        featuredProducts.add(new Product("Iphone 15Pro", "$120.00", R.drawable.iphone_15_plus_hero));
        featuredProducts.add(new Product("Reloj Casio", "$200.000", R.drawable.huawei_watch_fit_smart_watch_v2));
        featuredProducts.add(new Product("Auriculares Sony", "$150.00", R.drawable.dispositivos));
        featuredProducts.add(new Product("Lenovo Pad11", "$800.000", R.drawable.dispositivos));

        // Configurar Adapter
        adapter = new FeaturedProductAdapter(featuredProducts);
        recyclerFeatured.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerFeatured.setAdapter(adapter);

        ImageView cartIcon = findViewById(R.id.cartIcon);
        cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InicioActivity.this,CarritoActivity.class);
                startActivity(intent);
            }
        });
        ImageView mapicon = findViewById(R.id.mapIcon);
        mapicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InicioActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });

            RecyclerView recyclerView = findViewById(R.id.recyclerFeatured2);

    // Configuramos el GridLayoutManager con 3 columnas
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            recyclerView.setLayoutManager(gridLayoutManager);

    // Creamos una lista de productos
            List<Producto> listaProductos = new ArrayList<>();
            listaProductos.add(new Producto("Laptop", "$1.500.000", R.drawable.laptop));
            listaProductos.add(new Producto("Audífonos", "$200.000", R.drawable.audifonos));
            listaProductos.add(new Producto("Mouse", "$40.000", R.drawable.mouse));
            listaProductos.add(new Producto("Audífonos", "$200.000", R.drawable.audifonos));
            listaProductos.add(new Producto("Mouse", "$40.000", R.drawable.mouse));
            listaProductos.add(new Producto("Mouse", "$40.000", R.drawable.mouse));
    // agrega todos los productos que quieras

    // Creamos y seteamos el adaptador
            ProductoAdapter adapter = new ProductoAdapter(listaProductos);
            recyclerView.setAdapter(adapter);

    }
}
