package com.example.pmdm_ejercicioextra;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;


import com.example.pmdm_ejercicioextra.adapters.PartidosModelAdapter;
import com.example.pmdm_ejercicioextra.databinding.ActivityMainBinding;
import com.example.pmdm_ejercicioextra.modelos.Partido;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    //PARA RECOGER EL OBJETO DEL ADDPRODUCTACTIVITY
    private ArrayList<Partido> partidosArrayList;
    private ActivityResultLauncher<Intent> launcherAddEquipo;

    //CUANDO YA TENEMOS EL ADAPTER Y EL RECYCLER
    private PartidosModelAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        //Acuerdate de inicializar el ARRAYLIST
        partidosArrayList = new ArrayList<>();

       adapter = new PartidosModelAdapter(partidosArrayList, R.layout.partidos_view_holder, this);
//        int columnas = 1;
//        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
//            columnas = 2;
//        }

        int columnas = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE? 2 : 1;

        layoutManager = new GridLayoutManager(this,columnas);

        binding.contentMain.contenedor.setLayoutManager(layoutManager);
        binding.contentMain.contenedor.setAdapter(adapter);


        inicializaLaunchers();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            launcherAddEquipo.launch(new Intent(MainActivity.this, AddEquiposActivity.class));
            }
        });
    }

    private void inicializaLaunchers() {
        launcherAddEquipo = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()== RESULT_OK){
                    if(result.getData() != null && result.getData().getExtras() != null){
                        Partido p = (Partido) result.getData().getExtras().getSerializable("PARTIDO");
                        Toast.makeText(MainActivity.this, p.toString(), Toast.LENGTH_SHORT).show();
                        partidosArrayList.add(p);
                        //PARA ENSEÑARLOS NECESITAREMOS EL ADAPTER
                       adapter.notifyItemInserted(partidosArrayList.size()-1);
                    }
                }
            }
        });
    }


}