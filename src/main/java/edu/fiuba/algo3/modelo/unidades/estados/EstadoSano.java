package edu.fiuba.algo3.modelo.unidades.estados;

public class EstadoSano implements EstadoSalud{

    @Override
    public int calcularDanio(int danio){
        return danio;
    }

    @Override
    public boolean estaContaminado() {
        return false;
    }
}
