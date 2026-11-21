
package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.unidades.excepciones.CaminoBloqueadoException;
import edu.fiuba.algo3.modelo.unidades.movimientos.MovimientoTerrestre;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MontanaTest {

    @Test
    public void test01UnaUnidadTerrestreNoPuedeAtravesarMontana() {
        // Arrange
        Casilla casilla = new Casilla(new Montana(), new Posicion(0, 0));

        // Act & Assert
        assertThrows(CaminoBloqueadoException.class, () -> casilla.recibirTransito(new MovimientoTerrestre()));
    }
}