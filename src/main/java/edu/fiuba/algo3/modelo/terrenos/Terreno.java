package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.unidades.Unidad;

public abstract class Terreno {
    public abstract boolean esTransitable();

    public abstract void afectarA(Unidad unidad);

    public void recibirDanio(int danio) {
        // Por defecto, los terrenos no reciben daño.
    }
}
