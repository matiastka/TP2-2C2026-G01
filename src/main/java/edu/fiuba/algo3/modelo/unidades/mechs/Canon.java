package edu.fiuba.algo3.modelo.unidades.mechs;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Ataque;
import edu.fiuba.algo3.modelo.unidades.AtaqueLineaRecta;
import edu.fiuba.algo3.modelo.unidades.EfectoEmpujar;
import edu.fiuba.algo3.modelo.unidades.Mech;
import edu.fiuba.algo3.modelo.unidades.MotivoRechazoAccion;
import edu.fiuba.algo3.modelo.unidades.ResultadoAccion;

public class Canon extends Mech {

    private final Ataque ataque = new AtaqueLineaRecta(1, new EfectoEmpujar());

    public Canon() {
        super(3);
    }

    @Override
    protected ResultadoAccion<Void> ejecutarAtaque(Posicion objetivo, Tablero tablero) {
        if (!tablero.estaEnRango(objetivo)) {
            return ResultadoAccion.rechazo(MotivoRechazoAccion.DESTINO_FUERA_DEL_TABLERO);
        }

        if (!obtenerPosicion().estaEnLineaRectaA(objetivo, 1)) {
            return ResultadoAccion.rechazo(MotivoRechazoAccion.DESTINO_NO_ALCANZABLE);
        }

        Direccion direccion = obtenerPosicion().direccionHacia(objetivo);
        ataque.ejecutar(obtenerPosicion(), direccion, tablero);
        return ResultadoAccion.exito(null);
    }
}