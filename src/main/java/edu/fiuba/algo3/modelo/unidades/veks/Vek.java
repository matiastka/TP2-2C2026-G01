package edu.fiuba.algo3.modelo.unidades.veks;

import java.util.Optional;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Ataque;
import edu.fiuba.algo3.modelo.unidades.Movimiento;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public abstract class Vek extends Unidad {

    private EstadoTurnoVek estadoTurno;
    private Direccion direccionAtaque;
    private final Ataque ataque;

    protected Vek(int vida, int rango, Movimiento movimiento, Ataque ataque) {
        super(vida, rango, movimiento);
        this.estadoTurno = new EstadoVekDisponible(); 
        this.direccionAtaque = Direccion.NINGUNA; 
        this.ataque = ataque;
    }

    @Override
    public void moverA(Posicion destino, Tablero tablero) {
        this.estadoTurno = this.estadoTurno.mover(this, destino, tablero); 
    }

    public Optional<Casilla> simularImpactoDesde(Posicion origen, Direccion direccion, Tablero tablero) {
        return this.ataque.casillaImpactadaDesde(origen, direccion, tablero);
    }

    boolean ejecutarMovimientoVek(Posicion destino, Tablero tablero) {
        return super.ejecutarMovimiento(destino, tablero); 
    }

    public void prepararAtaque(Direccion direccion) {
        this.estadoTurno = this.estadoTurno.prepararAtaque(this, direccion); 
    }

    public void resolverAtaque(Tablero tablero) {
        this.estadoTurno = this.estadoTurno.resolverAtaque(this, tablero); 
    }

    void guardarDireccionAtaque(Direccion direccion) {
        this.direccionAtaque = direccion; 
    }

    void ejecutarAtaquePreparado(Tablero tablero) {
        if (this.direccionAtaque != Direccion.NINGUNA) {
            this.ataque.ejecutar(obtenerPosicion(), this.direccionAtaque, tablero);
            this.direccionAtaque = Direccion.NINGUNA; 
        }
    }

    @Override
    protected void hundirse() {
        recibirDanio(obtenerVida()); 
    }
    
    public AtaqueAnunciado generarAtaqueAnunciado() {
        return new AtaqueAnunciado(this);
    }
}