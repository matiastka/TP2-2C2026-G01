package edu.fiuba.algo3.modelo.tablero.direcciones;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DireccionTest {

    @Test
    public void test01NorteDesde23VaA13() {
        Posicion esperada = new Posicion(1, 3);
        Posicion actual = new Norte().desplazar(2, 3);
        assertEquals(esperada, actual);
    }

    @Test
    public void test02SurDesde23VaA33() {
        Posicion esperada = new Posicion(3, 3);
        Posicion actual = new Sur().desplazar(2, 3);
        assertEquals(esperada, actual);
    }

    @Test
    public void test03EsteDesde23VaA24() {
        Posicion esperada = new Posicion(2, 4);
        Posicion actual = new Este().desplazar(2, 3);
        assertEquals(esperada, actual);
    }

    @Test
    public void test04OesteDesde23VaA22() {
        Posicion esperada = new Posicion(2, 2);
        Posicion actual = new Oeste().desplazar(2, 3);
        assertEquals(esperada, actual);
    }

    @Test
    public void test05DosDireccionesDistintasNoSonIguales() {
        assertNotEquals(new Norte(), new Sur());
    }

    @Test
    public void test06Hay4Cardinales() {
        assertEquals(4, Direccion.cardinales().size());
    }
}