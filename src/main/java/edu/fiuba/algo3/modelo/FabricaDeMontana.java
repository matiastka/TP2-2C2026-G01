package edu.fiuba.algo3.modelo;

public class FabricaDeMontana extends FabricaDeTerreno {
    @Override
    public Terreno crear() {
        return new Montana();
    }
}
