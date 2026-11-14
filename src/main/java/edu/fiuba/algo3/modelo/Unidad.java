package edu.fiuba.algo3.modelo;

public abstract class Unidad {

    private int vida;
    protected int rango;
    protected Posicion posicion;
    protected EstadoTurno estadoTurno;

    protected Unidad(int vida, Posicion posicion) {
        this.vida = vida;
        this.posicion = posicion;
        this.estadoTurno = new EstadoDisponible();
    }

    public void moverA(Posicion destino, Tablero tablero) {
        this.estadoTurno = this.estadoTurno.mover(this, destino, tablero);
    }

    boolean ejecutarMovimiento(Posicion destino, Tablero tablero) {
        MovimientoTerrestre validador = new MovimientoTerrestre();

        if (validador.puedeLlegar(this.posicion, destino, this.obtenerRango(), tablero)) {
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
        this.vida -= danio;
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

    // Esto hay que verlo porque no tiene sentido que no implemente el contrato
    public void caerEnAgua(){

    }

    public void caerEnAcido(){

    }
}