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
        Mech artilleria = new Artilleria();
        Combate objetivo = new Combate();

        Posicion posArtilleria = new Posicion(4, 4);
        Posicion posObjetivoAdyacente = new Posicion(4, 5);

        tablero.ocuparCasilla(posArtilleria, artilleria);
        tablero.ocuparCasilla(posObjetivoAdyacente, objetivo);

        artilleria.atacarA(posObjetivoAdyacente, tablero);

        assertEquals(3, objetivo.obtenerVida());
        assertEquals(posObjetivoAdyacente.getColumna(), tablero.buscarPosicionDe(objetivo).getColumna());
    }

    @Test
    public void test02ArtilleriaAtacaPorEncimaDeObstaculos() {
        Tablero tablero = new Tablero(MAPA);
        Mech artilleria = new Artilleria();
        Combate objetivo = new Combate();

        Posicion posArtilleria = new Posicion(2, 0); // Fila 2 (hay montaña en 2,1)
        Posicion posObjetivo = new Posicion(2, 3);

        tablero.ocuparCasilla(posArtilleria, artilleria);
        tablero.ocuparCasilla(posObjetivo, objetivo);

        artilleria.atacarA(posObjetivo, tablero);

        assertEquals(2, objetivo.obtenerVida()); // 3 - 1 = 2
    }

    @Test
    public void test03ArtilleriaDaniaObjetivoYEmpujaUnidadesAdyacentesHaciaAfuera() {
        Tablero tablero = new Tablero(MAPA);
        Mech artilleria = new Artilleria();
        Combate centro = new Combate();
        Combate norte = new Combate();
        Combate sur = new Combate();
        Combate este = new Combate();
        Combate oeste = new Combate();

        Posicion posArtilleria = new Posicion(4, 0);
        Posicion posCentro = new Posicion(4, 4);

        Posicion posNorte = new Posicion(3, 4);
        Posicion posSur = new Posicion(5, 4);
        Posicion posOeste = new Posicion(4, 3);
        Posicion posEste = new Posicion(4, 5);

        tablero.ocuparCasilla(posArtilleria, artilleria);
        tablero.ocuparCasilla(posCentro, centro);
        tablero.ocuparCasilla(posNorte, norte);
        tablero.ocuparCasilla(posSur, sur);
        tablero.ocuparCasilla(posOeste, oeste);
        tablero.ocuparCasilla(posEste, este);

        artilleria.atacarA(posCentro, tablero);

        // Daño al centro
        assertEquals(2, centro.obtenerVida());

        // Empuje hacia afuera de las 4 casillas adyacentes
        assertEquals(new Posicion(2, 4).getFila(), tablero.buscarPosicionDe(norte).getFila());
        assertEquals(new Posicion(6, 4).getFila(), tablero.buscarPosicionDe(sur).getFila());
        assertEquals(new Posicion(4, 2).getColumna(), tablero.buscarPosicionDe(oeste).getColumna());
        assertEquals(new Posicion(4, 6).getColumna(), tablero.buscarPosicionDe(este).getColumna());
    }
}
