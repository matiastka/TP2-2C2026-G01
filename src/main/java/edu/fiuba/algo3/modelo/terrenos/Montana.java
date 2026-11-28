package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.tablero.*;

public class Montana extends Terreno {

    @Override
    public void ofrecerParaEmergencia(BuscadorDeEmergencias buscador, Casilla casilla) {
    }

    @Override
    public String obtenerNombre() {
        return "Montana";
    }

    @Override
    public void afectarA(Ocupante ocupante){

    }

    @Override
    public void recibirTransito(Transito transito, Casilla casilla) {
        transito.chocarConTerreno(casilla);
    }
}
