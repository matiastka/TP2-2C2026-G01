package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.tablero.Ocupante;
import edu.fiuba.algo3.modelo.tablero.Terreno;

public class Edificio extends Terreno {
    private final RedElectrica redElectrica = RedElectrica.getInstancia();
    private boolean destruido = false;

    @Override
    public boolean esTransitable() {
        return destruido;
    }

    @Override
    public void afectarA(Ocupante ocupante) {

    }

    @Override 
    public void recibirDanio(int danio) {
        if (!destruido && danio > 0) {
            this.destruido = true;
            this.redElectrica.reducir();
        }
    }

    @Override
    public boolean esEdificio() {
        return !destruido;
    }
}
