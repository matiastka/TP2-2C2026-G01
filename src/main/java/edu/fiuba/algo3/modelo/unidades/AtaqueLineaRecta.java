package edu.fiuba.algo3.modelo.unidades;

import java.util.Optional;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class AtaqueLineaRecta implements Ataque {

    private final int danio;
    private final EfectoImpacto efecto;

    public AtaqueLineaRecta(int danio, EfectoImpacto efecto) {
        this.danio = danio;
        this.efecto = efecto;
    }

    @Override 
    public Optional<Casilla> casillaImpactadaDesde(Posicion origen, Direccion direccion, Tablero tablero) {
        Posicion posActual = origen.siguienteEn(direccion);
        while (tablero.estaEnRango(posActual)) {
            Casilla casillaActual = tablero.enCasilla(posActual);
            if (!casillaActual.estaLibre() || !casillaActual.esTransitable()) {
                return Optional.of(casillaActual);
            }
            posActual = posActual.siguienteEn(direccion);
        }
        return Optional.empty();
    }

    @Override
    public void ejecutar(Posicion origen, Direccion direccion, Tablero tablero) {
        Posicion posActual = origen.siguienteEn(direccion);
        while (tablero.estaEnRango(posActual)) {
            Casilla casillaActual = tablero.enCasilla(posActual);

            if (!casillaActual.estaLibre() || !casillaActual.esTransitable()) {
                casillaActual.recibirDanio(danio);
                if (!casillaActual.estaLibre()) {
                    efecto.aplicar(casillaActual.obtenerOcupante(), posActual, direccion, tablero);
                }
                return;
            }

            posActual = posActual.siguienteEn(direccion);
        }
    }
}