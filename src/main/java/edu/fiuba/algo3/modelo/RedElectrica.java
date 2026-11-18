package edu.fiuba.algo3.modelo;

public class RedElectrica {
    private static final RedElectrica INSTANCIA = new RedElectrica();

    private int nivel = 3;

    private RedElectrica() {
    }

    public static RedElectrica getInstancia() {
        return INSTANCIA;
    }

    public int obtenerNivel() {
        return this.nivel;
    }

    public void reducir() {
        this.nivel = Math.max(0, this.nivel - 1);
    }

    public void reiniciar() {
        this.nivel = 3;
    }
}
