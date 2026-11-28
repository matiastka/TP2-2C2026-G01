package edu.fiuba.algo3.modelo.partida;

import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneradorDeEmergenciasTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";
    private static final String RUTA_MAPA_MONTANA_FILA0 = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_montana_fila0.txt";

    private GeneradorDeEmergencias generadorEnLaFila0() {
        return new GeneradorDeEmergencias(new FabricaDeVeks(), List.of(0));
    }

    @Test
    public void test01EnUnTurnoImparSeAnunciaUnaEmergencia() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);

        // Act
        generadorEnLaFila0().anunciarEmergencias(1, tablero);

        // Assert
        assertEquals(1, tablero.resolverEmergencias().size());
    }

    @Test
    public void test02EnUnTurnoParSeAnuncianDosEmergencias() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);

        // Act
        generadorEnLaFila0().anunciarEmergencias(2, tablero);

        // Assert
        assertEquals(2, tablero.resolverEmergencias().size());
    }

    @Test
    public void test03LaEmergenciaSeAnunciaEnLaPrimeraCasillaLibreDeLaFila() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        generadorEnLaFila0().anunciarEmergencias(1, tablero);

        // Act
        List<Vek> emergidos = tablero.resolverEmergencias();

        // Assert
        assertEquals(new Posicion(0, 0), emergidos.get(0).obtenerPosicion());
    }

    @Test
    public void test04NoSeAnunciaUnaEmergenciaEnUnaCasillaOcupada() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        new Combate().colocarseEn(new Posicion(0, 0), tablero);
        generadorEnLaFila0().anunciarEmergencias(1, tablero);

        // Act
        List<Vek> emergidos = tablero.resolverEmergencias();

        // Assert
        assertEquals(new Posicion(0, 1), emergidos.get(0).obtenerPosicion());
    }

    @Test
    public void test05NoSeAnunciaUnaEmergenciaEnUnaMontana() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_MONTANA_FILA0);
        new Combate().colocarseEn(new Posicion(0, 0), tablero);
        generadorEnLaFila0().anunciarEmergencias(1, tablero);

        // Act
        List<Vek> emergidos = tablero.resolverEmergencias();

        // Assert
        assertEquals(new Posicion(0, 2), emergidos.get(0).obtenerPosicion());
    }

    @Test
    public void test06SinFilasParaEmergerNoSeAnunciaNingunaEmergencia() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        GeneradorDeEmergencias generador = new GeneradorDeEmergencias(new FabricaDeVeks(), List.of());

        // Act
        generador.anunciarEmergencias(2, tablero);

        // Assert
        assertEquals(0, tablero.resolverEmergencias().size());
    }
}