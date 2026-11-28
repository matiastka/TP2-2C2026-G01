package edu.fiuba.algo3.modelo.partida;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

public class FabricaDeVeksTest {

    @Test
    public void test01ElPrimerVekSeCreaConElPrimerCreador() {
        // Arrange
        CreadorDeVek primero = mock(CreadorDeVek.class);
        CreadorDeVek segundo = mock(CreadorDeVek.class);
        FabricaDeVeks fabrica = new FabricaDeVeks(List.of(primero, segundo));

        // Act
        fabrica.crearSiguiente();

        // Assert
        verify(primero).crear();
    }

    @Test
    public void test02ElPrimerVekNoUsaAlSegundoCreador() {
        // Arrange
        CreadorDeVek primero = mock(CreadorDeVek.class);
        CreadorDeVek segundo = mock(CreadorDeVek.class);
        FabricaDeVeks fabrica = new FabricaDeVeks(List.of(primero, segundo));

        // Act
        fabrica.crearSiguiente();

        // Assert
        verifyNoInteractions(segundo);
    }

    @Test
    public void test03ElSegundoVekSeCreaConElSegundoCreador() {
        // Arrange
        CreadorDeVek primero = mock(CreadorDeVek.class);
        CreadorDeVek segundo = mock(CreadorDeVek.class);
        FabricaDeVeks fabrica = new FabricaDeVeks(List.of(primero, segundo));
        fabrica.crearSiguiente();

        // Act
        fabrica.crearSiguiente();

        // Assert
        verify(segundo).crear();
    }

    @Test
    public void test04DespuesDelUltimoCreadorVuelveAEmpezarPorElPrimero() {
        // Arrange
        CreadorDeVek primero = mock(CreadorDeVek.class);
        CreadorDeVek segundo = mock(CreadorDeVek.class);
        FabricaDeVeks fabrica = new FabricaDeVeks(List.of(primero, segundo));
        fabrica.crearSiguiente();
        fabrica.crearSiguiente();

        // Act
        fabrica.crearSiguiente();

        // Assert
        verify(primero, times(2)).crear();
    }
}
