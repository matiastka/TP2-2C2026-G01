package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Ocupante;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class EfectoEmpujar implements EfectoImpacto {

    @Override
    public void aplicar(Ocupante ocupante, Posicion posicionImpacto, Direccion direccion, Tablero tablero) {
        if (direccion == Direccion.NINGUNA) {
            return;
        }

        Posicion destino = posicionImpacto.siguienteEn(direccion);

        if (!tablero.estaEnRango(destino) || !tablero.enCasilla(destino).estaLibre() || !tablero.enCasilla(destino).esTransitable()) {
            ocupante.recibirDanio(1); 
            if (tablero.estaEnRango(destino) && !tablero.enCasilla(destino).esTransitable()) {
                tablero.enCasilla(destino).obtenerTerreno().recibirDanio(1); 
            }
            return;
        }

        ocupante.serEmpujadoA(destino, tablero);
    }
}