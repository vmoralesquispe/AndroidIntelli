package com.example.intellisoftgloriaaccesomovil.Activity;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.intellisoftgloriaaccesomovil.Adapter.AdapterListaRegistroAcompanante;
import com.example.intellisoftgloriaaccesomovil.BD.Acompañantes;
import com.example.intellisoftgloriaaccesomovil.BD.ConexionSQLiteHelper;
import com.example.intellisoftgloriaaccesomovil.BD.Utilidades;
import com.example.intellisoftgloriaaccesomovil.R;

import java.util.ArrayList;


public class IntellisoftGloriaAccesoMovil_RegistroVisita2 extends AppCompatActivity {

    RecyclerView myrecyclerview;
    ArrayList<Acompañantes> lstcontac;

    //DATABASE
    ConexionSQLiteHelper con;
    Cursor cursor;


    EditText NumDoc, NumDocVisita, Nombres, Paterno, Materno, Correo, Empresa, Ruc;
    CheckBox Visita, Seguro, Soat, Poliza, Sctr, Induc;
    Spinner TipDoc2;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tab_registrovisita2);

        //DATABASE------------------------
        con = new ConexionSQLiteHelper(this);
        //--------------------------------

        lstcontac = new ArrayList<>();
        myrecyclerview = (RecyclerView) findViewById(R.id.rvregistrovisitatab2);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(this));

        AdapterListaRegistroAcompanante recyclerview = new AdapterListaRegistroAcompanante(getApplicationContext(),lstcontac);
        myrecyclerview.setAdapter(recyclerview);

        Button btnAgregar = (Button) findViewById(R.id.btnAgregarAcompañantes);
        Button btnSalir = (Button) findViewById(R.id.btnSalirAcompañantes);

        TipDoc2 = (Spinner) findViewById(R.id.edtTipoDocumento2);

        NumDoc = (EditText) findViewById(R.id.edtNumeroDocumento2);
        NumDocVisita = (EditText) findViewById(R.id.edtNumeroDocumentoVisita2);
        Nombres = (EditText) findViewById(R.id.edtNombres2);
        Paterno = (EditText) findViewById(R.id.edtPaterno2);
        Materno = (EditText) findViewById(R.id.edtMaterno2);
        Correo = (EditText) findViewById(R.id.edtCorreo2);
        Empresa = (EditText) findViewById(R.id.edtEmpresa2);
        Ruc = (EditText) findViewById(R.id.edtRUC2);

        Visita = (CheckBox) findViewById(R.id.ChkVisitaTecnica2);
        Seguro = (CheckBox) findViewById(R.id.ChkSeguro2);
        Soat = (CheckBox) findViewById(R.id.ChkSoat2);
        Poliza = (CheckBox) findViewById(R.id.ChkPoliza2);
        Sctr = (CheckBox) findViewById(R.id.ChkSctr2);
        Induc = (CheckBox) findViewById(R.id.ChkInduc2);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TipDoc2.getSelectedItem().toString().trim().length() == 0 || NumDoc.getText().toString().trim().length() == 0
                        || NumDocVisita.getText().toString().trim().length() == 0 || Nombres.getText().toString().trim().length() == 0
                        || Paterno.getText().toString().trim().length() == 0 || Materno.getText().toString().trim().length() == 0
                        || Correo.getText().toString().trim().length() == 0 || Empresa.getText().toString().trim().length() == 0
                        || Ruc.getText().toString().trim().length() == 0){

                    Toast.makeText(IntellisoftGloriaAccesoMovil_RegistroVisita2.this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
                }else {
                    RegistrarAcompañantes();
                    //rellenarRVAcompañante();
                }
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntellisoftGloriaAccesoMovil_RegistroVisita2.this, IntellisoftGloriaAccesoMovil_Options.class);
                startActivity(intent);
            }
        });
    }

    private void RegistrarAcompañantes(){

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this);
        SQLiteDatabase db=conn.getWritableDatabase();

        String insert="INSERT INTO "+Utilidades.TB_Acompañantes.TABLA_ACOMPAÑANTES
                +" (" +Utilidades.TB_Acompañantes.CAMPO_TIPDOC+","+Utilidades.TB_Acompañantes.CAMPO_NUMDOC+","+Utilidades.TB_Acompañantes.CAMPO_NOMBRES+", "+Utilidades.TB_Acompañantes.CAMPO_NUMDOCVISITA+"," +
                " "+Utilidades.TB_Acompañantes.CAMPO_PATERNO+","+Utilidades.TB_Acompañantes.CAMPO_MATERNO+", "+Utilidades.TB_Acompañantes.CAMPO_CORREO+", "+Utilidades.TB_Acompañantes.CAMPO_EMPRESA+", "+Utilidades.TB_Acompañantes.CAMPO_RUC+"," +
                ""+Utilidades.TB_Acompañantes.CAMPO_VISITA_TECNICA+", "+Utilidades.TB_Acompañantes.CAMPO_SEGURO+", "+Utilidades.TB_Acompañantes.CAMPO_SOAT+", "+Utilidades.TB_Acompañantes.CAMPO_POLIZA+", " +
                ""+Utilidades.TB_Acompañantes.CAMPO_SCTR+", "+Utilidades.TB_Visita.CAMPO_INDUC_SEG+")" +
                " VALUES ('"+TipDoc2.getSelectedItem().toString()+"', '"+NumDoc.getText().toString()+"', '"+Nombres.getText().toString()+"' ,'" +NumDocVisita.getText().toString()+"'," +
                "'"+Paterno.getText().toString()+"', '"+Materno.getText().toString()+"', '"+Correo.getText().toString()+"', '"+Empresa.getText().toString()+"', " +
                "'"+Ruc.getText().toString()+"', '"+Visita.isChecked()+"', '"+Seguro.isChecked()+"', '"+Soat.isChecked()+"', " +
                "'"+Poliza.isChecked()+"', '"+Sctr.isChecked()+"', '"+Induc.isChecked()+"')";
        db.execSQL(insert);

        TipDoc2.setSelection(0);
        NumDoc.setText("");
        NumDocVisita.setText("");
        Nombres.setText("");
        Paterno.setText("");
        Materno.setText("");
        Correo.setText("");
        Empresa.setText("");
        Ruc.setText("");
        Visita.setChecked(false);
        Seguro.setChecked(false);
        Soat.setChecked(false);
        Poliza.setChecked(false);
        Sctr.setChecked(false);
        Induc.setChecked(false);
        Toast.makeText(IntellisoftGloriaAccesoMovil_RegistroVisita2.this,"REGISTRADO!" ,Toast.LENGTH_SHORT).show();
        db.close();
    }

    public void rellenarRVAcompañante(){
        SQLiteDatabase db = con.getReadableDatabase();
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
