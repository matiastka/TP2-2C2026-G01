package edu.fiuba.algo3.modelo;

public abstract class Mech extends Unidad {
	
	protected Posicion posicionAnterior;
	protected boolean deshacerMovimiento;
	
    protected Mech(int vida, Posicion posicion) {
        super(vida, posicion);
        this.rango = 3;
        boolean deshacerMovimiento = false;
    }

    public void atacarA(Posicion posicion, Tablero tablero) {
        if (this.yaAtaco) {
            System.out.println("Ataque inválido: el Mech ya atacó en este turno.");
            return;
        }
        ejecutarAtaque(posicion, tablero);
        this.yaAtaco = true;
    }

    protected abstract void ejecutarAtaque(Posicion posicion, Tablero tablero);

    /*
    protected void retroceso(Direccion direccion){

        if(!posicionEstaDentroDeTablero){
            return;
        }

        if(noHayNadaIntransitable && noHayOtraUnidad){
            SeMueveEnEsaDireccion;
        }

        else if (hayAlgoIntransitable || hayOtraUnidad){
            RecibeElMas1DeDaño;
        }

    }
    */
    @Override
    public void moverA(Posicion destino, Tablero tablero) {
        this.posicionAnterior = posicion;
        super.moverA(destino, tablero);
    }
    
    public void deshacerMovimiento(Tablero tablero) {
    	if (this.yaAtaco) {
            System.out.println("El Mech ya atacó en este turno. No se puede volver a la posicion anterior");
            return;
        }
    	if (this.deshacerMovimiento) {
    		System.out.println("El Mech ya deshizo un movimiento en este turno");
            return;
    	}
    	tablero.desOcuparCasilla(tablero.buscarPosicionDe(this));
    	tablero.ocuparCasilla(posicionAnterior, this);
    	this.deshacerMovimiento = true;
    	this.yaSeMovio = false;
        this.posicion = posicionAnterior;
    }
    
    @Override
    public void reiniciarTurno() {
    	this.deshacerMovimiento = false;
    	super.reiniciarTurno();
    }

    protected boolean calcularLineaRecta(Posicion objetivo){
        int dFila = Math.abs(objetivo.getFila() - posicion.getFila());
        int dCol = Math.abs(objetivo.getColumna() - posicion.getColumna());

        boolean esLineaRecta = (dFila == 0 && dCol >= 2) || (dCol == 0 && dFila >= 2);
        return esLineaRecta;
    }
}


