package edu.fiuba.algo3.modelo.unidades.mechs;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Ataque;
import edu.fiuba.algo3.modelo.unidades.AtaqueCuerpoACuerpo;
import edu.fiuba.algo3.modelo.unidades.EfectoEmpujar;
import edu.fiuba.algo3.modelo.unidades.Mech;

public class Combate extends Mech {

    private final Ataque ataque = new AtaqueCuerpoACuerpo(2, new EfectoEmpujar());

    public Combate() {
        super(3);
    }

    @Override
    protected void ejecutarAtaque(Posicion objetivo, Tablero tablero) {
        if (!obtenerPosicion().esAdyacente(objetivo)) {
            return;
        }
        Direccion direccion = obtenerPosicion().direccionHacia(objetivo);
        ataque.ejecutar(obtenerPosicion(), direccion, tablero);
    }
}