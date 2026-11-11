package edu.fiuba.algo3.modelo;

public class Artilleria extends Mech {

    public Artilleria(Posicion posicion) {
        super(2, posicion); // Vida según la consigna
    }

    @Override
     protected void ejecutarAtaque(Posicion objetivo, Tablero tablero) {
        if (!calcularLineaRecta(objetivo)){
            return;
        }

        Casilla casillaCentro = tablero.enCasilla(objetivo);
        if (!casillaCentro.estaLibre()) {
            casillaCentro.obtenerUnidad().recibirDanio(1);
        }

        for (Direccion direccion : Direccion.cardinales()) {
            Posicion posAdyacente = objetivo.siguienteEn(direccion);
            
            if (tablero.estaEnRango(posAdyacente)) {
                Casilla casillaAdyacente = tablero.enCasilla(posAdyacente);
                
                if (!casillaAdyacente.estaLibre()) {
                    Unidad unidadEmpujada = casillaAdyacente.obtenerUnidad();
                    Posicion posicionDestinoEmpuje = posAdyacente.siguienteEn(direccion);

                    if (tablero.estaEnRango(posicionDestinoEmpuje) && tablero.enCasilla(posicionDestinoEmpuje).estaLibre()) {
                        tablero.desOcuparCasilla(posAdyacente);
                        tablero.ocuparCasilla(posicionDestinoEmpuje, unidadEmpujada);
                    }
                }
            }
        }
    }
}
