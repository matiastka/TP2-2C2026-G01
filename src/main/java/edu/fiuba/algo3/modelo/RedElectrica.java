package edu.fiuba.algo3.modelo;

public class RedElectrica {
    private static final int NIVEL_INICIAL = 5;

    private int nivel = NIVEL_INICIAL;

    public int obtenerNivel() {
        return this.nivel;
    }

    public void reducir() {
        this.nivel = Math.max(0, this.nivel - 1);
    }
}
