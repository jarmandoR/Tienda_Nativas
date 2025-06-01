package com.example.tienda_nativas.Activities;



import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

//import com.example.tienda_nativas.Adapters.ProductoAdapterg;
//import com.example.tienda_nativas.DB.DBHelperg;
//import com.example.tienda_nativas.Models.a_productog;
import com.example.tienda_nativas.R;

import java.util.List;

public class ProductoListActivityg extends AppCompatActivity {

    private ProductoAdapterg adapter;
    private List<a_productog> productos;

    private final ActivityResultLauncher<Intent> launcherAgregarEditar =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    cargarProductos();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerProductos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productos = new DBHelperg(this).obtenerProductos();
        adapter = new ProductoAdapterg(productos, new ProductoAdapterg.OnProductoClickListener() {
            @Override
            public void onEditar(a_productog producto) {
                Intent intent = new Intent(ProductoListActivityg.this, agregarProductoActivity.class);
                intent.putExtra("producto", producto);
                launcherAgregarEditar.launch(intent);
            }

            @Override
            public void onEliminar(a_productog producto) {
                DBHelperg db = new DBHelperg(ProductoListActivityg.this);
                db.eliminarProducto(producto.getId());
                cargarProductos();
            }
        });

        recyclerView.setAdapter(adapter);

        ImageButton btnAgregar = findViewById(R.id.btnAgregarProducto);
        btnAgregar.setOnClickListener(v -> {
            Intent intent = new Intent(this, agregarProductoActivity.class);
            launcherAgregarEditar.launch(intent);
        });
    }

    private void cargarProductos() {
        productos = new DBHelperg(this).obtenerProductos();
        adapter.setProductos(productos);
        adapter.notifyDataSetChanged();
    }
}
