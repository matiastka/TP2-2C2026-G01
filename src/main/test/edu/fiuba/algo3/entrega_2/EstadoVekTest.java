
package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.mapa.ConstructorDeTablero;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
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
        vek.moverA(segundoDestino, tablero);

        // Assert
        assertEquals(primerDestino, vek.obtenerPosicion());
    }

    @Test
    public void test02EstadoVekDisponibleCambiaAConAtaquePreparadoAlPrepararAtaque() {
        // Arrange
        Saltador vek = new Saltador();
        EstadoTurnoVek estadoInicial = new EstadoVekDisponible();

        // Act
        EstadoTurnoVek nuevoEstado = estadoInicial.prepararAtaque(vek, Direccion.SUR);

        // Assert
        assertEquals(EstadoVekConAtaquePreparado.class, nuevoEstado.getClass());
    }

    @Test
    public void test03EstadoVekConAtaquePreparadoRestauraAEstadoDisponibleAlResolverAtaque() {
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_BASE);
        Saltador vek = new Saltador();
        vek.colocarseEn(new Posicion(1, 1), tablero);
        EstadoTurnoVek estadoConAtaque = new EstadoVekConAtaquePreparado();

        // Act
        EstadoTurnoVek nuevoEstado = estadoConAtaque.resolverAtaque(vek, tablero);

        // Assert
        assertEquals(EstadoVekDisponible.class, nuevoEstado.getClass());
    }
}