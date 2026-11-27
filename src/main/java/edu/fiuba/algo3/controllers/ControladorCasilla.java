package edu.fiuba.algo3.controllers;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.VisitanteVisualCasilla;
import edu.fiuba.algo3.modelo.excepciones.AccionInvalidaException;
import edu.fiuba.algo3.modelo.excepciones.PartidaTerminadaException;
import edu.fiuba.algo3.modelo.partida.Partida;
import edu.fiuba.algo3.modelo.tablero.BuscadorDeObjetivos;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.emergencias.EmergenciaVek;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Artilleria;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Canon;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Avispon;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Saltador;
import edu.fiuba.algo3.vistas.VistaBarraEstado;
import edu.fiuba.algo3.vistas.VistaPanelLateral;
import edu.fiuba.algo3.vistas.VistaTablero;

public class ControladorCasilla {
    private final Partida partida;
    private final EstadoSeleccionVista estado;
    private final VistaTablero vistaTablero;
    private final VistaPanelLateral panelLateral;
    private final VistaBarraEstado barraEstado;

    public ControladorCasilla(
            Partida partida,
            EstadoSeleccionVista estado,
            VistaTablero vistaTablero,
            VistaPanelLateral panelLateral,
            VistaBarraEstado barraEstado
    ) {
        this.partida = partida;
        this.estado = estado;
        this.vistaTablero = vistaTablero;
        this.panelLateral = panelLateral;
        this.barraEstado = barraEstado;
    }

    public void seleccionar(Casilla casilla) {
        if (this.estado.estaTerminado()) {
            return;
        }

        this.panelLateral.seleccionar(casilla);

        try {
            casilla.aceptar(new VisitanteClicCasilla(casilla));
        } catch (AccionInvalidaException | PartidaTerminadaException excepcion) {
            this.barraEstado.mostrarError(excepcion.getMessage());
        }
    }

    private class VisitanteClicCasilla implements VisitanteVisualCasilla {
        private final Casilla casilla;

        private VisitanteClicCasilla(Casilla casilla) {
            this.casilla = casilla;
        }

        private void seleccionarMech(Mech mech) {
            ControladorCasilla.this.seleccionarMech(mech);
        }

        private void ejecutarAccionSinMech() {
            Mech seleccionado = ControladorCasilla.this.estado.obtenerMechSeleccionado();
            if (seleccionado == null) {
                return;
            }

            Posicion destino = this.casilla.obtenerPosicion();
            if (ControladorCasilla.this.estado.obtenerDestinosValidos().contains(destino)) {
                seleccionado.moverA(destino, ControladorCasilla.this.partida.obtenerTablero());
                ControladorCasilla.this.barraEstado.mostrarMensaje("Movimiento realizado");
                ControladorCasilla.this.actualizarSeleccion(seleccionado);
            } else if (ControladorCasilla.this.estado.obtenerObjetivosValidos().contains(destino)) {
                seleccionado.atacarA(destino, ControladorCasilla.this.partida.obtenerTablero());
                ControladorCasilla.this.barraEstado.mostrarMensaje("Ataque realizado");
                ControladorCasilla.this.partida.retirarVeksMuertos();
                ControladorCasilla.this.partida.verificarFinInmediato();
                ControladorCasilla.this.actualizarSeleccion(seleccionado);
                ControladorCasilla.this.vistaTablero.refrescarAmenazas();
            }
        }

        @Override
        public void visitarCombate(Combate combate) {
            seleccionarMech(combate);
        }

        @Override
        public void visitarCanon(Canon canon) {
            seleccionarMech(canon);
        }

        @Override
        public void visitarArtilleria(Artilleria artilleria) {
            seleccionarMech(artilleria);
        }

        @Override
        public void visitarLuciernaga(Luciernaga luciernaga) {
            ejecutarAccionSobreVek(this.casilla.obtenerPosicion());
        }

        @Override
        public void visitarSaltador(Saltador saltador) {
            ejecutarAccionSobreVek(this.casilla.obtenerPosicion());
        }

        @Override
        public void visitarAvispon(Avispon avispon) {
            ejecutarAccionSobreVek(this.casilla.obtenerPosicion());
        }

        private void ejecutarAccionSobreVek(Posicion posicion) {
            Mech seleccionado = ControladorCasilla.this.estado.obtenerMechSeleccionado();
            if (seleccionado != null
                    && ControladorCasilla.this.estado.obtenerObjetivosValidos().contains(posicion)) {
                seleccionado.atacarA(posicion, ControladorCasilla.this.partida.obtenerTablero());
                ControladorCasilla.this.barraEstado.mostrarMensaje("Ataque realizado");
                ControladorCasilla.this.partida.retirarVeksMuertos();
                ControladorCasilla.this.partida.verificarFinInmediato();
                ControladorCasilla.this.actualizarSeleccion(seleccionado);
                ControladorCasilla.this.vistaTablero.refrescarAmenazas();
            }
        }

        @Override
        public void visitarSinOcupante() {
            ejecutarAccionSinMech();
        }

        @Override
        public void visitarSuelo() {
        }

        @Override
        public void visitarAgua() {
        }

        @Override
        public void visitarAcido() {
        }

        @Override
        public void visitarEdificio() {
        }

        @Override
        public void visitarMontana() {
        }

        @Override
        public void visitarSinEmergencia() {
        }

        @Override
        public void visitarEmergenciaVek(EmergenciaVek emergencia) {
        }
    }

    private void seleccionarMech(Mech mech) {
        this.estado.seleccionarMech(mech);
        actualizarSeleccion(mech);
    }

    private void actualizarSeleccion(Mech mech) {
        List<Posicion> destinos = obtenerDestinos(mech);
        List<Posicion> objetivos = obtenerObjetivos(mech);
        this.estado.establecerDestinosValidos(destinos);
        this.estado.establecerObjetivosValidos(objetivos);
        this.vistaTablero.resaltarDestinosYObjetivos(destinos, objetivos);
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
        BuscadorDeObjetivos buscador = new BuscadorDeObjetivos() {
            @Override
            public void encontrarMech(Casilla casilla) {
                Posicion posicion = casilla.obtenerPosicion();
                if (posicion.distanciaA(mech.obtenerPosicion()) <= mech.obtenerRango()
                        && !posicion.equals(mech.obtenerPosicion())) {
                    objetivos.add(posicion);
                }
            }

            @Override
            public void encontrarEdificio(Casilla casilla) {
                Posicion posicion = casilla.obtenerPosicion();
                if (posicion.distanciaA(mech.obtenerPosicion()) <= mech.obtenerRango()) {
                    objetivos.add(posicion);
                }
            }
        };

        for (int fila = 0; fila < tablero.cantidadFilas(); fila++) {
            for (int columna = 0; columna < tablero.cantidadColumnas(); columna++) {
                Posicion posicion = new Posicion(fila, columna);
                Casilla casilla = tablero.enCasilla(posicion);
                casilla.ofrecerComoObjetivo(buscador);
                casilla.aceptar(new VisitanteObjetivoVek(posicion, mech, objetivos));
            }
        }
        return List.copyOf(objetivos);
    }

    private class VisitanteObjetivoVek implements VisitanteVisualCasilla {
        private final Posicion posicion;
        private final Mech mech;
        private final List<Posicion> objetivos;

        private VisitanteObjetivoVek(
                Posicion posicion,
                Mech mech,
                List<Posicion> objetivos
        ) {
            this.posicion = posicion;
            this.mech = mech;
            this.objetivos = objetivos;
        }

        private void agregarSiEstaAlAlcance() {
            if (this.posicion.distanciaA(this.mech.obtenerPosicion()) <= this.mech.obtenerRango()
                    && !this.posicion.equals(this.mech.obtenerPosicion())) {
                this.objetivos.add(this.posicion);
            }
        }

        @Override
        public void visitarLuciernaga(Luciernaga luciernaga) {
            agregarSiEstaAlAlcance();
        }

        @Override
        public void visitarSaltador(Saltador saltador) {
            agregarSiEstaAlAlcance();
        }

        @Override
        public void visitarAvispon(Avispon avispon) {
            agregarSiEstaAlAlcance();
        }

        @Override
        public void visitarSuelo() {
        }

        @Override
        public void visitarAgua() {
        }

        @Override
        public void visitarAcido() {
        }

        @Override
        public void visitarEdificio() {
        }

        @Override
        public void visitarMontana() {
        }

        @Override
        public void visitarSinOcupante() {
        }

        @Override
        public void visitarCombate(Combate combate) {
        }

        @Override
        public void visitarCanon(Canon canon) {
        }

        @Override
        public void visitarArtilleria(Artilleria artilleria) {
        }

        @Override
        public void visitarSinEmergencia() {
        }

        @Override
        public void visitarEmergenciaVek(EmergenciaVek emergencia) {
        }
    }

}
