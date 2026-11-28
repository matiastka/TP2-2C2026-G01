package edu.fiuba.algo3.modelo.unidades.mechs;

import edu.fiuba.algo3.modelo.excepciones.AccionInvalidaException;
import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.estados.EstadoSalud;
import edu.fiuba.algo3.modelo.unidades.mechs.estado.arma.EstadoArma;
import edu.fiuba.algo3.modelo.unidades.mechs.estado.arma.EstadoArmaOperativa;
import edu.fiuba.algo3.modelo.unidades.mechs.estado.arma.EstadoArmaSumergida;
import edu.fiuba.algo3.modelo.unidades.mechs.estado.turno.EstadoDisponible;
import edu.fiuba.algo3.modelo.unidades.mechs.estado.turno.EstadoTurnoMech;
import edu.fiuba.algo3.modelo.unidades.movimientos.MovimientoTerrestre;

import java.util.List;
import java.util.Optional;

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

    public void ejecutarAtaqueValidado(Posicion posicion, Tablero tablero) {
        estadoArma.usar(this, posicion, tablero);
    }

    public abstract void ejecutarAtaque(Posicion posicion, Tablero tablero);

    protected abstract void validarObjetivo(Posicion objetivo, Tablero tablero);

    public void agregarSiPuedeAtacarA(Posicion objetivo, List<Posicion> objetivos, Tablero tablero) {
        try {
            validarObjetivo(objetivo, tablero);
        } catch (AccionInvalidaException e) {
            return;
        }
        objetivos.add(objetivo);
    }

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

    @Override
    public void entregarseA(ReceptorDeOcupante receptor) {
        receptor.recibirMech(this);
    }

    public void deshacerMovimiento(Tablero tablero) {
        this.estadoTurno = this.estadoTurno.deshacerMovimiento(this, tablero);
    }

    public void ejecutarDeshacerMovimiento(Tablero tablero) {
        tablero.recibirTransitoEn(this.posicionAnterior,new MovimientoTerrestre());
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

     public void ejecutarMovimientoMech(Posicion destino, Tablero tablero) {
        super.ejecutarMovimiento(destino, tablero);
    }

}