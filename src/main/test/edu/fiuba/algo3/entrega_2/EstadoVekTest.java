
package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.mapa.ConstructorDeTablero;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Sur;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.MotivoRechazoAccion;
import edu.fiuba.algo3.modelo.unidades.ResultadoAccion;
import edu.fiuba.algo3.modelo.unidades.veks.EstadoTurnoVek;
import edu.fiuba.algo3.modelo.unidades.veks.EstadoVekConAtaquePreparado;
import edu.fiuba.algo3.modelo.unidades.veks.EstadoVekDisponible;
import edu.fiuba.algo3.modelo.unidades.veks.Saltador;
import org.junit.jupiter.api.Test;

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
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_BASE);
        Saltador vek = new Saltador();
        Posicion posInicial = new Posicion(1, 1);
        Posicion primerDestino = new Posicion(1, 2);
        Posicion segundoDestino = new Posicion(1, 3);
        
        vek.colocarseEn(posInicial, tablero);

        // Act
        vek.moverA(primerDestino, tablero);
        ResultadoAccion<Void> resultadoMovimiento = vek.moverA(segundoDestino, tablero);
        assertRejected(resultadoMovimiento, MotivoRechazoAccion.MOVIMIENTO_YA_REALIZADO);

        // Assert
        assertEquals(primerDestino, vek.obtenerPosicion());
    }

    @Test
    public void test02EstadoVekDisponibleCambiaAConAtaquePreparadoAlPrepararAtaque() {
        // Arrange
        Saltador vek = new Saltador();
        EstadoTurnoVek estadoInicial = new EstadoVekDisponible();

        // Act
        ResultadoAccion<EstadoTurnoVek> resultado = estadoInicial.prepararAtaque(vek, new Sur());

        // Assert
        assertEquals(ResultadoAccion.Exito.class, resultado.getClass());
        assertEquals(EstadoVekConAtaquePreparado.class, ((ResultadoAccion.Exito<?>) resultado).valor().getClass());
    }

    @Test
    public void test03EstadoVekConAtaquePreparadoRestauraAEstadoDisponibleAlResolverAtaque() {
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_BASE);
        Saltador vek = new Saltador();
        vek.colocarseEn(new Posicion(1, 1), tablero);
        EstadoTurnoVek estadoConAtaque = new EstadoVekConAtaquePreparado();

        // Act
        ResultadoAccion<EstadoTurnoVek> resultado = estadoConAtaque.resolverAtaque(vek, tablero);

        // Assert
        assertEquals(ResultadoAccion.Exito.class, resultado.getClass());
        assertEquals(EstadoVekDisponible.class, ((ResultadoAccion.Exito<?>) resultado).valor().getClass());
    }

    private void assertRejected(ResultadoAccion<?> resultado, MotivoRechazoAccion motivo) {
        assertEquals(ResultadoAccion.Rechazo.class, resultado.getClass());
        assertEquals(motivo, ((ResultadoAccion.Rechazo<?>) resultado).motivo());
    }
}