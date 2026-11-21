package edu.fiuba.algo3.modelo.tablero;

public class SinOcupante implements Ocupante {

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
    public void recibirTransito(Transito transito, Casilla casilla) {
        casilla.recibirTransitoEnTerreno(transito);
    }

    @Override
    public void recibirEfectoDeImpacto(EfectoImpacto efecto, Posicion posicion, Direccion direccion, Tablero tablero) {
    }

    @Override
    public void recibirEmergencia(EmergenciaVek emergencia, Casilla casilla, Tablero tablero) {
        emergencia.emergerEn(casilla, tablero);
    }


}
