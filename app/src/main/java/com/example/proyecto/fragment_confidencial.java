package com.example.proyecto;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.db.BaseDatos;
import com.example.proyecto.modelo.ModeloRetorno;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_confidencial#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_confidencial extends Fragment {

    public Button btn_entrar_pokemon;
    public Button botonCambiarUsuario;
    public EditText txtConsulta;
    public String respuesta = "", imagen = "";
    public ModeloRetorno pokedex = new ModeloRetorno();

    View view;

    public fragment_confidencial() {
    }

    public static fragment_confidencial newInstance() {
        fragment_confidencial fragment = new fragment_confidencial();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_confidencial, container, false);

        //usuario validado
        TextView val = view.findViewById(R.id.usuario_validado);
        val.setText("Usuario: " + BaseDatos.getInstance().getNombre());

        //botonCambiar
        botonCambiarUsuario = view.findViewById(R.id.botonCambiarUsuario);
        botonCambiarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), UserChangeActivity.class);
                startActivity(i);
            }
        });


        //api
        btn_entrar_pokemon = view.findViewById(R.id.btn_entrar_pokemon);
        txtConsulta = view.findViewById(R.id.in_consulta);
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
                                Intent intent = new Intent(getContext(), Consulta_Activity.class);

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
        return view;
    }

    public void muestraToast(String mensaje){
        Toast.makeText(getContext(), "" + mensaje, Toast.LENGTH_SHORT).show();
    }

}