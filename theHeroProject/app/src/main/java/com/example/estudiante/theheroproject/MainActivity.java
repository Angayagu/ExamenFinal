package com.example.estudiante.theheroproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String token = "10157613116857292";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void buscarHeroe(View view){
        TextView texto = (TextView)findViewById(R.id.txtBusqueda);
        String seleccion = texto.getText().toString();
        Intent resultados = new Intent(getBaseContext(), resultados.class);
        resultados.putExtra("token", token);
        resultados.putExtra("seleccion", seleccion);
        startActivity(resultados);    }

}
