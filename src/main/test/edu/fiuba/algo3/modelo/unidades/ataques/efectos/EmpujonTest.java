
package edu.fiuba.algo3.modelo.unidades.ataques.efectos;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmpujonTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";
    private static final String RUTA_MAPA_EDIFICIO = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_edificio.txt";

    @Test
    public void test01AtravesarDesplazaAlOcupanteEmpujadoALaCasillaDestino() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Vek empujado = new Luciernaga();
        empujado.colocarseEn(new Posicion(3, 3), tablero);
        Posicion destino = new Posicion(3, 4);
        Empujon empujon = new Empujon(empujado, tablero);

        // Act
        empujon.atravesar(tablero.enCasilla(destino));

        // Assert
        assertEquals(destino, empujado.obtenerPosicion());
    }

    @Test
    public void test02ChocarConOcupanteNoDesplazaAlOcupanteEmpujado() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion origen = new Posicion(3, 3);
        Posicion posObstaculo = new Posicion(3, 4);
        Vek empujado = new Luciernaga();
        Vek obstaculo = new Luciernaga();
        empujado.colocarseEn(origen, tablero);
        obstaculo.colocarseEn(posObstaculo, tablero);
        Empujon empujon = new Empujon(empujado, tablero);

        // Act
        empujon.chocarConOcupante(tablero.enCasilla(posObstaculo));

        // Assert
        assertEquals(origen, empujado.obtenerPosicion());
    }

    @Test
    public void test03ChocarConOcupanteDaniaEn1AlOcupanteEmpujado() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion origen = new Posicion(3, 3);
        Posicion posObstaculo = new Posicion(3, 4);
        Vek empujado = new Luciernaga();
        Vek obstaculo = new Luciernaga();
        empujado.colocarseEn(origen, tablero);
        obstaculo.colocarseEn(posObstaculo, tablero);
        Empujon empujon = new Empujon(empujado, tablero);

        // Act
        empujon.chocarConOcupante(tablero.enCasilla(posObstaculo));

        // Assert
        assertEquals(2, empujado.obtenerVida());
    }

    @Test
    public void test04ChocarConOcupanteDaniaEn1AlOcupanteDeLaCasilla() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion origen = new Posicion(3, 3);
        Posicion posObstaculo = new Posicion(3, 4);
        Vek empujado = new Luciernaga();
        Vek obstaculo = new Luciernaga();
        empujado.colocarseEn(origen, tablero);
        obstaculo.colocarseEn(posObstaculo, tablero);
        Empujon empujon = new Empujon(empujado, tablero);

        // Act
        empujon.chocarConOcupante(tablero.enCasilla(posObstaculo));

        // Assert
        assertEquals(2, obstaculo.obtenerVida());
    }

    @Test
    public void test05ChocarConTerrenoNoDesplazaAlOcupanteEmpujado() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion origen = new Posicion(3, 2);
        Posicion posEdificio = new Posicion(3, 3);
        Vek empujado = new Luciernaga();
        empujado.colocarseEn(origen, tablero);
        Empujon empujon = new Empujon(empujado, tablero);

        // Act
        empujon.chocarConTerreno(tablero.enCasilla(posEdificio));

        // Assert
        assertEquals(origen, empujado.obtenerPosicion());
    }

    @Test
    public void test06ChocarConTerrenoDaniaEn1AlOcupanteEmpujado() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion origen = new Posicion(3, 2);
        Posicion posEdificio = new Posicion(3, 3);
        Vek empujado = new Luciernaga();
        empujado.colocarseEn(origen, tablero);
        Empujon empujon = new Empujon(empujado, tablero);

        // Act
        empujon.chocarConTerreno(tablero.enCasilla(posEdificio));

        // Assert
        assertEquals(2, empujado.obtenerVida());
    }

    @Test
    public void test07ChocarConTerrenoDaniaAlTerrenoDeLaCasilla() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        int redInicial = tablero.obtenerRedElectrica();
        Posicion origen = new Posicion(3, 2);
        Posicion posEdificio = new Posicion(3, 3);
        Vek empujado = new Luciernaga();
        empujado.colocarseEn(origen, tablero);
        Empujon empujon = new Empujon(empujado, tablero);

        // Act
        empujon.chocarConTerreno(tablero.enCasilla(posEdificio));

        // Assert
        assertEquals(redInicial - 1, tablero.obtenerRedElectrica());
    }
}