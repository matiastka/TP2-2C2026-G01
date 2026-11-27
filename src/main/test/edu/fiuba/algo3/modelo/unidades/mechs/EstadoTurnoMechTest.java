
package edu.fiuba.algo3.modelo.unidades.mechs;

import edu.fiuba.algo3.modelo.excepciones.MovimientoYaRealizadoException;
import edu.fiuba.algo3.modelo.excepciones.TurnoAgotadoException;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.estado.turno.EstadoDisponible;
import edu.fiuba.algo3.modelo.unidades.mechs.estado.turno.EstadoTurnoAgotado;
import edu.fiuba.algo3.modelo.unidades.mechs.estado.turno.EstadoTurnoMech;
import edu.fiuba.algo3.modelo.unidades.mechs.estado.turno.EstadoYaSeMovio;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Luciernaga;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EstadoTurnoMechTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";

    @Test
    public void test01EstadoDisponibleAlMoverseExitosamenteRetornaEstadoYaSeMovio() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
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
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
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
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
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
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Combate mech = new Combate();
        mech.colocarseEn(new Posicion(0, 0), tablero);
        EstadoTurnoMech estado = new EstadoYaSeMovio();

        // Act & Assert
        assertThrows(MovimientoYaRealizadoException.class, () -> estado.mover(mech, new Posicion(0, 1), tablero));
    }

    @Test
    public void test05EstadoTurnoAgotadoAlIntentarMoverseRetornaRechazo() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Combate mech = new Combate();
        mech.colocarseEn(new Posicion(0, 0), tablero);
        EstadoTurnoMech estado = new EstadoTurnoAgotado();

        // Act & Assert
        assertThrows(TurnoAgotadoException.class, () -> estado.mover(mech, new Posicion(0, 1), tablero));
    }

    @Test
    public void test06EstadoTurnoAgotadoAlIntentarAtacarNuevamenteRetornaRechazo() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Combate mech = new Combate();
        mech.colocarseEn(new Posicion(0, 0), tablero);
        EstadoTurnoMech estado = new EstadoTurnoAgotado();

        // Act & Assert
        assertThrows(TurnoAgotadoException.class, () -> estado.atacar(mech, new Posicion(0, 1), tablero));
    }
}