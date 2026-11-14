package edu.fiuba.algo3.modelo.terrenos;

public class FabricaDeAcido extends FabricaDeTerreno {
    @Override
    public Terreno crear() {
        return new Acido();
    }
}
