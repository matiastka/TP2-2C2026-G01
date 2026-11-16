package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class EstadoVekConAtaquePreparado implements EstadoTurnoVek {

    @Override
    public EstadoTurnoVek mover(Vek vek, Posicion destino, Tablero tablero) {
        System.out.println("Movimiento inválido: el Vek ya fijó su ataque para el próximo turno.");
        return this;
    }

    @Override
    public EstadoTurnoVek prepararAtaque(Vek vek, Direccion direccion) {
        System.out.println("Acción inválida: el Vek ya tiene un ataque preparado.");
        return this;
    }

    @Override
    public EstadoTurnoVek resolverAtaque(Vek vek, Tablero tablero) {
        vek.ejecutarAtaquePreparado(tablero);
        return new EstadoVekDisponible(); // Queda listo para moverse y preparar de nuevo
    }
}