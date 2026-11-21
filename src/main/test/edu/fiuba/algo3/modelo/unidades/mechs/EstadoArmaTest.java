
package edu.fiuba.algo3.modelo.unidades.mechs;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.excepciones.ArmaSumergidaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EstadoArmaTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";

    @Test
    public void test01UnArmaOperativaPuedeUsarse() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Mech canonMech = new Canon();
        canonMech.colocarseEn(new Posicion(2, 0), tablero);
        EstadoArma estadoArma = new EstadoArmaOperativa();

        // Act & Assert
        assertDoesNotThrow(() -> estadoArma.usar(canonMech, new Posicion(2, 3), tablero));
    }

    @Test
    public void test02UnArmaSumergidaNoPuedeUsarse() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Mech canonMech = new Canon();
        canonMech.colocarseEn(new Posicion(2, 0), tablero);
        EstadoArma estadoArma = new EstadoArmaSumergida();

        // Act & Assert
        assertThrows(ArmaSumergidaException.class, () -> estadoArma.usar(canonMech, new Posicion(2, 3), tablero));
    }
}