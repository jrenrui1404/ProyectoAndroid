package com.example.proyecto.modeloCrud;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto.R;

import java.util.List;

public class CocheAdapter extends RecyclerView.Adapter<CocheAdapter.ViewHolder> {

    private List<Coche> lista;
    private Context context;

    private int position;

    public void setPosition(int position){
        this.position = position;
    }

    public int getPosition(){
        return position;
    }

    public CocheAdapter(List<Coche> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fila = LayoutInflater.from(context).inflate(R.layout.item_coche, parent, false);
        return new ViewHolder(fila);
    }

    //rellena la info
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Coche usu = lista.get(position);
        holder.marca.setText(usu.getMarca());
        holder.modelo.setText(usu.getModelo());

        final int pos = position;

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(pos);
                return false;
            }
        });
    }

    //cuantos elementos hay
    @Override
    public int getItemCount() {
        return lista.size();
    }

    //imflamos la vista
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{

        private TextView marca;
        private TextView modelo;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            marca=itemView.findViewById(R.id.marca);
            modelo=itemView.findViewById(R.id.modelo);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo menuInfo) {
            MenuInflater inflater = new MenuInflater(view.getContext());
            inflater.inflate(R.menu.menu_content_coche, contextMenu);
        }


    }

}
