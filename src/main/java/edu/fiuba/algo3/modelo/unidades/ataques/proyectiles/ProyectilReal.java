package edu.fiuba.algo3.modelo.unidades.ataques.proyectiles;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.direcciones.Direccion;
import edu.fiuba.algo3.modelo.tablero.EfectoImpacto;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class ProyectilReal extends Proyectil {

    private final int danio;
    private final EfectoImpacto efecto;

    public ProyectilReal(int danio, EfectoImpacto efecto, Direccion direccion, Tablero tablero) {
        super(direccion, tablero);
        this.danio = danio;
        this.efecto = efecto;
    }

    @Override
    protected void impactar(Casilla casilla) {
        casilla.recibirDanio(this.danio);
        casilla.aplicarEfectoDeImpacto(this.efecto, this.direccion, this.tablero);
    }
}