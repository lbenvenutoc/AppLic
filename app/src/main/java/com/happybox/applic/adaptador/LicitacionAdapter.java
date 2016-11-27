package com.happybox.applic.adaptador;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.happybox.applic.LicApp;
import com.happybox.applic.R;
import com.happybox.applic.actividad.LicitacionActivity;
import com.happybox.applic.modelo.Licitacion;

import java.util.List;


public class LicitacionAdapter extends RecyclerView.Adapter<LicitacionAdapter.ViewHolder> {
    List<Licitacion> licitaciones;

    public void setLicitaciones(List<Licitacion> licitaciones) { this.licitaciones = licitaciones;}
    @Override
    public LicitacionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_licitacion, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LicitacionAdapter.ViewHolder holder,final int position) {        holder.codigoLicitacionTextView.setText("Código: "+licitaciones.get(position).getCodLic());
        holder.categoriaLicitacionTextView.setText("Categoria: "+licitaciones.get(position).getDesCat());
        holder.descripcionLicitacionTextView.setText("Resumen: "+licitaciones.get(position).getDesLic());
        holder.valorReferencialLicitacionTextView.setText("Valor Referencial: "+licitaciones.get(position).getValRef()+" "+licitaciones.get(position).getMonLic());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LicApp.obtenerInstancia().obtenerServicio().setLicitacion(licitaciones.get(position));
                    Intent intent = new Intent (v.getContext(), LicitacionActivity.class);
                    //intent.putExtra("licitacion", licitaciones.get(position));
                    v.getContext().startActivity(intent);
                }

        });


    }

    @Override
    public int getItemCount() {
        return licitaciones.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView codigoLicitacionTextView;
        TextView categoriaLicitacionTextView;
        TextView descripcionLicitacionTextView;
        TextView valorReferencialLicitacionTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            codigoLicitacionTextView = (TextView) itemView.findViewById(R.id.codigoLicitacionTextView);
            categoriaLicitacionTextView = (TextView) itemView.findViewById(R.id.categoriaLicitacionTextView);
            descripcionLicitacionTextView = (TextView) itemView.findViewById(R.id.descripcionLicitacionTextView);
            valorReferencialLicitacionTextView = (TextView) itemView.findViewById(R.id.valorReferencialLicitacionTextView);
        }
    }
}
