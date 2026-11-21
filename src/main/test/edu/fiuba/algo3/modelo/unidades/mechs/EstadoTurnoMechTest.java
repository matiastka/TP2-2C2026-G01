
package edu.fiuba.algo3.modelo.unidades.mechs;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.unidades.excepciones.MovimientoYaRealizadoException;
import edu.fiuba.algo3.modelo.unidades.excepciones.TurnoAgotadoException;
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
        Tablero tablero = new ConstructorTablero().construir(MAPA);

        Mech mech = new Combate();

        mech.colocarseEn(new Posicion(0, 0), tablero);

        EstadoTurnoMech estado = new EstadoDisponible();

        // Act
        EstadoTurnoMech resultado = estado.mover(mech, new Posicion(0, 1), tablero);

        // Assert
        assertEquals(EstadoYaSeMovio.class, resultado.getClass());
    }

    @Test
    public void test02EstadoDisponibleAlAtacarExitosamenteRetornaEstadoTurnoAgotado() {
        // Arrange
        Tablero tablero = new ConstructorTablero().construir(MAPA);

        Mech mech = new Combate();
        Luciernaga vek = new Luciernaga();

        mech.colocarseEn(new Posicion(0, 0), tablero);
        vek.colocarseEn(new Posicion(0, 1), tablero);

        EstadoTurnoMech estado = new EstadoDisponible();

        // Act
        EstadoTurnoMech resultado = estado.atacar(mech, new Posicion(0, 1), tablero);

        // Assert
        assertEquals(EstadoTurnoAgotado.class, resultado.getClass());
    }

    @Test
    public void test03EstadoYaSeMovioAlAtacarRetornaEstadoTurnoAgotado() {
        // Arrange
        Tablero tablero = new ConstructorTablero().construir(MAPA);

        Combate mech = new Combate();
        Luciernaga vek = new Luciernaga();

        mech.colocarseEn(new Posicion(0, 0), tablero);
        vek.colocarseEn(new Posicion(0, 1), tablero);

        EstadoTurnoMech estado = new EstadoYaSeMovio();

        // Act
        EstadoTurnoMech resultado = estado.atacar(mech, new Posicion(0, 1), tablero);

        // Assert
        assertEquals(EstadoTurnoAgotado.class, resultado.getClass());
    }

    @Test
    public void test04EstadoYaSeMovioAlIntentarMoverseNuevamenteRetornaRechazo() {
        // Arrange
        Tablero tablero = new ConstructorTablero().construir(MAPA);

        Combate mech = new Combate();

        mech.colocarseEn(new Posicion(0, 0), tablero);

        EstadoTurnoMech estado = new EstadoYaSeMovio();

        // Act
        assertThrows(MovimientoYaRealizadoException.class, () -> estado.mover(mech, new Posicion(0, 1), tablero));
    }

    @Test
    public void test05EstadoTurnoAgotadoAlIntentarMoverseRetornaRechazo() {
        // Arrange
        Tablero tablero = new ConstructorTablero().construir(MAPA);

        Combate mech = new Combate();

        mech.colocarseEn(new Posicion(0, 0), tablero);

        EstadoTurnoMech estado = new EstadoTurnoAgotado();

        // Act
        assertThrows(TurnoAgotadoException.class, () -> estado.mover(mech, new Posicion(0, 1), tablero));
    }

    @Test
    public void test06EstadoTurnoAgotadoAlIntentarAtacarNuevamenteRetornaRechazo() {
        // Arrange
        Tablero tablero = new ConstructorTablero().construir(MAPA);

        Combate mech = new Combate();

        mech.colocarseEn(new Posicion(0, 0), tablero);

        EstadoTurnoMech estado = new EstadoTurnoAgotado();

        // Act
        assertThrows(TurnoAgotadoException.class, () -> estado.atacar(mech, new Posicion(0, 1), tablero));
    }
}
