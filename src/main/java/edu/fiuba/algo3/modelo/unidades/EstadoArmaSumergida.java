package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class EstadoArmaSumergida implements EstadoArma{

    @Override
    public boolean usar(Mech mech, Posicion objetivo, Tablero tablero){
        System.out.println("Un Mech no puede atacar debajo del agua");
        return false;
    }
}
