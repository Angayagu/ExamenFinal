package com.example.estudiante.theheroproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Objects;

public class habilidades extends AppCompatActivity {
    public BarChart graficoBarras;
    private String [] etiquetas = {"fuerza","inteligencia","velocidad", "durabilidad", "poder", "combate"};
    private String fuerza, inteligencia, velocidad, durabilidad, poder, combate, nombre, nombre_completo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habilidades);
        setTitle("Grafico de barras");
        Intent main = getIntent();
        this.nombre= Objects.requireNonNull(Objects.requireNonNull(main.getExtras()).get("nombre")).toString();
        this.nombre_completo = Objects.requireNonNull(Objects.requireNonNull(main.getExtras()).get("nombre_completo")).toString();
        this.fuerza = Objects.requireNonNull(Objects.requireNonNull(main.getExtras()).get("fuerza")).toString();
        this.inteligencia = Objects.requireNonNull(Objects.requireNonNull(main.getExtras()).get("inteligencia")).toString();
        this.velocidad= Objects.requireNonNull(Objects.requireNonNull(main.getExtras()).get("velocidad")).toString();
        this.durabilidad = Objects.requireNonNull(Objects.requireNonNull(main.getExtras()).get("durabilidad")).toString();
        this.poder = Objects.requireNonNull(Objects.requireNonNull(main.getExtras()).get("poder")).toString();
        this.combate= Objects.requireNonNull(Objects.requireNonNull(main.getExtras()).get("combate")).toString();
        TextView txt_nombre = findViewById(R.id.txtNombre);
        TextView txt_nombre_completo = findViewById(R.id.txtNombreCompleto);
        txt_nombre.setText(nombre);
        txt_nombre_completo.setText(nombre_completo);
        this.iniciarGrafico();
        actualizarGrafico();
    }

    public void iniciarGrafico() {
        graficoBarras = findViewById(R.id.barChart);
        graficoBarras.getDescription().setEnabled(false);
        graficoBarras.setPinchZoom(false);
        graficoBarras.setDrawBarShadow(false);
        graficoBarras.setDrawGridBackground(false);
        XAxis xAxis = graficoBarras.getXAxis();
        xAxis.setLabelRotationAngle(90);
        xAxis.setValueFormatter(new ChartAXisValueFormatter(etiquetas));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        graficoBarras.getAxisLeft().setDrawGridLines(false);
        graficoBarras.animateY(2000);
        graficoBarras.getLegend().setEnabled(false);
    }

    private void actualizarGrafico() {
        ArrayList<BarEntry> dato_heroe = new ArrayList<>();
        if(!fuerza.equals("null"))
            dato_heroe.add(new BarEntry(0, Integer.parseInt(fuerza)));
        else
            dato_heroe.add(new BarEntry(0, 0));
        if(!inteligencia.equals("null"))
            dato_heroe.add(new BarEntry(1, Integer.parseInt(inteligencia)));
        else
            dato_heroe.add(new BarEntry(1, 0));
        if(!velocidad.equals("null"))
            dato_heroe.add(new BarEntry(2, Integer.parseInt(velocidad)));
        else
            dato_heroe.add(new BarEntry(2, 0));
        if(!durabilidad.equals("null"))
            dato_heroe.add(new BarEntry(3, Integer.parseInt(durabilidad)));
        else
            dato_heroe.add(new BarEntry(3, 0));
        if(!poder.equals("null"))
            dato_heroe.add(new BarEntry(4, Integer.parseInt(poder)));
        else
            dato_heroe.add(new BarEntry(4, 0));
        if(!combate.equals("null"))
            dato_heroe.add(new BarEntry(5, Integer.parseInt(combate)));
        else
            dato_heroe.add(new BarEntry(5, 0));
        llenarGrafico(dato_heroe);
    }


    private void llenarGrafico(ArrayList<BarEntry> dato_heroe){
        BarDataSet heroeDataSet;
        if ( graficoBarras.getData() != null && graficoBarras.getData().getDataSetCount() > 0) {
            heroeDataSet = (BarDataSet) graficoBarras.getData().getDataSetByIndex(0);
            heroeDataSet.setValues(dato_heroe);
            graficoBarras.getData().notifyDataChanged();
            graficoBarras.notifyDataSetChanged();
        }
        else {
            heroeDataSet = new BarDataSet(dato_heroe, "Data Set");
            heroeDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
            heroeDataSet.setDrawValues(true);
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(heroeDataSet);
            BarData data = new BarData(dataSets);
            graficoBarras.setData(data);
            graficoBarras.setFitBars(true);
        }
        graficoBarras.invalidate();
    }

}
