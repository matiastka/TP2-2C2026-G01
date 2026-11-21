package edu.fiuba.algo3.modelo.unidades.ataques;

import java.util.Optional;

import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.tablero.direcciones.Direccion;

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
    public Optional<Casilla> casillaImpactadaDesde(Posicion origen, Direccion direccion, Tablero tablero) {

        Posicion objetivo = origen;
        for (int i = 0; i < distancia; i++) {
            objetivo = objetivo.siguienteEn(direccion);
        }

        if (!tablero.estaEnRango(objetivo)) {
            return Optional.empty();
        }

        return Optional.of(tablero.enCasilla(objetivo));
    }

    @Override
    public void ejecutar(Posicion origen, Direccion direccion, Tablero tablero) {
        Posicion objetivo = origen;
        for (int i = 0; i < distancia; i++) {
            objetivo = objetivo.siguienteEn(direccion);
        }

        if (!tablero.estaEnRango(objetivo)) {
            return;
        }

        tablero.enCasilla(objetivo).recibirDanio(danio);

        for (Direccion dirCard : Direccion.cardinales()) {
            Posicion posAdyacente = objetivo.siguienteEn(dirCard);
            if (tablero.estaEnRango(posAdyacente)) {
                tablero.enCasilla(posAdyacente).aplicarEfectoDeImpacto(efectoAdyacente, dirCard, tablero);
            }
        }
    }
}