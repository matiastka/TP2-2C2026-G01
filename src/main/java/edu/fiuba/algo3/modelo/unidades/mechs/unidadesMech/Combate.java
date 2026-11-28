package edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech;

import edu.fiuba.algo3.modelo.excepciones.DestinoFueraDelTableroException;
import edu.fiuba.algo3.modelo.excepciones.DestinoNoAlcanzableException;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.direcciones.Direccion;
import edu.fiuba.algo3.modelo.unidades.ataques.Ataque;
import edu.fiuba.algo3.modelo.unidades.ataques.AtaqueCuerpoACuerpo;
import edu.fiuba.algo3.modelo.unidades.ataques.efectos.EfectoEmpujar;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;

public class Combate extends Mech {
    @Override
    public String obtenerNombre() {
        return "Combate";
    }

    private final Ataque ataque = new AtaqueCuerpoACuerpo(2, new EfectoEmpujar());

    public Combate() {
        super(3);
    }

    @Override
    public void ejecutarAtaque(Posicion objetivo, Tablero tablero) {
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