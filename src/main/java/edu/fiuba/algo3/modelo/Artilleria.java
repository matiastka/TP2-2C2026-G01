package edu.fiuba.algo3.modelo;

public class Artilleria extends Mech {

    public Artilleria(Posicion posicion) {
        super(2, posicion);
    }

    @Override
    protected void ejecutarAtaque(Posicion objetivo, Tablero tablero) {
        if (!calcularLineaRecta(objetivo)) {
            return;
        }

        aplicarDanioEn(objetivo, 1, tablero);

        for (Direccion direccion : Direccion.cardinales()) {
            Posicion posAdyacente = objetivo.siguienteEn(direccion);
            empujarUnidadDesde(posAdyacente, direccion, tablero);
        }
    }
}
