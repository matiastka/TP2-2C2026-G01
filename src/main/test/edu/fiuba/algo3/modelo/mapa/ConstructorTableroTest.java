package edu.fiuba.algo3.modelo.mapa;


import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.TableroInvalidoException;
import edu.fiuba.algo3.modelo.terrenos.Acido;
import edu.fiuba.algo3.modelo.terrenos.Agua;
import edu.fiuba.algo3.modelo.terrenos.Edificio;
import edu.fiuba.algo3.modelo.terrenos.Montana;
import edu.fiuba.algo3.modelo.terrenos.Suelo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConstructorTableroTest {
    @Test
    public void test01LaLetraSCreaUnaCasillaDeSuelo() {
        Tablero tablero = new ConstructorTablero().construir(new String[]{"S"});

        assertEquals(Suelo.class, tablero.enCasilla(new Posicion(0, 0)).obtenerTerreno().getClass());
    }

    @Test
    public void test02LaLetraECreaUnaCasillaDeEdificio() {
        Tablero tablero = new ConstructorTablero().construir(new String[]{"E"});

        assertEquals(Edificio.class, tablero.enCasilla(new Posicion(0, 0)).obtenerTerreno().getClass());
    }

    @Test
    public void test03LaLetraMCreaUnaCasillaDeMontana() {
        Tablero tablero = new ConstructorTablero().construir(new String[]{"M"});

        assertEquals(Montana.class, tablero.enCasilla(new Posicion(0, 0)).obtenerTerreno().getClass());
    }

    @Test
    public void test04LaLetraACreaUnaCasillaDeAgua() {
        Tablero tablero = new ConstructorTablero().construir(new String[]{"A"});

        assertEquals(Agua.class, tablero.enCasilla(new Posicion(0, 0)).obtenerTerreno().getClass());
    }

    @Test
    public void test05LaLetraCCreaUnaCasillaDeAcido() {
        Tablero tablero = new ConstructorTablero().construir(new String[]{"C"});

        assertEquals(Acido.class, tablero.enCasilla(new Posicion(0, 0)).obtenerTerreno().getClass());
    }

    @Test
    public void test06UnaLetraDesconocidaLanzaExcepcion() {
        ConstructorTablero constructor = new ConstructorTablero();

        assertThrows(TableroInvalidoException.class, () -> constructor.construir(new String[]{"X"}));
    }

    @Test
    public void test07DosCasillasConLaMismaLetraTienenTerrenosDistintos() {
        Tablero tablero = new ConstructorTablero().construir(new String[]{"EE"});

        assertNotSame(tablero.enCasilla(new Posicion(0, 0)).obtenerTerreno(),
                tablero.enCasilla(new Posicion(0, 1)).obtenerTerreno());
    }
}
