package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FabricaDeAguaTest {

    @Test
    public void test01LaFabricaDeAguaCreaUnTerrenoTransitable() {
        FabricaDeTerreno fabrica = new FabricaDeAgua();

        Terreno terreno = fabrica.crear();

        assertTrue(terreno.esTransitable());
    }

    @Test
    public void test02LaFabricaDeAguaCreaUnaCasillaTransitable() {
        FabricaDeTerreno fabrica = new FabricaDeAgua();

        Casilla casilla = fabrica.crearCasilla(new Posicion(0, 0));

        assertTrue(casilla.esTransitable());
    }

    @Test
    public void test03LaFabricaDeAguaCreaUnaCasillaLibre() {
        FabricaDeTerreno fabrica = new FabricaDeAgua();

        Casilla casilla = fabrica.crearCasilla(new Posicion(0, 0));

        assertTrue(casilla.estaLibre());
    }
}
