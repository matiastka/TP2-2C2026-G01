package edu.fiuba.algo3.modelo;

public class FabricaDeSuelo extends FabricaDeTerreno {
    @Override
    public Terreno crear() {
        return new Suelo();
    }
}
