package com.example.intellisoftgloriaaccesomovil.Activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Header;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.intellisoftgloriaaccesomovil.BD.ConexionSQLiteHelper;
import com.example.intellisoftgloriaaccesomovil.R;
import com.example.intellisoftgloriaaccesomovil.TabItems.Login;
import com.example.intellisoftgloriaaccesomovil.TabItems.User;
import com.example.intellisoftgloriaaccesomovil.TabItems.UserClient;
import com.example.intellisoftgloriaaccesomovil.Token.APIInterface;
import com.example.intellisoftgloriaaccesomovil.Token.APIClient;
import com.example.intellisoftgloriaaccesomovil.Token.AuthenticationRequest;
import com.example.intellisoftgloriaaccesomovil.Token.Token;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ConexionSQLiteHelper conn;

    EditText edtuser, edtpass;
    Button login, url;

    AuthenticationRequest authenticationRequest;
    APIInterface apiInterface;


    int contador = 0;

    private static String usuario = "admin";
    private static String password = "123";


    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://proy.intellisoftsa.com/Intellisoft.GloriaApiAccess/Api/EmpleadoPuntoControl/")
            .addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    UserClient userClient = retrofit.create(UserClient.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CREO BD Y AGREGA DATOS-----------
        conn = new ConexionSQLiteHelper(this);
        //---------------------------------








        edtuser = (EditText) findViewById(R.id.edtUser);
        edtpass = (EditText) findViewById(R.id.edtPassword);
        login = (Button) findViewById(R.id.btnLogin);
        url = (Button)findViewById(R.id.btnUrl);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                apiInterface = APIClient.getClient().create(APIInterface.class);
                authenticationRequest.user ="vmorales";
                authenticationRequest.password ="abc123";
                getTokenResponse();
*/

//login();
                if(edtuser.getText().toString().trim().length() == 0 && edtpass.getText().toString().trim().length() == 0){
                    Toast.makeText(MainActivity.this, "Ingrese un usuario y contraseña", Toast.LENGTH_SHORT).show();
                }else {

                    if(edtuser.getText().toString().trim().equals(usuario) && edtpass.getText().toString().trim().equals(password)){

                        Intent i = new Intent(MainActivity.this, IntellisoftGloriaAccesoMovil_Options.class);
                        startActivity(i);
                    }else {
                        Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com.pe"));   //Ejemplo de url
                startActivity(i);

                //finish();
                //System.exit(0);
            }
        });

    }

    private static String token;

    private void login (){
        Login login = new Login("vmorales", "abc123");
        Call<User> call = userClient.login(login);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, response.body().getToken(), Toast.LENGTH_SHORT).show();
                    token = response.body().getToken();
                }else {
                    Toast.makeText(MainActivity.this, "login failed :(", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getTokenResponse() {

        Call<Token> call = APIInterface.getLoginResponse(authenticationRequest);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Token token = response.body();
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                call.cancel();
            }
        });

    }


    @Override
    public void onBackPressed() {

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

        if(contador == 0){
            Toast.makeText(this, "Presione de nuevo para salir", Toast.LENGTH_SHORT).show();
            contador++;
        }else {
            super.onBackPressed();
        }

        new CountDownTimer(3000,1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                contador = 0;
            }
        }.start();
    }

}

/*
    private static Bitmap convertString64ToImage(String base64String) {
        byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
    public static Bitmap convertStringToBitmap(String base64String) {
        return convertString64ToImage(base64String);
    }

 */