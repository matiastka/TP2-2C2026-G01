package edu.fiuba.algo3.architecture;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MechCombateTest {
    private static final String[] MAPA = {
            "AMESSMSM",
            "AMSSECSA",
            "ASESSCSA",
            "ASSESCSA",
            "ASSSSCSA",
            "ASSSECSA",
            "ASSSSCSA",
            "AMMMSCSA"
    };

    @Test
    public void test01UnMechArtilleroSeMueve1EnHorizontal(){
        // Arrange
        Tablero tablero = new Tablero(MAPA);

        Unidad mechCombate = new Combate();

        tablero.ocuparCasilla(2,3,mechCombate);

        // Act
        mechCombate.moverseHorizontal(1, tablero, 2, 3);

        // Assert
        assertTrue(tablero.enCasilla(2,4).obtenerUnidad().esDelMismoTipoQue(new Combate()));

    }

    @Test
    public void test02UnMechArtilleroSeMueve1EnVertical(){
        // Arrange
        Tablero tablero = new Tablero(MAPA);

        Unidad mechCombate = new Combate();

        tablero.ocuparCasilla(2,3,mechCombate);

        // Act
        mechCombate.moverseVertical(-1, tablero, 2, 3);

        // Assert
        assertTrue(tablero.enCasilla(1,3).obtenerUnidad().esDelMismoTipoQue(new Combate()));

    }

}
