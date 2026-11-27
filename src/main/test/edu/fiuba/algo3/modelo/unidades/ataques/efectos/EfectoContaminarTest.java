
package edu.fiuba.algo3.modelo.unidades.ataques.efectos;

import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.direcciones.Este;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Avispon;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Luciernaga;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EfectoContaminarTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";

    @Test
    public void test01AplicarContaminaAUnMechYDuplicaElSiguienteDanio() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion pos = new Posicion(3, 3);
        Mech mech = new Combate();
        mech.colocarseEn(pos, tablero);
        EfectoContaminar efecto = new EfectoContaminar();

        // Act
        efecto.aplicar(mech, pos, new Este(), tablero);
        mech.recibirDanio(1);

        // Assert
        assertEquals(1, mech.obtenerVida());
    }

    @Test
    public void test02AplicarContaminaAUnaLuciernagaYDuplicaElSiguienteDanio() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion pos = new Posicion(3, 3);
        Vek luciernaga = new Luciernaga();
        luciernaga.colocarseEn(pos, tablero);
        EfectoContaminar efecto = new EfectoContaminar();

        // Act
        efecto.aplicar(luciernaga, pos, new Este(), tablero);
        luciernaga.recibirDanio(1);

        // Assert
        assertEquals(1, luciernaga.obtenerVida());
    }

    @Test
    public void test03AplicarNoContaminaAUnAvisponPorSerVolador() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion pos = new Posicion(3, 3);
        Vek avispon = new Avispon();
        avispon.colocarseEn(pos, tablero);
        EfectoContaminar efecto = new EfectoContaminar();

        // Act
        efecto.aplicar(avispon, pos, new Este(), tablero);
        avispon.recibirDanio(1);

        // Assert
        assertEquals(1, avispon.obtenerVida());
    }
}