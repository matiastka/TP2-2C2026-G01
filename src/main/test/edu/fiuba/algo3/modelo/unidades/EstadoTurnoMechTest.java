
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
        ResultadoAccion<EstadoTurnoMech> resultado = estado.mover(mech, new Posicion(0, 1), tablero);

        // Assert
        assertEquals(ResultadoAccion.Exito.class, resultado.getClass());
        assertEquals(EstadoYaSeMovio.class, ((ResultadoAccion.Exito<?>) resultado).valor().getClass());
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
        ResultadoAccion<EstadoTurnoMech> resultado = estado.atacar(mech, new Posicion(0, 1), tablero);

        // Assert
        assertEquals(ResultadoAccion.Exito.class, resultado.getClass());
        assertEquals(EstadoTurnoAgotado.class, ((ResultadoAccion.Exito<?>) resultado).valor().getClass());
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
        ResultadoAccion<EstadoTurnoMech> resultado = estado.atacar(mech, new Posicion(0, 1), tablero);

        // Assert
        assertEquals(ResultadoAccion.Exito.class, resultado.getClass());
        assertEquals(EstadoTurnoAgotado.class, ((ResultadoAccion.Exito<?>) resultado).valor().getClass());
    }

    @Test
    public void test04EstadoYaSeMovioAlIntentarMoverseNuevamenteRetornaRechazo() {
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Combate mech = new Combate();

        mech.colocarseEn(new Posicion(0, 0), tablero);

        EstadoTurnoMech estado = new EstadoYaSeMovio();

        // Act
        ResultadoAccion<EstadoTurnoMech> resultado = estado.mover(mech, new Posicion(0, 1), tablero);
        assertRejected(resultado, MotivoRechazoAccion.MOVIMIENTO_YA_REALIZADO);
    }

    @Test
    public void test05EstadoTurnoAgotadoAlIntentarMoverseRetornaRechazo() {
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Combate mech = new Combate();

        mech.colocarseEn(new Posicion(0, 0), tablero);

        EstadoTurnoMech estado = new EstadoTurnoAgotado();

        // Act
        ResultadoAccion<EstadoTurnoMech> resultado = estado.mover(mech, new Posicion(0, 1), tablero);
        assertRejected(resultado, MotivoRechazoAccion.TURNO_AGOTADO);
    }

    @Test
    public void test06EstadoTurnoAgotadoAlIntentarAtacarNuevamenteRetornaRechazo() {
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Combate mech = new Combate();

        mech.colocarseEn(new Posicion(0, 0), tablero);

        EstadoTurnoMech estado = new EstadoTurnoAgotado();

        // Act
        ResultadoAccion<EstadoTurnoMech> resultado = estado.atacar(mech, new Posicion(0, 1), tablero);
        assertRejected(resultado, MotivoRechazoAccion.TURNO_AGOTADO);
    }

    private void assertRejected(ResultadoAccion<?> resultado, MotivoRechazoAccion motivo) {
        assertEquals(ResultadoAccion.Rechazo.class, resultado.getClass());
        assertEquals(motivo, ((ResultadoAccion.Rechazo<?>) resultado).motivo());
    }
}