package edu.fiuba.algo3.modelo.unidades;

import java.util.Optional;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Ocupante;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class AtaqueCuerpoACuerpo implements Ataque {

    private final int danio;
    private final EfectoImpacto efecto;

    public AtaqueCuerpoACuerpo(int danio, EfectoImpacto efecto) {
        this.danio = danio;
        this.efecto = efecto;
    }

    @Override 
    public Optional<Casilla> casillaImpactadaDesde(Posicion origen, Direccion direccion, Tablero tablero) {
        Posicion objetivo = origen.siguienteEn(direccion);
        if (!tablero.estaEnRango(objetivo)) {
            return Optional.empty();
        }
        return Optional.of(tablero.enCasilla(objetivo));
    }

    @Override
    public void ejecutar(Posicion origen, Direccion direccion, Tablero tablero) {

        Posicion objetivo = origen.siguienteEn(direccion);
        if (!tablero.estaEnRango(objetivo)) {
            return;
        }

        Casilla casillaObjetivo = tablero.enCasilla(objetivo);

        if (!casillaObjetivo.estaLibre()) {
            Ocupante ocupante = casillaObjetivo.obtenerOcupante();
            ocupante.recibirDanio(danio);
            efecto.aplicar(ocupante, objetivo, direccion, tablero);
            return;
        }

        if (!casillaObjetivo.esTransitable()) {
            casillaObjetivo.obtenerTerreno().recibirDanio(danio);
        }
    }
}