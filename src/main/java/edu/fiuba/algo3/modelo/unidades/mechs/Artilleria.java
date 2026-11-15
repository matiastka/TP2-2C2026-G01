package edu.fiuba.algo3.modelo.unidades.mechs;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Mech;

public class Artilleria extends Mech {

    public Artilleria() {
        super(2);
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
