package edu.fiuba.algo3.entrega_2;

import static org.junit.jupiter.api.Assertions.*;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import org.junit.jupiter.api.Test;

public class ContaminacionTest {
    private static final String[] MAPA = {
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSCSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS"
    };

    @Test
    public void test01UnMechSeMueveAUnaCasillaConAcidoYAlRecibirDanioPierdeElDobleDeVida(){
        // Arrange
        Tablero tablero = new Tablero(MAPA);

        Posicion posicionMech = new Posicion(2,3);
        Posicion posicionAcido = new Posicion(2,4);

        Mech mechCombate = new Combate(posicionMech);

        int vidaEsperada = 1;

        // Act
        mechCombate.moverA(posicionAcido, tablero);
        mechCombate.recibirDanio(1);

        // Assert
        assertEquals(vidaEsperada,mechCombate.obtenerVida());
    }


}
