package com.example.tienda_nativas.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tienda_nativas.R;

public class LoginActivity extends AppCompatActivity {
    Button btn_comenzar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Aquí enlazas el XML que quieres mostrar


        // Botón Empezar
        btn_comenzar = findViewById(R.id.loginButton);
        btn_comenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, InicioActivity.class);
                startActivity(intent);
            }
        });
    }
}
