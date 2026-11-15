package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.tablero.Ocupante;

public abstract class Terreno {
    public abstract boolean esTransitable();

    public abstract void afectarA(Ocupante ocupante);

    public void recibirDanio(int danio) {
        // Por defecto, los terrenos no reciben daño.
    }
}
