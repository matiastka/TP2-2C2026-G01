package edu.fiuba.algo3.modelo.unidades.ataques;

import java.util.Optional;
import java.util.List;

import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.tablero.direcciones.Direccion;

public class AtaqueCuerpoACuerpo implements Ataque {
    @Override
    public List<Posicion> posicionesAmenazadasDesde(Posicion origen, Direccion direccion, Tablero tablero) {
        Posicion objetivo = origen.siguienteEn(direccion);
        if (!tablero.estaEnRango(objetivo)) {
            return List.of();
        }
        return List.of(objetivo);
    }

    private final int danio;
    private final EfectoImpacto efecto;

    public AtaqueCuerpoACuerpo(int danio, EfectoImpacto efecto) {
        this.danio = danio;
        this.efecto = efecto;
    }

    @Override 
    public Optional<Casilla> casillaImpactadaDesde(Posicion origen, Direccion direccion, Tablero tablero) {
        Posicion objetivo = origen.siguienteEn(direccion);
        if (!tablero.estaEnRango(objetivo)) {
            return Optional.empty();
        }
        return Optional.of(tablero.enCasilla(objetivo));
    }

    @Override
    public void ejecutar(Posicion origen, Direccion direccion, Tablero tablero) {
        Posicion objetivo = origen.siguienteEn(direccion);
        if (!tablero.estaEnRango(objetivo)) {
            return;
        }

        Casilla casillaObjetivo = tablero.enCasilla(objetivo);
        casillaObjetivo.recibirDanio(danio);
        casillaObjetivo.aplicarEfectoDeImpacto(efecto, direccion, tablero);
    }
}