package edu.fiuba.algo3.modelo.tablero;

public class SinEmergencia implements Emergencia {
    @Override
    public boolean estaActiva() {
        return false;
    }

    @Override
    public void resolver(Casilla casilla, Tablero tablero) {
    }
}
