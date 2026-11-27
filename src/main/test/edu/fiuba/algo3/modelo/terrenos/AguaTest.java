
package edu.fiuba.algo3.modelo.terrenos;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import edu.fiuba.algo3.modelo.unidades.movimientos.MovimientoTerrestre;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Luciernaga;

public class AguaTest {

    @Test
    public void test01UnaUnidadTerrestrePuedeAtravesarAgua() {
        // Arrange
        Casilla casilla = new Casilla(new Agua(), new Posicion(0, 0));

        // Act & Assert
        assertDoesNotThrow(() -> casilla.recibirTransito(new MovimientoTerrestre()));
    }

    @Test
    public void test02UnaLuciernagaQueCaeAlAguaMuere() {
        // Arrange
        Luciernaga luciernaga = new Luciernaga();
        Agua agua = new Agua();
        int vidaEsperada = 0;
        
        // Act
        agua.afectarA(luciernaga);
        int vidaActual = luciernaga.obtenerVida();
        
        // Assert
        assertEquals(vidaEsperada, vidaActual);
    }

    @Test
    public void test03UnMechCombateQueCaeAlAguaNoPierdeVida() {
        // Arrange
        Combate combate = new Combate();
        Agua agua = new Agua();
        int vidaEsperada = 3;
        
        // Act
        agua.afectarA(combate);
        int vidaActual = combate.obtenerVida();
        
        // Assert
        assertEquals(vidaEsperada, vidaActual);
    }
}