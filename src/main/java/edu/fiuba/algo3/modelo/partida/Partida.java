package edu.fiuba.algo3.modelo.partida;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.SujetoObservable;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.veks.ia.InteligenciaArtificialVek;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import edu.fiuba.algo3.modelo.unidades.veks.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.veks.Saltador;
import edu.fiuba.algo3.modelo.unidades.veks.Avispon;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Casilla;

public class Partida extends SujetoObservable {

    private static final int CANTIDAD_DE_TURNOS = 5;

    private final Tablero tablero;
    private final List<Mech> mechs;
    private final List<Vek> veks;
    private final Turno turno;
    private EstadoPartida estado;
    private int numeroDeTurno;
    private FaseTurno faseActual;

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
    }

    public void terminarFaseJugador() {
        this.estado = this.estado.terminarFaseJugador(this);
        notificarObservadores();
    }

    EstadoPartida jugarRestoDelTurno() {
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

    void comenzarSiguienteTurno() {
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

    public void retirarVeksMuertos() {
        for (Vek vek : List.copyOf(this.veks)) {
            if (vek.obtenerVida() <= 0) {
                this.tablero.desOcuparCasilla(vek.obtenerPosicion());
                this.veks.remove(vek);
            }
        }
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

}
