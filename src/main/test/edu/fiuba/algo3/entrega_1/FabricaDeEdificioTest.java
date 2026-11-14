package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FabricaDeEdificioTest {

    @Test
    public void test01LaFabricaDeEdificioCreaUnTerrenoNoTransitable() {
        FabricaDeTerreno fabrica = new FabricaDeEdificio();

        Terreno terreno = fabrica.crear();

        assertFalse(terreno.esTransitable());
    }

    @Test
    public void test02LaFabricaDeEdificioCreaUnaCasillaNoTransitable() {
        FabricaDeTerreno fabrica = new FabricaDeEdificio();

        Casilla casilla = fabrica.crearCasilla(new Posicion(0, 0));

        assertFalse(casilla.esTransitable());
    }

    @Test
    public void test03LaFabricaDeEdificioCreaUnaCasillaLibre() {
        FabricaDeTerreno fabrica = new FabricaDeEdificio();

        Casilla casilla = fabrica.crearCasilla(new Posicion(0, 0));

        assertTrue(casilla.estaLibre());
    }
}
