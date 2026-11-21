
package edu.fiuba.algo3.modelo.unidades.movimientos;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.terrenos.Suelo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AgregadorDeDestinosTest {

    @Test
    public void test01EncontrarTerrenoSeguroAgregaLaPosicionDeLaCasillaALaLista() {
        // Arrange
        List<Posicion> destinos = new ArrayList<>();
        AgregadorDeDestinos agregador = new AgregadorDeDestinos(destinos);
        Posicion posicion = new Posicion(2, 3);
        Casilla casilla = new Casilla(new Suelo(), posicion);

        // Act
        agregador.encontrarTerrenoSeguro(casilla);

        // Assert
        assertTrue(destinos.contains(posicion));
    }

    @Test
    public void test02EncontrarTerrenoSeguroAcumulaMultiplesPosicionesEnLaLista() {
        // Arrange
        List<Posicion> destinos = new ArrayList<>();
        AgregadorDeDestinos agregador = new AgregadorDeDestinos(destinos);
        Casilla primeraCasilla = new Casilla(new Suelo(), new Posicion(1, 1));
        Casilla segundaCasilla = new Casilla(new Suelo(), new Posicion(1, 2));

        // Act
        agregador.encontrarTerrenoSeguro(primeraCasilla);
        agregador.encontrarTerrenoSeguro(segundaCasilla);

        // Assert
        assertEquals(2, destinos.size());
    }
}