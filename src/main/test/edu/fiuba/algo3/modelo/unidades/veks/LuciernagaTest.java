
package edu.fiuba.algo3.modelo.unidades.veks;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.excepciones.DestinoNoAlcanzableException;
import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Luciernaga;

public class LuciernagaTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";

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
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Luciernaga vek = new Luciernaga();
        vek.colocarseEn(new Posicion(3, 3), tablero);

        // Act & Assert
        assertDoesNotThrow(() -> vek.prepararAtaque(new Posicion(3, 7)));
    }

    @Test
    public void test04LaLuciernagaNoPuedeAtacarUnaCasillaEnDiagonal() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Luciernaga vek = new Luciernaga();
        vek.colocarseEn(new Posicion(3, 3), tablero);

        // Act & Assert
        assertThrows(DestinoNoAlcanzableException.class, () -> vek.prepararAtaque(new Posicion(4, 4)));
    }
}