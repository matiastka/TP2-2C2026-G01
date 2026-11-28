package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.Observador;
import edu.fiuba.algo3.modelo.tablero.direcciones.Direccion;
import edu.fiuba.algo3.modelo.tablero.emergencias.EmergenciaVek;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;

import java.util.List;
import java.util.Optional;

public interface Ocupante {
    void agregarObservador(Observador observador);
    void quitarObservador(Observador observador);

    boolean ocupaCasilla();
    Optional<Unidad> obtenerUnidad();
    Optional<Mech> obtenerMech();
    void recibirDanio(int danio);
    void caerEnAgua();
    void caerEnAcido();
    void serEmpujadoA(Posicion destino, Tablero tablero);
    void contaminarse();

    void salirDelAgua();

    void recibirTransito(Transito transito, Casilla casilla);

    void recibirEfectoDeImpacto(EfectoImpacto efecto, Posicion posicion, Direccion direccion, Tablero tablero);

    List<Vek> recibirEmergencia(EmergenciaVek emergencia, Casilla casilla, Tablero tablero);

    void ofrecerComoObjetivo(BuscadorDeObjetivos buscador, Casilla casilla);

    void ofrecerParaEmergencia(BuscadorDeEmergencias buscador, Casilla casilla);
}