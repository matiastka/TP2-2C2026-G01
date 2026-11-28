package edu.fiuba.algo3.modelo.mapa;

import edu.fiuba.algo3.modelo.tablero.BuscadorDeObjetivos;
import edu.fiuba.algo3.modelo.tablero.Ocupante;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.TableroInvalidoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LectorDeMapaTest {

    private static final String RUTA_MAPA_1 = "src/main/java/edu/fiuba/algo3/modelo/mapa/mapa1.txt";

    @Test
    public void test01LaPrimeraCasillaDelMapaLeidoEsAgua() {
        // Arrange
        LectorDeMapa lector = new LectorDeMapa();
        Tablero tablero = lector.leer(RUTA_MAPA_1);
        Ocupante ocupante = mock(Ocupante.class);

        // Act
        tablero.aplicarEfectoDeTerrenoEn(new Posicion(0, 0), ocupante);

        // Assert
        verify(ocupante).caerEnAgua();
    }

    @Test
    public void test02LaTerceraCasillaDeLaPrimeraFilaDelMapaLeidoEsUnEdificio() {
        // Arrange
        LectorDeMapa lector = new LectorDeMapa();
        Tablero tablero = lector.leer(RUTA_MAPA_1);
        BuscadorDeObjetivos buscador = mock(BuscadorDeObjetivos.class);

        // Act
        tablero.enCasilla(new Posicion(0, 2)).ofrecerComoObjetivo(buscador);

        // Assert
        verify(buscador).encontrarEdificio(tablero.enCasilla(new Posicion(0, 2)));
    }

    @Test
    public void test03LeerUnMapaInexistenteLanzaExcepcion() {
        // Arrange
        LectorDeMapa lector = new LectorDeMapa();

        // Act & Assert
        assertThrows(TableroInvalidoException.class, () -> lector.leer("src/main/java/edu/fiuba/algo3/mapa/noExiste.txt"));
    }
}