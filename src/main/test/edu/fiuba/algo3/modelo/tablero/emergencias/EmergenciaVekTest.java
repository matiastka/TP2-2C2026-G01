package edu.fiuba.algo3.modelo.tablero.emergencias;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmergenciaVekTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";

    @Test
    public void test01ResolverEnUnaCasillaLibreDevuelveAlVekQueEmergio() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicion = new Posicion(3, 3);
        Vek vek = new Luciernaga();
        EmergenciaVek emergencia = new EmergenciaVek(vek);

        // Act
        List<Vek> emergidos = emergencia.resolver(tablero.enCasilla(posicion), tablero);

        // Assert
        assertEquals(List.of(vek), emergidos);
    }

    @Test
    public void test02ResolverEnUnaCasillaOcupadaNoDevuelveNingunVek() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicion = new Posicion(3, 3);
        new Combate().colocarseEn(posicion, tablero);
        EmergenciaVek emergencia = new EmergenciaVek(new Luciernaga());

        // Act
        List<Vek> emergidos = emergencia.resolver(tablero.enCasilla(posicion), tablero);

        // Assert
        assertEquals(List.of(), emergidos);
    }
}