package com.example.intellisoftgloriaaccesomovil.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.intellisoftgloriaaccesomovil.BD.ConexionSQLiteHelper;
import com.example.intellisoftgloriaaccesomovil.BD.Empleado;
import com.example.intellisoftgloriaaccesomovil.R;

public class IntellisoftGloriaAccesoMovil_ConsultaAccesos_Detalle extends AppCompatActivity {

    ConexionSQLiteHelper conn;

    TextView tnombre, tpaterno, tmaterno, ttipdoc, tnumdoc,tidempleado, tcodinterno, tcodtarjeta, tsede;
    ImageView ifoto;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogo_consultaaccesos);

        conn = new ConexionSQLiteHelper(this);

        tnombre = (TextView) findViewById(R.id.txtNombreDialog);
        tpaterno = (TextView) findViewById(R.id.txtPaternoDialog);
        tmaterno = (TextView) findViewById(R.id.txtMaternoDialog);
        ttipdoc = (TextView) findViewById(R.id.txtTipoDocumentoDialog);
        tnumdoc = (TextView) findViewById(R.id.txtDniDialog);
        tidempleado = (TextView) findViewById(R.id.txtIdEmpleadoDialog);
        tcodinterno = (TextView) findViewById(R.id.txtCodInternoDialog);
        tcodtarjeta = (TextView) findViewById(R.id.txtCodTarjetaDialog);
        tsede = (TextView) findViewById(R.id.txtSedeDialog);
        ifoto = (ImageView) findViewById(R.id.imgFotoDialog);

        Empleado empleado = (Empleado) getIntent().getSerializableExtra("empleado");
        if (empleado != null){

            tnombre.setText(empleado.getNombre());
            tpaterno.setText(empleado.getApellidoPaterno());
            tmaterno.setText(empleado.getApellidoMaterno());
            ttipdoc.setText(empleado.getTipoDocumento());
            tnumdoc.setText(empleado.getNumDocumento());
            tidempleado.setText(empleado.getIdEmpleado().toString());
            tcodinterno.setText(empleado.getCodInterno());
            tcodtarjeta.setText(empleado.getCodTarjeta());
            tsede.setText(empleado.getSede());

            //-------------fotos----------------------
            byte[] decodeString = android.util.Base64.decode(empleado.getFoto(),Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodeString,0,decodeString.length);
            ifoto.setImageBitmap(decodedByte);
        }
    }
}
    /*
    private static Bitmap convertString64ToImage(String base64String) {
        byte[] decodedString = android.util.Base64.decode(base64String, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

    public static Bitmap convertStringToBitmap(String base64String) {
        return convertString64ToImage(base64String);
    }
    */

