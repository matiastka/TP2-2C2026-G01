package edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech;

import edu.fiuba.algo3.modelo.VisitanteVisualCasilla;
import edu.fiuba.algo3.modelo.excepciones.DestinoFueraDelTableroException;
import edu.fiuba.algo3.modelo.excepciones.DestinoNoAlcanzableException;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.direcciones.Direccion;
import edu.fiuba.algo3.modelo.unidades.ataques.Ataque;
import edu.fiuba.algo3.modelo.unidades.ataques.AtaqueArtilleria;
import edu.fiuba.algo3.modelo.unidades.ataques.efectos.EfectoEmpujar;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;

public class Artilleria extends Mech {
    @Override
    public void aceptar(VisitanteVisualCasilla visitante) {
        visitante.visitarArtilleria(this);
    }

    public Artilleria() {
        super(2);
    }

    @Override
    public void ejecutarAtaque(Posicion objetivo, Tablero tablero) {
        if (!tablero.estaEnRango(objetivo)) {
            throw new DestinoFueraDelTableroException();
        }
        if (!obtenerPosicion().estaEnLineaRectaA(objetivo, 2)) {
            throw new DestinoNoAlcanzableException();
        }

        int distancia = Math.abs(obtenerPosicion().distanciaA(objetivo));
        Direccion direccion = obtenerPosicion().direccionHacia(objetivo);
        Ataque ataque = new AtaqueArtilleria(1, distancia, new EfectoEmpujar());
        ataque.ejecutar(obtenerPosicion(), direccion, tablero);
    }
}