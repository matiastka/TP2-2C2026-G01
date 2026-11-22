package edu.fiuba.algo3.modelo.unidades;

import java.util.List;

import edu.fiuba.algo3.modelo.SujetoObservable;
import edu.fiuba.algo3.modelo.VisitanteVisualCasilla;
import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.tablero.direcciones.Direccion;
import edu.fiuba.algo3.modelo.tablero.emergencias.EmergenciaVek;
import edu.fiuba.algo3.modelo.unidades.excepciones.AccionInvalidaException;
import edu.fiuba.algo3.modelo.unidades.movimientos.Movimiento;

public abstract class Unidad extends SujetoObservable implements Ocupante {
    @Override
    public abstract void aceptar(VisitanteVisualCasilla visitante);

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
        notificarObservadores();
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
        notificarObservadores();
    }

    public void descontaminarse(){
        this.estadoSalud = new EstadoSano();
        notificarObservadores();
    }

    public void agregarSiPuedeTerminarEn(Posicion destino, List<Posicion> destinos, Tablero tablero) {
        if (this.posicion.equals(destino)) {
            destinos.add(destino);
            return;
        }
        try {
            this.movimiento.validarCamino(this.posicion, destino, this.rango, tablero);
        } catch (AccionInvalidaException e) {
            return;
        }
        this.movimiento.agregarSiPuedeTerminarEn(tablero.enCasilla(destino), destinos);
    }

    public abstract void hundirse();

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
        if (this.obtenerVida() > 0) {
            reubicarEn(destino, tablero);
        }
    }

    public abstract void moverA(Posicion destino, Tablero tablero);

    protected EstadoSalud obtenerEstadoSalud() {
        return this.estadoSalud;
    }

    public boolean estaContaminado() {
        return this.estadoSalud.estaContaminado();
    }

    protected void establecerEstadoSalud(EstadoSalud estado) {
        this.estadoSalud = estado;
        notificarObservadores();
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
    public void ofrecerComoObjetivo(BuscadorDeObjetivos buscador, Casilla casilla) {
    }

    @Override
    public void recibirEmergencia(EmergenciaVek emergencia, Casilla casilla, Tablero tablero) {
        emergencia.serBloqueadaPor(this);
    }
}