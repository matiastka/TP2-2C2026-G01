package edu.fiuba.algo3.modelo.terrenos;

public class FabricaDeSuelo extends FabricaDeTerreno {
    @Override
    public Terreno crear() {
        return new Suelo();
    }
}
