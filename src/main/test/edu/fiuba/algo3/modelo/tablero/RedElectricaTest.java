package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RedElectricaTest {

    private static final String RUTA_MAPA_EDIFICIO = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_edificio.txt";

    @Test
    public void test01LaRedElectricaArrancaEnNivel5() {
        // Arrange
        RedElectrica red = new RedElectrica();

        // Act
        int nivel = red.obtenerNivel();

        // Assert
        assertEquals(5, nivel);
    }

    @Test
    public void test02ReducirLaRedLaBajaEn1() {
        // Arrange
        RedElectrica red = new RedElectrica();

        // Act
        red.reducir();

        // Assert
        assertEquals(4, red.obtenerNivel());
    }

    @Test
    public void test03LaRedNoBajaDeCero() {
        // Arrange
        RedElectrica red = new RedElectrica();

        // Act
        for (int i = 0; i < 6; i++) {
            red.reducir();
        }

        // Assert
        assertEquals(0, red.obtenerNivel());
    }

    @Test
    public void test04DaniarUnEdificioDeUnTableroNoAfectaLaRedDeOtroTablero() {
        // Arrange
        Tablero tableroAtacado = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero otroTablero = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);

        // Act
        tableroAtacado.enCasilla(new Posicion(3, 3)).recibirDanio(1);

        // Assert
        assertEquals(5, otroTablero.obtenerRedElectrica());
    }
}