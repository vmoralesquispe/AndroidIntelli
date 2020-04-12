package com.example.intellisoftgloriaaccesomovil.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.widget.ImageView;

import com.example.intellisoftgloriaaccesomovil.Adapter.AdapterListaConsultaAccesos;
import com.example.intellisoftgloriaaccesomovil.BD.ConexionSQLiteHelper;
import com.example.intellisoftgloriaaccesomovil.BD.Empleado;
import com.example.intellisoftgloriaaccesomovil.BD.Utilidades;
import com.example.intellisoftgloriaaccesomovil.R;
import com.suprema.CaptureResponder;
import com.suprema.IBioMiniDevice;

import java.util.ArrayList;

public class IntellisoftGloriaAccesoMovil_ConsultaAccesos extends AppCompatActivity {

    //DATABASE
    ConexionSQLiteHelper conn;

    IBioMiniDevice mCurrentDevice = null;

    //RECLYCER
    public static ArrayList<Empleado> listaEmpleado;
    RecyclerView recyclerViewEmpleado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intellisoft_gloria_acceso_movil__consulta_accesos);

        //DATABASE----------------
        conn = new ConexionSQLiteHelper(this);
        //------------------------

        //MainActivity.getToken(this);

        listaEmpleado = new ArrayList<>();
        recyclerViewEmpleado = (RecyclerView) findViewById(R.id.recyclerConsulta);
        recyclerViewEmpleado.setLayoutManager(new LinearLayoutManager(this));

        RellenarReclycerV_Empleado();

        AdapterListaConsultaAccesos adapter = new AdapterListaConsultaAccesos(listaEmpleado, getApplicationContext());
        recyclerViewEmpleado.setAdapter(adapter);

    }

    public void RellenarReclycerV_Empleado() {

        SQLiteDatabase db = conn.getReadableDatabase();
        Empleado usuario = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TB_Empleado.TABLA_EMPLEADO, null);

        while (cursor.moveToNext()) {
            usuario = new Empleado();
            usuario.setIdEmpleado(cursor.getInt(0));
            usuario.setCodInterno(cursor.getString(1));
            usuario.setTipoDocumento(cursor.getString(2));
            usuario.setNumDocumento(cursor.getString(3));
            usuario.setNombre(cursor.getString(4));
            usuario.setApellidoPaterno(cursor.getString(5));
            usuario.setApellidoMaterno(cursor.getString(6));
            usuario.setSede(cursor.getString(7));
            usuario.setCodTarjeta(cursor.getString(8));
            usuario.setFoto(cursor.getBlob(9));

            listaEmpleado.add(usuario);
        }

        db.close();
    }
}