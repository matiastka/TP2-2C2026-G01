
package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.unidades.*;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.Luciernaga;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EstadoTurnoTest {

    private static final String[] MAPA = {
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
    public void test01EstadoDisponibleAlMoverseExitosamenteRetornaEstadoYaSeMovio() {
        // Arrange
        Tablero tablero = new Tablero(MAPA);
        Combate mech = new Combate(new Posicion(0, 0));
        tablero.ocuparCasilla(new Posicion(0, 0), mech);
        EstadoTurno estado = new EstadoDisponible();

        // Act
        EstadoTurno nuevoEstado = estado.mover(mech, new Posicion(0, 1), tablero);

        // Assert
        assertEquals(EstadoYaSeMovio.class, nuevoEstado.getClass());
    }

    @Test
    public void test02EstadoDisponibleAlAtacarExitosamenteRetornaEstadoTurnoAgotado() {
        // Arrange
        Tablero tablero = new Tablero(MAPA);
        Combate mech = new Combate(new Posicion(0, 0));
        Luciernaga vek = new Luciernaga(new Posicion(0, 1));
        tablero.ocuparCasilla(new Posicion(0, 0), mech);
        tablero.ocuparCasilla(new Posicion(0, 1), vek);
        EstadoTurno estado = new EstadoDisponible();

        // Act
        EstadoTurno nuevoEstado = estado.atacar(mech, new Posicion(0, 1), tablero);

        // Assert
        assertEquals(EstadoTurnoAgotado.class, nuevoEstado.getClass());
    }

    @Test
    public void test03EstadoYaSeMovioAlAtacarRetornaEstadoTurnoAgotado() {
        // Arrange
        Tablero tablero = new Tablero(MAPA);
        Combate mech = new Combate(new Posicion(0, 0));
        Luciernaga vek = new Luciernaga(new Posicion(0, 1));
        tablero.ocuparCasilla(new Posicion(0, 0), mech);
        tablero.ocuparCasilla(new Posicion(0, 1), vek);
        EstadoTurno estado = new EstadoYaSeMovio();

        // Act
        EstadoTurno nuevoEstado = estado.atacar(mech, new Posicion(0, 1), tablero);

        // Assert
        assertEquals(EstadoTurnoAgotado.class, nuevoEstado.getClass());
    }

    @Test
    public void test04EstadoYaSeMovioAlIntentarMoverseNuevamenteDevuelveElMismoEstado() {
        // Arrange
        Tablero tablero = new Tablero(MAPA);
        Combate mech = new Combate(new Posicion(0, 0));
        tablero.ocuparCasilla(new Posicion(0, 0), mech);
        EstadoTurno estado = new EstadoYaSeMovio();

        // Act
        EstadoTurno nuevoEstado = estado.mover(mech, new Posicion(0, 1), tablero);

        // Assert
        assertSame(estado, nuevoEstado);
    }

    @Test
    public void test05EstadoTurnoAgotadoAlIntentarMoverseDevuelveElMismoEstado() {
        // Arrange
        Tablero tablero = new Tablero(MAPA);
        Combate mech = new Combate(new Posicion(0, 0));
        tablero.ocuparCasilla(new Posicion(0, 0), mech);
        EstadoTurno estado = new EstadoTurnoAgotado();

        // Act
        EstadoTurno nuevoEstado = estado.mover(mech, new Posicion(0, 1), tablero);

        // Assert
        assertSame(estado, nuevoEstado);
    }

    @Test
    public void test06EstadoTurnoAgotadoAlIntentarAtacarNuevamenteDevuelveElMismoEstado() {
        // Arrange
        Tablero tablero = new Tablero(MAPA);
        Combate mech = new Combate(new Posicion(0, 0));
        tablero.ocuparCasilla(new Posicion(0, 0), mech);
        EstadoTurno estado = new EstadoTurnoAgotado();

        // Act
        EstadoTurno nuevoEstado = estado.atacar(mech, new Posicion(0, 1), tablero);

        // Assert
        assertSame(estado, nuevoEstado);
    }
}