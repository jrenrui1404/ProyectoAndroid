package com.example.proyecto.modeloCrud;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.proyecto.R;
import com.example.proyecto.databinding.FragmentCrudBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class CrudFragment extends Fragment {

    private FragmentCrudBinding binding;
    private List<Coche> lista;
    private CocheAdapter adapter;

    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        binding = FragmentCrudBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        lista = new ArrayList<>();
        Coche u = new Coche();
        u.setMarca("Marca: " + "Audi");
        u.setModelo("Modelo: " + "A3");
        lista.add(u);

        adapter = new CocheAdapter(lista, getContext());
        recyclerView = binding.lista;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirDialog();
            }
        });

        binding.filtro.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String fil = binding.filtro.getText().toString();
                lista.clear();
                return false;
            }
        });

        return root;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item){
        //comprobamos menu accionado y realizamos su accion
        if(item.getItemId()==R.id.editar){
            //mostramos el dialog para editar
            int pos = adapter.getPosition();
            abrirDialog(pos);
        }else if (item.getItemId()==R.id.borrar){
            int pos = adapter.getPosition();
            lista.remove(pos);
            adapter.notifyDataSetChanged();
        }
        return super.onContextItemSelected(item);
    }


    public void abrirDialog(){
        abrirDialog(-1);
    }

    private int posicionEditar = -1;

    private void abrirDialog(int posicion) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Vehículos");
        builder.setMessage("Inserta tu coche");

        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_coche, null);
        builder.setView(view);

        //rellenamos los campos a la hora de editar el usuario
        posicionEditar = posicion;
        if(posicion>=0){
            Coche coche = lista.get(posicion);

            TextView marca = view.findViewById(R.id.marca);
            marca.setText(coche.getMarca());

            TextView modelo = view.findViewById(R.id.modelo);
            modelo.setText(coche.getModelo());
        }

        builder.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                insertarCoche(view);
            }
        });

        builder.create().show();

    }

    private void insertarCoche(View view) {

        Coche coche;

        if(posicionEditar == -1) {
            coche = new Coche();
            //crea un id aleatorio
            coche.setId(UUID.randomUUID().toString());
        } else {
            coche = lista.get(posicionEditar);
        }

        TextView marca = view.findViewById(R.id.marca);
        coche.setMarca("Marca: " + marca.getText() + "");

        TextView modelo = view.findViewById(R.id.modelo);
        coche.setModelo("Modelo: " + modelo.getText() + "");

        if(posicionEditar == -1) {
            lista.add(coche);
        } else {
            lista.set(posicionEditar, coche);
        }

        adapter.notifyDataSetChanged();

    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }

}