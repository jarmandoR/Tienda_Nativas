
package com.example.tienda_nativas.Activities;


import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

//import com.example.tienda_nativas.Models.a_producto;
import com.example.tienda_nativas.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class agregarProductoActivity extends AppCompatActivity {

    private EditText etNombre, etDescripcion, etPrecio, etCantidad;
    private Button btnGuardar, btnSubirImagen;
    private String imagenRuta = "";
    private long productoId = -1;

    private final ActivityResultLauncher<Intent> cameraLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bitmap foto = (Bitmap) result.getData().getExtras().get("data");
                    guardarImagenEnArchivo(foto);
                }
            });

    private final ActivityResultLauncher<String> permisoCamaraLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    abrirCamara();
                } else {
                    Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiti_agregar_producto);

        etNombre = findViewById(R.id.etNombre);
        etDescripcion = findViewById(R.id.etDescripcion);
        etPrecio = findViewById(R.id.etPrecio);
        etCantidad = findViewById(R.id.etCantidad);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnSubirImagen = findViewById(R.id.btnSubirImagen);

        btnGuardar.setOnClickListener(v -> guardarProducto());

        btnSubirImagen.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                abrirCamara();
            } else {
                permisoCamaraLauncher.launch(Manifest.permission.CAMERA);
            }
        });

        // Verificar si venimos para editar un producto
        if (getIntent().hasExtra("producto")) {
            a_productog producto = (a_productog) getIntent().getSerializableExtra("producto");
            if (producto != null) {
                productoId = producto.getId(); // o getIdProducto(), depende cómo lo llamaste
                etNombre.setText(producto.getNombre());
                etDescripcion.setText(producto.getDescripcion());
                etPrecio.setText(String.valueOf(producto.getPrecio()));
                etCantidad.setText(String.valueOf(producto.getCantidad()));
                imagenRuta = producto.getImagen();
            }
        }
    }

    //private void abrirCamara() {
      //  Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
       // cameraLauncher.launch(intent);
    //}

    private void guardarImagenEnArchivo(Bitmap bitmap) {
        String nombreArchivo = "producto_" + System.currentTimeMillis() + ".jpg";
        File archivo = new File(getFilesDir(), nombreArchivo);
        try (FileOutputStream fos = new FileOutputStream(archivo)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
            imagenRuta = archivo.getAbsolutePath();
            Toast.makeText(this, "Imagen guardada: " + imagenRuta, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error al guardar la imagen", Toast.LENGTH_SHORT).show();
        }
    }

    private void guardarProducto() {
        String nombre = etNombre.getText().toString().trim();
        String descripcion = etDescripcion.getText().toString().trim();
        String precioStr = etPrecio.getText().toString().trim();
        String cantidadStr = etCantidad.getText().toString().trim();

        if (nombre.isEmpty() || precioStr.isEmpty() || cantidadStr.isEmpty()) {
            Toast.makeText(this, "Por favor completa los campos obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        double precio = Double.parseDouble(precioStr);
        int cantidad = Integer.parseInt(cantidadStr);

        a_producto producto = new a_producto(nombre, descripcion, precio, cantidad, imagenRuta);

        DBHelperg dbHelperg = new DBHelperg(this);
        ContentValues values = new ContentValues();
        values.put("nombre", producto.getNombre());
        values.put("descripcion", producto.getDescripcion());
        values.put("precio", producto.getPrecio());
        values.put("cantidad", producto.getCantidad());
        values.put("imagen", producto.getImagen());

        if (productoId != -1) {
            int rows = dbHelperg.getWritableDatabase().update(DBHelperg.TABLE_PRODUCTOS, values, "id = ?", new String[]{String.valueOf(productoId)});
            if (rows > 0) {
                Toast.makeText(this, "Producto actualizado", Toast.LENGTH_LONG).show();
                finish();
                Intent intent = new Intent(agregarProductoActivity.this,ProductoListActivityg.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Error al actualizar producto", Toast.LENGTH_SHORT).show();
            }
        } else {
            long result = dbHelperg.getWritableDatabase().insert(DBHelperg.TABLE_PRODUCTOS, null, values);
            if (result != -1) {
                Toast.makeText(this, "Producto guardado con éxito", Toast.LENGTH_LONG).show();
                limpiarCampos();
                Intent intent = new Intent(agregarProductoActivity.this,ProductoListActivityg.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Error al guardar el producto", Toast.LENGTH_SHORT).show();
            }
        }

        dbHelperg.close();
    }

    private void limpiarCampos() {
        etNombre.setText("");
        etDescripcion.setText("");
        etPrecio.setText("");
        etCantidad.setText("");
        imagenRuta = "";
    }
    private void abrirCamara() {
        String[] opciones = {"Tomar foto", "Elegir de galería"};

        new AlertDialog.Builder(this)
                .setTitle("Selecciona una opción")
                .setItems(opciones, (dialog, which) -> {
                    if (which == 0) {
                        // Tomar foto con cámara
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        cameraLauncher.launch(intent);
                    } else {
                        // Elegir imagen desde la galería
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        galeriaLauncher.launch(intent);
                    }
                })
                .show();
    }
    private final ActivityResultLauncher<Intent> galeriaLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri imagenUri = result.getData().getData();
                    if (imagenUri != null) {
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imagenUri);
                            guardarImagenEnArchivo(bitmap);
                        } catch (IOException e) {
                            Toast.makeText(this, "Error al cargar imagen", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
}
