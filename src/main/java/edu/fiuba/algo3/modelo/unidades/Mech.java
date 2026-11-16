package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Ocupante;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public abstract class Mech extends Unidad {

    private Posicion posicionAnterior;
    private EstadoArma estadoArma;
    private EstadoTurnoMech estadoTurno;

    protected Mech(int vida) {
        super(vida, 3, new MovimientoTerrestre());
        this.estadoArma = new EstadoArmaOperativa();
        this.estadoTurno = new EstadoDisponible();
    }

    public void atacarA(Posicion posicion, Tablero tablero) {
        this.estadoTurno = this.estadoTurno.atacar(this, posicion, tablero);
    }

    boolean ejecutarAtaqueValidado(Posicion posicion, Tablero tablero) {
        return estadoArma.usar(this, posicion, tablero);
    }

    protected abstract void ejecutarAtaque(Posicion posicion, Tablero tablero);

    protected final boolean hayUnidadEn(Posicion posicion, Tablero tablero) {
        return tablero.estaEnRango(posicion) && !tablero.enCasilla(posicion).estaLibre();
    }

    protected final void aplicarDanioEn(Posicion posicion, int danio, Tablero tablero) {
        if (hayUnidadEn(posicion, tablero)) {
            tablero.enCasilla(posicion).obtenerOcupante().recibirDanio(danio);
        }
    }

    protected final void empujarUnidadDesde(Posicion origen, Direccion direccion, Tablero tablero) {
        if (direccion == Direccion.NINGUNA || !hayUnidadEn(origen, tablero)) {
            return;
        }

        Posicion destino = origen.siguienteEn(direccion);
        Ocupante ocupante = tablero.enCasilla(origen).obtenerOcupante();

        if (!tablero.estaEnRango(destino) || !tablero.enCasilla(destino).estaLibre() || !tablero.enCasilla(destino).esTransitable()) {
            ocupante.recibirDanio(1);
            return;
        }

        ocupante.serEmpujadoA(destino, tablero);
    }

    protected final void golpearYEmpujar(Posicion objetivo, int danio, Direccion direccion, Tablero tablero) {
        if (!hayUnidadEn(objetivo, tablero)) {
            return;
        }

        Ocupante ocupante = tablero.enCasilla(objetivo).obtenerOcupante();
        ocupante.recibirDanio(danio);

        if (direccion != Direccion.NINGUNA) {
            empujarUnidadDesde(objetivo, direccion, tablero);
        }
    }
 
    @Override
    public void moverA(Posicion destino, Tablero tablero) {
        Posicion posibleAnterior = this.obtenerPosicion();
        this.estadoTurno = this.estadoTurno.mover(this, destino, tablero);
        if (!this.obtenerPosicion().equals(posibleAnterior)) {
            this.posicionAnterior = posibleAnterior;
        }
    }

    public void reiniciarTurno(){
        this.estadoTurno = new EstadoDisponible();
    }

    public void deshacerMovimiento(Tablero tablero) {
        this.estadoTurno = this.estadoTurno.deshacerMovimiento(this, tablero);
    }

    void ejecutarDeshacerMovimiento(Tablero tablero) {
        reubicarEn(posicionAnterior, tablero);
    }

    protected boolean calcularLineaRecta(Posicion objetivo) {
        return obtenerPosicion().estaEnLineaRectaA(objetivo, 2);
    }

    @Override
    protected void reubicarEn(Posicion destino, Tablero tablero) {
        this.estadoArma = new EstadoArmaOperativa();
        super.reubicarEn(destino, tablero);
    }

    @Override
    protected void hundirse(){
        this.estadoArma = new EstadoArmaSumergida();
    }
}
