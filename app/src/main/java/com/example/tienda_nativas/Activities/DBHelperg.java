package com.example.tienda_nativas.Activities;

//package com.example.tienda_nativas.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

//import com.example.tienda_nativas.Models.a_productog;

import java.util.ArrayList;
import java.util.List;

public class DBHelperg extends SQLiteOpenHelper {

    public static final String DB_NAME = "Tienda.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_PRODUCTOS = "productos";

    public DBHelperg(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_PRODUCTOS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT," +
                "descripcion TEXT," +
                "precio REAL," +
                "cantidad INTEGER," +
                "imagen TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTOS);
        onCreate(db);
    }

    public long insertarProducto(a_productog producto) {
        ContentValues values = new ContentValues();
        values.put("nombre", producto.getNombre());
        values.put("descripcion", producto.getDescripcion());
        values.put("precio", producto.getPrecio());
        values.put("cantidad", producto.getCantidad());
        values.put("imagen", producto.getImagen());
        return getWritableDatabase().insert(TABLE_PRODUCTOS, null, values);
    }

    public long actualizarProducto(a_productog producto) {
        ContentValues values = new ContentValues();
        values.put("nombre", producto.getNombre());
        values.put("descripcion", producto.getDescripcion());
        values.put("precio", producto.getPrecio());
        values.put("cantidad", producto.getCantidad());
        values.put("imagen", producto.getImagen());
        return getWritableDatabase().update(TABLE_PRODUCTOS, values, "id=?", new String[]{String.valueOf(producto.getId())});
    }

    public int eliminarProducto(int id) {
        return getWritableDatabase().delete(TABLE_PRODUCTOS, "id=?", new String[]{String.valueOf(id)});
    }

    public List<a_productog> obtenerProductos() {
        List<a_productog> lista = new ArrayList<>();
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_PRODUCTOS, null);
        while (cursor.moveToNext()) {
            a_productog p = new a_productog(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getDouble(3),
                    cursor.getInt(4),
                    cursor.getString(5)
            );
            lista.add(p);
        }
        cursor.close();
        return lista;
    }
}