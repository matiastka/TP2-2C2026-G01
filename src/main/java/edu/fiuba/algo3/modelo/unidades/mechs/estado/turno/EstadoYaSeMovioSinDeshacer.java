package edu.fiuba.algo3.modelo.unidades.mechs.estado.turno;

import edu.fiuba.algo3.modelo.excepciones.AccionNoDisponibleException;
import edu.fiuba.algo3.modelo.excepciones.MovimientoYaRealizadoException;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;

public class EstadoYaSeMovioSinDeshacer implements EstadoTurnoMech {

    @Override
    public EstadoTurnoMech mover(Mech mech, Posicion destino, Tablero tablero) {
        throw new MovimientoYaRealizadoException();
    }

    @Override
    public EstadoTurnoMech atacar(Mech mech, Posicion posicion, Tablero tablero) {
        mech.ejecutarAtaqueValidado(posicion, tablero);
        return new EstadoTurnoAgotado();
    }

    @Override
    public EstadoTurnoMech deshacerMovimiento(Mech mech, Tablero tablero) {
        throw new AccionNoDisponibleException();
    }
}