package edu.fiuba.algo3.modelo.tablero;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.direcciones.Este;
import edu.fiuba.algo3.modelo.unidades.ataques.efectos.EfectoEmpujar;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import edu.fiuba.algo3.modelo.unidades.movimientos.MovimientoTerrestre;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

public class TableroTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";
    private static final String RUTA_MAPA_AGUA = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_agua.txt";
    private static final String RUTA_MAPA_ACIDO = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_acido.txt";

    @Test
    public void test01RecibirTransitoEnUnaCasillaLibreLaDejaAtravesar() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion posicion = new Posicion(3, 3);
        Transito transito = mock(Transito.class);

        // Act
        tablero.recibirTransitoEn(posicion, transito);

        // Assert
        verify(transito).atravesar(tablero.enCasilla(posicion));
    }

    @Test
    public void test02RecibirTransitoFueraDelTableroNoHaceNada() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Transito transito = mock(Transito.class);

        // Act
        tablero.recibirTransitoEn(new Posicion(8, 0), transito);

        // Assert
        verifyNoInteractions(transito);
    }

    @Test
    public void test03UnaCasillaSinOcupanteEstaLibre() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);

        // Act
        boolean libre = tablero.estaLibre(new Posicion(3, 3));

        // Assert
        assertTrue(libre);
    }

    @Test
    public void test04UnaCasillaConUnMechNoEstaLibre() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion posicion = new Posicion(3, 3);
        new Combate().colocarseEn(posicion, tablero);

        // Act
        boolean libre = tablero.estaLibre(posicion);

        // Assert
        assertFalse(libre);
    }

    @Test
    public void test05AplicarDanioEnUnaPosicionDaniaAlOcupante() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion posicion = new Posicion(3, 3);
        Mech mech = new Combate();
        mech.colocarseEn(posicion, tablero);

        // Act
        tablero.aplicarDanioEn(posicion, 1);

        // Assert
        assertEquals(2, mech.obtenerVida());
    }

    @Test
    public void test06AplicarEfectoDeImpactoEnUnaPosicionEmpujaAlOcupante() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(3, 3), tablero);

        // Act
        tablero.aplicarEfectoDeImpactoEn(new Posicion(3, 3), new EfectoEmpujar(), new Este());

        // Assert
        assertEquals(new Posicion(3, 4), mech.obtenerPosicion());
    }

    @Test
    public void test07AplicarEfectoDeImpactoFueraDelTableroNoHaceNada() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        EfectoImpacto efecto = mock(EfectoImpacto.class);

        // Act
        tablero.aplicarEfectoDeImpactoEn(new Posicion(0, 8), efecto, new Este());

        // Assert
        verifyNoInteractions(efecto);
    }

    @Test
    public void test08AbandonarUnaCasillaDeAguaHaceSalirDelAguaAlOcupante() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_AGUA);
        Ocupante ocupante = mock(Ocupante.class);

        // Act
        tablero.abandonarCasilla(new Posicion(2, 4), ocupante);

        // Assert
        verify(ocupante).salirDelAgua();
    }

    @Test
    public void test09AplicarElEfectoDeTerrenoDeUnaCasillaDeAcidoContaminaAlOcupante() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_ACIDO);
        Ocupante ocupante = mock(Ocupante.class);

        // Act
        tablero.aplicarEfectoDeTerrenoEn(new Posicion(1, 2), ocupante);

        // Assert
        verify(ocupante).caerEnAcido();
    }

    @Test
    public void test10UnaCasillaDeSueloLibreSeOfreceComoDestino() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion destino = new Posicion(3, 3);
        List<Posicion> destinos = new ArrayList<>();

        // Act
        tablero.ofrecerDestinoAMovimiento(destino, new MovimientoTerrestre(), destinos);

        // Assert
        assertEquals(List.of(destino), destinos);
    }

    @Test
    public void test11UnaCasillaDeAguaNoSeOfreceComoDestinoParaUnMovimientoTerrestre() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_AGUA);
        List<Posicion> destinos = new ArrayList<>();

        // Act
        tablero.ofrecerDestinoAMovimiento(new Posicion(2, 4), new MovimientoTerrestre(), destinos);

        // Assert
        assertEquals(List.of(), destinos);
    }
}
