package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Casilla {
    private Terreno terreno;
    private Ocupante ocupante;
    private final Posicion posicion;
    private Emergencia emergencia;

    public Casilla(Terreno terreno, Posicion posicion){
        this.posicion = posicion;
        this.terreno = terreno;
        this.ocupante = new SinOcupante();
        this.emergencia = new SinEmergencia();
    }

    public boolean estaLibre(){
        return !this.ocupante.ocupaCasilla();
    }

    public void ocupar(Ocupante ocupante){
        this.ocupante = ocupante;
    }

    public void desOcupar(){
        this.ocupante = new SinOcupante();
    }

    public void activarEmergencia(Unidad unidad) {
        this.emergencia = new EmergenciaVek(unidad);
    }

    public void resolverEmergencia(Tablero tablero) {
        this.emergencia.resolver(this, tablero);
    }

    public void recibirEmergencia(EmergenciaVek emergencia, Tablero tablero) {
        this.ocupante.recibirEmergencia(emergencia, this, tablero);
    }

    public void setEmergencia(Emergencia emergencia) {
        this.emergencia = emergencia;
    }

    public Posicion obtenerPosicion() {
        return posicion;
    }

    public Terreno obtenerTerreno(){
        return terreno;
    }

    public Ocupante obtenerOcupante() {
        return ocupante;
    }

    public void aplicarEfectoA(Ocupante ocupante) {
        this.terreno.afectarA(ocupante);
    }

    public void abandonar(Ocupante ocupante) {
        this.terreno.abandonar(ocupante);
    }

    public void cambiarTerreno(Terreno nuevoTerreno){
        this.terreno = nuevoTerreno;
    }

    public void recibirDanio(int danio) {
        this.ocupante.recibirDanio(danio);
        this.terreno.recibirDanio(danio, this);
    }

    public void recibirTransito(Transito transito) {
        this.ocupante.recibirTransito(transito, this);
    }

    public void recibirTransitoEnTerreno(Transito transito) {
        this.terreno.recibirTransito(transito, this);
    }

    public void aplicarEfectoDeImpacto(EfectoImpacto efecto, Direccion direccion, Tablero tablero) {
        this.ocupante.recibirEfectoDeImpacto(efecto, this.posicion, direccion, tablero);
    }

    public void ofrecerComoObjetivo(BuscadorDeObjetivos buscador) {
        this.ocupante.ofrecerComoObjetivo(buscador, this);
    }

    public void ofrecerTerrenoComoObjetivo(BuscadorDeObjetivos buscador) {
        this.terreno.ofrecerComoObjetivo(buscador, this);
    }

}