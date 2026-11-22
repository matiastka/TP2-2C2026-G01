package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.Observador;
import edu.fiuba.algo3.modelo.VisitanteVisualCasilla;
import edu.fiuba.algo3.modelo.tablero.direcciones.Direccion;
import edu.fiuba.algo3.modelo.tablero.emergencias.EmergenciaVek;

public interface Ocupante {
    default void agregarObservador(Observador observador) {
    }

    default void quitarObservador(Observador observador) {
    }

    void aceptar(VisitanteVisualCasilla visitante);
    boolean ocupaCasilla();
    void recibirDanio(int danio);
    void caerEnAgua();
    void caerEnAcido();
    void serEmpujadoA(Posicion destino, Tablero tablero);
    void contaminarse();

    default void salirDelAgua() {
    }

    default boolean esMech() {
        return false;
    }

    void recibirTransito(Transito transito, Casilla casilla);

    void recibirEfectoDeImpacto(EfectoImpacto efecto, Posicion posicion, Direccion direccion, Tablero tablero);

    void recibirEmergencia(EmergenciaVek emergencia, Casilla casilla, Tablero tablero);

    void ofrecerComoObjetivo(BuscadorDeObjetivos buscador, Casilla casilla);
}