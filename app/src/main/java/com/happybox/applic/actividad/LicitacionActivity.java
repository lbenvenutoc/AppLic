package com.happybox.applic.actividad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.happybox.applic.R;
import com.happybox.applic.modelo.Licitacion;

public class LicitacionActivity extends AppCompatActivity {

    TextView lblIdetificador;
    TextView lblNomenclatura;
    TextView lblNormativaAplicable;
    TextView lblVersionSeace;
    TextView lblEntidadConvocante;
    TextView lblDireccion;
    TextView lblCategoria;
    TextView lblResumen;
    TextView lblMonto;
    TextView lblFechaPublicacion;
    TextView lblFechaCierre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licitacion);
        lblIdetificador=(TextView) findViewById(R.id.lblIdetificador);
        lblNomenclatura=(TextView) findViewById(R.id.lblNomenclatura);
        lblNormativaAplicable=(TextView) findViewById(R.id.lblNormativaAplicable);
        lblVersionSeace=(TextView) findViewById(R.id.lblVersionSeace);
        lblEntidadConvocante=(TextView) findViewById(R.id.lblEntidadConvocante);
        lblDireccion=(TextView) findViewById(R.id.lblDireccion);
        lblCategoria=(TextView) findViewById(R.id.lblCategoria);
        lblResumen=(TextView) findViewById(R.id.lblResumen);
        lblMonto=(TextView) findViewById(R.id.lblMonto);
        lblFechaPublicacion=(TextView) findViewById(R.id.lblFechaPublicacion);
        lblFechaCierre=(TextView) findViewById(R.id.lblFechaCierre);

        Intent intent = getIntent();
        Licitacion licitacion = (Licitacion) intent.getExtras().getSerializable("licitacion");

        lblIdetificador.setText("Identificador de Convocatoria: "+licitacion.getCodLic());
        lblNomenclatura.setText("Nomenclatura: "+licitacion.getNomLic());
        lblNormativaAplicable.setText("Normativa Aplicable: "+licitacion.getNorAplLic());
        lblVersionSeace.setText("Version SEACE: "+licitacion.getVerSeaLic());
        lblEntidadConvocante.setText("Entidad convocante: "+licitacion.getDesEnt());
        lblDireccion.setText("Direccion legal: "+licitacion.getDirEnt());
        lblCategoria.setText("Categoría: "+licitacion.getDesCat());
        lblResumen.setText("Resumen: "+licitacion.getDesLic());
        lblMonto.setText("Monto referencial: "+licitacion.getValRef()+ " "+licitacion.getMonLic());
        lblFechaPublicacion.setText("Fecha y hora de publicación: "+licitacion.getFecPubLic());
        lblFechaCierre.setText("Fecha de cierre: "+licitacion.getFecTerLic());



    }


}
