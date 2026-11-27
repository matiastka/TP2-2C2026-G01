package edu.fiuba.algo3.modelo.mapa;

import edu.fiuba.algo3.modelo.tablero.RedElectrica;
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
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ConstructorTablero {

    public Tablero construir(List<String> filas) {
        RedElectrica redElectrica = new RedElectrica();
        Map<Character, Supplier<Terreno>> terrenos = Map.of(
                'S', Suelo::new,
                'E', () -> new Edificio(redElectrica),
                'M', Montana::new,
                'A', Agua::new,
                'C', Acido::new
        );
        Map<Posicion, Casilla> casillas = new HashMap<>();
        for (int fila = 0; fila < filas.size(); fila++) {
            for (int columna = 0; columna < filas.get(fila).length(); columna++) {
                Posicion posicion = new Posicion(fila, columna);
                char letra = filas.get(fila).charAt(columna);
                casillas.put(posicion, new Casilla(terrenoSegun(letra, terrenos), posicion));
            }
        }
        return new Tablero(casillas, redElectrica);
    }

    private Terreno terrenoSegun(char letra, Map<Character, Supplier<Terreno>> terrenos) {
        if (!terrenos.containsKey(letra)) {
            throw new TableroInvalidoException("Letra desconocida: " + letra);
        }
        return terrenos.get(letra).get();
    }
}