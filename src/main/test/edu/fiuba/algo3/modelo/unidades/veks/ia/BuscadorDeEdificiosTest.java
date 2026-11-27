
package edu.fiuba.algo3.modelo.unidades.veks.ia;

import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.RedElectrica;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.terrenos.Edificio;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BuscadorDeEdificiosTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";
    private static final String RUTA_MAPA_EDIFICIO = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_edificio.txt";

    @Test
    public void test01ObtenerEdificiosDevuelveListaVaciaAlCrearse() {
        // Arrange
        BuscadorDeEdificios buscador = new BuscadorDeEdificios();

        // Act
        List<Posicion> edificios = buscador.obtenerEdificios();

        // Assert
        assertTrue(edificios.isEmpty());
    }

    @Test
    public void test02EncontrarEdificioAgregaLaPosicionDeLaCasillaALaLista() {
        // Arrange
        BuscadorDeEdificios buscador = new BuscadorDeEdificios();
        Posicion posicionEdificio = new Posicion(2, 4);
        Casilla casilla = new Casilla(new Edificio(new RedElectrica()), posicionEdificio);

        // Act
        buscador.encontrarEdificio(casilla);

        // Assert
        assertTrue(buscador.obtenerEdificios().contains(posicionEdificio));
    }

    @Test
    public void test03RecorrerTerrenosEnTableroSinEdificiosDejaLaListaVacia() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        BuscadorDeEdificios buscador = new BuscadorDeEdificios();

        // Act
        tablero.recorrerTerrenos(buscador);

        // Assert
        assertTrue(buscador.obtenerEdificios().isEmpty());
    }

    @Test
    public void test04RecorrerTerrenosEnTableroConEdificioEncuentraSuPosicion() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        BuscadorDeEdificios buscador = new BuscadorDeEdificios();

        // Act
        tablero.recorrerTerrenos(buscador);

        // Assert
        assertEquals(List.of(new Posicion(3, 3)), buscador.obtenerEdificios());
    }
}