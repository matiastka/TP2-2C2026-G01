package edu.fiuba.algo3.modelo;

public abstract class Mech extends Unidad {

    protected Posicion posicionAnterior;

    protected Mech(int vida, Posicion posicion) {
        super(vida, posicion);
        this.rango = 3;
    }

    public void atacarA(Posicion posicion, Tablero tablero) {
        this.estadoTurno = this.estadoTurno.atacar(this, posicion, tablero);
    }

    boolean ejecutarAtaqueValidado(Posicion posicion, Tablero tablero) {
        ejecutarAtaque(posicion, tablero);
        return true;
    }

    protected abstract void ejecutarAtaque(Posicion posicion, Tablero tablero);

    @Override
    public void moverA(Posicion destino, Tablero tablero) {
        Posicion posibleAnterior = this.posicion;
        super.moverA(destino, tablero);
        if (!this.posicion.equals(posibleAnterior)) {
            this.posicionAnterior = posibleAnterior;
        }
    }

    public void deshacerMovimiento(Tablero tablero) {
        this.estadoTurno = this.estadoTurno.deshacerMovimiento(this, tablero);
    }

    void ejecutarDeshacerMovimiento(Tablero tablero) {
        tablero.desOcuparCasilla(tablero.buscarPosicionDe(this));
        tablero.ocuparCasilla(posicionAnterior, this);
        this.posicion = posicionAnterior;
    }

    protected boolean calcularLineaRecta(Posicion objetivo) {
        int dFila = Math.abs(objetivo.getFila() - posicion.getFila());
        int dCol = Math.abs(objetivo.getColumna() - posicion.getColumna());

        return (dFila == 0 && dCol >= 2) || (dCol == 0 && dFila >= 2);
    }
}