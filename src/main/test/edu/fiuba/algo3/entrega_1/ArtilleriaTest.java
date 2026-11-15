package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Artilleria;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import edu.fiuba.algo3.modelo.unidades.Mech;
import edu.fiuba.algo3.mapa.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArtilleriaTest {

    private static final String[] MAPA = {
            "SSSSSSSS",
            "SSSSSSSS",
            "SMSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS"
    };

    @Test
    public void test01ArtilleriaNoPuedeAtacarCasillaAdyacente() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posArtilleria = new Posicion(4, 4);
        Posicion posObjetivoAdyacente = new Posicion(4, 5);

        Mech artilleria = new Artilleria();
        Mech objetivo = new Combate();

        artilleria.colocarseEn(posArtilleria, tablero);
        objetivo.colocarseEn(posObjetivoAdyacente, tablero);

        artilleria.atacarA(posObjetivoAdyacente, tablero);

        assertEquals(3, objetivo.obtenerVida());
    }

    @Test
    public void test02ArtilleriaAtacaPorEncimaDeObstaculos() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posArtilleria = new Posicion(2, 0); // Fila 2 (hay montaña en 2,1)
        Posicion posObjetivo = new Posicion(2, 3);

        Mech artilleria = new Artilleria();
        Mech objetivo = new Combate();

        artilleria.colocarseEn(posArtilleria, tablero);
        objetivo.colocarseEn(posObjetivo, tablero);

        artilleria.atacarA(posObjetivo, tablero);

        assertEquals(2, objetivo.obtenerVida()); // 3 - 1 = 2
    }

    @Test
    public void test04ArtilleriaDaniaALaUnidadDelCentro() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);
        Posicion posArtilleria = new Posicion(4, 0);
        Posicion posCentro = new Posicion(4, 4);
        Mech artilleria = new Artilleria();
        Mech centro = new Combate();
        artilleria.colocarseEn(posArtilleria, tablero);
        centro.colocarseEn(posCentro, tablero);

        artilleria.atacarA(posCentro, tablero);

        assertEquals(2, centro.obtenerVida());
    }

    @Test
    public void test05ArtilleriaEmpujaHaciaElNorteALaUnidadDeArribaDelCentro() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posArtilleria = new Posicion(4, 0);
        Posicion posCentro = new Posicion(4, 4);
        Posicion posNorte = new Posicion(3, 4);

        Mech artilleria = new Artilleria();
        Mech norte = new Combate();

        artilleria.colocarseEn(posArtilleria, tablero);
        norte.colocarseEn(posNorte, tablero);

        artilleria.atacarA(posCentro, tablero);

        assertSame(norte, tablero.enCasilla(new Posicion(2, 4)).obtenerOcupante());
    }

    @Test
    public void test06ArtilleriaEmpujaHaciaElSurALaUnidadDeAbajoDelCentro() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);
        Posicion posArtilleria = new Posicion(4, 0);
        Posicion posCentro = new Posicion(4, 4);
        Posicion posSur = new Posicion(5, 4);

        Mech artilleria = new Artilleria();
        Mech sur = new Combate();

        artilleria.colocarseEn(posArtilleria, tablero);
        sur.colocarseEn(posSur, tablero);

        artilleria.atacarA(posCentro, tablero);

        assertSame(sur, tablero.enCasilla(new Posicion(6, 4)).obtenerOcupante());
    }

    @Test
    public void test07ArtilleriaEmpujaHaciaElOesteALaUnidadDeLaIzquierdaDelCentro() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posArtilleria = new Posicion(4, 0);
        Posicion posCentro = new Posicion(4, 4);
        Posicion posOeste = new Posicion(4, 3);

        Mech artilleria = new Artilleria();
        Mech oeste = new Combate();

        artilleria.colocarseEn(posArtilleria, tablero);
        oeste.colocarseEn(posOeste, tablero);

        artilleria.atacarA(posCentro, tablero);

        assertSame(oeste, tablero.enCasilla(new Posicion(4, 2)).obtenerOcupante());
    }

    @Test
    public void test08ArtilleriaEmpujaHaciaElEsteALaUnidadDeLaDerechaDelCentro() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posArtilleria = new Posicion(4, 0);
        Posicion posCentro = new Posicion(4, 4);
        Posicion posEste = new Posicion(4, 5);

        Mech artilleria = new Artilleria();
        Mech este = new Combate();

        artilleria.colocarseEn(posArtilleria, tablero);
        este.colocarseEn(posEste, tablero);

        artilleria.atacarA(posCentro, tablero);

        assertSame(este, tablero.enCasilla(new Posicion(4, 6)).obtenerOcupante());
    }
    @Test
    public void test09ArtilleriaEmpiezaCon2DeVida() {
        // Arrange
        Artilleria artilleria = new Artilleria();
        int vidaEsperada = 2;

        // Act
        int vidaActual = artilleria.obtenerVida();

        // Assert
        assertEquals(vidaEsperada, vidaActual);
    }

    @Test
    public void test10ArtilleriaTieneRango3() {
        // Arrange
        Artilleria artilleria = new Artilleria();
        int rangoEsperado = 3;

        // Act
        int rangoActual = artilleria.obtenerRango();

        // Assert
        assertEquals(rangoEsperado, rangoActual);
    }
}
