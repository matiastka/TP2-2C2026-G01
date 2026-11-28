package edu.fiuba.algo3.modelo.mapa;

import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.TableroInvalidoException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LectorDeMapa {

    public Tablero leer(String ruta) {
        return new ConstructorTablero().construir(leerFilas(ruta));
    }

    private List<String> leerFilas(String ruta) {
        try {
            List<String> filas = new ArrayList<>();
            for (String linea : Files.readAllLines(Paths.get(ruta))) {
                String fila = linea.trim();
                if (!fila.isEmpty()) {
                    filas.add(fila);
                }
            }
            return filas;
        } catch (IOException e) {
            throw new TableroInvalidoException("No se pudo leer el mapa: " + ruta);
        }
    }
}