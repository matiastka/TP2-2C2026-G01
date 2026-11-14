
package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Agua;
import edu.fiuba.algo3.modelo.Luciernaga;
import edu.fiuba.algo3.modelo.Combate;
import edu.fiuba.algo3.modelo.Posicion;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AguaTest {

    @Test
    public void test01AguaEsTransitable() {
        // Arrange
        Agua agua = new Agua();

        // Act
        boolean esTransitable = agua.esTransitable();

        // Assert
        assertTrue(esTransitable);
    }

    @Test
    public void test02UnaLuciernagaQueCaeAlAguaMuere() {
        // Arrange
        Luciernaga luciernaga = new Luciernaga(new Posicion(0, 0));
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
        Combate combate = new Combate(new Posicion(0, 0));
        Agua agua = new Agua();
        int vidaEsperada = 3;
        
        // Act
        agua.afectarA(combate);
        int vidaActual = combate.obtenerVida();
        
        // Assert
        assertEquals(vidaEsperada, vidaActual);
    }
}