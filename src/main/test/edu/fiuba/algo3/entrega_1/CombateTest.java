
package edu.fiuba.algo3.entrega_1;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.excepciones.CaminoBloqueadoException;
import edu.fiuba.algo3.modelo.excepciones.DestinoNoAlcanzableException;
import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Luciernaga;

public class CombateTest {

    private static final String RUTA_MAPA_EDIFICIO = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_edificio.txt";

    @Test
    public void test01UnMechCombateSeMueve1ALaDerecha() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionMechOrigen = new Posicion(2, 3);
        Posicion posicionMechDestino = new Posicion(2, 4);
        Unidad mechCombate = new Combate();
        mechCombate.colocarseEn(posicionMechOrigen, tablero);

        // Act
        mechCombate.moverA(posicionMechDestino, tablero);

        // Assert
        assertSame(mechCombate, tablero.enCasilla(posicionMechDestino).obtenerOcupante());
    }

    @Test
    public void test02UnMechCombateSeMueve1Arriba() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionMechOrigen = new Posicion(2, 3);
        Posicion posicionMechDestino = new Posicion(1, 3);
        Unidad mechCombate = new Combate();
        mechCombate.colocarseEn(posicionMechOrigen, tablero);

        // Act
        mechCombate.moverA(posicionMechDestino, tablero);

        // Assert
        assertSame(mechCombate, tablero.enCasilla(posicionMechDestino).obtenerOcupante());
    }

    @Test
    public void test03AlMoverseElMechLaCasillaDeOrigenQuedaLibre() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionMechOrigen = new Posicion(2, 3);
        Posicion posicionMechDestino = new Posicion(2, 4);
        Unidad mechCombate = new Combate();
        mechCombate.colocarseEn(posicionMechOrigen, tablero);

        // Act
        mechCombate.moverA(posicionMechDestino, tablero);

        // Assert
        assertTrue(tablero.enCasilla(posicionMechOrigen).estaLibre());
    }

    @Test
    public void test04AlMoverseElMechQuedaEnLaCasillaDestino() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionMechOrigen = new Posicion(2, 3);
        Posicion posicionMechDestino = new Posicion(2, 4);
        Unidad mechCombate = new Combate();
        mechCombate.colocarseEn(posicionMechOrigen, tablero);

        // Act
        mechCombate.moverA(posicionMechDestino, tablero);

        // Assert
        assertSame(mechCombate, tablero.enCasilla(posicionMechDestino).obtenerOcupante());
    }

    @Test
    public void test05UnMechNoSeMueveFueraDeSuRango() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionMechDestino = new Posicion(4, 5);
        Posicion posicionMechOrigen = new Posicion(4, 1);
        Unidad mechCombate = new Combate();
        mechCombate.colocarseEn(posicionMechOrigen, tablero);

        // Act & Assert
        assertThrows(DestinoNoAlcanzableException.class, () -> mechCombate.moverA(posicionMechDestino, tablero));
    }

    @Test
    public void test06UnMechCombatePuedeMoverseExactamenteSuRangoMaximo() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionMechOrigen = new Posicion(4, 1);
        Posicion posicionRangoDestino = new Posicion(4, 4);
        Unidad mechCombate = new Combate();
        mechCombate.colocarseEn(posicionMechOrigen, tablero);

        // Act
        mechCombate.moverA(posicionRangoDestino, tablero);

        // Assert
        assertSame(mechCombate, tablero.enCasilla(posicionRangoDestino).obtenerOcupante());
    }

    @Test
    public void test07UnMechNoPuedeAtravesarAOtroMech() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionMech1Origen = new Posicion(4, 1);
        Posicion posicionMech2Origen = new Posicion(4, 2);
        Posicion posicionDestinoMech1 = new Posicion(4, 3);
        Unidad mechCombate = new Combate();
        Unidad otroMech = new Combate();
        mechCombate.colocarseEn(posicionMech1Origen, tablero);
        otroMech.colocarseEn(posicionMech2Origen, tablero);

        // Act & Assert
        assertThrows(CaminoBloqueadoException.class, () -> mechCombate.moverA(posicionDestinoMech1, tablero));
    }

    @Test
    public void test08UnMechNoPuedeAtravesarUnEdificioYSeQuedaEnElMismoLugar() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionMechOrigen = new Posicion(3, 1);
        Posicion posicionEdificio = new Posicion(3, 3);
        Unidad mechCombate = new Combate();
        mechCombate.colocarseEn(posicionMechOrigen, tablero);

        // Act & Assert
        assertThrows(CaminoBloqueadoException.class, () -> mechCombate.moverA(posicionEdificio, tablero));
    }

    @Test
    public void test09ElPunioHace2DeDanioAUnaLucierganaAdyacente() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionMechOrigen = new Posicion(4, 2);
        Posicion posicionVekOrigen = new Posicion(4, 3);
        Mech mechCombate = new Combate();
        Unidad vekLuciernaga = new Luciernaga();
        int vidaVekEsperada = 1;
        mechCombate.colocarseEn(posicionMechOrigen, tablero);
        vekLuciernaga.colocarseEn(posicionVekOrigen, tablero);

        // Act
        mechCombate.atacarA(posicionVekOrigen, tablero);

        // Assert
        assertEquals(vidaVekEsperada, vekLuciernaga.obtenerVida());
    }

    @Test
    public void test10ElPunioEmpujaAlObjetivoUnaCasillaEnDireccionAlGolpe() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionMechOrigen = new Posicion(4, 2);
        Posicion posicionVekOrigen = new Posicion(4, 3);
        Posicion posicionTrasEmpuje = new Posicion(4, 4);
        Mech mechCombate = new Combate();
        Unidad vekLuciernaga = new Luciernaga();
        mechCombate.colocarseEn(posicionMechOrigen, tablero);
        vekLuciernaga.colocarseEn(posicionVekOrigen, tablero);

        // Act
        mechCombate.atacarA(posicionVekOrigen, tablero);

        // Assert
        assertSame(vekLuciernaga, tablero.enCasilla(posicionTrasEmpuje).obtenerOcupante());
    }

    @Test
    public void test11TrasSerEmpujadoLaCasillaDondeEstabaQuedaLibre() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionMechOrigen = new Posicion(4, 2);
        Posicion posicionVekOrigen = new Posicion(4, 3);
        Mech mechCombate = new Combate();
        Unidad vekLuciernaga = new Luciernaga();
        mechCombate.colocarseEn(posicionMechOrigen, tablero);
        vekLuciernaga.colocarseEn(posicionVekOrigen, tablero);

        // Act
        mechCombate.atacarA(posicionVekOrigen, tablero);

        // Assert
        assertTrue(tablero.enCasilla(posicionVekOrigen).estaLibre());
    }

    @Test
    public void test12SeEmpujaAUnVekAlLimiteDelMapaElMismoQuedaEnElBorde() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionMechOrigen = new Posicion(6, 4);
        Posicion posicionVekOrigen = new Posicion(7, 4);
        Mech mechCombate = new Combate();
        Unidad vekLuciernaga = new Luciernaga();
        mechCombate.colocarseEn(posicionMechOrigen, tablero);
        vekLuciernaga.colocarseEn(posicionVekOrigen, tablero);

        // Act
        mechCombate.atacarA(posicionVekOrigen, tablero);

        // Assert
        assertSame(vekLuciernaga, tablero.enCasilla(posicionVekOrigen).obtenerOcupante());
    }

    @Test
    public void test13AtacarAUnVekNoAdyacenteNoLeQuitaVida() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionMechOrigen = new Posicion(4, 1);
        Posicion posicionVekOrigen = new Posicion(4, 3);
        Mech mechCombate = new Combate();
        Unidad vekLuciernaga = new Luciernaga();
        mechCombate.colocarseEn(posicionMechOrigen, tablero);
        vekLuciernaga.colocarseEn(posicionVekOrigen, tablero);

        // Act & Assert
        assertThrows(DestinoNoAlcanzableException.class, () -> mechCombate.atacarA(posicionVekOrigen, tablero));
    }

    @Test
    public void test14AtacarAUnVekNoAdyacenteNoLoEmpuja() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionMechOrigen = new Posicion(4, 1);
        Posicion posicionVekOrigen = new Posicion(4, 3);
        Mech mechCombate = new Combate();
        Unidad vekLuciernaga = new Luciernaga();
        mechCombate.colocarseEn(posicionMechOrigen, tablero);
        vekLuciernaga.colocarseEn(posicionVekOrigen, tablero);

        // Act & Assert
        assertThrows(DestinoNoAlcanzableException.class, () -> mechCombate.atacarA(posicionVekOrigen, tablero));
    }

    @Test
    public void test15AtacarUnaCasillaVaciaNoDebeLanzarUnError() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionMech = new Posicion(4, 2);
        Posicion posicionVacia = new Posicion(4, 3);
        Mech mechCombate = new Combate();
        mechCombate.colocarseEn(posicionMech, tablero);

        // Act & Assert
        assertDoesNotThrow(() -> mechCombate.atacarA(posicionVacia, tablero));
    }

    @Test
    public void test16ElPunioNoEmpujaAUnaUnidadContraUnEdificio() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionMech = new Posicion(3, 1);
        Posicion posicionVek = new Posicion(3, 2);
        Mech mechCombate = new Combate();
        Unidad vekLuciernaga = new Luciernaga();
        mechCombate.colocarseEn(posicionMech, tablero);
        vekLuciernaga.colocarseEn(posicionVek, tablero);

        // Act
        mechCombate.atacarA(posicionVek, tablero);

        // Assert
        assertSame(vekLuciernaga, tablero.enCasilla(posicionVek).obtenerOcupante());
    }

    @Test
    public void test17ElPunioEmpujaAUnaUnidadQueYaSeMovioEnElTurno() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionMech = new Posicion(4, 2);
        Posicion posicionVekOrigen = new Posicion(4, 4);
        Posicion posicionVekDestino = new Posicion(4, 3);
        Mech mechCombate = new Combate();
        Unidad vekLuciernaga = new Luciernaga();
        mechCombate.colocarseEn(posicionMech, tablero);
        vekLuciernaga.colocarseEn(posicionVekOrigen, tablero);
        vekLuciernaga.moverA(posicionVekDestino, tablero);

        // Act
        mechCombate.atacarA(posicionVekDestino, tablero);

        // Assert
        assertSame(vekLuciernaga, tablero.enCasilla(posicionVekOrigen).obtenerOcupante());
    }

    @Test
    public void test18CombateEmpiezaCon3DeVida() {
        // Arrange
        Combate combate = new Combate();
        int vidaEsperada = 3;

        // Act
        int vidaActual = combate.obtenerVida();

        // Assert
        assertEquals(vidaEsperada, vidaActual);
    }

    @Test
    public void test19CombateTieneRango3() {
        // Arrange
        Combate combate = new Combate();
        int rangoEsperado = 3;

        // Act
        int rangoActual = combate.obtenerRango();

        // Assert
        assertEquals(rangoEsperado, rangoActual);
    }

    @Test
    public void test20Recibir2DeDanioLoDejaCon1DeVida() {
        // Arrange
        Combate combate = new Combate();
        int danioARecibir = 2;
        int vidaEsperada = 1;

        // Act
        combate.recibirDanio(danioARecibir);
        int vidaActual = combate.obtenerVida();

        // Assert
        assertEquals(vidaEsperada, vidaActual);
    }
}