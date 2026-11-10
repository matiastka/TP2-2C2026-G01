package edu.fiuba.algo3.modelo;

public abstract class Mech extends Unidad {
	
	protected Posicion posicionAnterior;
	protected boolean deshacerMovimiento;
	
    protected Mech(int vida) {
        super(vida);
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
    
    @Override
    public void moverA(Posicion destino, Tablero tablero) {
        this.posicionAnterior = tablero.buscarPosicionDe(this); 
        super.moverA(destino, tablero);
    }
    
    @Override
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
    }
    
    @Override
    public void reiniciarTurno() {
    	this.deshacerMovimiento = false;
    	super.reiniciarTurno();
    }
}


