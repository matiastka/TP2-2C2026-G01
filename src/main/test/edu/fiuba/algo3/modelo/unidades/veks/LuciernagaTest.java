
package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.DestinoNoAlcanzableException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LuciernagaTest {

    private static final String[] MAPA = {
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS"
    };

    @Test
    public void test01LuciernagaEmpiezaCon3DeVida() {
        // Arrange
        Luciernaga luciernaga = new Luciernaga();
        int vidaEsperada = 3;

        // Act
        int vidaActual = luciernaga.obtenerVida();

        // Assert
        assertEquals(vidaEsperada, vidaActual);
    }

    @Test
    public void test02LuciernagaTieneRango2() {
        // Arrange
        Luciernaga luciernaga = new Luciernaga();
        int rangoEsperado = 2;

        // Act
        int rangoActual = luciernaga.obtenerRango();

        // Assert
        assertEquals(rangoEsperado, rangoActual);
    }

    @Test
    public void test03LaLuciernagaPuedeAtacarUnaCasillaLejanaEnLineaRecta() {
        // Arrange
        Tablero tablero = new ConstructorTablero().construir(MAPA);
        Luciernaga vek = new Luciernaga();
        vek.colocarseEn(new Posicion(3, 3), tablero);

        // Act & Assert
        assertDoesNotThrow(() -> vek.prepararAtaque(new Posicion(3, 7)));
    }

    @Test
    public void test04LaLuciernagaNoPuedeAtacarUnaCasillaEnDiagonal() {
        // Arrange
        Tablero tablero = new ConstructorTablero().construir(MAPA);
        Luciernaga vek = new Luciernaga();
        vek.colocarseEn(new Posicion(3, 3), tablero);

        // Act & Assert
        assertThrows(DestinoNoAlcanzableException.class, () -> vek.prepararAtaque(new Posicion(4, 4)));
    }

}