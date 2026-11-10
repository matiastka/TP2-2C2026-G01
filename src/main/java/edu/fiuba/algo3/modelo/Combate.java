
package edu.fiuba.algo3.modelo;

public class Combate extends Mech {

    public Combate(){
        super(3);
    }

    @Override
    protected int obtenerRango() {
        return 3;
    }

    @Override 
    protected void ejecutarAtaque(Posicion objetivo, Tablero tablero){
        Posicion origen = tablero.buscarPosicionDe(this);

        if (origen.distanciaA(objetivo) == 1) { // Toda la logica de empujar, golpear creo que se debe delegar con Polimorfismo. Esto es PROVISIONAL! (Mati)
            if (tablero.enCasilla(objetivo).estaLibre()){ // Incluso tiene sentido  que se ataque una casilla vacia. No deberia lanzar una excepcion ni fallar
                System.out.println("La casilla a atacar esta vacia");
            }
            else{
                // Toda la parte de calcular el movimiento de empuje se deberia hacer usando delegacion y/o polimorfismo, en un proximo refactor
                // incluye a origen
                Unidad golpeada = tablero.enCasilla(objetivo).obtenerUnidad();
                golpeada.recibirDanio(2);

                int dirFila = Integer.compare(objetivo.getFila(), origen.getFila());
                int dirColumna = Integer.compare(objetivo.getColumna(), origen.getColumna());
                Posicion destinoEmpuje = new Posicion(objetivo.getFila() + dirFila, objetivo.getColumna() + dirColumna);

                golpeada.moverA(destinoEmpuje, tablero);
            }

        }
        else {
            System.out.println("El objetivo a atacar no es adyacente.\nPor ende no se aplica el danio ni el empuje");
        }
    }

}