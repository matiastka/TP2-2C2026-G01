package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DireccionTest {

    @Test
    public void test01NorteDesde23VaA13() {
        Posicion esperada = new Posicion(1, 3);
        Posicion actual = Direccion.NORTE.desplazar(2, 3);
        assertEquals(esperada, actual);
    }

    @Test
    public void test02SurDesde23VaA33() {
        Posicion esperada = new Posicion(3, 3);
        Posicion actual = Direccion.SUR.desplazar(2, 3);
        assertEquals(esperada, actual);
    }

    @Test
    public void test03EsteDesde23VaA24() {
        Posicion esperada = new Posicion(2, 4);
        Posicion actual = Direccion.ESTE.desplazar(2, 3);
        assertEquals(esperada, actual);
    }

    @Test
    public void test04OesteDesde23VaA22() {
        Posicion esperada = new Posicion(2, 2);
        Posicion actual = Direccion.OESTE.desplazar(2, 3);
        assertEquals(esperada, actual);
    }

    @Test
    public void test05NingunaNoSeMueve() {
        Posicion esperada = new Posicion(2, 3);
        Posicion actual = Direccion.NINGUNA.desplazar(2, 3);
        assertEquals(esperada, actual);
    }

    @Test
    public void test06Hay4Cardinales() {
        int cantidadEsperada = 4;
        int cantidadActual = Direccion.cardinales().length;
        assertEquals(cantidadEsperada, cantidadActual);
    }
}