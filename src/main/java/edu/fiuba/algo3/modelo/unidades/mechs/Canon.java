package edu.fiuba.algo3.modelo.unidades.mechs;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Ataque;
import edu.fiuba.algo3.modelo.unidades.AtaqueLineaRecta;
import edu.fiuba.algo3.modelo.unidades.EfectoEmpujar;
import edu.fiuba.algo3.modelo.unidades.Mech;

public class Canon extends Mech {

    private final Ataque ataque = new AtaqueLineaRecta(1, new EfectoEmpujar());

    public Canon() {
        super(3);
    }

    @Override
    protected void ejecutarAtaque(Posicion objetivo, Tablero tablero) {
        Direccion direccion = obtenerPosicion().direccionHacia(objetivo);
        ataque.ejecutar(obtenerPosicion(), direccion, tablero);
    }
}