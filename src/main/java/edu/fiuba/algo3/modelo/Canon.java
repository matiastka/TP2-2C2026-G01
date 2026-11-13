
package edu.fiuba.algo3.modelo;

public class Canon extends Mech {

    public Canon(Posicion posicion) {
        super(3, posicion);
    }

    @Override
     protected void ejecutarAtaque(Posicion objetivo, Tablero tablero) {
        Direccion direccion = posicion.direccionHacia(objetivo);
        if (direccion == Direccion.NINGUNA) return;

        Posicion posActual = posicion.siguienteEn(direccion);
        while (tablero.estaEnRango(posActual)) {
            Casilla casillaActual = tablero.enCasilla(posActual);
            if (!casillaActual.estaLibre() || !casillaActual.esTransitable()) {

                if (!casillaActual.estaLibre()) {
                    Unidad unidadImpactada = casillaActual.obtenerUnidad();
                    unidadImpactada.recibirDanio(1);
                    Posicion posDestinoEmpuje = posActual.siguienteEn(direccion);

                    if (tablero.estaEnRango(posDestinoEmpuje)) {
                        unidadImpactada.reubicarEn(posDestinoEmpuje, tablero);
                    }
                }
                return;
            }
            posActual = posActual.siguienteEn(direccion);
        }
    }
}