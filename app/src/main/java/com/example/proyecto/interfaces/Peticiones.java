package com.example.proyecto.interfaces;

import com.example.proyecto.modelo.Pokedex;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Peticiones {

    @GET("pokemon/{id}")
    Call<Pokedex> consultar(@Path("id") String id);

}
