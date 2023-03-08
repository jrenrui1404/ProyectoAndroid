package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Consulta_Activity extends AppCompatActivity {

    TextView txt_info;
    ImageView img1;
    String temp;
    Button btn_volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        txt_info=findViewById(R.id.txt_info);
        img1=findViewById(R.id.imageView);
        btn_volver=findViewById(R.id.btn_volver);
        Bundle extras = getIntent().getExtras();
        String informacion = extras.getString("informacion");
        String imagen = extras.getString("imagen");
        txt_info.setText(informacion);
        System.out.println(imagen);
        Picasso.get().load(imagen).error(R.mipmap.ic_launcher).into(img1);
        btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Consulta_Activity.this, ConfidencialActivity.class);
                startActivity(intent);
            }
        });

    }

    public void muestraToast(String mensaje) {
        Toast.makeText(this, "" + mensaje, Toast.LENGTH_SHORT).show();
    }

}