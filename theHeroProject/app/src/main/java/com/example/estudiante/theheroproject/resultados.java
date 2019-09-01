package com.example.estudiante.theheroproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class resultados extends AppCompatActivity {
    private RequestQueue ListaRequest = null;
    private int tamanoBusqueda;
    private Map<String, TextView> nombreTV;
    private String token = "";
    private String seleccion = "";
    private ListView listaHeroes;
    private hero Heroe;
    private ArrayList<hero> ArrayHeroes;
    private RequestQueue mQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        listaHeroes = findViewById(R.id.listViewHeroe);
        listaHeroes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Heroe = (hero) listaHeroes.getItemAtPosition(position);
                mostrarPoderes();
            }
        });
        mQueue = Volley.newRequestQueue(this);
        Intent main = getIntent();
        this.token = Objects.requireNonNull(Objects.requireNonNull(main.getExtras()).get("token")).toString();
        this.seleccion = Objects.requireNonNull(Objects.requireNonNull(main.getExtras()).get("seleccion")).toString();
        solicitarHeroe(seleccion);
    }



    public void solicitarHeroe(String seleccion) {

        String url = "https://www.superheroapi.com/api/php/"+token+"/search/"+ seleccion;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ArrayHeroes = new ArrayList<>();
                        try {
                            JSONArray resultado = (JSONArray) response.getJSONArray("results");
                            String id, nombre, inteligencia, fuerza, velocidad, durabilidad, poder, combate, nombreCompleto;
                            hero heroe1;
                            for(int i = 0; i<resultado.length(); i++){
                                JSONObject registro = (JSONObject) resultado.get(i);
                                id = registro.getString("id");
                                nombre = registro.getString("name");
                                JSONObject poderes = (JSONObject) registro.getJSONObject("powerstats");
                                JSONObject bibliografia = (JSONObject) registro.getJSONObject("biography");
                                inteligencia = poderes.getString("intelligence");
                                fuerza = poderes.getString("strength");
                                velocidad = poderes.getString("speed");
                                durabilidad = poderes.getString("durability");
                                poder = poderes.getString("power");
                                combate = poderes.getString("combat");
                                nombreCompleto = bibliografia.getString("full-name");
                                heroe1 = new hero(id,nombre,inteligencia,fuerza,velocidad,durabilidad,poder,combate,nombreCompleto);
                                ArrayHeroes.add(heroe1);
                            }
                            TextView txtResultados = (TextView)findViewById(R.id.txtTotal);
                            txtResultados.setText(""+resultado.length());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        listaHeroes.setAdapter(new AdapterHeroe(getBaseContext(), ArrayHeroes));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog alertDialog = new
                        AlertDialog.Builder(Objects.requireNonNull(getBaseContext())).create();
                alertDialog.setTitle("Alerta");
                alertDialog.setMessage("No es posible obtener los datos.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int
                                    which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });
        mQueue.add(request);
    }



    private void mostrarPoderes(){
        Intent caracteristica = new Intent(getBaseContext(), habilidades.class);
        caracteristica.putExtra("nombre", Heroe.getNombre());
        caracteristica.putExtra("nombre_completo", Heroe.getNombre_completo());
        caracteristica.putExtra("fuerza", Heroe.getFuerza());
        caracteristica.putExtra("inteligencia", Heroe.getInteligencia());
        caracteristica.putExtra("velocidad", Heroe.getVelocidad());
        caracteristica.putExtra("durabilidad", Heroe.getDurabilidad());
        caracteristica.putExtra("poder", Heroe.getPoder());
        caracteristica.putExtra("combate", Heroe.getCombate());
        startActivity(caracteristica);
    }

}
