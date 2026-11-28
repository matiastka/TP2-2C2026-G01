package edu.fiuba.algo3.controllers;

import java.util.function.Consumer;

import edu.fiuba.algo3.modelo.excepciones.AccionInvalidaException;
import edu.fiuba.algo3.modelo.partida.Partida;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.vistas.VistaBarraEstado;
import edu.fiuba.algo3.vistas.seleccion.ManejadorAccionesSeleccion;
import edu.fiuba.algo3.vistas.seleccion.SeleccionJugador;

public class ControladorCasilla implements Consumer<Casilla>, ManejadorAccionesSeleccion {
    private final Partida partida;
    private final SeleccionJugador seleccion;
    private final VistaBarraEstado barraEstado;

    public ControladorCasilla(Partida partida, SeleccionJugador seleccion, VistaBarraEstado barraEstado) {
        this.partida = partida;
        this.seleccion = seleccion;
        this.barraEstado = barraEstado;
    }

    @Override
    public void accept(Casilla casilla) {
        try {
            this.seleccion.procesarClic(casilla, this);
        } catch (AccionInvalidaException excepcion) {
            this.barraEstado.mostrarError(excepcion.getMessage());
        }
    }

    @Override
    public void seleccionarMech(Mech mech) {
        this.seleccion.seleccionarMech(mech);
    }

    @Override
    public void mover(Mech mech, Posicion destino) {
        mech.moverA(destino, this.partida.obtenerTablero());
        this.seleccion.movimientoRealizado(mech);
    }

    @Override
    public void atacar(Mech mech, Posicion objetivo) {
        mech.atacarA(objetivo, this.partida.obtenerTablero());
        this.partida.retirarVeksMuertos();
        this.seleccion.ataqueRealizado(mech);
        this.partida.verificarFinInmediato();
    }
}
