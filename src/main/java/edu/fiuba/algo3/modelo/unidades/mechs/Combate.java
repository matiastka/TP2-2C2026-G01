
package edu.fiuba.algo3.modelo.unidades.mechs;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Mech;

public class Combate extends Mech {

    public Combate(){
        super(3);
    }

    @Override
    protected void ejecutarAtaque(Posicion objetivo, Tablero tablero) {
        if (!obtenerPosicion().esAdyacente(objetivo)) {
            System.out.println("El objetivo a atacar no es adyacente.\nPor ende no se aplica el danio ni el empuje");
            return;
        }

        if (!tablero.enCasilla(objetivo).estaLibre()) {
            Direccion direccion = obtenerPosicion().direccionHacia(objetivo);
            golpearYEmpujar(objetivo, 2, direccion, tablero);
            return;
        }

        if (!tablero.enCasilla(objetivo).esTransitable()) {
            tablero.enCasilla(objetivo).obtenerTerreno().recibirDanio(1);
            return;
        }

        System.out.println("La casilla a atacar esta vacia");
    }

}