package com.example.proyecto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.db.BaseDatos;
import com.example.proyecto.modelo.Usuario;


import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private TextView nombre;
    private TextView password;
    private TextView mensaje;

    private Realm con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre=findViewById(R.id.usuario);
        password=findViewById(R.id.password);
        mensaje = findViewById(R.id.error);

        con = BaseDatos.getInstance().conectar(getBaseContext());
        long cuantos = con.where(Usuario.class).count();
        if(cuantos==0){
            //admin, admin
            mostrarRegistro();

        }

        Button login = findViewById(R.id.button_entrar);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        Button registro = findViewById(R.id.botonRegistro);
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarRegistro();
            }
        });


    }


    public void mostrarRegistro(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Registro");
        builder.setMessage("Crear el primer usuario");

        View v = LayoutInflater.from(this).inflate(R.layout.dialog_registro, null);
        builder.setView(v);

        builder.setPositiveButton("Registrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    Usuario u = new Usuario();
                    u.setId("1");
                    TextView usu=v.findViewById(R.id.registro_usuario);
                    u.setNombre(usu.getText()+"");

                    TextView pass=v.findViewById(R.id.registro_password);
                    u.setPassword(pass.getText()+"");

                    con.beginTransaction();
                    con.copyToRealmOrUpdate(u);
                    con.commitTransaction();
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "No se pudo registrar " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        builder.create().show();
    }


    public void login(){

        Usuario u = con.where(Usuario.class).equalTo("nombre", nombre.getText().toString()).findFirst();
        if(u==null){
            mensaje.setText("Usuario no encontrado");
        }else {
            if(u.getPassword().equals(password.getText().toString())){
                BaseDatos.getInstance().setNombre(nombre.getText().toString());
                Intent i = new Intent(this, ConfidencialActivity.class);
                startActivity(i);
            }else{
                mensaje.setText("La contraseña es incorrecta");
            }
        }

        Toast.makeText(this, "Estás haciendo login", Toast.LENGTH_SHORT).show();
    }

}