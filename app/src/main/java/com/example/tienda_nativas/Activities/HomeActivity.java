package com.example.tienda_nativas.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tienda_nativas.R;

public class HomeActivity  extends AppCompatActivity {
    Button btn_comenzar;
    TextView Registro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // Aquí enlazas el XML que quieres mostrar

        // Botón Empezar
        btn_comenzar = findViewById(R.id.btn_comenzar);

        // Texto Registrar
        Registro = findViewById(R.id.Registro);

        // Click para Empezar (ir a LoginActivity)
        btn_comenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // Click para Registrar (ir a RegistroActivity)
        Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
    }
}
