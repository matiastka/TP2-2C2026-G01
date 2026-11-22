package edu.fiuba.algo3.modelo.unidades.mechs;

import edu.fiuba.algo3.modelo.tablero.BuscadorDeObjetivos;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.EstadoSalud;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.movimientos.MovimientoTerrestre;

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

    public void atacarA(Posicion posicion, Tablero tablero) {
        this.estadoTurno = this.estadoTurno.atacar(this, posicion, tablero);
    }

    void ejecutarAtaqueValidado(Posicion posicion, Tablero tablero) {
        estadoArma.usar(this, posicion, tablero);
    }

    protected abstract void ejecutarAtaque(Posicion posicion, Tablero tablero);

    @Override
    public void moverA(Posicion destino, Tablero tablero) {
        Posicion posibleAnterior = this.obtenerPosicion();
        EstadoSalud posibleSaludAnterior = this.obtenerEstadoSalud();
        EstadoArma posibleEstadoArmaAnterior = this.estadoArma;

        this.estadoTurno = this.estadoTurno.mover(this, destino, tablero);
        this.posicionAnterior = posibleAnterior;
        this.estadoSaludAnterior = posibleSaludAnterior;
        this.estadoArmaAnterior = posibleEstadoArmaAnterior;
    }

    public void reiniciarTurno() {
        this.estadoTurno = new EstadoDisponible();
    }

    public String obtenerEstadoArma() {
        return this.estadoArma.obtenerDescripcion();
    }

    public void deshacerMovimiento(Tablero tablero) {
        this.estadoTurno = this.estadoTurno.deshacerMovimiento(this, tablero);
    }

    void ejecutarDeshacerMovimiento(Tablero tablero) {
        tablero.enCasilla(this.posicionAnterior).recibirTransito(new MovimientoTerrestre());
        this.establecerEstadoSalud(this.estadoSaludAnterior);
        this.estadoArma = this.estadoArmaAnterior;
        notificarObservadores();
        reubicarSinEfectoEn(posicionAnterior, tablero);
    }

    @Override
    public void hundirse() {
        this.estadoArma = new EstadoArmaSumergida();
        notificarObservadores();
    }

    @Override
    public void salirDelAgua() {
        this.estadoArma = new EstadoArmaOperativa();
        notificarObservadores();
    }

    private EstadoSalud estadoSaludAnterior;

    @Override
    public void ofrecerComoObjetivo(BuscadorDeObjetivos buscador, Casilla casilla) {
        buscador.encontrarMech(casilla);
    }

    void ejecutarMovimientoMech(Posicion destino, Tablero tablero) {
        super.ejecutarMovimiento(destino, tablero);
    }

}