
package edu.fiuba.algo3.modelo.tablero.emergencias;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SinEmergenciaTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";

    @Test
    public void test01ResolverSobreCasillaLibreLaDejaLibre() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Casilla casilla = tablero.enCasilla(new Posicion(3, 3));
        Emergencia sinEmergencia = new SinEmergencia();

        // Act
        sinEmergencia.resolver(casilla, tablero);

        // Assert
        assertTrue(casilla.estaLibre());
    }

    @Test
    public void test02ResolverSobreCasillaOcupadaNoDaniaAlOcupante() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicion = new Posicion(3, 3);
        Mech mech = new Combate();
        mech.colocarseEn(posicion, tablero);
        Casilla casilla = tablero.enCasilla(posicion);
        Emergencia sinEmergencia = new SinEmergencia();

        // Act
        sinEmergencia.resolver(casilla, tablero);

        // Assert
        assertEquals(3, mech.obtenerVida());
    }
}