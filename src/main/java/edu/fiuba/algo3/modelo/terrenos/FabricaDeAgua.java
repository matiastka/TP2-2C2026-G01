package edu.fiuba.algo3.modelo.terrenos;

public class FabricaDeAgua extends FabricaDeTerreno {
    @Override
    public Terreno crear() {
        return new Agua();
    }

}
