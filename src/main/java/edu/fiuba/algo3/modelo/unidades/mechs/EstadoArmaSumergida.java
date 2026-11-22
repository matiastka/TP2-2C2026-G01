package edu.fiuba.algo3.modelo.unidades.mechs;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.excepciones.ArmaSumergidaException;

public class EstadoArmaSumergida implements EstadoArma{

    @Override
    public void usar(Mech mech, Posicion objetivo, Tablero tablero) {
        throw new ArmaSumergidaException();
    }

    @Override
    public String obtenerDescripcion() {
        return "Sumergido";
    }
}
