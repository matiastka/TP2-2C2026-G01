
package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.excepciones.AtaqueYaPreparadoException;
import edu.fiuba.algo3.modelo.excepciones.MovimientoYaRealizadoException;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.veks.estado.EstadoTurnoVek;
import edu.fiuba.algo3.modelo.unidades.veks.estado.EstadoVekConAtaquePreparado;
import edu.fiuba.algo3.modelo.unidades.veks.estado.EstadoVekDisponible;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Saltador;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EstadoVekTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";

    @Test
    public void test01VekDisponibleSeMueveYSuEstadoBloqueaMovimientosSucesivos() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion posInicial = new Posicion(1, 1);
        Posicion primerDestino = new Posicion(1, 2);
        Posicion segundoDestino = new Posicion(1, 3);
        Saltador vek = new Saltador();
        vek.colocarseEn(posInicial, tablero);
        vek.moverA(primerDestino, tablero);

        // Act & Assert
        assertThrows(MovimientoYaRealizadoException.class, () -> vek.moverA(segundoDestino, tablero));
    }

    @Test
    public void test02ConElAtaquePreparadoElVekYaNoPuedeMoverse() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Saltador vek = new Saltador();
        vek.colocarseEn(new Posicion(1, 1), tablero);
        EstadoTurnoVek estadoInicial = new EstadoVekDisponible();
        EstadoTurnoVek resultado = estadoInicial.prepararAtaque(vek, new Posicion(2, 1));

        // Act & Assert
        assertThrows(AtaqueYaPreparadoException.class, () -> resultado.mover(vek, new Posicion(1, 2), tablero));
    }

    @Test
    public void test03DespuesDeResolverSuAtaqueElVekPuedeVolverAMoverse() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Saltador vek = new Saltador();
        vek.colocarseEn(new Posicion(1, 1), tablero);
        EstadoTurnoVek estadoConAtaque = new EstadoVekConAtaquePreparado();
        EstadoTurnoVek resultado = estadoConAtaque.resolverAtaque(vek, tablero);

        // Act
        resultado.mover(vek, new Posicion(1, 2), tablero);

        // Assert
        assertEquals(new Posicion(1, 2), vek.obtenerPosicion());
    }
}