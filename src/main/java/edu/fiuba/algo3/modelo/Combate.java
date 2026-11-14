
package edu.fiuba.algo3.modelo;

public class Combate extends Mech {

    public Combate(Posicion posicion){
        super(3, posicion);
    }

    @Override
    protected void ejecutarAtaque(Posicion objetivo, Tablero tablero) {
        if (!posicion.esAdyacente(objetivo)) {
            System.out.println("El objetivo a atacar no es adyacente.\nPor ende no se aplica el danio ni el empuje");
            return;
        }

        if (tablero.enCasilla(objetivo).estaLibre()) {
            System.out.println("La casilla a atacar esta vacia");
            return;
        }

        Direccion direccion = posicion.direccionHacia(objetivo);
        golpearYEmpujar(objetivo, 2, direccion, tablero);
    }

}