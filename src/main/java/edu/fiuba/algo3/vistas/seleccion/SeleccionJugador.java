package edu.fiuba.algo3.vistas.seleccion;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.partida.Partida;
import edu.fiuba.algo3.modelo.tablero.BuscadorDeObjetivos;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import edu.fiuba.algo3.vistas.VistaBarraEstado;
import edu.fiuba.algo3.vistas.VistaPanelLateral;
import edu.fiuba.algo3.vistas.VistaTablero;

public class SeleccionJugador {
    private final Partida partida;
    private final VistaTablero vistaTablero;
    private final VistaPanelLateral panelLateral;
    private final VistaBarraEstado barraEstado;
    private final List<Posicion> destinosValidos = new ArrayList<>();
    private final List<Posicion> objetivosValidos = new ArrayList<>();
    private EstadoSeleccion estado = new SinSeleccion();
    private boolean terminada;

    public SeleccionJugador(
            Partida partida,
            VistaTablero vistaTablero,
            VistaPanelLateral panelLateral,
            VistaBarraEstado barraEstado
    ) {
        this.partida = partida;
        this.vistaTablero = vistaTablero;
        this.panelLateral = panelLateral;
        this.barraEstado = barraEstado;
    }

    public void procesarClic(Casilla casilla, ManejadorAccionesSeleccion manejador) {
        if (this.terminada) {
            return;
        }
        this.panelLateral.seleccionar(casilla);
        casilla.obtenerOcupante().entregarseA(new ClicSobreOcupante(this, casilla, manejador));
    }

    void clicEnMech(Mech mech, ManejadorAccionesSeleccion manejador) {
        this.estado.clicEnMech(mech, this, manejador);
    }

    void clicEnVek(Posicion posicion, ManejadorAccionesSeleccion manejador) {
        this.estado.clicEnVek(posicion, this, manejador);
    }

    void clicEnCasillaVacia(Casilla casilla, ManejadorAccionesSeleccion manejador) {
        this.estado.clicEnCasillaVacia(casilla, this, manejador);
    }


    public void seleccionarMech(Mech mech) {
        this.estado = new MechSeleccionado(mech);
        actualizarAlcances(mech);
    }

    public void ejecutarSobreMech(AccionSobreMech accion) {
        this.estado.ejecutar(accion);
    }

    public void procesarClicEnCasillaVacia(
                Mech mech,
                Casilla casilla,
                ManejadorAccionesSeleccion manejador
    ) {
            Posicion posicion = casilla.obtenerPosicion();
            if (this.destinosValidos.contains(posicion)) {
                manejador.mover(mech, posicion);
            } else if (this.objetivosValidos.contains(posicion)) {
                manejador.atacar(mech, posicion);
            }
    }

    public void atacarSiEsObjetivoValido(
                Mech mech,
                Posicion posicion,
                ManejadorAccionesSeleccion manejador
    ) {
        if (this.objetivosValidos.contains(posicion)) {
            manejador.atacar(mech, posicion);
        }
    }

    public void movimientoRealizado(Mech mech) {
        this.barraEstado.mostrarMensaje("Movimiento realizado");
        actualizarAlcances(mech);
    }

    public void ataqueRealizado(Mech mech) {
        this.barraEstado.mostrarMensaje("Ataque realizado");
        actualizarAlcances(mech);
        this.vistaTablero.refrescarAmenazas();
    }

    public void terminar() {
        this.terminada = true;
        this.estado = new SinSeleccion();
        this.destinosValidos.clear();
        this.objetivosValidos.clear();
        this.vistaTablero.limpiarResaltados();
    }

    private void actualizarAlcances(Mech mech) {
        this.destinosValidos.clear();
        this.destinosValidos.addAll(obtenerDestinos(mech));
        this.objetivosValidos.clear();
        this.objetivosValidos.addAll(obtenerObjetivos(mech));
        this.vistaTablero.resaltarDestinosYObjetivos(
                this.destinosValidos,
                this.objetivosValidos
        );
    }

    private List<Posicion> obtenerDestinos(Mech mech) {
        Tablero tablero = this.partida.obtenerTablero();
        List<Posicion> destinos = new ArrayList<>();
        for (int fila = 0; fila < tablero.cantidadFilas(); fila++) {
            for (int columna = 0; columna < tablero.cantidadColumnas(); columna++) {
                Posicion posicion = new Posicion(fila, columna);
                mech.agregarSiPuedeLlegarA(posicion, destinos, tablero);
            }
        }
        return List.copyOf(destinos);
    }

    private List<Posicion> obtenerObjetivos(Mech mech) {
        Tablero tablero = this.partida.obtenerTablero();
        List<Posicion> objetivos = new ArrayList<>();
        BuscadorDeObjetivos buscador = new BuscadorObjetivosDeMech(mech, objetivos);
        for (int fila = 0; fila < tablero.cantidadFilas(); fila++) {
            for (int columna = 0; columna < tablero.cantidadColumnas(); columna++) {
                Posicion posicion = new Posicion(fila, columna);
                Casilla casilla = tablero.enCasilla(posicion);
                casilla.ofrecerComoObjetivo(buscador);
            }
        }
        for (Vek vek : this.partida.obtenerVeks()) {
            Posicion posicion = vek.obtenerPosicion();
            if (posicion.distanciaA(mech.obtenerPosicion()) <= mech.obtenerRango()
                    && !posicion.equals(mech.obtenerPosicion())) {
                objetivos.add(posicion);
            }
        }
        return List.copyOf(objetivos);
    }
}
