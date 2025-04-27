package com.example.tienda_nativas.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tienda_nativas.R;

public class Splash_Activity extends AppCompatActivity {
    private static final int SPLASH_DISPLAY_LENGTH = 3000; // 30 segundos (30,000 ms)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash); // Aquí cargas el layout de tu Splash

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Después de 30 segundos, iniciar LoginActivity
                Intent mainIntent = new Intent(Splash_Activity.this, HomeActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
