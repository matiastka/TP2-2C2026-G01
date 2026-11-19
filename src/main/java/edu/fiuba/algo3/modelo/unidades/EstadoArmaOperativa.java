package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class EstadoArmaOperativa implements EstadoArma{

    @Override
    public ResultadoAccion<Void> usar(Mech mech, Posicion objetivo, Tablero tablero){
        return mech.ejecutarAtaque(objetivo, tablero);
    }
}
