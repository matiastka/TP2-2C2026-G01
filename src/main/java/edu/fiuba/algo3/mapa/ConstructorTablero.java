package edu.fiuba.algo3.mapa;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.TableroInvalidoException;
import edu.fiuba.algo3.modelo.tablero.Terreno;
import edu.fiuba.algo3.modelo.terrenos.Acido;
import edu.fiuba.algo3.modelo.terrenos.Agua;
import edu.fiuba.algo3.modelo.terrenos.Edificio;
import edu.fiuba.algo3.modelo.terrenos.Montana;
import edu.fiuba.algo3.modelo.terrenos.Suelo;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ConstructorTablero {
    private static final Map<Character, Supplier<Terreno>> TERRENOS = Map.of(
            'S', Suelo::new,
            'E', Edificio::new,
            'M', Montana::new,
            'A', Agua::new,
            'C', Acido::new
    );

    public Tablero construir(String[] mapa) {
        Map<Posicion, Casilla> casillas = new HashMap<>();
        for (int fila = 0; fila < mapa.length; fila++) {
            for (int columna = 0; columna < mapa[fila].length(); columna++) {
                Posicion posicion = new Posicion(fila, columna);
                char letra = mapa[fila].charAt(columna);
                casillas.put(posicion, new Casilla(terrenoSegun(letra), posicion));
            }
        }
        return new Tablero(casillas);
    }

    private Terreno terrenoSegun(char letra) {
        if (!TERRENOS.containsKey(letra)) {
            throw new TableroInvalidoException("Letra desconocida: " + letra);
        }
        return TERRENOS.get(letra).get();
    }
}