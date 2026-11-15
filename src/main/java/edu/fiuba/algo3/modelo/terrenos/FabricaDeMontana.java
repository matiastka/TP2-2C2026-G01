package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.tablero.Terreno;

public class FabricaDeMontana extends FabricaDeTerreno {
    @Override
    public Terreno crear() {
        return new Montana();
    }
}
