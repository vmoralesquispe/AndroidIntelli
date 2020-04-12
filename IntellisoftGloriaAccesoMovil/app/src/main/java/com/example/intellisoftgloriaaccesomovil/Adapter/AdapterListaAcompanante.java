package com.example.intellisoftgloriaaccesomovil.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.intellisoftgloriaaccesomovil.Activity.IntellisoftGloriaAccesoMovil_Acompanante_Detalle;
import com.example.intellisoftgloriaaccesomovil.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.intellisoftgloriaaccesomovil.BD.Acompañantes;
import java.util.ArrayList;

public class AdapterListaAcompanante extends RecyclerView.Adapter<AdapterListaAcompanante.MyViewHolder> {

    Context mContext;
    ArrayList<Acompañantes> listaAcompañante;

    public AdapterListaAcompanante(Context context, ArrayList<Acompañantes> listaAcompañante) {
        this.mContext = context;
        this.listaAcompañante = listaAcompañante;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlista_acompanantes, null, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListaAcompanante.MyViewHolder holder, int position) {

        holder.tv_name.setText(listaAcompañante.get(position).getNombres().toString());
        holder.tv_paterno.setText(listaAcompañante.get(position).getPaterno());
        holder.tv_materno.setText(listaAcompañante.get(position).getMaterno());
        holder.tv_dni.setText(listaAcompañante.get(position).getNumDoc().toString());

    }

    @Override
    public int getItemCount() {
        return listaAcompañante.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tv_name, tv_paterno, tv_materno, tv_dni;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.txtNombreAcompañantes);
            tv_paterno = (TextView) itemView.findViewById(R.id.txtPaternoAcompañantes);
            tv_materno = (TextView) itemView.findViewById(R.id.txtMaternoAcompañantes);
            tv_dni = (TextView) itemView.findViewById(R.id.txtNumDocumentoAcompañantes);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, IntellisoftGloriaAccesoMovil_Acompanante_Detalle.class);
            intent.putExtra("acompañantes",listaAcompañante.get(getAdapterPosition()));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        }
    }
}
