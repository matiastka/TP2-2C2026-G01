package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Transito;

public abstract class Proyectil implements Transito {

    protected final Direccion direccion;
    protected final Tablero tablero;

    protected Proyectil(Direccion direccion, Tablero tablero) {
        this.direccion = direccion;
        this.tablero = tablero;
    }

    public void lanzarDesde(Posicion origen) {
        Posicion siguiente = origen.siguienteEn(this.direccion);
        if (this.tablero.estaEnRango(siguiente)) {
            this.tablero.enCasilla(siguiente).recibirTransito(this);
        }
    }

    @Override
    public void atravesar(Casilla casilla) {
        lanzarDesde(casilla.obtenerPosicion());
    }

    @Override
    public void chocarConOcupante(Casilla casilla) {
        impactar(casilla);
    }

    @Override
    public void chocarConTerreno(Casilla casilla) {
        impactar(casilla);
    }

    protected abstract void impactar(Casilla casilla);
}