
package edu.fiuba.algo3.modelo.mapa;

import edu.fiuba.algo3.modelo.tablero.BuscadorDeObjetivos;
import edu.fiuba.algo3.modelo.tablero.Ocupante;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.TableroInvalidoException;
import edu.fiuba.algo3.modelo.tablero.Transito;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ConstructorTableroTest {

    @Test
    public void test01LaLetraSCreaUnaCasillaDeSuelo() {
        // Arrange
        ConstructorTablero constructor = new ConstructorTablero();
        Tablero tablero = constructor.construir(List.of("S"));
        Transito transito = mock(Transito.class);

        // Act
        tablero.recibirTransitoEn(new Posicion(0, 0), transito);

        // Assert
        verify(transito).atravesar(tablero.enCasilla(new Posicion(0, 0)));
    }

    @Test
    public void test02LaLetraECreaUnaCasillaDeEdificio() {
        // Arrange
        ConstructorTablero constructor = new ConstructorTablero();
        Tablero tablero = constructor.construir(List.of("E"));
        BuscadorDeObjetivos buscador = mock(BuscadorDeObjetivos.class);

        // Act
        tablero.enCasilla(new Posicion(0, 0)).ofrecerComoObjetivo(buscador);

        // Assert
        verify(buscador).encontrarEdificio(tablero.enCasilla(new Posicion(0, 0)));
    }

    @Test
    public void test03LaLetraMCreaUnaCasillaDeMontana() {
        // Arrange
        ConstructorTablero constructor = new ConstructorTablero();
        Tablero tablero = constructor.construir(List.of("M"));
        Transito transito = mock(Transito.class);

        // Act
        tablero.recibirTransitoEn(new Posicion(0, 0), transito);

        // Assert
        verify(transito).chocarConTerreno(tablero.enCasilla(new Posicion(0, 0)));
    }

    @Test
    public void test04LaLetraACreaUnaCasillaDeAgua() {
        // Arrange
        ConstructorTablero constructor = new ConstructorTablero();
        Tablero tablero = constructor.construir(List.of("A"));
        Ocupante ocupante = mock(Ocupante.class);

        // Act
        tablero.aplicarEfectoDeTerrenoEn(new Posicion(0, 0), ocupante);

        // Assert
        verify(ocupante).caerEnAgua();
    }

    @Test
    public void test05LaLetraCCreaUnaCasillaDeAcido() {
        // Arrange
        ConstructorTablero constructor = new ConstructorTablero();
        Tablero tablero = constructor.construir(List.of("C"));
        Ocupante ocupante = mock(Ocupante.class);

        // Act
        tablero.aplicarEfectoDeTerrenoEn(new Posicion(0, 0), ocupante);

        // Assert
        verify(ocupante).caerEnAcido();
    }

    @Test
    public void test06UnaLetraDesconocidaLanzaExcepcion() {
        // Arrange
        ConstructorTablero constructor = new ConstructorTablero();

        // Act & Assert
        assertThrows(TableroInvalidoException.class, () -> constructor.construir(List.of("X")));
    }

    @Test
    public void test07DosCasillasConLaMismaLetraTienenTerrenosDistintos() {
        // Arrange
        ConstructorTablero constructor = new ConstructorTablero();

        // Act
        Tablero tablero = constructor.construir(List.of("EE"));

        // Assert
        assertNotSame(tablero.enCasilla(new Posicion(0, 0)).obtenerTerreno(),
                tablero.enCasilla(new Posicion(0, 1)).obtenerTerreno());
    }
}