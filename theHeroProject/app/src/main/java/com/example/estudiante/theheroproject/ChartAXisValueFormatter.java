package com.example.estudiante.theheroproject;


import com.github.mikephil.charting.formatter.ValueFormatter;
class ChartAXisValueFormatter extends ValueFormatter {
    private final String[] mValores;
    public ChartAXisValueFormatter(String[] valores) {
        mValores = valores;
    }

    @Override
    public String getFormattedValue(float value) {
        int val = (int) (value);
        String etiqueta;
        if (val >= 0 && val < mValores.length) {
            etiqueta= mValores[val];
        } else {
            etiqueta= "";
        }
        return etiqueta;
    }
}
