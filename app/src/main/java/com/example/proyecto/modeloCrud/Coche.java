package com.example.proyecto.modeloCrud;

import com.example.proyecto.modelo.Usuario;

import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;

public class Coche {

    private String id;
    private String marca;
    private String modelo;

    public Coche() {
    }

    public Coche(String id, String marca, String modelo) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "id='" + id + '\'' +
                ", nombre='" + marca + '\'' +
                ", email='" + modelo + '\'' +
                '}';
    }
}
