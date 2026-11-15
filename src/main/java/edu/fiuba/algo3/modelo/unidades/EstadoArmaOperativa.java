package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class EstadoArmaOperativa implements EstadoArma{

    @Override
    public boolean usar(Mech mech, Posicion objetivo, Tablero tablero){
        mech.ejecutarAtaque(objetivo, tablero);
        return true;
    }
}
