package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.Observador;
import edu.fiuba.algo3.modelo.VisitanteVisualCasilla;
import edu.fiuba.algo3.modelo.tablero.direcciones.Direccion;
import edu.fiuba.algo3.modelo.tablero.emergencias.EmergenciaVek;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;

import java.util.List;

public class SinOcupante implements Ocupante {
    @Override
    public void aceptar(VisitanteVisualCasilla visitante) {
        visitante.visitarSinOcupante();
    }

    @Override
    public boolean ocupaCasilla() {
        return false;
    }

    @Override
    public void recibirDanio(int danio) {

    }

    @Override
    public void caerEnAgua() {

    }

    @Override
    public void caerEnAcido() {

    }

    @Override
    public void serEmpujadoA(Posicion destino, Tablero tablero) {

    }

    @Override
    public void contaminarse() {
    }

    @Override
    public void salirDelAgua() {
    }

    @Override
    public void agregarObservador(Observador observador) {
    }

    @Override
    public void quitarObservador(Observador observador) {
    }

    @Override
    public void recibirTransito(Transito transito, Casilla casilla) {
        casilla.recibirTransitoEnTerreno(transito);
    }

    @Override
    public void recibirEfectoDeImpacto(EfectoImpacto efecto, Posicion posicion, Direccion direccion, Tablero tablero) {
    }

    @Override
    public List<Vek> recibirEmergencia(EmergenciaVek emergencia, Casilla casilla, Tablero tablero) {
        return emergencia.emergerEn(casilla, tablero);
    }

    @Override
    public void ofrecerComoObjetivo(BuscadorDeObjetivos buscador, Casilla casilla) {
        casilla.ofrecerTerrenoComoObjetivo(buscador);
    }


}
