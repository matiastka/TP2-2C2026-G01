package edu.fiuba.algo3.modelo.partida;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;

public class Escuadron {

    private final List<Mech> mechs;

    public Escuadron(List<Mech> mechs) {
        this.mechs = new ArrayList<>(mechs);
    }

    public void reiniciarTurno() {
        for (Mech mech : this.mechs) {
            mech.reiniciarTurno();
        }
    }

    public void retirarDestruidos(Tablero tablero) {
        for (Mech mech : List.copyOf(this.mechs)) {
            if (mech.retirarseSiEstaDestruida(tablero)) {
                this.mechs.remove(mech);
            }
        }
    }

    public boolean fueDestruido() {
        return this.mechs.isEmpty();
    }
}