package edu.fiuba.algo3.modelo;

public class Artilleria extends Mech {

    public Artilleria() {
        super(2); // Vida según la consigna
    }

    @Override
    protected int obtenerRango() {
        return 3; // Rango de movimiento según la consigna
    }

    @Override
     protected void ejecutarAtaque(Posicion objetivo, Tablero tablero) {
        Posicion origen = tablero.buscarPosicionDe(this);
        if (origen == null) return;

        int dFila = Math.abs(objetivo.getFila() - origen.getFila());
        int dCol = Math.abs(objetivo.getColumna() - origen.getColumna());

        // Debe ser en línea recta (misma fila o columna) y a distancia >= 2
        boolean esLineaRecta = (dFila == 0 && dCol >= 2) || (dCol == 0 && dFila >= 2);
        if (!esLineaRecta) return;

        // 1. Infligir daño a la casilla objetivo si hay una unidad
        Casilla casillaCentro = tablero.enCasilla(objetivo);
        if (!casillaCentro.estaLibre()) {
            casillaCentro.obtenerUnidad().recibirDanio(1);
        }

        // 2. Empujar unidades adyacentes hacia afuera (Norte, Sur, Este, Oeste)
        int[][] direcciones = {
            {-1, 0}, // Norte
            {1, 0},  // Sur
            {0, 1},  // Este
            {0, -1}  // Oeste
        };

        for (int[] dir : direcciones) {
            Posicion posAdyacente = new Posicion(objetivo.getFila() + dir[0], objetivo.getColumna() + dir[1]);
            
            if (tablero.estaEnRango(posAdyacente)) {
                Casilla casillaAdyacente = tablero.enCasilla(posAdyacente);
                
                if (!casillaAdyacente.estaLibre()) {
                    Unidad unidadEmpujada = casillaAdyacente.obtenerUnidad();
                    Posicion posDestinoEmpuje = new Posicion(posAdyacente.getFila() + dir[0], posAdyacente.getColumna() + dir[1]);

                    if (tablero.estaEnRango(posDestinoEmpuje) && tablero.enCasilla(posDestinoEmpuje).estaLibre()) {
                        tablero.desOcuparCasilla(posAdyacente);
                        tablero.ocuparCasilla(posDestinoEmpuje, unidadEmpujada);
                    }
                }
            }
        }
    }
}
