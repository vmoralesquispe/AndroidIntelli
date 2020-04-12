package com.example.intellisoftgloriaaccesomovil.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.intellisoftgloriaaccesomovil.R;

public class IntellisoftGloriaAccesoMovil_Options extends AppCompatActivity {

    Button btnsinc, btncontrolacces, btnregistrovisita, btnaprobvisita, btnconsultaacceso, btnsalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intellisoft_gloria_movil__options);

        btnsinc = (Button)findViewById(R.id.btnSincronizacion);
        btncontrolacces = (Button)findViewById(R.id.btnControlAcceso);
        btnregistrovisita = (Button)findViewById(R.id.btnRegistroVisita);
        btnaprobvisita = (Button)findViewById(R.id.btnAprobacionVisita);
        btnconsultaacceso = (Button)findViewById(R.id.btnConsultaAccesos);
        btnsalir = (Button)findViewById(R.id.btnExit);

        btnsinc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntellisoftGloriaAccesoMovil_Options.this, IntellisoftGloriaAccesoMovil_Sincronizacion.class);
                startActivity(intent);
            }
        });

        btncontrolacces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntellisoftGloriaAccesoMovil_Options.this, IntellisoftGloriaAccesoMovil_ControlAcceso.class);
                startActivity(intent);
            }
        });

        btnregistrovisita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntellisoftGloriaAccesoMovil_Options.this, IntellisoftGloriaAccesoMovil_RegistroVisita.class);
                startActivity(intent);
            }
        });

        btnaprobvisita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntellisoftGloriaAccesoMovil_Options.this, IntellisoftGloriaAccesoMovil_AprobarVisita.class);
                startActivity(intent);
            }
        });

        btnconsultaacceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntellisoftGloriaAccesoMovil_Options.this, IntellisoftGloriaAccesoMovil_ConsultaAccesos.class);
                startActivity(intent);
            }
        });

        btnsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(IntellisoftGloriaAccesoMovil_Options.this);
                alerta.setIcon(R.drawable.logout);
                alerta.setMessage("¿Desea salir de la aplicación?");
                alerta.setCancelable(true);
                        alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                /*Intent intent = new Intent(IntellisoftGloriaMovil_Options.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                                System.exit(0);
                                 */

                                onBackPressed();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();
                            }
                        });
                AlertDialog titulo = alerta.create();
                titulo.setTitle("Salida");
                titulo.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }
}