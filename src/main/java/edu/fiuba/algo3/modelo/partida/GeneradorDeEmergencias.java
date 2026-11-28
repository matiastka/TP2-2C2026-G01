package edu.fiuba.algo3.modelo.partida;

import java.util.List;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class GeneradorDeEmergencias {

    private static final List<Integer> ORDEN_DE_FILAS = List.of(0, 7, 1, 6, 2, 5, 3, 4);

    private final FabricaDeVeks fabrica;
    private final List<Integer> filas;

    public GeneradorDeEmergencias() {
        this(new FabricaDeVeks(), ORDEN_DE_FILAS);
    }

    public GeneradorDeEmergencias(FabricaDeVeks fabrica, List<Integer> filas) {
        this.fabrica = fabrica;
        this.filas = List.copyOf(filas);
    }

    public void anunciarEmergencias(int numeroDeTurno, Tablero tablero) {
        CasillasParaEmergencia candidatas = new CasillasParaEmergencia();
        for (int fila : this.filas) {
            for (int columna = 0; columna < tablero.cantidadColumnas(); columna++) {
                tablero.ofrecerParaEmergencia(new Posicion(fila, columna), candidatas);
            }
        }
        for (Posicion posicion : candidatas.primeras(cantidadParaElTurno(numeroDeTurno))) {
            tablero.anunciarEmergencia(posicion, this.fabrica.crearSiguiente());
        }
    }

    private int cantidadParaElTurno(int numeroDeTurno) {
        if (numeroDeTurno % 2 == 0) {
            return 2;
        }
        return 1;
    }
}