package edu.fiuba.algo3.modelo.partida;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.SujetoObservable;
import edu.fiuba.algo3.modelo.partida.estado.Derrota;
import edu.fiuba.algo3.modelo.partida.estado.EnJuego;
import edu.fiuba.algo3.modelo.partida.estado.EstadoPartida;
import edu.fiuba.algo3.modelo.partida.estado.Victoria;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import edu.fiuba.algo3.modelo.unidades.veks.ia.InteligenciaArtificialVek;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Avispon;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Saltador;

public class Partida extends SujetoObservable {

    private static final int CANTIDAD_DE_TURNOS = 5;

    private final Tablero tablero;
    private final List<Mech> mechs;
    private final List<Vek> veks;
    private final Turno turno;
    private EstadoPartida estado;
    private int numeroDeTurno;
    private FaseTurno faseActual;
    private int contadorVeksEliminados;

    public Partida(Tablero tablero, List<Mech> mechs, List<Vek> veks) {
        this.tablero = tablero;
        this.mechs = new ArrayList<>(mechs);
        this.veks = new ArrayList<>(veks);
        this.turno = new Turno(this.veks, new InteligenciaArtificialVek());
        this.estado = new EnJuego();
        this.numeroDeTurno = 1;
        this.faseActual = FaseTurno.VEKS;
        this.turno.faseVek(this.tablero);
        this.faseActual = FaseTurno.JUGADOR;
        anunciarNuevasEmergencias(1);
        notificarObservadores();
        this.contadorVeksEliminados = 0;
    }

    public void terminarFaseJugador() {
        this.estado = this.estado.terminarFaseJugador(this);
        notificarObservadores();
    }

    public EstadoPartida jugarRestoDelTurno() {
        this.faseActual = FaseTurno.RESOLUCION;
        this.turno.faseResolucion(this.tablero);
        this.faseActual = FaseTurno.EMERGENCIA;
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
        this.faseActual = FaseTurno.VEKS;
        this.turno.faseVek(this.tablero);
        this.faseActual = FaseTurno.JUGADOR;
        int cantidad = this.numeroDeTurno % 2 == 0 ? 2 : 1;
        anunciarNuevasEmergencias(cantidad);
        notificarObservadores();
    }

    private void anunciarNuevasEmergencias(int cantidad) {
        for (int indice = 0; indice < cantidad; indice++) {
            Posicion posicion = buscarCasillaTransitableLibre(indice);
            if (posicion == null) {
                return;
            }
            Vek nuevoVek = crearVekSegun(indice);
            this.tablero.anunciarEmergencia(posicion, nuevoVek);
        }
    }

    private Vek crearVekSegun(int indice) {
        int tipo = (this.numeroDeTurno + indice) % 3;
        if (tipo == 0) {
            return new Luciernaga();
        }
        if (tipo == 1) {
            return new Saltador();
        }
        return new Avispon();
    }

    private Posicion buscarCasillaTransitableLibre(int indice) {
        int[] filas = {0, 7, 1, 6, 2, 5, 3, 4};
        for (int fila : filas) {
            for (int columna = 0; columna < this.tablero.cantidadColumnas(); columna++) {
                int ajuste = (indice + columna) % this.tablero.cantidadColumnas();
                Posicion posicion = new Posicion(fila, ajuste);
                Casilla casilla = this.tablero.enCasilla(posicion);
                if (casilla.estaLibre()
                        && casilla.obtenerTerreno().esTransitableParaEmergencia()) {
                    return posicion;
                }
            }
        }
        return null;
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

    public FaseTurno obtenerFaseActual() {
        return this.faseActual;
    }

    public Tablero obtenerTablero() {
        return this.tablero;
    }

    public List<Mech> obtenerMechs() {
        return List.copyOf(this.mechs);
    }

    public List<Vek> obtenerVeks() {
        return List.copyOf(this.veks);
    }

    public int obtenerVeksEliminados(){
        return this.contadorVeksEliminados;
    }

}
