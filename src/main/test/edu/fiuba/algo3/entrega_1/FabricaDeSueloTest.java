package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FabricaDeSueloTest {

    @Test
    public void test01LaFabricaDeSueloCreaUnTerrenoTransitable() {
        FabricaDeTerreno fabrica = new FabricaDeSuelo();

        Terreno terreno = fabrica.crear();

        assertTrue(terreno.esTransitable());
    }

    @Test
    public void test02LaFabricaDeSueloCreaUnaCasillaTransitable() {
        FabricaDeTerreno fabrica = new FabricaDeSuelo();

        Casilla casilla = fabrica.crearCasilla(new Posicion(0, 0));

        assertTrue(casilla.esTransitable());
    }

    @Test
    public void test03LaFabricaDeSueloCreaUnaCasillaLibre() {
        FabricaDeTerreno fabrica = new FabricaDeSuelo();

        Casilla casilla = fabrica.crearCasilla(new Posicion(0, 0));

        assertTrue(casilla.estaLibre());
    }
}