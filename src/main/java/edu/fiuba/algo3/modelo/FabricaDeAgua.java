package edu.fiuba.algo3.modelo;

public class FabricaDeAgua extends FabricaDeTerreno {
    @Override
    public Terreno crear() {
        return new Agua();
    }

}
