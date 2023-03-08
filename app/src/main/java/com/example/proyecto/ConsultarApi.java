package com.example.proyecto;

import com.example.proyecto.interfaces.Peticiones;
import com.example.proyecto.modelo.ModeloRetorno;
import com.example.proyecto.modelo.Pokedex;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConsultarApi {

    public static String URL = "https://pokeapi.co/api/v2/";
    public static Retrofit varRetro;
    ModeloRetorno modeloRetorno = new ModeloRetorno();

    public void respuesta(String id){
        varRetro = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        Peticiones consApi = varRetro.create(Peticiones.class);

        Call<Pokedex> call = consApi.consultar(id);

        call.enqueue(new Callback<Pokedex>() {
            @Override
            public void onResponse(Call<Pokedex> call, Response<Pokedex> response) {
                try{
                    if (response.isSuccessful()){
                        Pokedex pokedex = response.body();
                        modeloRetorno.setId(pokedex.getId());
                        modeloRetorno.setName(pokedex.getName());
                        modeloRetorno.setHeight(pokedex.getHeight());
                        modeloRetorno.setWeight(pokedex.getWeight());
                        modeloRetorno.setFront_default(pokedex.getSprites().getFront_default());
                    }else{
                        System.out.println("Mal");
                        System.out.println(call);
                    }
                }catch (Exception ex){
                    System.out.println("Error " + ex);
                }
            }

            @Override
            public void onFailure(Call<Pokedex> call, Throwable t) {
                System.out.println("Error " + t);
            }
        });

    }

}
