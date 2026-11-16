
package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.mapa.ConstructorDeTablero;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.Luciernaga;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EstadoTurnoMechTest {

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
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Mech mech = new Combate();

        mech.colocarseEn(new Posicion(0, 0), tablero);

        EstadoTurnoMech estado = new EstadoDisponible();

        // Act
        EstadoTurnoMech nuevoEstado = estado.mover(mech, new Posicion(0, 1), tablero);

        // Assert
        assertEquals(EstadoYaSeMovio.class, nuevoEstado.getClass());
    }

    @Test
    public void test02EstadoDisponibleAlAtacarExitosamenteRetornaEstadoTurnoAgotado() {
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Mech mech = new Combate();
        Luciernaga vek = new Luciernaga();

        mech.colocarseEn(new Posicion(0, 0), tablero);
        vek.colocarseEn(new Posicion(0, 1), tablero);

        EstadoTurnoMech estado = new EstadoDisponible();

        // Act
        EstadoTurnoMech nuevoEstado = estado.atacar(mech, new Posicion(0, 1), tablero);

        // Assert
        assertEquals(EstadoTurnoAgotado.class, nuevoEstado.getClass());
    }

    @Test
    public void test03EstadoYaSeMovioAlAtacarRetornaEstadoTurnoAgotado() {
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Combate mech = new Combate();
        Luciernaga vek = new Luciernaga();

        mech.colocarseEn(new Posicion(0, 0), tablero);
        vek.colocarseEn(new Posicion(0, 1), tablero);

        EstadoTurnoMech estado = new EstadoYaSeMovio();

        // Act
        EstadoTurnoMech nuevoEstado = estado.atacar(mech, new Posicion(0, 1), tablero);

        // Assert
        assertEquals(EstadoTurnoAgotado.class, nuevoEstado.getClass());
    }

    @Test
    public void test04EstadoYaSeMovioAlIntentarMoverseNuevamenteDevuelveElMismoEstado() {
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Combate mech = new Combate();

        mech.colocarseEn(new Posicion(0, 0), tablero);

        EstadoTurnoMech estado = new EstadoYaSeMovio();

        // Act
        EstadoTurnoMech nuevoEstado = estado.mover(mech, new Posicion(0, 1), tablero);

        // Assert
        assertSame(estado, nuevoEstado);
    }

    @Test
    public void test05EstadoTurnoAgotadoAlIntentarMoverseDevuelveElMismoEstado() {
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Combate mech = new Combate();

        mech.colocarseEn(new Posicion(0, 0), tablero);

        EstadoTurnoMech estado = new EstadoTurnoAgotado();

        // Act
        EstadoTurnoMech nuevoEstado = estado.mover(mech, new Posicion(0, 1), tablero);

        // Assert
        assertSame(estado, nuevoEstado);
    }

    @Test
    public void test06EstadoTurnoAgotadoAlIntentarAtacarNuevamenteDevuelveElMismoEstado() {
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Combate mech = new Combate();

        mech.colocarseEn(new Posicion(0, 0), tablero);

        EstadoTurnoMech estado = new EstadoTurnoAgotado();

        // Act
        EstadoTurnoMech nuevoEstado = estado.atacar(mech, new Posicion(0, 1), tablero);

        // Assert
        assertSame(estado, nuevoEstado);
    }
}