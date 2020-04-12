package com.example.intellisoftgloriaaccesomovil.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.intellisoftgloriaaccesomovil.BD.Empleado;
import com.example.intellisoftgloriaaccesomovil.Activity.IntellisoftGloriaAccesoMovil_ConsultaAccesos_Detalle;
import com.example.intellisoftgloriaaccesomovil.R;
import java.util.ArrayList;


public class AdapterListaConsultaAccesos extends RecyclerView.Adapter <AdapterListaConsultaAccesos.NewsViewHolder>{

    private Context context;
    ArrayList<Empleado> listaEmpleado;

    public AdapterListaConsultaAccesos(ArrayList<Empleado> listaEmpleado, Context mContext) {
        this.listaEmpleado = listaEmpleado;
        this.context = mContext;
    }


    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlista_empleado,null,false);

        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        holder.nombre.setText(listaEmpleado.get(position).getNombre());
        holder.codinterno.setText(listaEmpleado.get(position).getApellidoPaterno());
        holder.sede.setText(listaEmpleado.get(position).getApellidoMaterno());
        //holder.tipdocumento.setText(listaEmpleado.get(position).getTipoDocumento());
        holder.numdocumento.setText(listaEmpleado.get(position).getNumDocumento().toString());

    }

    @Override
    public int getItemCount() {
        return listaEmpleado.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private LinearLayout item_user;
        private TextView nombre, codinterno, sede, tipdocumento, numdocumento;
        public ImageView ifoto;

        public NewsViewHolder(View itemView) {
            super(itemView);
            item_user =(LinearLayout)itemView.findViewById(R.id.itemUserLista);
            nombre = (TextView)itemView.findViewById(R.id.txtUser);
            codinterno = (TextView)itemView.findViewById(R.id.txtCodInterno);
            sede = (TextView)itemView.findViewById(R.id.txtUserSede);
            tipdocumento = (TextView)itemView.findViewById(R.id.txtTipoDocumento);
            numdocumento = (TextView)itemView.findViewById(R.id.txtNumDocumento);
            ifoto =(ImageView)itemView.findViewById(R.id.imgFotoDialog);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(context, IntellisoftGloriaAccesoMovil_ConsultaAccesos_Detalle.class);
            intent.putExtra("empleado",listaEmpleado.get(getAdapterPosition()));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
