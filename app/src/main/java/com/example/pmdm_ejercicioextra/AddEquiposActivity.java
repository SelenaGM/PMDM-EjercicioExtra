package com.example.pmdm_ejercicioextra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pmdm_ejercicioextra.databinding.ActivityAddEquiposBinding;
import com.example.pmdm_ejercicioextra.modelos.Partido;

public class AddEquiposActivity extends AppCompatActivity {
    
    private ActivityAddEquiposBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        binding = ActivityAddEquiposBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //CREAMOS LA ACCION CUANDO LE DE AL BOTON
        binding.btnGuardarAddEquiposActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //CREAMOS UN PARTIDO CON LA FUNCION
                Partido partido = crearPartido();
                if(partido != null){
                    //METEMOS EL BUNDLE Y DEVOLVEMOS
                    Bundle bundle= new Bundle();
                    bundle.putSerializable("PARTIDO",partido);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK,intent);

                    finish();

                    
                }
                else{
                    Toast.makeText(AddEquiposActivity.this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Partido crearPartido() {
        if(binding.spEquipoLocalAddEquiposActivity.getSelectedItemPosition() == 0){
            return null;
        }
        if(binding.spEquipoVisitanteAddEquiposActivity.getSelectedItemPosition() == 0){
            return null;
        }
        if(binding.txtGolesEquipoLocalAddEquiposActivity.getText().toString().isEmpty() || binding.txtGolesEquipoVisitanteAddEquiposActivity.getText().toString().isEmpty() ||
        binding.txtResumenAddEquiposActivity.getText().toString().isEmpty()){
            return null;
        }
        String equiopL = (String) binding.spEquipoLocalAddEquiposActivity.getSelectedItem();
        String equipoV = (String) binding.spEquipoVisitanteAddEquiposActivity.getSelectedItem();
        int golL = Integer.parseInt(binding.txtGolesEquipoLocalAddEquiposActivity.getText().toString());
        int golV = Integer.parseInt(binding.txtGolesEquipoVisitanteAddEquiposActivity.getText().toString());
        
        return new Partido(equiopL,golL,equipoV,golV , binding.txtResumenAddEquiposActivity.getText().toString());
        
        
    }
}