
package edu.fiuba.algo3.modelo;

public class Canon extends Mech {

    public Canon() {
        super(3);
    }

    @Override
    public int obtenerRango() {
        return 3;
    }

    @Override
    public void atacarA(Posicion objetivo, Tablero tablero) {
        Posicion origen = tablero.buscarPosicionDe(this);
        if (origen == null) return;

        int cFila = Integer.compare(objetivo.getFila(), origen.getFila());
        int cCol = Integer.compare(objetivo.getColumna(), origen.getColumna());

        if (cFila != 0 && cCol != 0) return;
        if (cFila == 0 && cCol == 0) return;

        int filaActual = origen.getFila() + cFila;
        int colActual = origen.getColumna() + cCol;

        while (true) {
            Posicion posActual = new Posicion(filaActual, colActual);

            if (!tablero.estaEnRango(posActual)) {
                break;
            }

            Casilla casillaActual = tablero.enCasilla(posActual);

            if (!casillaActual.estaLibre() || !casillaActual.esTransitable()) {
                Unidad unidadImpactada = casillaActual.obtenerUnidad();
                
                if (unidadImpactada != null) {
                    unidadImpactada.recibirDanio(1);

                    Posicion posDestinoEmpuje = new Posicion(filaActual + cFila, colActual + cCol);
                    
                    if (tablero.estaEnRango(posDestinoEmpuje)) {
                        tablero.desOcuparCasilla(posActual);
                        tablero.ocuparCasilla(posDestinoEmpuje, unidadImpactada);
                    }
                }
                break;
            }

            filaActual += cFila;
            colActual += cCol;
        }
    }
}