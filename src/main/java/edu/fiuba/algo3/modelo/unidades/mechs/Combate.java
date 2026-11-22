package edu.fiuba.algo3.modelo.unidades.mechs;

import edu.fiuba.algo3.modelo.VisitanteVisualCasilla;
import edu.fiuba.algo3.modelo.tablero.direcciones.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.ataques.Ataque;
import edu.fiuba.algo3.modelo.unidades.ataques.AtaqueCuerpoACuerpo;
import edu.fiuba.algo3.modelo.unidades.ataques.efectos.EfectoEmpujar;
import edu.fiuba.algo3.modelo.unidades.excepciones.DestinoFueraDelTableroException;
import edu.fiuba.algo3.modelo.unidades.excepciones.DestinoNoAlcanzableException;

public class Combate extends Mech {
    @Override
    public void aceptar(VisitanteVisualCasilla visitante) {
        visitante.visitarCombate(this);
    }

    private final Ataque ataque = new AtaqueCuerpoACuerpo(2, new EfectoEmpujar());

    public Combate() {
        super(3);
    }

    @Override
    protected void ejecutarAtaque(Posicion objetivo, Tablero tablero) {
        if (!tablero.estaEnRango(objetivo)) {
            throw new DestinoFueraDelTableroException();
        }

        if (!obtenerPosicion().esAdyacente(objetivo)) {
            throw new DestinoNoAlcanzableException();
        }

        Direccion direccion = obtenerPosicion().direccionHacia(objetivo);
        ataque.ejecutar(obtenerPosicion(), direccion, tablero);
    }
}