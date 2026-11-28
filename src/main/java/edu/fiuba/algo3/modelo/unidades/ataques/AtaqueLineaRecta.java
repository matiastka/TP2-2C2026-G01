package edu.fiuba.algo3.modelo.unidades.ataques;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.tablero.direcciones.Direccion;
import edu.fiuba.algo3.modelo.unidades.ataques.proyectiles.ProyectilReal;
import edu.fiuba.algo3.modelo.unidades.ataques.proyectiles.ProyectilSimulado;

public class AtaqueLineaRecta implements Ataque {
    @Override
    public List<Posicion> posicionesAmenazadasDesde(Posicion origen, Direccion direccion, Tablero tablero) {
        List<Posicion> posiciones = new ArrayList<>();
        Posicion siguiente = origen.siguienteEn(direccion);
        while (tablero.estaEnRango(siguiente)){
            posiciones.add(siguiente);
            if (!tablero.estaLibre(siguiente)){
                break;
            }
            siguiente = siguiente.siguienteEn(direccion);
        }
        return List.copyOf(posiciones);
    }

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