package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Ocupante;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public abstract class Unidad implements Ocupante {

    private int vida;
    private int rango;
    private Posicion posicion;
    private EstadoSalud estadoSalud;
    private Movimiento movimiento;

    protected Unidad(int vida, int rango, Movimiento movimiento) {
        this.vida = vida;
        this.rango = rango;
        this.estadoSalud = new EstadoSano();
        this.movimiento = movimiento;
    }

    protected boolean ejecutarMovimiento(Posicion destino, Tablero tablero) {
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

    public void colocarseEn(Posicion posicion, Tablero tablero) {
        tablero.ocuparCasilla(posicion, this);
        this.posicion = posicion;
    }

    public int obtenerRango() {
        return rango;
    }

    @Override
    public void recibirDanio(int danio) {
        this.vida -= this.estadoSalud.calcularDanio(danio);
    }

    public int obtenerVida() {
        return vida;
    }

    @Override
    public boolean ocupaCasilla() {
        return true;
    }

    public Posicion obtenerPosicion() {
        return posicion;
    }

    public void contaminarse(){
        this.estadoSalud = new EstadoContaminado();
    }

    public void descontaminarse(){
        this.estadoSalud = new EstadoSano();
    }

    protected abstract void hundirse();

    @Override
    public void caerEnAgua(){
        movimiento.caerEnAgua(this);
    }

    @Override
    public void caerEnAcido(){
        movimiento.caerEnAcido(this);
    }

    @Override
    public void serEmpujadoA(Posicion destino, Tablero tablero) {
        reubicarEn(destino, tablero);
    }

    public abstract void moverA(Posicion destino, Tablero tablero);
}