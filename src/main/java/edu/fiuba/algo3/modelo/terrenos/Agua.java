package edu.fiuba.algo3.modelo.terrenos;


import edu.fiuba.algo3.modelo.tablero.*;

public class Agua extends Terreno {
    @Override
    public String obtenerNombre() {
        return "Agua";
    }

    @Override
    public void afectarA(Ocupante ocupante){
        ocupante.caerEnAgua();
    }

    @Override
    public void abandonar(Ocupante ocupante) {
        ocupante.salirDelAgua();
    }

    @Override
    public void ofrecerComoDestino(BuscadorDeDestinos buscador, Casilla casilla) {
    }

    @Override
    public void ofrecerParaEmergencia(BuscadorDeEmergencias buscador, Casilla casilla) {
    }

}
