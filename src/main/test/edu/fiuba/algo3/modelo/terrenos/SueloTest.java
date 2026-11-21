
package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.unidades.movimientos.MovimientoTerrestre;
import edu.fiuba.algo3.modelo.unidades.veks.Luciernaga;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SueloTest {

    @Test
    public void test01UnaUnidadTerrestrePuedeAtravesarSuelo() {
        // Arrange
        Casilla casilla = new Casilla(new Suelo(), new Posicion(0, 0));

        // Act & Assert
        assertDoesNotThrow(() -> casilla.recibirTransito(new MovimientoTerrestre()));
    }

    @Test
    public void test02SueloNoLeHaceNadaAUnaLuciernaga() {
        // Arrange
        Luciernaga luciernaga = new Luciernaga();
        Suelo suelo = new Suelo();
        int vidaEsperada = 3;
        
        // Act
        suelo.afectarA(luciernaga);
        int vidaActual = luciernaga.obtenerVida();
        
        // Assert
        assertEquals(vidaEsperada, vidaActual);
    }
}