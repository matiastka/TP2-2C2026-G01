package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FabricaDeMontanaTest {

    @Test
    public void test01LaFabricaDeMontanaCreaUnTerrenoNoTransitable() {
        FabricaDeTerreno fabrica = new FabricaDeMontana();

        Terreno terreno = fabrica.crear();

        assertFalse(terreno.esTransitable());
    }

    @Test
    public void test02LaFabricaDeMontanaCreaUnaCasillaNoTransitable() {
        FabricaDeTerreno fabrica = new FabricaDeMontana();

        Casilla casilla = fabrica.crearCasilla(new Posicion(0, 0));

        assertFalse(casilla.esTransitable());
    }

    @Test
    public void test03LaFabricaDeMontanaCreaUnaCasillaLibre() {
        FabricaDeTerreno fabrica = new FabricaDeMontana();

        Casilla casilla = fabrica.crearCasilla(new Posicion(0, 0));

        assertTrue(casilla.estaLibre());
    }
}
