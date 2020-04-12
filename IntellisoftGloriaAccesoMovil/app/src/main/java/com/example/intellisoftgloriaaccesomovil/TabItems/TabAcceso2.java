package com.example.intellisoftgloriaaccesomovil.TabItems;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.intellisoftgloriaaccesomovil.Activity.IntellisoftGloriaAccesoMovil_Options;
import com.example.intellisoftgloriaaccesomovil.R;

public class TabAcceso2 extends AppCompatActivity {

    TextView  nombre, sede,fechaHora, placa, puesto, empresa, jefe;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // Inflate the layout for this fragment
        setContentView(R.layout.fragment_tab_controldeacceso2);


        nombre = (TextView) findViewById(R.id.txtNombre);
        sede = (TextView) findViewById(R.id.txtSede);
        fechaHora = (TextView) findViewById(R.id.txtFechaHoraControlAcceso);
        placa = (TextView) findViewById(R.id.txtPlacaControlAcceso);
        puesto =(TextView) findViewById(R.id.txtPuestoControlAcceso);
        empresa =(TextView) findViewById(R.id.txtEmpresaControlAcceso);
        jefe =(TextView) findViewById(R.id.txtJefeControlAcceso);

        Button btnacep = (Button) findViewById(R.id.btnAceptar);
        btnacep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TabAcceso2.this, IntellisoftGloriaAccesoMovil_Options.class);
                startActivity(i);
            }
        });

    }

}
