package edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech;

import edu.fiuba.algo3.modelo.VisitanteVisualCasilla;
import edu.fiuba.algo3.modelo.excepciones.DestinoFueraDelTableroException;
import edu.fiuba.algo3.modelo.excepciones.DestinoNoAlcanzableException;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.direcciones.Direccion;
import edu.fiuba.algo3.modelo.unidades.ataques.Ataque;
import edu.fiuba.algo3.modelo.unidades.ataques.AtaqueLineaRecta;
import edu.fiuba.algo3.modelo.unidades.ataques.efectos.EfectoEmpujar;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;

public class Canon extends Mech {
    @Override
    public void aceptar(VisitanteVisualCasilla visitante) {
        visitante.visitarCanon(this);
    }

    private final Ataque ataque = new AtaqueLineaRecta(1, new EfectoEmpujar());

    public Canon() {
        super(3);
    }

    @Override
    public void ejecutarAtaque(Posicion objetivo, Tablero tablero) {
        if (!tablero.estaEnRango(objetivo)) {
            throw new DestinoFueraDelTableroException();
        }

        if (!obtenerPosicion().estaEnLineaRectaA(objetivo, 1)) {
            throw new DestinoNoAlcanzableException();
        }

        Direccion direccion = obtenerPosicion().direccionHacia(objetivo);
        ataque.ejecutar(obtenerPosicion(), direccion, tablero);
    }
}