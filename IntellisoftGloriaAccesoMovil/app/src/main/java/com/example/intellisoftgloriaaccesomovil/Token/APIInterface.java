package com.example.intellisoftgloriaaccesomovil.Token;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIInterface {

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("/authenticate")
    static Call<Token> getLoginResponse(@Body AuthenticationRequest request) {
        return null;
    }

}
