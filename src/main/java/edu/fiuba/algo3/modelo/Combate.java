
package edu.fiuba.algo3.modelo;

public class Combate extends Mech {

    public Combate(){
        super(3);
    }

    @Override
    protected int obtenerRango() {
        return 3;
    }

    public void atacarA(Posicion objetivo, Tablero tablero){
        Posicion origen = tablero.buscarPosicionDe(this);

        if (origen.distanciaA(objetivo) == 1) { // Esto tambien no deberia estar con el refactor
            Unidad golpeada = tablero.enCasilla(objetivo.getFila(), objetivo.getColumna()).obtenerUnidad();
            golpeada.recibirDanio(2);

            // Toda la parte de calcular el movimiento de empuje se deberia hacer usando delegacion y/o polimorfismo, en un proximo refactor
            // incluye a origen
            int dirFila = Integer.compare(objetivo.getFila(), origen.getFila());
            int dirColumna = Integer.compare(objetivo.getColumna(), origen.getColumna());
            Posicion destinoEmpuje = new Posicion(objetivo.getFila() + dirFila, objetivo.getColumna() + dirColumna);

            golpeada.moverA(destinoEmpuje, tablero);
        }
        else {
            System.out.print("El objetivo a atacar no es adyacente.\nPor ende no se aplico el daño ni el empuje");
        }
    }

}