package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Ocupante;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class EfectoEmpujar implements EfectoImpacto {

    @Override
    public void aplicar(Ocupante ocupante, Posicion posicionImpacto, Direccion direccion, Tablero tablero) {
        Posicion destino = posicionImpacto.siguienteEn(direccion);

        // Regla 3: Fuera del tablero -> no se mueve y no recibe daño de colisión
        if (!tablero.estaEnRango(destino)) {
            return;
        }

        Casilla casillaDestino = tablero.enCasilla(destino);

        // Regla 2a: Choque contra otra unidad -> ambas reciben 1 de daño y ninguna se mueve
        if (!casillaDestino.estaLibre()) {
            ocupante.recibirDanio(1);
            casillaDestino.obtenerOcupante().recibirDanio(1);
            return;
        }

        // Regla 2b: Choque contra obstáculo (Edificio o Montaña) -> unidad recibe 1 de daño y el obstáculo también
        if (!casillaDestino.esTransitable()) {
            ocupante.recibirDanio(1);
            casillaDestino.obtenerTerreno().recibirDanio(1);
            return;
        }

        // Regla 1: Casilla libre y transitable -> se desplaza y aplica efecto del terreno
        ocupante.serEmpujadoA(destino, tablero);
    }
}