package edu.fiuba.algo3.modelo;

public class Casilla {
    private Terreno terreno;
    private Unidad unidad;

    public Casilla(Terreno terreno, Posicion posicion){
        this.terreno = terreno;
        this.unidad = new SinUnidad(posicion);
    }

    public boolean estaLibre(){
        return !this.unidad.ocupaCasilla();
    }

    public void ocupar(Unidad unidad){
        this.unidad = unidad;
    }

    public void desOcupar(Posicion posicion){
        this.unidad = new SinUnidad(posicion);
    }

    public void cambiarTerreno(Terreno terreno){
        this.terreno = terreno;
    }

    public Terreno obtenerTerreno(){
        return terreno;
    }

    public Unidad obtenerUnidad() {
        return unidad;
    }

    public void aplicarEfectoA(Unidad unidad) {
        this.terreno.afectarA(unidad);
    }

    public boolean esTransitable() {
        return this.terreno.esTransitable(); 
    }


}
