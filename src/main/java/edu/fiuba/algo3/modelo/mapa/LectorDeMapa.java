package edu.fiuba.algo3.modelo.mapa;

import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.TableroInvalidoException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class LectorDeMapa {

    public Tablero leer(String ruta) {
        return new ConstructorTablero().construir(leerFilas(ruta));
    }

    private List<String> leerFilas(String ruta) {
        try {
            return Files.readAllLines(Paths.get(ruta)).stream()
                    .map(String::trim)
                    .filter(linea -> !linea.isEmpty())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new TableroInvalidoException("No se pudo leer el mapa: " + ruta);
        }
    }
}