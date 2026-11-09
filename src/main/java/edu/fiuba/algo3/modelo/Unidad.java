
package edu.fiuba.algo3.modelo;

public abstract class Unidad {

    private int vida;

    public Unidad(int vida){
        this.vida = vida;
    }

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

    // Quizas convenga tener este metodo publico y el atributo rango (Mati)
    protected abstract int obtenerRango();

    public void recibirDanio(int danio){
        this.vida = vida - danio;
    }

    public int obtenerVida(){
        return vida;
    }

}