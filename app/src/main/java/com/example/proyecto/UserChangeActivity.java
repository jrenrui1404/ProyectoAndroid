package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyecto.db.BaseDatos;
import com.example.proyecto.modelo.Usuario;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class UserChangeActivity extends AppCompatActivity {

    private TextView usuario;
    private TextView password;

    private Realm con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_change);

        usuario=findViewById(R.id.usuario);
        password=findViewById(R.id.password);

        con = BaseDatos.getInstance().conectar(getBaseContext());
        Usuario u = con.where(Usuario.class).findFirst();
        usuario.setText(u.getNombre());
        usuario.setText(u.getPassword());


        Button boton = findViewById(R.id.boton_guardar);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });

    }

    public void guardar(){
        con.beginTransaction();
        Usuario u = con.where(Usuario.class).findFirst();
        u.setNombre(usuario.getText().toString());
        u.setPassword(password.getText().toString());
        con.copyToRealmOrUpdate(u);
        con.commitTransaction();
        Intent i = new Intent(this, ConfidencialActivity.class);
        startActivity(i);
    }

}