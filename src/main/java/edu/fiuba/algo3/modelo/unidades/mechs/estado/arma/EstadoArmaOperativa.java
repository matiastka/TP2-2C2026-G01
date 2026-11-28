package edu.fiuba.algo3.modelo.unidades.mechs.estado.arma;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;

public class EstadoArmaOperativa implements EstadoArma{

    @Override
    public void usar(Mech mech, Posicion objetivo, Tablero tablero){
        mech.ejecutarAtaque(objetivo, tablero);
    }

    @Override
    public String obtenerDescripcion() {
        return "Operativo";
    }
}
