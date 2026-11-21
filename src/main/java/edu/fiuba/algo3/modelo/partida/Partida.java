package edu.fiuba.algo3.modelo.partida;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Mech;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.veks.InteligenciaArtificialVek;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;

public class Partida {

    private static final int CANTIDAD_DE_TURNOS = 5;

    private final Tablero tablero;
    private final List<Mech> mechs;
    private final List<Vek> veks;
    private final Turno turno;
    private EstadoPartida estado;
    private int numeroDeTurno;

    public Partida(Tablero tablero, List<Mech> mechs, List<Vek> veks) {
        this.tablero = tablero;
        this.mechs = new ArrayList<>(mechs);
        this.veks = new ArrayList<>(veks);
        this.turno = new Turno(this.veks, new InteligenciaArtificialVek());
        this.estado = new EnJuego();
        this.numeroDeTurno = 1;
        this.turno.faseVek(this.tablero);
    }

    public void terminarFaseJugador() {
        this.estado = this.estado.terminarFaseJugador(this);
    }

    EstadoPartida jugarRestoDelTurno() {
        this.turno.faseResolucion(this.tablero);
        this.turno.faseEmergencia(this.tablero);
        retirarUnidadesMuertas();
        EstadoPartida siguiente = evaluarFinDelTurno();
        siguiente.comenzarTurno(this);
        return siguiente;
    }

    private EstadoPartida evaluarFinDelTurno() {
        if (this.tablero.obtenerRedElectrica() == 0 || this.mechs.isEmpty()) {
            return new Derrota();
        }
        if (this.numeroDeTurno == CANTIDAD_DE_TURNOS) {
            return new Victoria();
        }
        return new EnJuego();
    }

    void comenzarSiguienteTurno() {
        this.numeroDeTurno++;
        for (Mech mech : this.mechs) {
            mech.reiniciarTurno();
        }
        this.turno.faseVek(this.tablero);
    }

    private void retirarUnidadesMuertas() {
        retirarMuertas(this.mechs);
        retirarMuertas(this.veks);
    }

    private void retirarMuertas(List<? extends Unidad> unidades) {
        for (Unidad unidad : List.copyOf(unidades)) {
            if (unidad.obtenerVida() <= 0) {
                this.tablero.desOcuparCasilla(unidad.obtenerPosicion());
                unidades.remove(unidad);
            }
        }
    }

    public EstadoPartida obtenerEstado() {
        return this.estado;
    }

    public int obtenerNumeroDeTurno() {
        return this.numeroDeTurno;
    }

}
