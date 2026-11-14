package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.unidades.Unidad;

public abstract class Terreno {
    public abstract boolean esTransitable();

    public abstract void afectarA(Unidad unidad);
}
