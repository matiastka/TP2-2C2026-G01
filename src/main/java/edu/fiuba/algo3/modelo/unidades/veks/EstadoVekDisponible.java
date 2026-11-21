package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class EstadoVekDisponible implements EstadoTurnoVek {

    @Override
    public EstadoTurnoVek mover(Vek vek, Posicion destino, Tablero tablero) {
        vek.ejecutarMovimientoVek(destino, tablero);
        return new EstadoVekYaSeMovio();
    }

    @Override
    public EstadoTurnoVek prepararAtaque(Vek vek, Posicion objetivo) {
        vek.apuntarA(objetivo);
        return new EstadoVekConAtaquePreparado();
    }

    @Override
    public EstadoTurnoVek resolverAtaque(Vek vek, Tablero tablero) {
        return this;
    }
}