package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public abstract class Mech extends Unidad {

    private Posicion posicionAnterior;
    private EstadoArma estadoArma;
    private EstadoArma estadoArmaAnterior;
    private EstadoTurnoMech estadoTurno;

    protected Mech(int vida) {
        super(vida, 3, new MovimientoTerrestre());
        this.estadoArma = new EstadoArmaOperativa();
        this.estadoTurno = new EstadoDisponible();
    }

    public ResultadoAccion<Void> atacarA(Posicion posicion, Tablero tablero) {
        return this.estadoTurno.atacar(this, posicion, tablero).map(nuevoEstado -> {
            this.estadoTurno = nuevoEstado;
            return null;
        });
    }

    ResultadoAccion<Void> ejecutarAtaqueValidado(Posicion posicion, Tablero tablero) {
        return estadoArma.usar(this, posicion, tablero);
    }

    protected abstract ResultadoAccion<Void> ejecutarAtaque(Posicion posicion, Tablero tablero);

    @Override
    public ResultadoAccion<Void> moverA(Posicion destino, Tablero tablero) {
        Posicion posibleAnterior = this.obtenerPosicion();
        EstadoSalud posibleSaludAnterior = this.obtenerEstadoSalud();
        EstadoArma posibleEstadoArmaAnterior = this.estadoArma;

        return this.estadoTurno.mover(this, destino, tablero).map(nuevoEstado -> {
            this.estadoTurno = nuevoEstado;
            if (!this.obtenerPosicion().equals(posibleAnterior)) {
                this.posicionAnterior = posibleAnterior;
                this.estadoSaludAnterior = posibleSaludAnterior;
                this.estadoArmaAnterior = posibleEstadoArmaAnterior;
            }
            return null;
        });
    }

    public void reiniciarTurno() {
        this.estadoTurno = new EstadoDisponible();
    }

    public ResultadoAccion<Void> deshacerMovimiento(Tablero tablero) {
        return this.estadoTurno.deshacerMovimiento(this, tablero).map(nuevoEstado -> {
            this.estadoTurno = nuevoEstado;
            return null;
        });
    }

    void ejecutarDeshacerMovimiento(Tablero tablero) {
        this.establecerEstadoSalud(this.estadoSaludAnterior);
        this.estadoArma = this.estadoArmaAnterior;
        reubicarSinEfectoEn(posicionAnterior, tablero);
    }

    @Override
    protected void hundirse() {
        this.estadoArma = new EstadoArmaSumergida();
    }

    @Override
    public void salirDelAgua() {
        this.estadoArma = new EstadoArmaOperativa();
    }

    private EstadoSalud estadoSaludAnterior;

    @Override
    public boolean esMech() {
        return true;
    }
}