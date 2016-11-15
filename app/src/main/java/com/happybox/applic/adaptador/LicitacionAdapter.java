package com.happybox.applic.adaptador;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.happybox.applic.R;
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
        holder.lblIdLicitacion.setText(""+licitaciones.get(position).getCodLic());
        holder.lblDesLicitacion.setText(licitaciones.get(position).getDesLic());
        holder.lblMonLicitacion.setText(""+licitaciones.get(position).getMonLic());
    }

    @Override
    public int getItemCount() {
        return licitaciones.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView lblIdLicitacion;
        TextView lblDesLicitacion;
        TextView lblMonLicitacion;
        public ViewHolder(View itemView) {
            super(itemView);
            lblIdLicitacion = (TextView) itemView.findViewById(R.id.lblIdLicitacion);
            lblDesLicitacion = (TextView) itemView.findViewById(R.id.lblDesLicitacion);
            lblMonLicitacion = (TextView) itemView.findViewById(R.id.lblMonLicitacion);
        }
    }
}
