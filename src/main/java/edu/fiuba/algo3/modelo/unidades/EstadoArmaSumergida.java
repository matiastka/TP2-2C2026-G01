package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class EstadoArmaSumergida implements EstadoArma{

    @Override
    public void usar(Mech mech, Posicion objetivo, Tablero tablero) {
        throw new ArmaSumergidaException();
    }
}
