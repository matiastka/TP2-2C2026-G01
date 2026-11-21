package edu.fiuba.algo3.modelo.unidades;

public class EstadoContaminado implements EstadoSalud {

    @Override
    public int calcularDanio(int danio){
        return danio * 2;
    }
}
