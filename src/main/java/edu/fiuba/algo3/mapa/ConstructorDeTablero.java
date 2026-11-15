package edu.fiuba.algo3.mapa;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.TableroInvalidoException;
import edu.fiuba.algo3.modelo.terrenos.*;

import java.util.HashMap;
import java.util.Map;

public class ConstructorDeTablero {
    private static final Map<Character, FabricaDeTerreno> FABRICAS = Map.of(
            'S', new FabricaDeSuelo(),
            'E', new FabricaDeEdificio(),
            'M', new FabricaDeMontana(),
            'A', new FabricaDeAgua(),
            'C', new FabricaDeAcido()
    );

    public Tablero construir(String[] mapa) {
        Map<Posicion, Casilla> casillas = new HashMap<>();
        for (int fila = 0; fila < mapa.length; fila++) {
            for (int columna = 0; columna < mapa[fila].length(); columna++) {
                Posicion posicion = new Posicion(fila, columna);
                char letra = mapa[fila].charAt(columna);
                casillas.put(posicion, fabricaSegun(letra).crearCasilla(posicion));
            }
        }
        return new Tablero(casillas);
    }

    private FabricaDeTerreno fabricaSegun(char letra) {
        if (!FABRICAS.containsKey(letra)) {
            throw new TableroInvalidoException("Letra desconocida: " + letra);
        }
        return FABRICAS.get(letra);
    }
}
