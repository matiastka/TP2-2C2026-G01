
package edu.fiuba.algo3.entrega_1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.excepciones.DestinoNoAlcanzableException;
import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Artilleria;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;

public class ArtilleriaTest {

    private static final String RUTA_MAPA_MONTANA = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_montana.txt";

    @Test
    public void test01ArtilleriaNoPuedeAtacarCasillaAdyacente() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_MONTANA);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posArtilleria = new Posicion(4, 4);
        Posicion posObjetivoAdyacente = new Posicion(4, 5);

        Mech artilleria = new Artilleria();
        Mech objetivo = new Combate();
        
        artilleria.colocarseEn(posArtilleria, tablero);
        objetivo.colocarseEn(posObjetivoAdyacente, tablero);

        // Act & Assert
        assertThrows(DestinoNoAlcanzableException.class, () -> artilleria.atacarA(posObjetivoAdyacente, tablero));
    }

    @Test
    public void test02ArtilleriaAtacaPorEncimaDeObstaculos() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_MONTANA);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posArtilleria = new Posicion(2, 0);
        Posicion posObjetivo = new Posicion(2, 3);
        Mech artilleria = new Artilleria();
        Mech objetivo = new Combate();
        artilleria.colocarseEn(posArtilleria, tablero);
        objetivo.colocarseEn(posObjetivo, tablero);

        // Act
        artilleria.atacarA(posObjetivo, tablero);

        // Assert
        assertEquals(2, objetivo.obtenerVida());
    }

    @Test
    public void test03ArtilleriaDaniaALaUnidadDelCentro() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_MONTANA);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posArtilleria = new Posicion(4, 0);
        Posicion posCentro = new Posicion(4, 4);
        Mech artilleria = new Artilleria();
        Mech centro = new Combate();
        artilleria.colocarseEn(posArtilleria, tablero);
        centro.colocarseEn(posCentro, tablero);

        // Act
        artilleria.atacarA(posCentro, tablero);

        // Assert
        assertEquals(2, centro.obtenerVida());
    }

    @Test
    public void test04ArtilleriaEmpujaHaciaElNorteALaUnidadDeArribaDelCentro() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_MONTANA);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posArtilleria = new Posicion(4, 0);
        Posicion posCentro = new Posicion(4, 4);
        Posicion posNorte = new Posicion(3, 4);
        Mech artilleria = new Artilleria();
        Mech norte = new Combate();
        artilleria.colocarseEn(posArtilleria, tablero);
        norte.colocarseEn(posNorte, tablero);

        // Act
        artilleria.atacarA(posCentro, tablero);

        // Assert
        assertSame(norte, tablero.enCasilla(new Posicion(2, 4)).obtenerOcupante());
    }

    @Test
    public void test05ArtilleriaEmpujaHaciaElSurALaUnidadDeAbajoDelCentro() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_MONTANA);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posArtilleria = new Posicion(4, 0);
        Posicion posCentro = new Posicion(4, 4);
        Posicion posSur = new Posicion(5, 4);
        Mech artilleria = new Artilleria();
        Mech sur = new Combate();
        artilleria.colocarseEn(posArtilleria, tablero);
        sur.colocarseEn(posSur, tablero);

        // Act
        artilleria.atacarA(posCentro, tablero);

        // Assert
        assertSame(sur, tablero.enCasilla(new Posicion(6, 4)).obtenerOcupante());
    }

    @Test
    public void test06ArtilleriaEmpujaHaciaElOesteALaUnidadDeLaIzquierdaDelCentro() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_MONTANA);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posArtilleria = new Posicion(4, 0);
        Posicion posCentro = new Posicion(4, 4);
        Posicion posOeste = new Posicion(4, 3);
        Mech artilleria = new Artilleria();
        Mech oeste = new Combate();
        artilleria.colocarseEn(posArtilleria, tablero);
        oeste.colocarseEn(posOeste, tablero);

        // Act
        artilleria.atacarA(posCentro, tablero);

        // Assert
        assertSame(oeste, tablero.enCasilla(new Posicion(4, 2)).obtenerOcupante());
    }

    @Test
    public void test07ArtilleriaEmpujaHaciaElEsteALaUnidadDeLaDerechaDelCentro() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_MONTANA);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posArtilleria = new Posicion(4, 0);
        Posicion posCentro = new Posicion(4, 4);
        Posicion posEste = new Posicion(4, 5);
        Mech artilleria = new Artilleria();
        Mech este = new Combate();
        artilleria.colocarseEn(posArtilleria, tablero);
        este.colocarseEn(posEste, tablero);

        // Act
        artilleria.atacarA(posCentro, tablero);

        // Assert
        assertSame(este, tablero.enCasilla(new Posicion(4, 6)).obtenerOcupante());
    }

    @Test
    public void test08ArtilleriaEmpiezaCon2DeVida() {
        // Arrange
        Artilleria artilleria = new Artilleria();
        int vidaEsperada = 2;

        // Act
        int vidaActual = artilleria.obtenerVida();

        // Assert
        assertEquals(vidaEsperada, vidaActual);
    }

    @Test
    public void test09ArtilleriaTieneRango3() {
        // Arrange
        Artilleria artilleria = new Artilleria();
        int rangoEsperado = 3;

        // Act
        int rangoActual = artilleria.obtenerRango();

        // Assert
        assertEquals(rangoEsperado, rangoActual);
    }
}