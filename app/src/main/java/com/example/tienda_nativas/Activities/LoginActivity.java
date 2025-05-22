package com.example.tienda_nativas.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tienda_nativas.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {

    // Código que identifica el intento de inicio de sesión con Google
    private static final int RC_SIGN_IN = 1000;

    // Botón para ingresar sin Google
    Button btn_comenzar;

    // Botón para iniciar sesión con Google
    Button btnGoogle;

    // Cliente de Google para el inicio de sesión
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Referencia al botón que permite continuar sin Google
        btn_comenzar = findViewById(R.id.loginButton);
        btn_comenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cuando el usuario presiona "Comenzar", va a la actividad principal sin usar Google
                Intent intent = new Intent(LoginActivity.this, InicioActivity.class);
                startActivity(intent);
            }
        });

        // Referencia al botón de Google
        btnGoogle = findViewById(R.id.btnGoogle);

        // Configuración de Google Sign-In
        // Aquí se construye una opción para iniciar sesión con Google pidiendo solo el email
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Se crea el cliente de inicio de sesión con Google con las opciones configuradas
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Cuando se presiona el botón de Google, inicia el proceso de autenticación
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInWithGoogle();
            }
        });
    }

    // Función que lanza el intent para iniciar sesión con Google
    private void signInWithGoogle() {
        // Se obtiene el intent del cliente de Google y se lanza con startActivityForResult
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    // Se llama automáticamente cuando el usuario regresa de la pantalla de Google Sign-In
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Verificamos si el resultado corresponde al intento de Google Sign-In
        if (requestCode == RC_SIGN_IN) {
            // Se obtiene la cuenta de Google desde el intent
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    // Esta función maneja el resultado del intento de inicio de sesión con Google
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            // Si no hubo errores, obtenemos la cuenta
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Extraemos el correo y nombre del usuario
            String email = account.getEmail();
            String name = account.getDisplayName();

            // Mostramos un mensaje de bienvenida
            Toast.makeText(this, "Bienvenido: " + name + "\nEmail: " + email, Toast.LENGTH_LONG).show();

            // Iniciamos la siguiente actividad (InicioActivity)
            Intent intent = new Intent(this, InicioActivity.class);
            startActivity(intent);
            finish(); // Cerramos la actividad de login para que no se pueda volver atrás

        } catch (ApiException e) {
            // Si hay algún error, lo mostramos en el log y al usuario
            Log.w("GOOGLE_SIGN_IN", "Fallo el login. Código=" + e.getStatusCode());
            Toast.makeText(this, "Error al iniciar sesión con Google", Toast.LENGTH_SHORT).show();
        }
    }
}