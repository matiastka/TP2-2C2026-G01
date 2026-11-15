package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.terrenos.Terreno;

public class Casilla {
    private Terreno terreno;
    private Ocupante ocupante;
    private final Posicion posicion;

    public Casilla(Terreno terreno, Posicion posicion){
        this.posicion = posicion;
        this.terreno = terreno;
        this.ocupante = new SinOcupante();
    }

    public boolean estaLibre(){
        return !this.ocupante.ocupaCasilla();
    }

    public void ocupar(Ocupante ocupante){
        this.ocupante = ocupante;
    }

    public void desOcupar(){
        this.ocupante = new SinOcupante();
    }

    public Terreno obtenerTerreno(){
        return terreno;
    }

    public Ocupante obtenerOcupante() {
        return ocupante;
    }

    public void aplicarEfectoA(Ocupante ocupante) {
        this.terreno.afectarA(ocupante);
    }

    public boolean esTransitable() {
        return this.terreno.esTransitable(); 
    }

}
