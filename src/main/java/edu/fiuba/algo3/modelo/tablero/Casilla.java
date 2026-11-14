package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.unidades.SinUnidad;
import edu.fiuba.algo3.modelo.terrenos.Terreno;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Casilla {
    private Terreno terreno;
    private Unidad unidad;
    private final Posicion posicion;

    public Casilla(Terreno terreno, Posicion posicion){
        this.posicion = posicion;
        this.terreno = terreno;
        this.unidad = new SinUnidad(posicion);
    }

    public boolean estaLibre(){
        return !this.unidad.ocupaCasilla();
    }

    public void ocupar(Unidad unidad){
        this.unidad = unidad;
    }

    public void desOcupar(){
        this.unidad = new SinUnidad(posicion);
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
