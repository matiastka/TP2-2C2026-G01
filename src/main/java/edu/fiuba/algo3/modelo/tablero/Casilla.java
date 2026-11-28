package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.SujetoObservable;
import edu.fiuba.algo3.modelo.tablero.direcciones.Direccion;
import edu.fiuba.algo3.modelo.tablero.emergencias.Emergencia;
import edu.fiuba.algo3.modelo.tablero.emergencias.EmergenciaVek;
import edu.fiuba.algo3.modelo.tablero.emergencias.SinEmergencia;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import java.util.List;

public class Casilla extends SujetoObservable {
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
        notificarObservadores();
    }

    public void desOcupar(){
        this.ocupante = new SinOcupante();
        notificarObservadores();
    }

    public void activarEmergencia(Vek vek) {
        this.emergencia = new EmergenciaVek(vek);
        notificarObservadores();
    }

    public List<Vek> resolverEmergencia(Tablero tablero) {
        return this.emergencia.resolver(this, tablero);
    }

    public List<Vek> recibirEmergencia(EmergenciaVek emergencia, Tablero tablero) {
        return this.ocupante.recibirEmergencia(emergencia, this, tablero);
    }

    public void setEmergencia(Emergencia emergencia) {
        this.emergencia = emergencia;
        notificarObservadores();
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

    public Emergencia obtenerEmergencia() {
        return emergencia;
    }

    public void aplicarEfectoA(Ocupante ocupante) {
        this.terreno.afectarA(ocupante);
    }

    public void abandonar(Ocupante ocupante) {
        this.terreno.abandonar(ocupante);
    }

    public void cambiarTerreno(Terreno nuevoTerreno){
        this.terreno = nuevoTerreno;
        notificarObservadores();
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

    public void ofrecerTerrenoComoDestino(BuscadorDeDestinos buscador) {
        this.terreno.ofrecerComoDestino(buscador, this);
    }

    public void ofrecerParaEmergencia(BuscadorDeEmergencias buscador) {
        this.ocupante.ofrecerParaEmergencia(buscador, this);
    }

    public void ofrecerTerrenoParaEmergencia(BuscadorDeEmergencias buscador) {
        this.terreno.ofrecerParaEmergencia(buscador, this);
    }


}