
package edu.fiuba.algo3.modelo;

public class Canon extends Mech {

    public Canon(Posicion posicion) {
        super(3, posicion);
    }

    @Override
    protected void ejecutarAtaque(Posicion objetivo, Tablero tablero) {
        Direccion direccion = posicion.direccionHacia(objetivo);
        if (direccion == Direccion.NINGUNA) {
            return;
        }

        Posicion posActual = posicion.siguienteEn(direccion);
        while (tablero.estaEnRango(posActual)) {
            Casilla casillaActual = tablero.enCasilla(posActual);

            if (!casillaActual.estaLibre()) {
                golpearYEmpujar(posActual, 1, direccion, tablero);
                return;
            }

            if (!casillaActual.esTransitable()) {
                return;
            }

            posActual = posActual.siguienteEn(direccion);
        }
    }
}