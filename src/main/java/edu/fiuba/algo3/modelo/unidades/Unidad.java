package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public abstract class Unidad {

    private int vida;
    protected int rango;
    protected Posicion posicion;
    protected EstadoTurno estadoTurno;
    private EstadoSalud estadoSalud;
    private Movimiento movimiento;

    protected Unidad(int vida, Posicion posicion, Movimiento movimiento) {
        this.vida = vida;
        this.posicion = posicion;
        this.estadoTurno = new EstadoDisponible();
        this.estadoSalud = new EstadoSano();
        this.movimiento = movimiento;
    }

    public void moverA(Posicion destino, Tablero tablero) {
        this.estadoTurno = this.estadoTurno.mover(this, destino, tablero);
    }

    boolean ejecutarMovimiento(Posicion destino, Tablero tablero) {
        if (this.movimiento.puedeLlegar(this.posicion, destino, this.obtenerRango(), tablero)) {
            reubicarEn(destino, tablero);
            return true;
        } else {
            System.out.println("Movimiento inválido: fuera de rango o camino bloqueado");
            return false;
        }
    }

    protected void reubicarEn(Posicion destino, Tablero tablero) {
        tablero.desOcuparCasilla(this.posicion);
        tablero.ocuparCasilla(destino, this);
        this.posicion = destino;
        tablero.enCasilla(destino).aplicarEfectoA(this);
    }

    public void reiniciarTurno() {
        this.estadoTurno = new EstadoDisponible();
    }

    public int obtenerRango() {
        return rango;
    }

    public void recibirDanio(int danio) {
        this.vida -= this.estadoSalud.calcularDanio(danio);
    }

    public int obtenerVida() {
        return vida;
    }

    public boolean ocupaCasilla() {
        return true;
    }

    public Posicion obtenerPosicion() {
        return posicion;
    }

    public void contaminarse(){
        this.estadoSalud = new EstadoContaminado();
    }

    protected abstract void hundirse();

    // Esto hay que verlo porque no tiene sentido que no implemente el contrato (Mati)
    // Ahi esta tomando forma (Mati 2)
    public void caerEnAgua(){
        movimiento.caerEnAgua(this);
    }

    public void caerEnAcido(){
        movimiento.caerEnAcido(this);
    }
}