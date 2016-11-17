package com.happybox.applic.adaptador;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.happybox.applic.R;
import com.happybox.applic.actividad.LicitacionActivity;
import com.happybox.applic.actividad.LoginActivity;
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
    public void onBindViewHolder(LicitacionAdapter.ViewHolder holder, int position) {
        holder.lblIdLicitacion.setText("Código: "+licitaciones.get(position).getCodLic());
        holder.lblCatLicitacion.setText("Categoria: "+licitaciones.get(position).getDesCat());
        holder.lblDesLicitacion.setText("Resumen: "+licitaciones.get(position).getDesLic());
        holder.lblMonLicitacion.setText("Valor Referencial: "+licitaciones.get(position).getValRef()+" "+licitaciones.get(position).getMonLic());
        final int pos = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent (v.getContext(), LicitacionActivity.class);
                    intent.putExtra("licitacion", licitaciones.get(pos));
                    v.getContext().startActivity(intent);
                }

        });


    }

    @Override
    public int getItemCount() {
        return licitaciones.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView lblIdLicitacion;
        TextView lblCatLicitacion;
        TextView lblDesLicitacion;
        TextView lblMonLicitacion;
        public ViewHolder(View itemView) {
            super(itemView);
            lblIdLicitacion = (TextView) itemView.findViewById(R.id.lblIdLicitacion);
            lblCatLicitacion = (TextView) itemView.findViewById(R.id.lblCatLicitacion);
            lblDesLicitacion = (TextView) itemView.findViewById(R.id.lblDesLicitacion);
            lblMonLicitacion = (TextView) itemView.findViewById(R.id.lblMonLicitacion);
        }
    }
}
