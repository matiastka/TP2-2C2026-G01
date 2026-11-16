package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public abstract class Mech extends Unidad {

    private Posicion posicionAnterior;
    private EstadoArma estadoArma;
    private EstadoTurnoMech estadoTurno;

    protected Mech(int vida) {
        super(vida, 3, new MovimientoTerrestre());
        this.estadoArma = new EstadoArmaOperativa();
        this.estadoTurno = new EstadoDisponible();
    }

    public void atacarA(Posicion posicion, Tablero tablero) {
        this.estadoTurno = this.estadoTurno.atacar(this, posicion, tablero);
    }

    boolean ejecutarAtaqueValidado(Posicion posicion, Tablero tablero) {
        return estadoArma.usar(this, posicion, tablero);
    }

    protected abstract void ejecutarAtaque(Posicion posicion, Tablero tablero);

    @Override
    public void moverA(Posicion destino, Tablero tablero) {
        Posicion posibleAnterior = this.obtenerPosicion();
        this.estadoTurno = this.estadoTurno.mover(this, destino, tablero);
        if (!this.obtenerPosicion().equals(posibleAnterior)) {
            this.posicionAnterior = posibleAnterior;
        }
    }

    public void reiniciarTurno() {
        this.estadoTurno = new EstadoDisponible();
    }

    public void deshacerMovimiento(Tablero tablero) {
        this.estadoTurno = this.estadoTurno.deshacerMovimiento(this, tablero);
    }

    void ejecutarDeshacerMovimiento(Tablero tablero) {
        reubicarEn(posicionAnterior, tablero);
    }

    @Override
    protected void reubicarEn(Posicion destino, Tablero tablero) {
        this.estadoArma = new EstadoArmaOperativa();
        super.reubicarEn(destino, tablero);
    }

    @Override
    protected void hundirse() {
        this.estadoArma = new EstadoArmaSumergida();
    }
}