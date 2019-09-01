package com.example.estudiante.theheroproject;

public class hero {
    private final String id;
    private final String nombre;
    private final String inteligencia;
    private final String fuerza;
    private final String velocidad;
    private final String durabilidad;
    private final String poder;
    private final String combate;
    private final String nombre_completo;

    public hero(String id, String nombre, String inteligencia, String fuerza,
                 String velocidad, String durabilidad, String poder, String combate, String nombre_completo){
        this.id = id;
        this.nombre = nombre;
        this.inteligencia = inteligencia;
        this.fuerza = fuerza;
        this.velocidad = velocidad;
        this.durabilidad = durabilidad;
        this.poder = poder;
        this.combate = combate;
        this.nombre_completo = nombre_completo;
    }


    public String getId() {    return id;   }
    public String getNombre() {      return nombre;   }
    public String getInteligencia() { return inteligencia;   }
    public String getFuerza() {       return fuerza;    }
    public String getVelocidad() {    return velocidad;    }
    public String getDurabilidad() {  return durabilidad;   }
    public String getPoder() {        return poder;    }
    public String getCombate() {      return combate;   }
    public String getNombre_completo() {   return nombre_completo;   }

}
