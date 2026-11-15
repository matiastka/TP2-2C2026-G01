package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.RedElectrica;

import java.util.Map;

public class Tablero {
    private static final int CANTIDAD_DE_FILAS = 8;
    private static final int CANTIDAD_DE_COLUMNAS = 8;
    private final RedElectrica redElectrica = RedElectrica.getInstancia();
    private Map<Posicion, Casilla> casillas;

    public Tablero(Map<Posicion, Casilla> casillas){
        this.casillas = casillas;
    }

    public Casilla enCasilla(Posicion posicion){
        return casillas.get(posicion);
    }

    public int cantidadFilas(){
        return CANTIDAD_DE_FILAS;
    }

    public int cantidadColumnas(){
        return CANTIDAD_DE_COLUMNAS;
    }

    public void ocuparCasilla(Posicion posicion, Ocupante ocupante){
        enCasilla(posicion).ocupar(ocupante);
    }

    public void desOcuparCasilla(Posicion posicion){
        enCasilla(posicion).desOcupar();
    }

    public boolean estaEnRango(Posicion posicion){
        return casillas.containsKey(posicion);
    }

    public int obtenerRedElectrica() {
        return this.redElectrica.obtenerNivel();
    }

    public void reducirRedElectrica(int danio) {
        this.redElectrica.reducir(danio);
    }

}
