package edu.fiuba.algo3.modelo.unidades.estados;

public class EstadoContaminado implements EstadoSalud {

    @Override
    public int calcularDanio(int danio){
        return danio * 2;
    }

    @Override
    public boolean estaContaminado() {
        return true;
    }
}
