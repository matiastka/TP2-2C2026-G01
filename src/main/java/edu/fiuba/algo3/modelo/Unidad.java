
package edu.fiuba.algo3.modelo;

public abstract class Unidad {
    public boolean esDelMismoTipoQue(Unidad otro){
        return this.getClass() == otro.getClass();
    }

    public void moverseHorizontal(int rango, Tablero tablero, int x, int y){
        if (!tablero.estaEnRango(x, y+rango)) {
            System.out.println("Estas fuera de Rango");
        }
        tablero.ocuparCasilla(x,y + rango, this);
        tablero.desOcuparCasilla(x, y);
    }
    
    public void moverseVertical(int rango, Tablero tablero, int x, int y){
        if (!tablero.estaEnRango(x+rango, y)){
            System.out.println("Estas fuera de Rango");
        }
        tablero.ocuparCasilla(x + rango,y, this);
        tablero.desOcuparCasilla(x, y);
    }

    public void moverA(Posicion destino, Tablero tablero) {
        Posicion origen = tablero.buscarPosicionDe(this); 
        
        MovimientoTerrestre validador = new MovimientoTerrestre();
        
        if (validador.puedeLlegar(origen, destino, this.obtenerRango(), tablero)) {
            tablero.desOcuparCasilla(origen.getFila(), origen.getColumna());
            tablero.ocuparCasilla(destino.getFila(), destino.getColumna(), this);
        } else {
            System.out.println("Movimiento inválido: fuera de rango o camino bloqueado");
        }
    }

    protected abstract int obtenerRango();
}