package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.MovimientoTerrestre;

public class Luciernaga extends Vek {
    public Luciernaga(){
        super(3, 2, new MovimientoTerrestre());
    }
@Override
    protected void ejecutarAtaque(Direccion direccion, Tablero tablero) {
        Posicion actual = obtenerPosicion().siguienteEn(direccion);

        while (tablero.estaEnRango(actual) && tablero.enCasilla(actual).esTransitable()) {
            if (!tablero.enCasilla(actual).estaLibre()) {
                tablero.enCasilla(actual).obtenerOcupante().recibirDanio(1);
                return;
            }
            actual = actual.siguienteEn(direccion);
        }
    }
}
