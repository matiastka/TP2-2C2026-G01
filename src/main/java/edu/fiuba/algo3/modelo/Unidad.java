
package edu.fiuba.algo3.modelo;

public abstract class Unidad {

    private int vida;
    protected boolean yaSeMovio;
    protected boolean yaAtaco;

    protected Unidad(int vida){
    
        this.vida = vida;
        this.yaSeMovio = false;
        this.yaAtaco = false;
    }

    public boolean esDelMismoTipoQue(Unidad otro){
        return this.getClass() == otro.getClass();
    }

    public void moverA(Posicion destino, Tablero tablero) {
        if (this.yaSeMovio) {
            System.out.println("Movimiento inválido: la unidad ya se movió en este turno.");
            return;
        }

        Posicion origen = tablero.buscarPosicionDe(this); 
        
        MovimientoTerrestre validador = new MovimientoTerrestre();
        
        if (validador.puedeLlegar(origen, destino, this.obtenerRango(), tablero)) {
            tablero.desOcuparCasilla(origen);
            tablero.ocuparCasilla(destino, this);
            this.yaSeMovio = true;
        }
        else { // Quizas aca podriamos lanzar una excepcion (Mati)
            System.out.println("Movimiento inválido: fuera de rango o camino bloqueado");
        }
    }

    public void reiniciarTurno() {
        this.yaSeMovio = false;
        this.yaAtaco = false;
    }

    // Quizas convenga tener este metodo publico y el atributo rango (Mati)
    protected abstract int obtenerRango();

    public void recibirDanio(int danio){
        this.vida = vida - danio;
    }

    public int obtenerVida(){
        return vida;
    }

	public abstract void deshacerMovimiento(Tablero tablero);

	public abstract void atacarA(Posicion posicion, Tablero tablero);

}