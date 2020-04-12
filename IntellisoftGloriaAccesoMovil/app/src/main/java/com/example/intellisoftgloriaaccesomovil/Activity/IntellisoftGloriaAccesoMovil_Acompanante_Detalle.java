package com.example.intellisoftgloriaaccesomovil.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.intellisoftgloriaaccesomovil.BD.Acompañantes;
import com.example.intellisoftgloriaaccesomovil.R;

public class IntellisoftGloriaAccesoMovil_Acompanante_Detalle extends AppCompatActivity {

    TextView nombres, paterno, materno, docvisita, tipdoc, doc, correo, empresa, ruc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intellisoft_gloria_acceso_movil__acompanante__detalle);

        nombres =(TextView)findViewById(R.id.txtNombreAcompañantesDialog);
        paterno =(TextView)findViewById(R.id.txtPaternoDialog2);
        materno =(TextView)findViewById(R.id.txtMaternoDialog2);
        docvisita =(TextView)findViewById(R.id.txtDniDialogAcompañanteVisita);
        tipdoc =(TextView)findViewById(R.id.txtTipoDocumentoDialog2);
        doc =(TextView)findViewById(R.id.txtDniAcompañateDialog);
        correo =(TextView)findViewById(R.id.txtCorreoAcompañante);
        empresa =(TextView)findViewById(R.id.txtEmpresaAcompañante);
        ruc =(TextView)findViewById(R.id.txtRucAcompañante);

        Acompañantes acompañantes = (Acompañantes) getIntent().getSerializableExtra("acompañantes");
        if (acompañantes != null){

            nombres.setText(acompañantes.getNombres());
            paterno.setText(acompañantes.getPaterno());
            materno.setText(acompañantes.getMaterno());
            docvisita.setText(acompañantes.getNumDocVisita().toString());
            tipdoc.setText(acompañantes.getTipoDoc());
            doc.setText(acompañantes.getNumDoc().toString());
            correo.setText(acompañantes.getCorreo());
            empresa.setText(acompañantes.getEmpresa());
            ruc.setText(acompañantes.getRUC().toString());

        }
    }
}
