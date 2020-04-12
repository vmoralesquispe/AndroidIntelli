package com.example.intellisoftgloriaaccesomovil.BD;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.FileUtils;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.suprema.IBioMiniDevice;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {
    private static final String TAG = ConexionSQLiteHelper.class.getSimpleName();

    public static IBioMiniDevice mCurrentDevice = null;

     Resources mResources;
    Context context;
    SQLiteDatabase db;

    public static final String DATABASE_NAME = "bd_movilGloria.db";

    public ConexionSQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, 1);

        mResources = context.getResources();
        db = this.getWritableDatabase();
    }

    @SuppressLint("NewApi")
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Utilidades.TB_Empleado.CREAR_TABLA_EMPLEADO);
        Log.d(TAG, "BASE DE DATOS CREADA CORRECTAMENTE");

        try {
            getDataEmpleado(db);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

       /* db.execSQL(Utilidades.TB_Empleado_BIO.CREAR_TABLA_EMPLEADO_BIO);

        try {
            getDataEmpleadoBio(db);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        db.execSQL(Utilidades.TB_Marca.CREAR_TABLA_MARCA);
        db.execSQL(Utilidades.TB_Visita.CREAR_TABLA_VISITA);
        db.execSQL(Utilidades.TB_Acompañantes.CREAR_TABLA_ACOMPAÑANTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TB_Empleado.TABLA_EMPLEADO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TB_Empleado_BIO.TABLA_EMPLEADO_BIO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TB_Marca.TABLA_MARCA);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TB_Visita.TABLA_VISITA);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TB_Acompañantes.TABLA_ACOMPAÑANTES);
        context.deleteDatabase("bd_movilGloria.db");
        onCreate(db);
    }


    //----------------------------------------------------------------CONSUME EL API--------------------------------------------------------------------------
    public static void getDataEmpleado(SQLiteDatabase db) throws IOException, JSONException{

        String sqlite = "https://proy.intellisoftsa.com/Intellisoft.GloriaApiAccess/Api/EmpleadoPuntoControl/2";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL url = null;
        HttpURLConnection conn;

        final String data_IdEmpleado = "IdEmpleado";
        final String data_Cod_Interno = "CodigoInterno";
        final String data_Tipo_Documento = "TipoDocumento";
        final String data_Numero_Documento = "NumeroDocumento";
        final String data_Nombre = "Nombre";
        final String dataApellido_Paterno = "ApellidoPaterno";
        final String dataApellido_Materno = "ApellidoMaterno";
        final String data_Sede = "Sede";
        final String dataCod_Tarjeta = "CodigoTarjeta";
        final String dataFoto = "Foto";

        try {
            url = new URL(sqlite);

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String inputLine;
            StringBuffer response = new StringBuffer();
            String json = "";

            while ((inputLine = in.readLine())!=null)
            {
                response.append(inputLine);
            }

            json = response.toString();
            JSONArray jsonArray = null;
            jsonArray = new JSONArray(json);

            for (int i = 0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Log.d("Salida", jsonObject.optString("$id"));

                Integer IdEmpleado;
                String CodigoInterno;
                String TipoDocumento;
                String NumeroDocumento;
                String Nombre;
                String ApellidoPaterno;
                String ApellidoMaterno;
                String Sede;
                String CodigoTarjeta;
                String Foto;

                JSONObject empleadoItemObject = jsonArray.getJSONObject(i);

                IdEmpleado = empleadoItemObject.getInt(data_IdEmpleado);
                CodigoInterno = empleadoItemObject.getString(data_Cod_Interno);
                TipoDocumento = empleadoItemObject.getString(data_Tipo_Documento);
                NumeroDocumento = empleadoItemObject.getString(data_Numero_Documento);
                Nombre = empleadoItemObject.getString(data_Nombre);
                ApellidoPaterno = empleadoItemObject.getString(dataApellido_Paterno);
                ApellidoMaterno = empleadoItemObject.getString(dataApellido_Materno);
                Sede = empleadoItemObject.getString(data_Sede);
                CodigoTarjeta = empleadoItemObject.getString(dataCod_Tarjeta);
                Foto = empleadoItemObject.getString(dataFoto);

                ContentValues empleadovalues = new ContentValues();

                empleadovalues.put(Utilidades.TB_Empleado.CAMPO_IDEMPLEADO, IdEmpleado);
                empleadovalues.put(Utilidades.TB_Empleado.CAMPO_CODIGOINTERNO, CodigoInterno);
                empleadovalues.put(Utilidades.TB_Empleado.CAMPO_TIPODOCUMENTO, TipoDocumento);
                empleadovalues.put(Utilidades.TB_Empleado.CAMPO_NUMERODOCUMENTO, NumeroDocumento);
                empleadovalues.put(Utilidades.TB_Empleado.CAMPO_NOMBRE, Nombre);
                empleadovalues.put(Utilidades.TB_Empleado.CAMPO_APELLIDOPATERNO, ApellidoPaterno);
                empleadovalues.put(Utilidades.TB_Empleado.CAMPO_APELLIDOMATERNO, ApellidoMaterno);
                empleadovalues.put(Utilidades.TB_Empleado.CAMPO_SEDE, Sede);
                empleadovalues.put(Utilidades.TB_Empleado.CAMPO_CODIGOTARJETA, CodigoTarjeta);
                empleadovalues.put(Utilidades.TB_Empleado.CAMPO_FOTO, Foto);

                db.insert(Utilidades.TB_Empleado.TABLA_EMPLEADO, null, empleadovalues);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    //-----------------------------------------------API-EMPLEADOBIO--------------------------------
    //@RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void getDataEmpleadoBio(SQLiteDatabase db) throws IOException, JSONException{


        //API Empleado BIO

        String sqlite = "https://proy.intellisoftsa.com/Intellisoft.GloriaApiAccess/api/EmpleadoBio/huellah5?IdEmpleado=203";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL url = null;
        HttpURLConnection conn;


        final String data_IdHuella = "IdHuella";
        final String data_IdEmpleado = "IdEmpleado";
        final String data_ImagenHuella = "ImagenHuella";
        final String data_Imagen_Huella_Temp = "ImagenHuellaTemp";

        try {
            url = new URL(sqlite);

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String inputLine;
            StringBuffer response = new StringBuffer();
            String json = "";

            while ((inputLine = in.readLine())!=null)
            {
                response.append(inputLine);
            }

            json = response.toString();
            JSONArray jsonArray = null;
            jsonArray = new JSONArray(json);

            for (int i = 0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Log.d("TMP", jsonObject.optString("IdEmpleado"));

                Integer IdHuella;
                Integer IdEmpleado;
                String ImagenHuella;
                String ImagenHuellaTemp;


                JSONObject empleadoItemObject = jsonArray.getJSONObject(i);


                IdHuella = empleadoItemObject.getInt(data_IdHuella);
                IdEmpleado = empleadoItemObject.getInt(data_IdEmpleado);
                ImagenHuella = empleadoItemObject.getString(data_ImagenHuella);
                ImagenHuellaTemp = empleadoItemObject.getString(data_Imagen_Huella_Temp);

                ContentValues empleadovalues = new ContentValues();

                empleadovalues.put(Utilidades.TB_Empleado_BIO.CAMPO_ID_HUELLA, IdHuella);
                empleadovalues.put(Utilidades.TB_Empleado_BIO.CAMPO_ID_EMPLEADO, IdEmpleado);
                empleadovalues.put(Utilidades.TB_Empleado_BIO.CAMPO_IMAGEN_HUELLA, ImagenHuella);
                empleadovalues.put(Utilidades.TB_Empleado_BIO.CAMPO_IMAGEN_HUELLA_TMP, ImagenHuellaTemp);

                db.insert(Utilidades.TB_Empleado_BIO.TABLA_EMPLEADO_BIO, null, empleadovalues);

//--------------------------------------------------------------------------------------------------

//                byte[] data = Base64.decode(ImagenHuella, Base64.DEFAULT);
//                String text = new String(data, "Windows-1252");

                    try {
                        File file = new File(Environment.getExternalStoragePublicDirectory(
                                "/Finger/") + "/" + IdEmpleado + ".tmp");
                        FileOutputStream osw = new FileOutputStream(file);
                        osw.write(ImagenHuella.getBytes());
                        //file.length();
                        osw.flush();
                        Log.d("Archivos creados", jsonObject.optString("ImagenHuellaTemp"));
                        osw.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
//--------------------------------------------------------------------------------------------------

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    private static Bitmap convertString64ToImage(String base64String) {
        byte[] decodedString = android.util.Base64.decode(base64String, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

    public static Bitmap convertStringToBitmap(String base64String) {
        return convertString64ToImage(base64String);
    }

}
