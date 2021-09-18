package com.example.inventario;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CharacterapiService {
    @GET("character")
    Call<CharacterResponse> obetenerDatosCharacter(@Query("page") int page);
}
