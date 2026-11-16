package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Ocupante;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class AtaqueArtilleria implements Ataque {

    private final int danio;
    private final int distancia;
    private final EfectoImpacto efectoAdyacente;

    public AtaqueArtilleria(int danio, int distancia, EfectoImpacto efectoAdyacente) {
        this.danio = danio;
        this.distancia = distancia;
        this.efectoAdyacente = efectoAdyacente;
    }

    @Override
    public void ejecutar(Posicion origen, Direccion direccion, Tablero tablero) {
        if (direccion == Direccion.NINGUNA) {
            return;
        }

        Posicion objetivo = origen;
        for (int i = 0; i < distancia; i++) {
            objetivo = objetivo.siguienteEn(direccion);
        }

        if (!tablero.estaEnRango(objetivo)) {
            return;
        }

        Casilla casillaObjetivo = tablero.enCasilla(objetivo);
        if (!casillaObjetivo.estaLibre()) {
            casillaObjetivo.obtenerOcupante().recibirDanio(danio);
        } else if (!casillaObjetivo.esTransitable()) {
            casillaObjetivo.obtenerTerreno().recibirDanio(danio);
        }

        for (Direccion dirCard : Direccion.cardinales()) {
            Posicion posAdyacente = objetivo.siguienteEn(dirCard);
            if (tablero.estaEnRango(posAdyacente) && !tablero.enCasilla(posAdyacente).estaLibre()) {
                Ocupante ocupanteAdyacente = tablero.enCasilla(posAdyacente).obtenerOcupante();
                efectoAdyacente.aplicar(ocupanteAdyacente, posAdyacente, dirCard, tablero);
            }
        }
    }
}