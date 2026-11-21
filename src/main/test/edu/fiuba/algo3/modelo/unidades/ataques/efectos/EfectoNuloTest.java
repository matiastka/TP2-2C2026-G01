
package edu.fiuba.algo3.modelo.unidades.ataques.efectos;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.direcciones.Este;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.veks.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EfectoNuloTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";

    @Test
    public void test01AplicarNoModificaLaVidaDelOcupante() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion pos = new Posicion(3, 3);
        Vek objetivo = new Luciernaga();
        objetivo.colocarseEn(pos, tablero);
        EfectoNulo efecto = new EfectoNulo();

        // Act
        efecto.aplicar(objetivo, pos, new Este(), tablero);

        // Assert
        assertEquals(3, objetivo.obtenerVida());
    }

    @Test
    public void test02AplicarNoModificaLaPosicionDelOcupante() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion pos = new Posicion(3, 3);
        Vek objetivo = new Luciernaga();
        objetivo.colocarseEn(pos, tablero);
        EfectoNulo efecto = new EfectoNulo();

        // Act
        efecto.aplicar(objetivo, pos, new Este(), tablero);

        // Assert
        assertEquals(pos, objetivo.obtenerPosicion());
    }

    @Test
    public void test03AplicarNoContaminaAlOcupante() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion pos = new Posicion(3, 3);
        Mech mech = new Combate();
        mech.colocarseEn(pos, tablero);
        EfectoNulo efecto = new EfectoNulo();

        // Act
        efecto.aplicar(mech, pos, new Este(), tablero);
        mech.recibirDanio(1);

        // Assert
        assertEquals(2, mech.obtenerVida());
    }
}