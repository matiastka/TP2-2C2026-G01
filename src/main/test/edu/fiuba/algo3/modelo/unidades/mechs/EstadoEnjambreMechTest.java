
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

import static org.junit.jupiter.api.Assertions.assertThrows;

public class EstadoEnjambreMechTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";

    @Test
    public void test01DespuesDeMoverseElMechNoPuedeVolverAMoverse() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(0, 0), tablero);
        EstadoTurnoMech estado = new EstadoDisponible();
        EstadoTurnoMech resultado = estado.mover(mech, new Posicion(0, 1), tablero);

        // Act & Assert
        assertThrows(MovimientoYaRealizadoException.class, () -> resultado.mover(mech, new Posicion(0, 2), tablero));
    }

    @Test
    public void test02DespuesDeAtacarSinMoverseElMechYaNoPuedeMoverse() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Mech mech = new Combate();
        Luciernaga vek = new Luciernaga();
        mech.colocarseEn(new Posicion(0, 0), tablero);
        vek.colocarseEn(new Posicion(0, 1), tablero);
        EstadoTurnoMech estado = new EstadoDisponible();

        EstadoTurnoMech resultado = estado.atacar(mech, new Posicion(0, 1), tablero);

        // Act & Assert
        assertThrows(TurnoAgotadoException.class, () -> resultado.mover(mech, new Posicion(1, 0), tablero));
    }

    @Test
    public void test03DespuesDeMoverseYAtacarElMechYaNoPuedeMoverse() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Combate mech = new Combate();
        Luciernaga vek = new Luciernaga();
        mech.colocarseEn(new Posicion(0, 0), tablero);
        vek.colocarseEn(new Posicion(0, 1), tablero);
        EstadoTurnoMech estado = new EstadoYaSeMovio();

        EstadoTurnoMech resultado = estado.atacar(mech, new Posicion(0, 1), tablero);

        // Act & Assert
        assertThrows(TurnoAgotadoException.class, () -> resultado.mover(mech, new Posicion(1, 0), tablero));
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