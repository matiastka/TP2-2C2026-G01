package edu.fiuba.algo3.modelo.partida;

import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Saltador;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FabricaDeVeksTest {

    @Test
    public void test01ElPrimerVekQueSeCreaEsUnSaltador() {
        // Arrange
        FabricaDeVeks fabrica = new FabricaDeVeks();

        // Act
        Vek vek = fabrica.crearSiguiente();

        // Assert
        assertEquals(Saltador.class, vek.getClass());
    }

    @Test
    public void test02DespuesDeCrearLosTresTiposVuelveAEmpezarPorElSaltador() {
        // Arrange
        FabricaDeVeks fabrica = new FabricaDeVeks();
        fabrica.crearSiguiente();
        fabrica.crearSiguiente();
        fabrica.crearSiguiente();

        // Act
        Vek vek = fabrica.crearSiguiente();

        // Assert
        assertEquals(Saltador.class, vek.getClass());
    }
}