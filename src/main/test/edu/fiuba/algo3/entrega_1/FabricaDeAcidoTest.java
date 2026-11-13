package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FabricaDeAcidoTest {

    @Test
    public void test01LaFabricaDeAcidoCreaUnTerrenoTransitable() {
        FabricaDeTerreno fabrica = new FabricaDeAcido();

        Terreno terreno = fabrica.crear();

        assertTrue(terreno.esTransitable());
    }

    @Test
    public void test02LaFabricaDeAcidoCreaUnaCasillaTransitable() {
        FabricaDeTerreno fabrica = new FabricaDeAcido();

        Casilla casilla = fabrica.crearCasilla(new Posicion(0, 0));

        assertTrue(casilla.esTransitable());
    }

    @Test
    public void test03LaFabricaDeAcidoCreaUnaCasillaLibre() {
        FabricaDeTerreno fabrica = new FabricaDeAcido();

        Casilla casilla = fabrica.crearCasilla(new Posicion(0, 0));

        assertTrue(casilla.estaLibre());
    }
}
