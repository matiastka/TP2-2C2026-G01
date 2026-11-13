package edu.fiuba.algo3.modelo;

import java.util.HashMap;
import java.util.Map;

public class Tablero {
    private static final int CANTIDAD_DE_FILAS = 8;
    private static final int CANTIDAD_DE_COLUMNAS = 8;
    private Map<Posicion, Casilla> casillas = new HashMap<>();
    private static final Map<Character, FabricaDeTerreno> FABRICAS = Map.of('S', new FabricaDeSuelo(),
            'E', new FabricaDeEdificio(),
            'M', new FabricaDeMontana(),
            'A', new FabricaDeAgua(),
            'C', new FabricaDeAcido()    );

    public Tablero(String[] mapa){
        for (int fila = 0; fila < CANTIDAD_DE_FILAS; fila++) {
            for (int columna = 0; columna < CANTIDAD_DE_COLUMNAS; columna++) {
                char letra = mapa[fila].charAt(columna);
                Posicion posicion = new Posicion(fila, columna);
                casillas.put(posicion, fabricaSegun(letra).crearCasilla(posicion));
            }
        }
    }

    private FabricaDeTerreno fabricaSegun(char letra) {
        if (!FABRICAS.containsKey(letra)) {
            throw new TableroInvalidoException("Letra desconocida: " + letra);
        }
        return FABRICAS.get(letra);
    }

    public Casilla enCasilla(Posicion posicion){
        return casillas.get(posicion);
    }

    public int cantidadFilas(){
        return CANTIDAD_DE_FILAS;
    }

    public int cantidadColumnas(){
        return CANTIDAD_DE_COLUMNAS;
    }

    public void ocuparCasilla(Posicion posicion, Unidad unidad){
        enCasilla(posicion).ocupar(unidad);
    }

    public void desOcuparCasilla(Posicion posicion){
        enCasilla(posicion).desOcupar();
    }

    public boolean estaEnRango(Posicion posicion){
        return casillas.containsKey(posicion);
    }

}
