package edu.fiuba.algo3.modelo;

public enum Direccion {
    NORTE(-1, 0), SUR(1, 0), ESTE(0, 1), OESTE(0, -1), NINGUNA(0,0);
    private final int diferenciaFila, diferenciaColumna;

    Direccion(int fila, int columna){
        this.diferenciaFila = fila;
        this.diferenciaColumna = columna;
    }

    public Posicion desplazar(int posicionFila, int posicionColumna){
        return new Posicion(posicionFila + diferenciaFila, posicionColumna + diferenciaColumna);
    }

    public static Direccion[] cardinales(){
            return new Direccion[]{NORTE, SUR, ESTE, OESTE};
    }

}
