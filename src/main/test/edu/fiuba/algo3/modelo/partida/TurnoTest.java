package edu.fiuba.algo3.modelo.partida;

import java.util.List;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.veks.ia.InteligenciaArtificialVek;
import edu.fiuba.algo3.modelo.unidades.veks.Saltador;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class TurnoTest {

    private static final String[] MAPA_LIBRE = {
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS"
    };

    private static final String[] MAPA_CON_EDIFICIO = {
            "SSSSSSSS",
            "SESSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS"
    };

    @Test
    public void test01EnLaFaseDeResolucionAtacaPrimeroElPrimerVekDelTurno() {
        // Arrange
        Tablero tablero = new ConstructorTablero().construir(MAPA_LIBRE);

        Posicion posicionVek1 = new Posicion(1,1);
        Posicion posicionVek2 = new Posicion(1,2);

        Saltador vekSaltador1 = new Saltador();
        Saltador vekSaltador2 = new Saltador();

        vekSaltador1.colocarseEn(posicionVek1, tablero);
        vekSaltador2.colocarseEn(posicionVek2, tablero);

        vekSaltador1.prepararAtaque(posicionVek2);
        vekSaltador2.prepararAtaque(posicionVek1);

        Turno turno = new Turno(List.of(vekSaltador1, vekSaltador2), new InteligenciaArtificialVek());

        // Act
        turno.faseResolucion(tablero);

        // Assert
        assertEquals(2, vekSaltador1.obtenerVida());
    }

    @Test
    public void test02SiCambiaElOrdenDelTurnoCambiaQuienAtacaPrimero() {
        // Arrange
        Tablero tablero = new ConstructorTablero().construir(MAPA_LIBRE);

        Saltador primero = new Saltador();
        Saltador segundo = new Saltador();

        primero.colocarseEn(new Posicion(1, 1), tablero);
        segundo.colocarseEn(new Posicion(1, 2), tablero);

        primero.prepararAtaque(new Posicion(1, 2));
        segundo.prepararAtaque(new Posicion(1, 1));

        Turno turno = new Turno(List.of(segundo, primero), new InteligenciaArtificialVek());

        // Act
        turno.faseResolucion(tablero);

        // Assert
        assertEquals(0, primero.obtenerVida());
    }

    @Test
    public void test03EnLaFaseVekCadaVekAnunciaSuAtaqueYSeEjecutaEnLaResolucion() {
        // Arrange
        Tablero tablero = new ConstructorTablero().construir(MAPA_CON_EDIFICIO);

        Saltador saltador = new Saltador();

        saltador.colocarseEn(new Posicion(4, 1), tablero);

        Turno turno = new Turno(List.of(saltador), new InteligenciaArtificialVek());

        int redInicial = tablero.obtenerRedElectrica();

        // Act
        turno.faseVek(tablero);
        turno.faseResolucion(tablero);

        // Assert
        assertEquals(redInicial - 1, tablero.obtenerRedElectrica());
    }

    @Test
    public void test04LaFaseVekSoloAnunciaYNoEjecutaElAtaque() {
        // Arrange
        Tablero tablero = new ConstructorTablero().construir(MAPA_CON_EDIFICIO);

        Saltador saltador = new Saltador();

        saltador.colocarseEn(new Posicion(4, 1), tablero);

        Turno turno = new Turno(List.of(saltador), new InteligenciaArtificialVek());

        int redInicial = tablero.obtenerRedElectrica();

        // Act
        turno.faseVek(tablero);

        // Assert
        assertEquals(redInicial, tablero.obtenerRedElectrica());
    }

    @Test
    public void test05EnLaFaseDeEmergenciaEmergeElVekAnunciado() {
        // Arrange
        Tablero tablero = new ConstructorTablero().construir(MAPA_LIBRE);
        Posicion posicion = new Posicion(3, 3);
        Saltador vekQueEmerge = new Saltador();
        tablero.anunciarEmergencia(posicion, vekQueEmerge);
        Turno turno = new Turno(List.of(), new InteligenciaArtificialVek());

        // Act
        turno.faseEmergencia(tablero);

        // Assert
        assertSame(vekQueEmerge, tablero.enCasilla(posicion).obtenerOcupante());
    }
}
