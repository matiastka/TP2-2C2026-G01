package edu.fiuba.algo3.modelo.partida;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.SujetoObservable;
import edu.fiuba.algo3.modelo.partida.estado.Derrota;
import edu.fiuba.algo3.modelo.partida.estado.EnJuego;
import edu.fiuba.algo3.modelo.partida.estado.EstadoPartida;
import edu.fiuba.algo3.modelo.partida.estado.Victoria;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import edu.fiuba.algo3.modelo.unidades.veks.ia.InteligenciaArtificialVek;

public class Partida extends SujetoObservable {

    private static final int CANTIDAD_DE_TURNOS = 5;

    private final Tablero tablero;
    private final List<Mech> mechs;
    private final List<Vek> veks;
    private final Turno turno;
    private final GeneradorDeEmergencias generador;
    private EstadoPartida estado;
    private int numeroDeTurno;
    private int contadorVeksEliminados;

    public Partida(Tablero tablero, List<Mech> mechs, List<Vek> veks) {
        this(tablero, mechs, veks, new GeneradorDeEmergencias());
    }

    public Partida(Tablero tablero, List<Mech> mechs, List<Vek> veks, GeneradorDeEmergencias generador) {
        this.tablero = tablero;
        this.mechs = new ArrayList<>(mechs);
        this.veks = new ArrayList<>(veks);
        this.turno = new Turno(this.veks, new InteligenciaArtificialVek());
        this.generador = generador;
        this.estado = new EnJuego();
        this.numeroDeTurno = 1;
        this.turno.faseVek(this.tablero);
        this.generador.anunciarEmergencias(this.numeroDeTurno, this.tablero);
        notificarObservadores();
        this.contadorVeksEliminados = 0;
    }

    public void terminarFaseJugador() {
        this.estado = this.estado.terminarFaseJugador(this);
        notificarObservadores();
    }

    public EstadoPartida jugarRestoDelTurno() {
        this.turno.faseResolucion(this.tablero);
        List<Vek> emergidos = this.turno.faseEmergencia(this.tablero);
        for (Vek vek : emergidos) {
            if (!this.veks.contains(vek)) {
                this.veks.add(vek);
            }
        }
        retirarUnidadesMuertas();
        EstadoPartida siguiente = evaluarFinDelTurno();
        siguiente.comenzarTurno(this);
        return siguiente;
    }

    private EstadoPartida evaluarFinDelTurno() {
        if (this.tablero.obtenerRedElectrica() <= 0 || this.mechs.isEmpty()) {
            return new Derrota();
        }
        if (this.numeroDeTurno == CANTIDAD_DE_TURNOS) {
            return new Victoria();
        }
        return new EnJuego();
    }

    public void comenzarSiguienteTurno() {
        this.numeroDeTurno++;
        for (Mech mech : this.mechs) {
            mech.reiniciarTurno();
        }
        this.turno.faseVek(this.tablero);
        this.generador.anunciarEmergencias(this.numeroDeTurno, this.tablero);
        notificarObservadores();
    }

    private void retirarUnidadesMuertas() {
        retirarMuertas(this.mechs);
        this.contadorVeksEliminados += retirarMuertas(this.veks);
    }

    private int retirarMuertas(List<? extends Unidad> unidades) {
        int retiradas = 0;
        for (Unidad unidad : List.copyOf(unidades)) {
            if (unidad.obtenerVida() <= 0) {
                this.tablero.desOcuparCasilla(unidad.obtenerPosicion());
                unidades.remove(unidad);
                retiradas++;
            }
        }
        return retiradas;
    }

    public void retirarVeksMuertos() {
        this.contadorVeksEliminados += retirarMuertas(this.veks);
        notificarObservadores();
    }

    public void verificarFinInmediato() {
        retirarUnidadesMuertas();
        if (this.tablero.obtenerRedElectrica() <= 0 || this.mechs.isEmpty()) {
            this.estado = new Derrota();
        }
        notificarObservadores();
    }

    public EstadoPartida obtenerEstado() {
        return this.estado;
    }

    public int obtenerNumeroDeTurno() {
        return this.numeroDeTurno;
    }

    public Tablero obtenerTablero() {
        return this.tablero;
    }

    public List<Vek> obtenerVeks() {
        return List.copyOf(this.veks);
    }

    public int obtenerVeksEliminados(){
        return this.contadorVeksEliminados;
    }

}