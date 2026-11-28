
package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.excepciones.DestinoNoAlcanzableException;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Avispon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AvisponTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";

    @Test
    public void test01ElAvisponPuedeAtacarUnaCasillaAdyacente() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Avispon vek = new Avispon();
        vek.colocarseEn(new Posicion(3, 3), tablero);

        // Act & Assert
        assertDoesNotThrow(() -> vek.prepararAtaque(new Posicion(3, 4)));
    }

    @Test
    public void test02ElAvisponNoPuedeAtacarUnaCasillaNoAdyacente() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Avispon vek = new Avispon();
        vek.colocarseEn(new Posicion(3, 3), tablero);

        // Act & Assert
        assertThrows(DestinoNoAlcanzableException.class, () -> vek.prepararAtaque(new Posicion(3, 5)));
    }
}