package edu.fiuba.algo3.modelo.unidades.ataques.efectos;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Ocupante;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Transito;

public class Empujon implements Transito {

    private final Ocupante empujado;
    private final Tablero tablero;

    public Empujon(Ocupante empujado, Tablero tablero) {
        this.empujado = empujado;
        this.tablero = tablero;
    }

    @Override
    public void atravesar(Casilla casilla) {
        this.empujado.serEmpujadoA(casilla.obtenerPosicion(), this.tablero);
    }

    @Override
    public void chocarConOcupante(Casilla casilla) {
        chocar(casilla);
    }

    @Override
    public void chocarConTerreno(Casilla casilla) {
        chocar(casilla);
    }

    private void chocar(Casilla casilla) {
        this.empujado.recibirDanio(1);
        casilla.recibirDanio(1);
    }
}