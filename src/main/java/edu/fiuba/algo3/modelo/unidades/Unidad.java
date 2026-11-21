package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.*;

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

    protected void ejecutarMovimiento(Posicion destino, Tablero tablero) {
        this.movimiento.validarCamino(this.posicion, destino, this.obtenerRango(), tablero);
        reubicarEn(destino, tablero);
    }

    protected void reubicarEn(Posicion destino, Tablero tablero) {
        tablero.enCasilla(this.posicion).abandonar(this);
        trasladarA(destino, tablero);
        tablero.enCasilla(destino).aplicarEfectoA(this);
    }

    protected void reubicarSinEfectoEn(Posicion destino, Tablero tablero) {
        trasladarA(destino, tablero);
    }

    private void trasladarA(Posicion destino, Tablero tablero) {
        tablero.trasladar(this, this.posicion, destino);
        this.posicion = destino;
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

    @Override 
    public void contaminarse(){
        this.estadoSalud = new EstadoContaminado();
    }

    public void descontaminarse(){
        this.estadoSalud = new EstadoSano();
    }

    public boolean puedeMoverseYTerminarEn(Posicion destino, Tablero tablero) {
        if (this.posicion.equals(destino)) {
            return true;
        }
        try {
            this.movimiento.validarCamino(this.posicion, destino, this.rango, tablero);
        } catch (AccionInvalidaException e) {
            return false;
        }
        return this.movimiento.esSeguroTerminarEn(tablero.enCasilla(destino).obtenerTerreno());
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

    protected EstadoSalud obtenerEstadoSalud() {
    return this.estadoSalud;
    }

    protected void establecerEstadoSalud(EstadoSalud estado) {
        this.estadoSalud = estado;
    }

    @Override
    public void recibirTransito(Transito transito, Casilla casilla) {
        transito.chocarConOcupante(casilla);
    }

    @Override
    public void recibirEfectoDeImpacto(EfectoImpacto efecto, Posicion posicion, Direccion direccion, Tablero tablero) {
        efecto.aplicar(this, posicion, direccion, tablero);
    }

    @Override
    public void recibirEmergencia(EmergenciaVek emergencia, Casilla casilla, Tablero tablero) {
        emergencia.serBloqueadaPor(this);
    }

    @Override
    public void ofrecerComoObjetivo(BuscadorDeObjetivos buscador, Casilla casilla) {
    }

}