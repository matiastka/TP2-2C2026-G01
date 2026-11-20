package edu.fiuba.algo3.modelo.unidades;

import java.util.Optional;

import edu.fiuba.algo3.modelo.tablero.*;

public class AtaqueLineaRecta implements Ataque {

    private final int danio;
    private final EfectoImpacto efecto;

    public AtaqueLineaRecta(int danio, EfectoImpacto efecto) {
        this.danio = danio;
        this.efecto = efecto;
    }

    @Override
    public Optional<Casilla> casillaImpactadaDesde(Posicion origen, Direccion direccion, Tablero tablero) {
        ProyectilSimulado proyectil = new ProyectilSimulado(direccion, tablero);
        proyectil.lanzarDesde(origen);
        return proyectil.casillaImpactada();
    }

    @Override
    public void ejecutar(Posicion origen, Direccion direccion, Tablero tablero) {
        new ProyectilReal(this.danio, this.efecto, direccion, tablero).lanzarDesde(origen);
    }

}