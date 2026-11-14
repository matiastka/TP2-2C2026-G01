package edu.fiuba.algo3.modelo;

public class FabricaDeEdificio extends FabricaDeTerreno {
    @Override
    public Terreno crear() {
        return new Edificio();
    }
}
