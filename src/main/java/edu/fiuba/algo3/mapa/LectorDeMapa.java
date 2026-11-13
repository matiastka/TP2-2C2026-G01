package edu.fiuba.algo3.mapa;

import edu.fiuba.algo3.modelo.TableroInvalidoException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LectorDeMapa {

    public String[] leer(String ruta) {
        try {
            return Files.readAllLines(Paths.get(ruta)).stream()
                    .map(String::trim)
                    .filter(linea -> !linea.isEmpty())
                    .toArray(String[]::new);
        } catch (IOException e) {
            throw new TableroInvalidoException("No se pudo leer el mapa: " + ruta);
        }
    }
}
