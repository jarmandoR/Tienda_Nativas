package com.example.tienda_nativas.Activities;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tienda_nativas.R;

import java.util.ArrayList;
import java.util.List;
public class CarritoActivity extends AppCompatActivity{
    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter; // Tu adaptador
    private List<CartItem> cartItemList; // Tu lista de productos en el carrito

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito); // Asegúrate que sea tu XML correcto

        cartRecyclerView = findViewById(R.id.cartRecyclerView);

        // Configurar el RecyclerView
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // Simulación de productos en el carrito
        cartItemList = new ArrayList<>();
        cartItemList.add(new CartItem("Laptop Asus", 25000, R.drawable.dispositivos));
        cartItemList.add(new CartItem("Mouse Logitech", 800, R.drawable.dispositivos));
        cartItemList.add(new CartItem("Teclado Mecánico", 1500, R.drawable.dispositivos));

        // Configurar el Adaptador
        cartAdapter = new CartAdapter(cartItemList);
        cartRecyclerView.setAdapter(cartAdapter);
    }
}
