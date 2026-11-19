package edu.fiuba.algo3.modelo.unidades.mechs;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Ataque;
import edu.fiuba.algo3.modelo.unidades.AtaqueArtilleria;
import edu.fiuba.algo3.modelo.unidades.EfectoEmpujar;
import edu.fiuba.algo3.modelo.unidades.Mech;
import edu.fiuba.algo3.modelo.unidades.MotivoRechazoAccion;
import edu.fiuba.algo3.modelo.unidades.ResultadoAccion;

public class Artilleria extends Mech {

    public Artilleria() {
        super(2);
    }

    @Override
    protected ResultadoAccion<Void> ejecutarAtaque(Posicion objetivo, Tablero tablero) {
        if (!tablero.estaEnRango(objetivo)) {
            return ResultadoAccion.rechazo(MotivoRechazoAccion.DESTINO_FUERA_DEL_TABLERO);
        }
        if (!obtenerPosicion().estaEnLineaRectaA(objetivo, 2)) {
            return ResultadoAccion.rechazo(MotivoRechazoAccion.DESTINO_NO_ALCANZABLE);
        }
        int distancia = Math.abs(obtenerPosicion().distanciaA(objetivo)); // o calcula pasos en línea recta
        Direccion direccion = obtenerPosicion().direccionHacia(objetivo);
        Ataque ataque = new AtaqueArtilleria(1, distancia, new EfectoEmpujar());
        ataque.ejecutar(obtenerPosicion(), direccion, tablero);
        return ResultadoAccion.exito(null);
    }
}