package edu.fiuba.algo3.modelo.partida;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;

public class EscuadronTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";

    @Test
    public void test01UnEscuadronConUnMechConVidaNoFueDestruido() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Mech combate = new Combate();
        combate.colocarseEn(new Posicion(3, 3), tablero);
        Escuadron escuadron = new Escuadron(List.of(combate));

        // Act
        escuadron.retirarDestruidos(tablero);

        // Assert
        assertFalse(escuadron.fueDestruido());
    }

    @Test
    public void test02SiSeRetiranTodosSusMechsElEscuadronFueDestruido() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Mech combate = new Combate();
        combate.colocarseEn(new Posicion(3, 3), tablero);
        Escuadron escuadron = new Escuadron(List.of(combate));
        combate.recibirDanio(5);

        // Act
        escuadron.retirarDestruidos(tablero);

        // Assert
        assertTrue(escuadron.fueDestruido());
    }

    @Test
    public void test03UnMechDestruidoSeRetiraDelTablero() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion posicion = new Posicion(3, 3);
        Mech combate = new Combate();
        combate.colocarseEn(posicion, tablero);
        Escuadron escuadron = new Escuadron(List.of(combate));
        combate.recibirDanio(5);

        // Act
        escuadron.retirarDestruidos(tablero);

        // Assert
        assertTrue(tablero.enCasilla(posicion).estaLibre());
    }
}