package edu.fiuba.algo3.modelo;

public class Tablero {
    private int fila;
    private int columna;
    private Casilla[][] casillas;

    public Tablero(String[] mapa){
        this.columna = 8;
        this.fila = 8;
        this.casillas = new Casilla[fila][columna];
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                char letra = mapa[fila].charAt(columna);
                casillas[fila][columna] = new Casilla(terrenoSegun(letra));
            }
        }
    }

    private Terreno terrenoSegun(char letra){
        switch(letra){
            case 'S':
                return new Suelo();
            case 'E':
                return new Edificio();
            case 'M':
                return new Montana();
            case 'A':
                return new Agua();
            case 'C':
                return new Acido();
            default:
                throw new TableroInvalidoException("Letra Desconocida" + letra);
        }

    }

    public Casilla enCasilla(int x, int y){
        return casillas[x][y];
    }

    public int cantidadFilas(){
        return fila;
    }

    public int cantidadColumnas(){
        return columna;
    }

    public void ocuparCasilla(int x, int y, Unidad unidad){
        casillas[x][y].ocupar(unidad);
    }

    public void desOcuparCasilla(int x, int y){
        casillas[x][y].desOcupar();
    }

    public boolean estaEnRango(int x, int y){
        if (x < 0){
            return false;
        } else if (x > 8) {
            return false;
        }
        else if(y < 0){
            return false;
        }
        else if (y > 8){
            return false;
        }
        else{
            return true;
        }
    }

}
