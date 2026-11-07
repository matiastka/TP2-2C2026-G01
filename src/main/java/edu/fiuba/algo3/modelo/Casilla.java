package edu.fiuba.algo3.modelo;

public class Casilla {
    private Posicion posicion;
    private Terreno terreno;
    private Unidad unidad;

    public Casilla(Terreno terreno){
        this.terreno = terreno;
    }

    public boolean estaLibre(){
        return true;
    }

    public void ocupar(Unidad unidad){
        this.unidad = unidad;
    }

    public void desOcupar(){
        this.unidad = null;
    }

    public void cambiarTerreno(Terreno terreno){

    }

    public Terreno obtenerTerreno(){
        return terreno;
    }

    public Unidad obtenerUnidad() {
        return unidad;
    }


}
