package edu.fiuba.algo3.modelo.tablero;

public abstract class Terreno {

    public abstract void afectarA(Ocupante ocupante);

    public void abandonar(Ocupante ocupante) {
    }

    public void ofrecerComoObjetivo(BuscadorDeObjetivos buscador, Casilla casilla) {
    }

    public boolean esPeligrosoParaTerrestres() {
        return false;
    }

    public void recibirDanio(int danio, Casilla casilla) {
    }

    public void recibirTransito(Transito transito, Casilla casilla) {
        transito.atravesar(casilla);
    }

    public void ofrecerComoDestino(BuscadorDeDestinos buscador, Casilla casilla) {
        buscador.encontrarTerrenoSeguro(casilla);
    }

}
