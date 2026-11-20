package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.AtaqueYaPreparadoException;

public class EstadoVekConAtaquePreparado implements EstadoTurnoVek {

    @Override
    public EstadoTurnoVek mover(Vek vek, Posicion destino, Tablero tablero) {
        throw new AtaqueYaPreparadoException();
    }

    @Override
    public EstadoTurnoVek prepararAtaque(Vek vek, Direccion direccion) {
        throw new AtaqueYaPreparadoException();
    }

    @Override
    public EstadoTurnoVek resolverAtaque(Vek vek, Tablero tablero) {
        vek.ejecutarAtaquePreparado(tablero);
        return new EstadoVekDisponible();
    }
}