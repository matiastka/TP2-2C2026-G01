package edu.fiuba.algo3.controllers;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;

public class EstadoSeleccionVista {
    private Mech mechSeleccionado;
    private final List<Posicion> destinosValidos = new ArrayList<>();
    private final List<Posicion> objetivosValidos = new ArrayList<>();
    private boolean terminado;

    public Mech obtenerMechSeleccionado() {
        return this.mechSeleccionado;
    }

    public void seleccionarMech(Mech mech) {
        this.mechSeleccionado = mech;
    }

    public void limpiarSeleccion() {
        this.mechSeleccionado = null;
        this.destinosValidos.clear();
        this.objetivosValidos.clear();
    }

    public List<Posicion> obtenerDestinosValidos() {
        return List.copyOf(this.destinosValidos);
    }

    public void establecerDestinosValidos(List<Posicion> destinos) {
        this.destinosValidos.clear();
        this.destinosValidos.addAll(destinos);
    }

    public List<Posicion> obtenerObjetivosValidos() {
        return List.copyOf(this.objetivosValidos);
    }

    public void establecerObjetivosValidos(List<Posicion> objetivos) {
        this.objetivosValidos.clear();
        this.objetivosValidos.addAll(objetivos);
    }

    public boolean estaTerminado() {
        return this.terminado;
    }

    public void terminar() {
        this.terminado = true;
        limpiarSeleccion();
    }
}
