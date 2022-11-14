package com.example.pmdm_ejercicioextra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pmdm_ejercicioextra.databinding.ActivityAddEquiposBinding;
import com.example.pmdm_ejercicioextra.databinding.ActivityMostrarInformacionBinding;
import com.example.pmdm_ejercicioextra.modelos.Partido;

public class MostrarInformacionActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_informacion);

        //LA TERCERA ACTIVIDAD PARA MOSTRAR LA INFORMACION QUE NOS MANDA EL ADAPTER
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Partido p = (Partido) bundle.getSerializable("PARTIDO");

        TextView lblEquipoLocal = findViewById(R.id.lblEquipoLocalMostrarActivity);
        lblEquipoLocal.setText(p.getEquipo1());
        TextView lblEquipoVisitante = findViewById(R.id.lblEquipoVisitanteMostrarActivity);
        lblEquipoVisitante.setText(p.getEquipo2());
        TextView lblGolesL = findViewById(R.id.lblGolesLocalMostrarActivity);
        lblGolesL.setText(String.valueOf(p.getGol1()));
        TextView lblGolesV = findViewById(R.id.lblGolesVisitanteMostrarActivity);
        lblGolesV.setText(String.valueOf(p.getGol2()));
        TextView lblResumen = findViewById(R.id.lblResumenMostrarActivity);
        lblResumen.setText(p.getResumen());


    }
}