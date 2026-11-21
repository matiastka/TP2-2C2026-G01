package edu.fiuba.algo3.modelo.tablero;

import java.util.Map;

import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Tablero {
    private static final int CANTIDAD_DE_FILAS = 8;
    private static final int CANTIDAD_DE_COLUMNAS = 8;
    private final RedElectrica redElectrica;
    private Map<Posicion, Casilla> casillas;

    public Tablero(Map<Posicion, Casilla> casillas, RedElectrica redElectrica){
        this.casillas = casillas;
        this.redElectrica = redElectrica;
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

    public void trasladar(Ocupante ocupante, Posicion origen, Posicion destino) {
        desOcuparCasilla(origen);
        ocuparCasilla(destino, ocupante);
    }

    public void anunciarEmergencia(Posicion posicion, Unidad unidad) {
        if (!casillas.containsKey(posicion)) {
            throw new IllegalArgumentException("La posición de emergencia no pertenece al tablero.");
        }
        enCasilla(posicion).activarEmergencia(unidad);
    }

    public void resolverEmergencia(Posicion posicion) {
        enCasilla(posicion).resolverEmergencia(this);
    }

    public void avanzarTurno() {
        for (Casilla casilla : casillas.values()) {
            casilla.resolverEmergencia(this);
        }
    }

    public boolean estaEnRango(Posicion posicion){
        return casillas.containsKey(posicion);
    }

    public int obtenerRedElectrica() {
        return this.redElectrica.obtenerNivel();
    }
}
