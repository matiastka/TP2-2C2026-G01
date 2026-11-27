package edu.fiuba.algo3.modelo.partida;

import java.util.List;

import edu.fiuba.algo3.modelo.unidades.veks.Vek;

public class FabricaDeVeks {

    private final List<CreadorDeVek> creadores;
    private int siguiente;

    public FabricaDeVeks() {
        this.creadores = List.of(new CreadorDeSaltador(), new CreadorDeAvispon(), new CreadorDeLuciernaga());
        this.siguiente = 0;
    }

    public Vek crearSiguiente() {
        Vek vek = this.creadores.get(this.siguiente).crear();
        this.siguiente = (this.siguiente + 1) % this.creadores.size();
        return vek;
    }
}