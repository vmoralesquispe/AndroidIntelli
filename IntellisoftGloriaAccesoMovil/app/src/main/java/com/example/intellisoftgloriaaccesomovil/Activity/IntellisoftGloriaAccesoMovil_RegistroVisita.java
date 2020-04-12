package com.example.intellisoftgloriaaccesomovil.Activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.intellisoftgloriaaccesomovil.BD.ConexionSQLiteHelper;
import com.example.intellisoftgloriaaccesomovil.BD.Utilidades;
import com.example.intellisoftgloriaaccesomovil.R;

public class IntellisoftGloriaAccesoMovil_RegistroVisita extends AppCompatActivity {

    EditText  NumDoc, Nombres, Paterno, Materno, Correo, Empresa, Ruc, FechaHoraInicio, FechaHoraFin, Contacto, Motivo;
    CheckBox Visita, Seguro, Soat, Poliza, Sctr, Induc;
    Spinner TipDoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tab_registrovisita1);

        Button btnregis = (Button) findViewById(R.id.btnRegistrar);

        TipDoc = (Spinner) findViewById(R.id.edtTipoDocumento);

        NumDoc = (EditText) findViewById(R.id.edtNumeroDocumento);
        Nombres = (EditText) findViewById(R.id.edtNombres);
        Paterno = (EditText) findViewById(R.id.edtPaterno);
        Materno = (EditText) findViewById(R.id.edtMaterno);
        Correo = (EditText) findViewById(R.id.edtCorreo);
        Empresa = (EditText) findViewById(R.id.edtEmpresa);
        Ruc = (EditText) findViewById(R.id.edtRUC);
        FechaHoraInicio = (EditText) findViewById(R.id.edtFechaHoraInicio);
        FechaHoraFin = (EditText) findViewById(R.id.edtFechaHoraSalida);
        Contacto = (EditText) findViewById(R.id.edtContacto);
        Motivo = (EditText) findViewById(R.id.edtMotivo);

        Visita = (CheckBox) findViewById(R.id.ChkVisitaTecnica);
        Seguro = (CheckBox) findViewById(R.id.ChkSeguro);
        Soat = (CheckBox) findViewById(R.id.ChkSoat);
        Poliza = (CheckBox) findViewById(R.id.ChkPoliza);
        Sctr = (CheckBox) findViewById(R.id.ChkSctr);
        Induc = (CheckBox) findViewById(R.id.ChkInduc);

        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TipDoc.getSelectedItem().toString().trim().length() == 0 || NumDoc.getText().toString().trim().length() == 0
                        || Nombres.getText().toString().trim().length() == 0 || Paterno.getText().toString().trim().length() == 0
                        || Materno.getText().toString().trim().length() == 0 || Correo.getText().toString().trim().length() == 0
                        || Empresa.getText().toString().trim().length() == 0 || Ruc.getText().toString().trim().length() == 0
                        || FechaHoraInicio.getText().toString().trim().length() == 0 || FechaHoraFin.getText().toString().trim().length() == 0
                        || Contacto.getText().toString().trim().length() == 0 || Motivo.getText().toString().trim().length() == 0){

                    Toast.makeText(IntellisoftGloriaAccesoMovil_RegistroVisita.this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
                }else {


                    RegistrarVisita();
                    Intent i = new Intent(IntellisoftGloriaAccesoMovil_RegistroVisita.this, IntellisoftGloriaAccesoMovil_RegistroVisita2.class);
                    startActivity(i);



                }
            }
        });
    }

    private void RegistrarVisita(){

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this);
        SQLiteDatabase db=conn.getWritableDatabase();

        String insert="INSERT INTO "+Utilidades.TB_Visita.TABLA_VISITA
                +" (" +Utilidades.TB_Visita.CAMPO_TIPDOC+","+Utilidades.TB_Visita.CAMPO_NUMDOC+","+Utilidades.TB_Visita.CAMPO_NOMBRES+", "+Utilidades.TB_Visita.CAMPO_PATERNO+"," +
                ""+Utilidades.TB_Visita.CAMPO_MATERNO+", "+Utilidades.TB_Visita.CAMPO_CORREO+", "+Utilidades.TB_Visita.CAMPO_EMPRESA+", "+Utilidades.TB_Visita.CAMPO_RUC+"," +
                ""+Utilidades.TB_Visita.CAMPO_ENTRADAFECHAHORA+", "+Utilidades.TB_Visita.CAMPO_SALIDAFECHAHORA+", "+Utilidades.TB_Visita.CAMPO_CONTACTO+"," +
                ""+Utilidades.TB_Visita.CAMPO_MOTIVO+", "+Utilidades.TB_Visita.CAMPO_VISITA_TECNICA+", "+Utilidades.TB_Visita.CAMPO_SEGURO+", " +
                ""+Utilidades.TB_Visita.CAMPO_SOAT+", "+Utilidades.TB_Visita.CAMPO_POLIZA+", "+Utilidades.TB_Visita.CAMPO_SCTR+", "+Utilidades.TB_Visita.CAMPO_INDUC_SEG+")" +
                " VALUES ('"+TipDoc.getSelectedItem().toString()+"', '"+NumDoc.getText().toString()+"','" +Nombres.getText().toString()+"', '"+Paterno.getText().toString()+"', " +
                "'"+Materno.getText().toString()+"', '"+Correo.getText().toString()+"', '"+Empresa.getText().toString()+"', '"+Ruc.getText().toString()+"', " +
                "'"+FechaHoraInicio.getText().toString()+"', '"+FechaHoraFin.getText().toString()+"','" + ""+Contacto.getText().toString()+"', '"+Motivo.getText().toString()+"'," +
                " '"+Visita.isChecked()+"', '"+Seguro.isChecked()+"', '"+Soat.isChecked()+"', '"+Poliza.isChecked()+"', '"+Sctr.isChecked()+"', '"+Induc.isChecked()+"')";

            db.execSQL(insert);

            TipDoc.setSelection(0);
            NumDoc.setText("");
            Nombres.setText("");
            Paterno.setText("");
            Materno.setText("");
            Correo.setText("");
            Empresa.setText("");
            Ruc.setText("");
            FechaHoraInicio.setText("");
            FechaHoraFin.setText("");
            Contacto.setText("");
            Motivo.setText("");
            Visita.setChecked(false);
            Seguro.setChecked(false);
            Soat.setChecked(false);
            Poliza.setChecked(false);
            Sctr.setChecked(false);
            Induc.setChecked(false);
            Toast.makeText(IntellisoftGloriaAccesoMovil_RegistroVisita.this,"REGISTRADO!" ,Toast.LENGTH_SHORT).show();
            db.close();
    }
}
