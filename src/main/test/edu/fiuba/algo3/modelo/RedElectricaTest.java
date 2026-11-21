package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RedElectricaTest {

    @Test
    public void test01LaRedElectricaArrancaEnNivel5() {
        RedElectrica red = new RedElectrica();

        assertEquals(5, red.obtenerNivel());
    }

    @Test
    public void test02ReducirLaRedLaBajaEn1() {
        RedElectrica red = new RedElectrica();

        red.reducir();

        assertEquals(4, red.obtenerNivel());
    }

    @Test
    public void test03LaRedNoBajaDeCero() {
        RedElectrica red = new RedElectrica();
        for (int i = 0; i < 6; i++) {
            red.reducir();
        }

        assertEquals(0, red.obtenerNivel());
    }

    @Test
    public void test04DaniarUnEdificioDeUnTableroNoAfectaLaRedDeOtroTablero() {
        String[] mapa = {"E"};
        Tablero tableroAtacado = new ConstructorTablero().construir(mapa);
        Tablero otroTablero = new ConstructorTablero().construir(mapa);

        tableroAtacado.enCasilla(new Posicion(0, 0)).recibirDanio(1);

        assertEquals(5, otroTablero.obtenerRedElectrica());
    }
}

