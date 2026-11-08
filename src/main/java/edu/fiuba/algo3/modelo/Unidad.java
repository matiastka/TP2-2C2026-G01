
package edu.fiuba.algo3.modelo;

public abstract class Unidad {

    public boolean esDelMismoTipoQue(Unidad otro){
        return this.getClass() == otro.getClass();
    }

    public void moverA(Posicion destino, Tablero tablero) {
        Posicion origen = tablero.buscarPosicionDe(this); 
        
        MovimientoTerrestre validador = new MovimientoTerrestre();
        
        if (validador.puedeLlegar(origen, destino, this.obtenerRango(), tablero)) {
            tablero.desOcuparCasilla(origen.getFila(), origen.getColumna());
            tablero.ocuparCasilla(destino.getFila(), destino.getColumna(), this);
        }
        else { // Quizas aca podriamos lanzar una excepcion (Mati)
            System.out.println("Movimiento inválido: fuera de rango o camino bloqueado");
        }
    }

    protected abstract int obtenerRango();
}