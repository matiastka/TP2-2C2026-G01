package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Movimiento;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public abstract class Vek extends Unidad {

    private EstadoTurnoVek estadoTurno;
    private Direccion direccionAtaque;

    protected Vek(int vida, int rango, Movimiento movimiento) {
        super(vida, rango, movimiento);
        this.estadoTurno = new EstadoVekDisponible();
        this.direccionAtaque = Direccion.NINGUNA;
    }

    @Override 
    public void moverA(Posicion destino, Tablero tablero) {
        this.estadoTurno = this.estadoTurno.mover(this, destino, tablero);
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
            ejecutarAtaque(this.direccionAtaque, tablero);
            this.direccionAtaque = Direccion.NINGUNA;
        }
    }

    @Override
    protected void hundirse() {
        recibirDanio(obtenerVida());
    }

    protected abstract void ejecutarAtaque(Direccion direccion, Tablero tablero);

}
