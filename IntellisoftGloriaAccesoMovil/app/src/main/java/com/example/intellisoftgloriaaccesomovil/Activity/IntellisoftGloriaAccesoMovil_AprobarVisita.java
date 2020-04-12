package com.example.intellisoftgloriaaccesomovil.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.intellisoftgloriaaccesomovil.Adapter.AdapterListaAprobarVisita;
import com.example.intellisoftgloriaaccesomovil.BD.ConexionSQLiteHelper;
import com.example.intellisoftgloriaaccesomovil.BD.Utilidades;
import com.example.intellisoftgloriaaccesomovil.BD.Visita;
import com.example.intellisoftgloriaaccesomovil.R;
import java.util.ArrayList;

public class IntellisoftGloriaAccesoMovil_AprobarVisita extends AppCompatActivity {

    RecyclerView myrecyclerview;
    ArrayList<Visita> lstcontac;

    //DATABASE
    ConexionSQLiteHelper con;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intellisoft_gloria_movil_aprobarvisita);

        //DATABASE------------------------
        con = new ConexionSQLiteHelper(this);
        //--------------------------------

        lstcontac = new ArrayList<>();
        myrecyclerview = (RecyclerView) findViewById(R.id.tab1_recyclerview);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(this));

        RellenarReclycerV_Visita();

        AdapterListaAprobarVisita recyclerview = new AdapterListaAprobarVisita(getApplicationContext(),lstcontac);
        myrecyclerview.setAdapter(recyclerview);

    }

    public void RellenarReclycerV_Visita(){
        SQLiteDatabase db = con.getReadableDatabase();
        Visita visita = null;

        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TB_Visita.TABLA_VISITA,null);

        while (cursor.moveToNext()){
            visita = new Visita();
            visita.setTipoDoc(cursor.getString(0));
            visita.setNumDoc(cursor.getInt(1));
            visita.setNombres(cursor.getString(2));
            visita.setPaterno(cursor.getString(3));
            visita.setMaterno(cursor.getString(4));
            visita.setCorreo(cursor.getString(5));
            visita.setEmpresa(cursor.getString(6));
            visita.setRUC(cursor.getInt(7));
            visita.setEntradaFechaHora(cursor.getString(8));
            visita.setSalidaFechaHora(cursor.getString(9));
            visita.setContacto(cursor.getString(10));
            visita.setMotivo(cursor.getString(11));

            lstcontac.add(visita);
        }

        db.close();
    }
}
