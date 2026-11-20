package edu.fiuba.algo3.modelo.unidades;

import static org.junit.jupiter.api.Assertions.*;

import edu.fiuba.algo3.mapa.ConstructorDeTablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Canon;
import org.junit.jupiter.api.Test;

public class EstadoArmaTest {
    private static final String[] MAPA = {
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS"
    };

    @Test
    public void test01UnArmaOperativaPuedeUsarse() {
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);
        Mech canonMech = new Canon();
        canonMech.colocarseEn(new Posicion(2, 0), tablero);

        EstadoArma estadoArma = new EstadoArmaOperativa();

        // Act
        assertDoesNotThrow(() -> estadoArma.usar(canonMech, new Posicion(2, 3), tablero));
    }

    @Test
    public void test02UnArmaSumergidaNoPuedeUsarse() {
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);
        Mech canonMech = new Canon();
        canonMech.colocarseEn(new Posicion(2, 0), tablero);

        EstadoArma estadoArma = new EstadoArmaSumergida();

        // Act
        assertThrows(ArmaSumergidaException.class, () -> estadoArma.usar(canonMech, new Posicion(2, 3), tablero));
    }
}
