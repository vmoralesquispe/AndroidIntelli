package com.example.intellisoftgloriaaccesomovil.Activity;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.intellisoftgloriaaccesomovil.Adapter.AdapterListaAcompanante;
import com.example.intellisoftgloriaaccesomovil.BD.Acompañantes;
import com.example.intellisoftgloriaaccesomovil.BD.ConexionSQLiteHelper;
import com.example.intellisoftgloriaaccesomovil.BD.Utilidades;
import com.example.intellisoftgloriaaccesomovil.BD.Visita;
import com.example.intellisoftgloriaaccesomovil.R;
import java.util.ArrayList;


public class IntellisoftGloriaAccesoMovil_AprobarVisita2 extends AppCompatActivity {

    RecyclerView myrecyclerview;
    ArrayList<Acompañantes> lstcontac;

    //DATABASE
    ConexionSQLiteHelper conn;

    EditText iniciodatetime, findatetime, contacto, motivo, sede;
    Button aprobar, rechazar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intellisoft_gloria_movil_aprobarvisita2);

        conn = new ConexionSQLiteHelper(this);


        lstcontac = new ArrayList<>();
        myrecyclerview = (RecyclerView) findViewById(R.id.rvaprobarvisitatab2);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(this));

        RellenarReclycerV_Acompañantes();

        AdapterListaAcompanante recyclerview = new AdapterListaAcompanante(getApplicationContext(),lstcontac);
        myrecyclerview.setAdapter(recyclerview);


        iniciodatetime = (EditText) findViewById(R.id.edtInicioDateTime2);
        findatetime = (EditText) findViewById(R.id.edtFinDateTime2);
        contacto = (EditText) findViewById(R.id.edtContacto2);
        motivo = (EditText) findViewById(R.id.edtMotivo2);
        sede = (EditText) findViewById(R.id.edtSede2);

        aprobar = (Button) findViewById(R.id.aprobartab2);
        aprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntellisoftGloriaAccesoMovil_AprobarVisita2.this, IntellisoftGloriaAccesoMovil_Options.class);
                startActivity(intent);
            }
        });

        rechazar = (Button) findViewById(R.id.rechazartab2);
        rechazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntellisoftGloriaAccesoMovil_AprobarVisita2.this, IntellisoftGloriaAccesoMovil_Options.class);
                startActivity(intent);
            }
        });

        Visita visita = (Visita) getIntent().getSerializableExtra("visita");
        if (visita != null) {

            iniciodatetime.setText(visita.getEntradaFechaHora());
            findatetime.setText(visita.getSalidaFechaHora());
            contacto.setText(visita.getContacto());
            motivo.setText(visita.getMotivo());
            //sede.setText(visita.getSede());

        }

    }
    public void RellenarReclycerV_Acompañantes(){
        SQLiteDatabase db = conn.getReadableDatabase();
        Acompañantes acompañantes = null;

        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TB_Acompañantes.TABLA_ACOMPAÑANTES,null);

        while (cursor.moveToNext()){
            acompañantes = new Acompañantes();
            acompañantes.setTipoDoc(cursor.getString(0));
            acompañantes.setNumDoc(cursor.getInt(1));
            acompañantes.setNumDocVisita(cursor.getInt(2));
            acompañantes.setNombres(cursor.getString(3));
            acompañantes.setPaterno(cursor.getString(4));
            acompañantes.setMaterno(cursor.getString(5));
            acompañantes.setCorreo(cursor.getString(6));
            acompañantes.setEmpresa(cursor.getString(7));
            acompañantes.setRUC(cursor.getInt(8));

            lstcontac.add(acompañantes);
        }

        db.close();
    }
}
