package com.example.proyecto.db;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class BaseDatos {

    private static BaseDatos instance = new BaseDatos();
    public static BaseDatos getInstance(){
        return instance;
    }

    private Realm con;
    private String nombre;

    public Realm conectar(Context context){
        if (con == null){
            Realm.init(context);
            String nombre="ejemplo_login_v5";
            RealmConfiguration config = new RealmConfiguration.Builder().name(nombre).build();
            con = Realm.getInstance(config);
        }
        return con;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
