
package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.unidades.MovimientoTerrestre;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AcidoTest {

    @Test
    public void test01UnaUnidadTerrestrePuedeAtravesarAcido() {
        // Arrange
        Casilla casilla = new Casilla(new Acido(), new Posicion(0, 0));

        // Act & Assert
        assertDoesNotThrow(() -> casilla.recibirTransito(new MovimientoTerrestre()));
    }
}