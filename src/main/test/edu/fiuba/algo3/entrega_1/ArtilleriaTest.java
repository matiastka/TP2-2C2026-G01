package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
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
        Tablero tablero = new Tablero(MAPA);

        Posicion posArtilleria = new Posicion(4, 4);
        Posicion posObjetivoAdyacente = new Posicion(4, 5);

        Mech artilleria = new Artilleria(posArtilleria);
        Mech objetivo = new Combate(posObjetivoAdyacente);

        tablero.ocuparCasilla(posArtilleria, artilleria);
        tablero.ocuparCasilla(posObjetivoAdyacente, objetivo);

        artilleria.atacarA(posObjetivoAdyacente, tablero);

        assertEquals(3, objetivo.obtenerVida());
    }

    @Test
    public void test02ArtilleriaAtacaPorEncimaDeObstaculos() {
        Tablero tablero = new Tablero(MAPA);

        Posicion posArtilleria = new Posicion(2, 0); // Fila 2 (hay montaña en 2,1)
        Posicion posObjetivo = new Posicion(2, 3);

        Mech artilleria = new Artilleria(posArtilleria);
        Mech objetivo = new Combate(posObjetivo);

        tablero.ocuparCasilla(posArtilleria, artilleria);
        tablero.ocuparCasilla(posObjetivo, objetivo);

        artilleria.atacarA(posObjetivo, tablero);

        assertEquals(2, objetivo.obtenerVida()); // 3 - 1 = 2
    }

    @Test
    public void test04ArtilleriaDaniaALaUnidadDelCentro() {
        Tablero tablero = new Tablero(MAPA);
        Posicion posArtilleria = new Posicion(4, 0);
        Posicion posCentro = new Posicion(4, 4);
        Mech artilleria = new Artilleria(posArtilleria);
        Mech centro = new Combate(posCentro);
        tablero.ocuparCasilla(posArtilleria, artilleria);
        tablero.ocuparCasilla(posCentro, centro);

        artilleria.atacarA(posCentro, tablero);

        assertEquals(2, centro.obtenerVida());
    }

    @Test
    public void test05ArtilleriaEmpujaHaciaElNorteALaUnidadDeArribaDelCentro() {
        Tablero tablero = new Tablero(MAPA);
        Posicion posArtilleria = new Posicion(4, 0);
        Posicion posCentro = new Posicion(4, 4);
        Posicion posNorte = new Posicion(3, 4);
        Mech artilleria = new Artilleria(posArtilleria);
        Mech norte = new Combate(posNorte);
        tablero.ocuparCasilla(posArtilleria, artilleria);
        tablero.ocuparCasilla(posNorte, norte);

        artilleria.atacarA(posCentro, tablero);

        assertSame(norte, tablero.enCasilla(new Posicion(2, 4)).obtenerUnidad());
    }

    @Test
    public void test06ArtilleriaEmpujaHaciaElSurALaUnidadDeAbajoDelCentro() {
        Tablero tablero = new Tablero(MAPA);
        Posicion posArtilleria = new Posicion(4, 0);
        Posicion posCentro = new Posicion(4, 4);
        Posicion posSur = new Posicion(5, 4);
        Mech artilleria = new Artilleria(posArtilleria);
        Mech sur = new Combate(posSur);
        tablero.ocuparCasilla(posArtilleria, artilleria);
        tablero.ocuparCasilla(posSur, sur);

        artilleria.atacarA(posCentro, tablero);

        assertSame(sur, tablero.enCasilla(new Posicion(6, 4)).obtenerUnidad());
    }

    @Test
    public void test07ArtilleriaEmpujaHaciaElOesteALaUnidadDeLaIzquierdaDelCentro() {
        Tablero tablero = new Tablero(MAPA);
        Posicion posArtilleria = new Posicion(4, 0);
        Posicion posCentro = new Posicion(4, 4);
        Posicion posOeste = new Posicion(4, 3);
        Mech artilleria = new Artilleria(posArtilleria);
        Mech oeste = new Combate(posOeste);
        tablero.ocuparCasilla(posArtilleria, artilleria);
        tablero.ocuparCasilla(posOeste, oeste);

        artilleria.atacarA(posCentro, tablero);

        assertSame(oeste, tablero.enCasilla(new Posicion(4, 2)).obtenerUnidad());
    }

    @Test
    public void test08ArtilleriaEmpujaHaciaElEsteALaUnidadDeLaDerechaDelCentro() {
        Tablero tablero = new Tablero(MAPA);
        Posicion posArtilleria = new Posicion(4, 0);
        Posicion posCentro = new Posicion(4, 4);
        Posicion posEste = new Posicion(4, 5);
        Mech artilleria = new Artilleria(posArtilleria);
        Mech este = new Combate(posEste);
        tablero.ocuparCasilla(posArtilleria, artilleria);
        tablero.ocuparCasilla(posEste, este);

        artilleria.atacarA(posCentro, tablero);

        assertSame(este, tablero.enCasilla(new Posicion(4, 6)).obtenerUnidad());
    }
}
