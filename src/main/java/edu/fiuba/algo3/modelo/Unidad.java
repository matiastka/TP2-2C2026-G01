
package edu.fiuba.algo3.modelo;

public abstract class Unidad {

    private int vida;
    protected int rango;
    protected boolean yaSeMovio;
    protected boolean yaAtaco;
    protected Posicion posicion;

    protected Unidad(int vida, Posicion posicion){
        this.vida = vida;
        this.yaSeMovio = false;
        this.yaAtaco = false;
        this.posicion = posicion;
    }

    public boolean esDelMismoTipoQue(Unidad otro){
        return this.getClass() == otro.getClass();
    }

    public void moverA(Posicion destino, Tablero tablero) {
        if (this.yaSeMovio) {
            System.out.println("Movimiento inválido: la unidad ya se movió en este turno.");
            return;
        }
        
        MovimientoTerrestre validador = new MovimientoTerrestre();
        
        if (validador.puedeLlegar(this.posicion, destino, this.obtenerRango(), tablero)) {
            tablero.desOcuparCasilla(this.posicion);
            tablero.ocuparCasilla(destino, this);
            this.yaSeMovio = true;
            this.posicion = destino;
        }
        else { // Quizas aca podriamos lanzar una excepcion (Mati)
            System.out.println("Movimiento inválido: fuera de rango o camino bloqueado");
        }
    }

    public void reiniciarTurno() {
        this.yaSeMovio = false;
        this.yaAtaco = false;
    }

    // Quizas convenga tener este metodo publico
    public  int obtenerRango(){
        return rango;
    }

    public void recibirDanio(int danio){
        this.vida = vida - danio;
    }

    public int obtenerVida(){
        return vida;
    }

    public boolean ocupaCasilla(){
        return true;
    }

    public Posicion obtenerPosicion(){
        return posicion;
    }

}