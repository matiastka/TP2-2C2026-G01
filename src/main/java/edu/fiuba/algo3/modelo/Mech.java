package edu.fiuba.algo3.modelo;

public abstract class Mech extends Unidad {

    protected Posicion posicionAnterior;

    protected Mech(int vida, Posicion posicion) {
        super(vida, posicion);
        this.rango = 3;
    }

    public void atacarA(Posicion posicion, Tablero tablero) {
        this.estadoTurno = this.estadoTurno.atacar(this, posicion, tablero);
    }

    boolean ejecutarAtaqueValidado(Posicion posicion, Tablero tablero) {
        ejecutarAtaque(posicion, tablero);
        return true;
    }

    protected abstract void ejecutarAtaque(Posicion posicion, Tablero tablero);

    protected final boolean hayUnidadEn(Posicion posicion, Tablero tablero) {
        return tablero.estaEnRango(posicion) && !tablero.enCasilla(posicion).estaLibre();
    }

    protected final void aplicarDanioEn(Posicion posicion, int danio, Tablero tablero) {
        if (hayUnidadEn(posicion, tablero)) {
            tablero.enCasilla(posicion).obtenerUnidad().recibirDanio(danio);
        }
    }

    protected final void empujarUnidadDesde(Posicion origen, Direccion direccion, Tablero tablero) {
        if (direccion == Direccion.NINGUNA || !hayUnidadEn(origen, tablero)) {
            return;
        }

        Posicion destino = origen.siguienteEn(direccion);
        if (!tablero.estaEnRango(destino) || !tablero.enCasilla(destino).estaLibre()) {
            return;
        }

        Unidad unidad = tablero.enCasilla(origen).obtenerUnidad();
        unidad.reubicarEn(destino, tablero);
    }

    protected final void golpearYEmpujar(Posicion objetivo, int danio, Direccion direccion, Tablero tablero) {
        if (!hayUnidadEn(objetivo, tablero)) {
            return;
        }

        Unidad unidad = tablero.enCasilla(objetivo).obtenerUnidad();
        unidad.recibirDanio(danio);

        if (direccion != Direccion.NINGUNA) {
            empujarUnidadDesde(objetivo, direccion, tablero);
        }
    }

    @Override
    public void moverA(Posicion destino, Tablero tablero) {
        Posicion posibleAnterior = this.posicion;
        super.moverA(destino, tablero);
        if (!this.posicion.equals(posibleAnterior)) {
            this.posicionAnterior = posibleAnterior;
        }
    }

    public void deshacerMovimiento(Tablero tablero) {
        this.estadoTurno = this.estadoTurno.deshacerMovimiento(this, tablero);
    }

    void ejecutarDeshacerMovimiento(Tablero tablero) {
        reubicarEn(posicionAnterior, tablero);
    }

    protected boolean calcularLineaRecta(Posicion objetivo) {
        return posicion.estaEnLineaRectaA(objetivo, 2);
    }
}
