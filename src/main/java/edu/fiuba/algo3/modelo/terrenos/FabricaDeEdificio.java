package edu.fiuba.algo3.modelo.terrenos;

public class FabricaDeEdificio extends FabricaDeTerreno {
    @Override
    public Terreno crear() {
        return new Edificio();
    }
}
