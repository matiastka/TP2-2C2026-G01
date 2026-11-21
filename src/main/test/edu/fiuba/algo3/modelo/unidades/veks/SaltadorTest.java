package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.DestinoNoAlcanzableException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SaltadorTest {

    private static final String[] MAPA = {
            "SSSSSSSS", "SSSSSSSS", "SSSSSSSS", "SSSSSSSS",
            "SSSSSSSS", "SSSSSSSS", "SSSSSSSS", "SSSSSSSS"
    };

    @Test
    public void test01ElSaltadorPuedeAtacarUnaCasillaAdyacente() {
        // Arrange
        Tablero tablero = new ConstructorTablero().construir(MAPA);
        Saltador vek = new Saltador();
        vek.colocarseEn(new Posicion(3, 3), tablero);

        // Act & Assert
        assertDoesNotThrow(() -> vek.prepararAtaque(new Posicion(3, 4)));
    }

    @Test
    public void test02ElSaltadorNoPuedeAtacarUnaCasillaNoAdyacente() {
        // Arrange
        Tablero tablero = new ConstructorTablero().construir(MAPA);
        Saltador vek = new Saltador();
        vek.colocarseEn(new Posicion(3, 3), tablero);

        // Act & Assert
        assertThrows(DestinoNoAlcanzableException.class, () -> vek.prepararAtaque(new Posicion(3, 5)));
    }
}