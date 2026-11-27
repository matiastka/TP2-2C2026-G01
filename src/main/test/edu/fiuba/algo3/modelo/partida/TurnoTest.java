
package edu.fiuba.algo3.modelo.partida;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.veks.ia.InteligenciaArtificialVek;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Saltador;

public class TurnoTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";
    private static final String RUTA_MAPA_EDIFICIO = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_edificio.txt";

    @Test
    public void test01EnLaFaseDeResolucionAtacaPrimeroElPrimerVekDelTurno() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionVek1 = new Posicion(1, 1);
        Posicion posicionVek2 = new Posicion(1, 2);
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
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
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
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Saltador saltador = new Saltador();
        saltador.colocarseEn(new Posicion(3, 1), tablero);
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
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Saltador saltador = new Saltador();
        saltador.colocarseEn(new Posicion(3, 1), tablero);
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
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
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