package edu.fiuba.algo3.modelo.tablero;

import java.util.Map;

import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import java.util.ArrayList;
import java.util.List;
import edu.fiuba.algo3.modelo.tablero.direcciones.Direccion;
import edu.fiuba.algo3.modelo.unidades.movimientos.Movimiento;

public class Tablero {
    private static final int CANTIDAD_DE_FILAS = 8;
    private static final int CANTIDAD_DE_COLUMNAS = 8;
    private final RedElectrica redElectrica;
    private Map<Posicion, Casilla> casillas;

    public Tablero(Map<Posicion, Casilla> casillas, RedElectrica redElectrica){
        this.casillas = casillas;
        this.redElectrica = redElectrica;
    }

    public Casilla enCasilla(Posicion posicion){
        return casillas.get(posicion);
    }

    public int cantidadFilas(){
        return CANTIDAD_DE_FILAS;
    }

    public int cantidadColumnas(){
        return CANTIDAD_DE_COLUMNAS;
    }

    public void ocuparCasilla(Posicion posicion, Ocupante ocupante){
        enCasilla(posicion).ocupar(ocupante);
    }

    public void desOcuparCasilla(Posicion posicion){
        enCasilla(posicion).desOcupar();
    }

    public void trasladar(Ocupante ocupante, Posicion origen, Posicion destino) {
        desOcuparCasilla(origen);
        ocuparCasilla(destino, ocupante);
    }

    public void anunciarEmergencia(Posicion posicion, Vek vek) {
        if (!casillas.containsKey(posicion)) {
            throw new IllegalArgumentException("La posición de emergencia no pertenece al tablero.");
        }
        enCasilla(posicion).activarEmergencia(vek);
    }

    public List<Vek> resolverEmergencias() {
        List<Vek> emergidos = new ArrayList<>();
        for (Casilla casilla : casillas.values()) {
            emergidos.addAll(casilla.resolverEmergencia(this));
        }
        return List.copyOf(emergidos);
    }

    public void ofrecerParaEmergencia(Posicion posicion, BuscadorDeEmergencias buscador) {
        enCasilla(posicion).ofrecerParaEmergencia(buscador);
    }

    public boolean estaEnRango(Posicion posicion){
        return casillas.containsKey(posicion);
    }

    public int obtenerRedElectrica() {
        return this.redElectrica.obtenerNivel();
    }

    public void recorrerTerrenos(BuscadorDeObjetivos buscador) {
        for (int fila = 0; fila < CANTIDAD_DE_FILAS; fila++) {
            for (int columna = 0; columna < CANTIDAD_DE_COLUMNAS; columna++) {
                enCasilla(new Posicion(fila, columna)).ofrecerTerrenoComoObjetivo(buscador);
            }
        }
    }
    public void recibirTransitoEn(Posicion posicion, Transito transito){
        if(!estaEnRango(posicion)){
            return;
        }
        enCasilla(posicion).recibirTransito(transito);
    }
    public boolean estaLibre(Posicion posicion){
        return enCasilla(posicion).estaLibre();
    }
    public void aplicarDanioEn(Posicion posicion ,int danio){
        if(estaEnRango(posicion)){
            enCasilla(posicion).recibirDanio(danio);
        }
    }
    public void aplicarEfectoDeImpactoEn(Posicion posicion ,EfectoImpacto efecto , Direccion direccion){
        if(estaEnRango(posicion)){
            enCasilla(posicion).aplicarEfectoDeImpacto(efecto,direccion,this);
        }
    }
    public void abandonarCasilla(Posicion posicion,Ocupante ocupante){
        enCasilla(posicion).abandonar(ocupante);
    }
    public void aplicarEfectoDeTerrenoEn(Posicion posicion,Ocupante ocupante){
        enCasilla(posicion).aplicarEfectoA(ocupante);
    }
    public void ofrecerDestinoAMovimiento(Posicion destino ,Movimiento movimiento,List<Posicion> destinos){
        movimiento.agregarSiPuedeTerminarEn(enCasilla(destino), destinos);
    }
}
