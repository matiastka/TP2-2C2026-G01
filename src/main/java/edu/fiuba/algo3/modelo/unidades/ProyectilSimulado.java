package edu.fiuba.algo3.modelo.unidades;

import java.util.Optional;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class ProyectilSimulado extends Proyectil {

    private Optional<Casilla> casillaImpactada = Optional.empty();

    public ProyectilSimulado(Direccion direccion, Tablero tablero) {
        super(direccion, tablero);
    }

    @Override
    protected void impactar(Casilla casilla) {
        this.casillaImpactada = Optional.of(casilla);
    }

    public Optional<Casilla> casillaImpactada() {
        return this.casillaImpactada;
    }
}