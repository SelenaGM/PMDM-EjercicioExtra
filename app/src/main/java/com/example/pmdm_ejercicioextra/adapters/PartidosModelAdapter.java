package com.example.pmdm_ejercicioextra.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsetsController;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmdm_ejercicioextra.MainActivity;
import com.example.pmdm_ejercicioextra.MostrarInformacionActivity;
import com.example.pmdm_ejercicioextra.R;
import com.example.pmdm_ejercicioextra.modelos.Partido;

import java.util.List;

public class PartidosModelAdapter  extends RecyclerView.Adapter<PartidosModelAdapter.PartidoVH>{
    //EL MODEL ADAPTER ENSEÑARA LOS CARDS Y TAMBIEN PUEDES COGER LA POSICION DEL CARD EN EL QUE CLICKAS PARA HACER OTRAS COSAS
    private List<Partido> objects;
    private int resource;
    private Context context;


    //tienes que hacer un constructor
    public PartidosModelAdapter(List<Partido> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;


    }

    public class PartidoVH extends RecyclerView.ViewHolder {
        TextView lblEquipoLocal, lblEquipoVisitante, lblGolesLocal, lblGolesVisitante;
        ImageButton btnInfo;

        public PartidoVH(@NonNull View itemView) {
            super(itemView);
            lblEquipoLocal = itemView.findViewById(R.id.lblEquipoLocalCard);
            lblEquipoVisitante= itemView.findViewById(R.id.lblEquipoVisitanteCard);
            lblGolesLocal = itemView.findViewById(R.id.lblGolesLocalCard);
            lblGolesVisitante = itemView.findViewById(R.id.lblGolesVisitanteCard);
            btnInfo = itemView.findViewById(R.id.btnInfoCard);
        }
    }

    @NonNull
    @Override
    public PartidosModelAdapter.PartidoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //ESTE SIEMPRE ES IGUAL
        View partidoView = LayoutInflater.from(context).inflate(resource, null);
        partidoView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        return new PartidoVH(partidoView);
    }

    @Override
    public void onBindViewHolder(@NonNull PartidosModelAdapter.PartidoVH holder, int position) {
        //ESTO HACE COSAS VARIAS
        Partido p = objects.get(position);
        holder.lblEquipoLocal.setText(p.getEquipo1());
        holder.lblEquipoVisitante.setText(p.getEquipo2());
        holder.lblGolesLocal.setText(String.valueOf(p.getGol1()));
        holder.lblGolesVisitante.setText(String.valueOf(p.getGol2()));

        //CUANDO CLICAS AL BOTON DE INFO TE ENSEÑARA EL ALERT DIALOG
        holder.btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInfo(p, holder.getAdapterPosition()).show();
            }
        });

        //PARA PODER ABRIR UNA ACTIVIDAD DESDE EL ADAPTER PARA TENER LA POSICION Y ENVIAR EL PARTIDO A LA TERCERA ACTIVIDAD
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(context, MostrarInformacionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("PARTIDO", p);
                bundle.putInt("POSICION",holder.getAdapterPosition() );
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });


    }



    private AlertDialog showInfo(Partido p, int adapterPosition) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("RESULTADO DEL PARTIDO");
        if(p.getGol1()<p.getGol2()){
            builder.setMessage("El ganador es "+p.getEquipo2());
        }
        if (p.getGol1()>p.getGol2()){
            builder.setMessage("El ganador es "+p.getEquipo1());
        }else{
            builder.setMessage("Empate");
        }
        builder.setNegativeButton("Cancelar", null);

        return builder.create();
    }


    @Override
    public int getItemCount() {
        return objects.size();
    }


}
