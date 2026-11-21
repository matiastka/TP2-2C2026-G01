package edu.fiuba.algo3.modelo.unidades.mechs;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class EstadoArmaOperativa implements EstadoArma{

    @Override
    public void usar(Mech mech, Posicion objetivo, Tablero tablero){
        mech.ejecutarAtaque(objetivo, tablero);
    }
}
