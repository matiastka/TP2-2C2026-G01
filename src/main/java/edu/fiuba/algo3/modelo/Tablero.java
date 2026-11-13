package edu.fiuba.algo3.modelo;

import java.util.Map;

public class Tablero {
    private static final int CANTIDAD_DE_FILAS = 8;
    private static final int CANTIDAD_DE_COLUMNAS = 8;
    private Casilla[][] casillas;
    private static final Map<Character, FabricaDeTerreno> FABRICAS = Map.of('S', new FabricaDeSuelo(),
            'E', new FabricaDeEdificio(),
            'M', new FabricaDeMontana(),
            'A', new FabricaDeAgua(),
            'C', new FabricaDeAcido()    );

    public Tablero(String[] mapa){
        this.casillas = new Casilla[CANTIDAD_DE_FILAS][CANTIDAD_DE_COLUMNAS];
        for (int fila = 0; fila < CANTIDAD_DE_FILAS; fila++) {
            for (int columna = 0; columna < CANTIDAD_DE_COLUMNAS; columna++) {
                char letra = mapa[fila].charAt(columna);
                casillas[fila][columna] = fabricaSegun(letra).crearCasilla(new Posicion(fila, columna));
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
        return casillas[posicion.getFila()][posicion.getColumna()];
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
        enCasilla(posicion).desOcupar(posicion);
    }

    public boolean estaEnRango(Posicion posicion){
        if (posicion.getFila() < 0 || posicion.getFila() >= CANTIDAD_DE_FILAS){
            return false;
        }

        if (posicion.getColumna() < 0 || posicion.getColumna() >= CANTIDAD_DE_COLUMNAS){
            return false;
        }

        return true;
    }

    public Posicion buscarPosicionDe(Unidad unidadBuscada) { // Revisar
        for (int f = 0; f < CANTIDAD_DE_FILAS; f++) {
            for (int c = 0; c < CANTIDAD_DE_COLUMNAS; c++) {
                Casilla casillaActual = casillas[f][c];

                if (casillaActual.obtenerUnidad() == unidadBuscada) {
                    return new Posicion(f, c);
                }
            }
        }
        return null;
    }

}
