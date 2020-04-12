package com.example.intellisoftgloriaaccesomovil.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.intellisoftgloriaaccesomovil.BD.ConexionSQLiteHelper;
import com.example.intellisoftgloriaaccesomovil.BD.Utilidades;
import com.example.intellisoftgloriaaccesomovil.R;
import com.github.ybq.android.spinkit.style.Circle;
import org.json.JSONException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import static com.example.intellisoftgloriaaccesomovil.BD.ConexionSQLiteHelper.getDataEmpleado;
import static com.example.intellisoftgloriaaccesomovil.BD.ConexionSQLiteHelper.getDataEmpleadoBio;

public class IntellisoftGloriaAccesoMovil_Sincronizacion extends AppCompatActivity {

    private static final String TAG =IntellisoftGloriaAccesoMovil_Sincronizacion.class.getSimpleName();

    Button btnsinc;
    ImageView imgsincr1, imgcheck1, imgsincr2, imgcheck2, imgsincr3, imgcheck3;

    TextView txtdatetime;

    ProgressBar progressBar;

    private ObjectAnimator animatorRotation;
    private long animationDuration = 1000;

    private final int TIEMPO = 2000;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intellisoft_gloria_acceso_movil__sincronizacion);

//        Log.d("------DESCAGA-----", "Inciiando");
  //      Downloand d = new Downloand();
    //    d.execute();

        progressBar = (ProgressBar)findViewById(R.id.carga);
        Circle circle = new Circle();
        progressBar.setIndeterminateDrawable(circle);

        txtdatetime =(TextView)findViewById(R.id.txtdatetime);

        btnsinc = (Button)findViewById(R.id.btnSincronizar);
        imgsincr1 = (ImageView)findViewById(R.id.imgSincro1);
        imgcheck1 =(ImageView)findViewById(R.id.imgCheck1);
        imgsincr2 = (ImageView)findViewById(R.id.imgSincro2);
        imgcheck2 =(ImageView)findViewById(R.id.imgCheck2);
        imgsincr3 = (ImageView)findViewById(R.id.imgSincro3);
        imgcheck3 =(ImageView)findViewById(R.id.imgCheck3);

        btnsinc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // ClassConection classConection = new ClassConection();
               // classConection.execute("https://proy.intellisoftsa.com/Intellisoft.GloriaApiAccess/api/EmpleadoBio/huellah5?IdEmpleado=203");

                getTmp();

                animacion("rotation1");
                animacion("rotation2");
                animacion("rotation3");
                imgcheck1.setVisibility(View.INVISIBLE);
                imgsincr1.setVisibility(View.VISIBLE);
                imgcheck2.setVisibility(View.INVISIBLE);
                imgsincr2.setVisibility(View.VISIBLE);
                imgcheck3.setVisibility(View.INVISIBLE);
                imgsincr3.setVisibility(View.VISIBLE);

                //Intent b = new Intent(IntellisoftGloriaAccesoMovil_Sincronizacion.this,IntellisoftGloriaMovil_Options.class);
                //Sincronizar();
                //startActivity(b);
            }
        });

    }



    public void getTmp(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            URL url = new URL("https://proy.intellisoftsa.com/Intellisoft.GloriaApiAccess/api/EmpleadoBio/huellah5?IdEmpleado=203");

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.connect();

            File file = new File(Environment.getExternalStoragePublicDirectory(
                    "/Finger/") + "/" + "203" + ".tmp");

            FileOutputStream fileOutput = new FileOutputStream(file);
            InputStream inputStream = urlConnection.getInputStream();

            int totalSize = urlConnection.getContentLength();
            int downloadedSize = 0;

            byte[] buffer = new byte[1024];
            int bufferLength = 0;

            while ( (bufferLength = inputStream.read(buffer)) > 0 ) {

                fileOutput.write(buffer, 0, bufferLength);
                downloadedSize += bufferLength;
                //actualizaProgreso(downloadedSize, totalSize);
            }
            fileOutput.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void animacion(String animacion){

        switch (animacion){
            case "rotation3":
                animatorRotation = ObjectAnimator.ofFloat(imgsincr3, "rotation", 360f, 0f);
                animatorRotation.setDuration(animationDuration);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(animatorRotation);
                animatorSet.start();
                animatorSet.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Sincronizar();
                        animatorSet.start();
                        imgsincr3.setVisibility(View.INVISIBLE);
                        imgcheck3.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                break;
            case "rotation2":
                animatorRotation = ObjectAnimator.ofFloat(imgsincr2, "rotation", 360f, 0f);
                animatorRotation.setDuration(animationDuration);
                AnimatorSet animatorSet2 = new AnimatorSet();
                animatorSet2.play(animatorRotation);
                animatorSet2.start();
                animatorSet2.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        animatorSet2.start();
                        imgsincr2.setVisibility(View.INVISIBLE);
                        imgcheck2.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                break;
            case "rotation1":
                animatorRotation = ObjectAnimator.ofFloat(imgsincr1, "rotation", 360f, 0f);
                animatorRotation.setDuration(animationDuration);
                AnimatorSet animatorSet1 = new AnimatorSet();
                animatorSet1.play(animatorRotation);
                animatorSet1.start();
                animatorSet1.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        animatorSet1.start();
                        imgsincr1.setVisibility(View.INVISIBLE);
                        imgcheck1.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                });
                break;
        }
    }

    public void Sincronizar() {

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this);
        SQLiteDatabase db = conn.getWritableDatabase();

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            // Si hay conexión a Internet en este momento

            //------------------------- Borro la tabla ---------------------------------
            db.execSQL("DROP TABLE " + Utilidades.TB_Empleado.TABLA_EMPLEADO);
          //  db.execSQL("DROP TABLE " + Utilidades.TB_Empleado_BIO.TABLA_EMPLEADO_BIO);
            //db.execSQL("DROP TABLE " + Utilidades.TB_Visita.TABLA_VISITA);
            Log.d(TAG, "TABLA BORRADA CORRECTAMENTE");

            //--------------La vuelvo a insertar con los datos actualizados-------------
            db.execSQL(Utilidades.TB_Empleado.CREAR_TABLA_EMPLEADO);
            try {
                //CONSUMO DEL API----
                getDataEmpleado(db);
                //-------------------
                Log.d(TAG, "DATOS INSERTADOS");

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            /*db.execSQL(Utilidades.TB_Empleado_BIO.CREAR_TABLA_EMPLEADO_BIO);
            try {
                getDataEmpleadoBio(db);
                Log.d(TAG, "TMP Ingresado");

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }*/

            //db.execSQL(Utilidades.TB_Acompañantes.CREAR_TABLA_ACOMPAÑANTES);

            Calendar calendario = Calendar.getInstance();
            Calendar c1 = Calendar.getInstance();
            String dia = Integer.toString(c1.get(Calendar.DATE));
            String mes = Integer.toString(c1.get(Calendar.MONTH));
            String annio = Integer.toString(c1.get(Calendar.YEAR));
            int hora = calendario.get(Calendar.HOUR_OF_DAY);
            int minutos = calendario.get(Calendar.MINUTE);
            int segundos = calendario.get(Calendar.SECOND);
            txtdatetime.setText("Última actualización: "+dia+"/"+mes+"/"+annio+" "+hora+":"+minutos+":"+segundos);

            Toast.makeText(IntellisoftGloriaAccesoMovil_Sincronizacion.this, "DATOS ACTUALIZADOS", Toast.LENGTH_SHORT).show();

        } else {
            // No hay conexión a Internet en este momento

            Toast.makeText(this, "VERIFIQUE SU CONEXIÓN A INTERNET", Toast.LENGTH_SHORT).show();

        }

    }
}
