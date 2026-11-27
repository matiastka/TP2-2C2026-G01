package edu.fiuba.algo3.modelo.partida;

import java.util.List;

import edu.fiuba.algo3.modelo.SujetoObservable;
import edu.fiuba.algo3.modelo.partida.estado.Derrota;
import edu.fiuba.algo3.modelo.partida.estado.EnJuego;
import edu.fiuba.algo3.modelo.partida.estado.EstadoPartida;
import edu.fiuba.algo3.modelo.partida.estado.Victoria;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import edu.fiuba.algo3.modelo.unidades.veks.ia.InteligenciaArtificialVek;

public class Partida extends SujetoObservable {

    private static final int CANTIDAD_DE_TURNOS = 5;

    private final Tablero tablero;
    private final Escuadron escuadron;
    private final Enjambre enjambre;
    private final GeneradorDeEmergencias generador;
    private EstadoPartida estado;
    private int numeroDeTurno;

    public Partida(Tablero tablero, List<Mech> mechs, List<Vek> veks) {
        this(tablero, mechs, veks, new GeneradorDeEmergencias());
    }

    public Partida(Tablero tablero, List<Mech> mechs, List<Vek> veks, GeneradorDeEmergencias generador) {
        this.tablero = tablero;
        this.escuadron = new Escuadron(mechs);
        this.enjambre = new Enjambre(veks, new InteligenciaArtificialVek());
        this.generador = generador;
        this.estado = new EnJuego();
        this.numeroDeTurno = 1;
        this.enjambre.faseVek(this.tablero);
        this.generador.anunciarEmergencias(this.numeroDeTurno, this.tablero);
        notificarObservadores();
    }

    public void terminarFaseJugador() {
        this.estado = this.estado.terminarFaseJugador(this);
        notificarObservadores();
    }

    public EstadoPartida jugarRestoDelTurno() {
        this.enjambre.faseResolucion(this.tablero);
        this.enjambre.faseEmergencia(this.tablero);
        retirarUnidadesDestruidas();
        EstadoPartida siguiente = evaluarFinDelTurno();
        siguiente.comenzarTurno(this);
        return siguiente;
    }

    private EstadoPartida evaluarFinDelTurno() {
        if (esDerrota()) {
            return new Derrota();
        }
        if (this.numeroDeTurno == CANTIDAD_DE_TURNOS) {
            return new Victoria();
        }
        return new EnJuego();
    }

    private boolean esDerrota() {
        return this.tablero.obtenerRedElectrica() <= 0 || this.escuadron.fueDestruido();
    }

    public void comenzarSiguienteTurno() {
        this.numeroDeTurno++;
        this.escuadron.reiniciarTurno();
        this.enjambre.faseVek(this.tablero);
        this.generador.anunciarEmergencias(this.numeroDeTurno, this.tablero);
        notificarObservadores();
    }

    private void retirarUnidadesDestruidas() {
        this.escuadron.retirarDestruidos(this.tablero);
        this.enjambre.retirarDestruidos(this.tablero);
    }

    public void retirarVeksMuertos() {
        this.enjambre.retirarDestruidos(this.tablero);
        notificarObservadores();
    }

    public void verificarFinInmediato() {
        retirarUnidadesDestruidas();
        if (esDerrota()) {
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
        return this.enjambre.integrantes();
    }

    public int obtenerVeksEliminados() {
        return this.enjambre.cantidadDeEliminados();
    }

}