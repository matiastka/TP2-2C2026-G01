package edu.fiuba.algo3.modelo.mapa;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.TableroInvalidoException;
import edu.fiuba.algo3.modelo.terrenos.Agua;
import edu.fiuba.algo3.modelo.terrenos.Edificio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LectorDeMapaTest {

    private static final String RUTA_MAPA_1 = "src/main/java/edu/fiuba/algo3/modelo/mapa/mapa1.txt";

    @Test
    public void test01LaPrimeraCasillaDelMapaLeidoEsAgua() {
        // Arrange
        LectorDeMapa lector = new LectorDeMapa();

        // Act
        Tablero tablero = lector.leer(RUTA_MAPA_1);

        // Assert
        assertEquals(Agua.class, tablero.enCasilla(new Posicion(0, 0)).obtenerTerreno().getClass());
    }

    @Test
    public void test02LaTerceraCasillaDeLaPrimeraFilaDelMapaLeidoEsUnEdificio() {
        // Arrange
        LectorDeMapa lector = new LectorDeMapa();

        // Act
        Tablero tablero = lector.leer(RUTA_MAPA_1);

        // Assert
        assertEquals(Edificio.class, tablero.enCasilla(new Posicion(0, 2)).obtenerTerreno().getClass());
    }

    @Test
    public void test03LeerUnMapaInexistenteLanzaExcepcion() {
        // Arrange
        LectorDeMapa lector = new LectorDeMapa();

        // Act & Assert
        assertThrows(TableroInvalidoException.class, () -> lector.leer("src/main/java/edu/fiuba/algo3/mapa/noExiste.txt"));
    }
}