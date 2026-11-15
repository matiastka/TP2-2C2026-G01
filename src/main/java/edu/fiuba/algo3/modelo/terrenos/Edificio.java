package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.tablero.Ocupante;

public class Edificio extends Terreno {
    private final RedElectrica redElectrica = RedElectrica.getInstancia();

    @Override
    public boolean esTransitable() {
        return false;
    }

    @Override
    public void afectarA(Ocupante ocupante) {

    }

    @Override
    public void recibirDanio(int danio) {
        this.redElectrica.reducir(danio);
    }
}
