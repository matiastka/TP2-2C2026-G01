
package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.excepciones.MovimientoYaRealizadoException;
import edu.fiuba.algo3.modelo.unidades.veks.estado.EstadoVekConAtaquePreparado;
import edu.fiuba.algo3.modelo.unidades.veks.estado.EstadoVekDisponible;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Saltador;
import edu.fiuba.algo3.modelo.unidades.veks.estado.EstadoTurnoVek;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EstadoVekTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";

    @Test
    public void test01VekDisponibleSeMueveYSuEstadoBloqueaMovimientosSucesivos() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
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
    public void test02EstadoVekDisponibleCambiaAConAtaquePreparadoAlPrepararAtaque() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Saltador vek = new Saltador();
        vek.colocarseEn(new Posicion(1, 1), tablero);
        EstadoTurnoVek estadoInicial = new EstadoVekDisponible();

        // Act
        EstadoTurnoVek resultado = estadoInicial.prepararAtaque(vek, new Posicion(2, 1));

        // Assert
        assertEquals(EstadoVekConAtaquePreparado.class, resultado.getClass());
    }

    @Test
    public void test03EstadoVekConAtaquePreparadoRestauraAEstadoDisponibleAlResolverAtaque() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Saltador vek = new Saltador();
        vek.colocarseEn(new Posicion(1, 1), tablero);
        EstadoTurnoVek estadoConAtaque = new EstadoVekConAtaquePreparado();

        // Act
        EstadoTurnoVek resultado = estadoConAtaque.resolverAtaque(vek, tablero);

        // Assert
        assertEquals(EstadoVekDisponible.class, resultado.getClass());
    }
}