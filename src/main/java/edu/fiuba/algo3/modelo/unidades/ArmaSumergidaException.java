package edu.fiuba.algo3.modelo.unidades;

public class ArmaSumergidaException extends AccionInvalidaException {
    public ArmaSumergidaException() {
        super("Un Mech no puede atacar debajo del agua");
    }
}
