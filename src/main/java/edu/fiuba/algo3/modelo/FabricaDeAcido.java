package edu.fiuba.algo3.modelo;

public class FabricaDeAcido extends FabricaDeTerreno {
    @Override
    public Terreno crear() {
        return new Acido();
    }
}
