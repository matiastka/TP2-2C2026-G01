package edu.fiuba.algo3.modelo.unidades.veks;

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
        this.estadoTurno = new EstadoVekDisponible(); //[cite: 27]
        this.direccionAtaque = Direccion.NINGUNA; //[cite: 27]
        this.ataque = ataque;
    }

    @Override
    public void moverA(Posicion destino, Tablero tablero) {
        this.estadoTurno = this.estadoTurno.mover(this, destino, tablero); //[cite: 27]
    }

    boolean ejecutarMovimientoVek(Posicion destino, Tablero tablero) {
        return super.ejecutarMovimiento(destino, tablero); //[cite: 27]
    }

    public void prepararAtaque(Direccion direccion) {
        this.estadoTurno = this.estadoTurno.prepararAtaque(this, direccion); //[cite: 27]
    }

    public void resolverAtaque(Tablero tablero) {
        this.estadoTurno = this.estadoTurno.resolverAtaque(this, tablero); //[cite: 27]
    }

    void guardarDireccionAtaque(Direccion direccion) {
        this.direccionAtaque = direccion; //[cite: 27]
    }

    void ejecutarAtaquePreparado(Tablero tablero) {
        if (this.direccionAtaque != Direccion.NINGUNA) {
            this.ataque.ejecutar(obtenerPosicion(), this.direccionAtaque, tablero);
            this.direccionAtaque = Direccion.NINGUNA; //[cite: 27]
        }
    }

    @Override
    protected void hundirse() {
        recibirDanio(obtenerVida()); //[cite: 27]
    }
}