
package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.excepciones.MovimientoYaRealizadoException;
import edu.fiuba.algo3.modelo.unidades.veks.ia.EstadoTurnoVek;
import edu.fiuba.algo3.modelo.unidades.veks.EstadoVekConAtaquePreparado;
import edu.fiuba.algo3.modelo.unidades.veks.EstadoVekDisponible;
import edu.fiuba.algo3.modelo.unidades.veks.Saltador;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EstadoVekTest {

    private static final String[] MAPA_BASE = {
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS"
    };

    @Test
    public void test01VekDisponibleSeMueveYSuEstadoBloqueaMovimientosSucesivos() {
        // Arrange
        Tablero tablero = new ConstructorTablero().construir(MAPA_BASE);


        Posicion posInicial = new Posicion(1, 1);
        Posicion primerDestino = new Posicion(1, 2);
        Posicion segundoDestino = new Posicion(1, 3);

        Saltador vek = new Saltador();

        vek.colocarseEn(posInicial, tablero);

        // Act
        vek.moverA(primerDestino, tablero);

        assertThrows(MovimientoYaRealizadoException.class, () -> vek.moverA(segundoDestino, tablero));

    }

    @Test
    public void test02EstadoVekDisponibleCambiaAConAtaquePreparadoAlPrepararAtaque() {
        // Arrange
        Tablero tablero = new ConstructorTablero().construir(MAPA_BASE);

        Saltador vek = new Saltador();

        vek.colocarseEn(new Posicion(1,1), tablero);

        EstadoTurnoVek estadoInicial = new EstadoVekDisponible();

        // Act
        EstadoTurnoVek resultado = estadoInicial.prepararAtaque(vek, new Posicion(2,1));

        // Assert
        assertEquals(EstadoVekConAtaquePreparado.class, resultado.getClass());
    }

    @Test
    public void test03EstadoVekConAtaquePreparadoRestauraAEstadoDisponibleAlResolverAtaque() {
        // Arrange
        Tablero tablero = new ConstructorTablero().construir(MAPA_BASE);
        Saltador vek = new Saltador();
        vek.colocarseEn(new Posicion(1, 1), tablero);
        EstadoTurnoVek estadoConAtaque = new EstadoVekConAtaquePreparado();

        // Act
        EstadoTurnoVek resultado = estadoConAtaque.resolverAtaque(vek, tablero);

        // Assert
        assertEquals(EstadoVekDisponible.class, resultado.getClass());
    }
}
