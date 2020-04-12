package com.example.intellisoftgloriaaccesomovil.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.intellisoftgloriaaccesomovil.BD.Visita;
import com.example.intellisoftgloriaaccesomovil.R;
import com.example.intellisoftgloriaaccesomovil.Activity.IntellisoftGloriaAccesoMovil_AprobarVisita2;
import java.util.ArrayList;

public class AdapterListaAprobarVisita extends RecyclerView.Adapter<AdapterListaAprobarVisita.MyViewHolder>{

    private View.OnClickListener listener;

    Context mContext;
    ArrayList<Visita> listaVisita;

    public AdapterListaAprobarVisita(Context context, ArrayList<Visita> listaVisita) {
        this.mContext = context;
        this.listaVisita = listaVisita;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlista_visita, null, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_name.setText(listaVisita.get(position).getNombres());
        holder.tv_paterno.setText(listaVisita.get(position).getPaterno());
        holder.tv_materno.setText(listaVisita.get(position).getMaterno());
        holder.tv_dni.setText(listaVisita.get(position).getNumDoc().toString());
        //holder.img.setImageResource(listaVisita.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return listaVisita.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tv_name, tv_dni, tv_paterno, tv_materno;
        private ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.txtUserAprob);
            tv_paterno = (TextView) itemView.findViewById(R.id.txtPaternoAprob);
            tv_materno = (TextView) itemView.findViewById(R.id.txtMaternoAprob);
            tv_dni = (TextView) itemView.findViewById(R.id.txtNumDocumentoAprob);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, IntellisoftGloriaAccesoMovil_AprobarVisita2.class);
            intent.putExtra("visita",listaVisita.get(getAdapterPosition()));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        }
    }
}
