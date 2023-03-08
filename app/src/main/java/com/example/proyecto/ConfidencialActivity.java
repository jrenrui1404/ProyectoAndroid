package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.databinding.ActivityMainBinding;
import com.example.proyecto.db.BaseDatos;
import com.example.proyecto.modelo.ModeloRetorno;
import com.google.android.material.textfield.TextInputEditText;

import java.security.spec.ECField;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConfidencialActivity extends AppCompatActivity {

    public Button btn_entrar_pokemon;
    public EditText txtConsulta;
    public String respuesta = "", imagen = "";
    public ModeloRetorno pokedex = new ModeloRetorno();

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_confidencial);

        //usuario validado
        TextView val = findViewById(R.id.usuario_validado);
        val.setText("Usuario: " + BaseDatos.getInstance().getNombre());

        //navegacion
        binding.bottonNs

        //api
        btn_entrar_pokemon = findViewById(R.id.btn_entrar_pokemon);
        txtConsulta = findViewById(R.id.in_consulta);
        btn_entrar_pokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConsultarApi rg = new ConsultarApi();
                try{
                    rg.respuesta(txtConsulta.getText().toString());
                    muestraToast("Procesando...");
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            pokedex = rg.modeloRetorno;
                            respuesta = "ID: " + pokedex.getId() + "\n" +
                                    "Nombre: " + pokedex.getName() + "\n" +
                                    "Altura: " + pokedex.getHeight() + "\n" +
                                    "Peso: " + pokedex.getWeight();
                            imagen = pokedex.getFront_default();
                            if(!respuesta.equals("")){
                                Intent intent = new Intent(ConfidencialActivity.this, Consulta_Activity.class);

                                intent.putExtra("informacion", respuesta);
                                intent.putExtra("imagen", imagen);
                                startActivity(intent);
                            }
                        }
                    }, 5000);
                } catch (Exception ex){
                    muestraToast("Error: " + ex);
                }
            }
        });
    }

    public void irUser(View v){
        Intent i = new Intent(this, UserChangeActivity.class);
        startActivity(i);
    }

    public void muestraToast(String mensaje){
        Toast.makeText(this, "" + mensaje, Toast.LENGTH_SHORT).show();
    }


}