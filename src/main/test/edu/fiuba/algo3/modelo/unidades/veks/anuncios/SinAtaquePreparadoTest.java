
package edu.fiuba.algo3.modelo.unidades.veks.anuncios;

import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.ataques.Ataque;
import edu.fiuba.algo3.modelo.unidades.ataques.AtaqueCuerpoACuerpo;
import edu.fiuba.algo3.modelo.unidades.ataques.efectos.EfectoNulo;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SinAtaquePreparadoTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";
    private static final String RUTA_MAPA_EDIFICIO = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_edificio.txt";

    @Test
    public void test01EjecutarNoDaniaALosOcupantesAdyacentes() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion origen = new Posicion(3, 3);
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(3, 4), tablero);
        Ataque ataque = new AtaqueCuerpoACuerpo(2, new EfectoNulo());
        AtaquePreparado sinAtaque = new SinAtaquePreparado();

        // Act
        sinAtaque.ejecutar(ataque, origen, tablero);

        // Assert
        assertEquals(3, mech.obtenerVida());
    }

    @Test
    public void test02EjecutarNoAlteraLaRedElectricaAlApuntarAUnEdificio() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        int redInicial = tablero.obtenerRedElectrica();
        Posicion origen = new Posicion(3, 2);
        Ataque ataque = new AtaqueCuerpoACuerpo(1, new EfectoNulo());
        AtaquePreparado sinAtaque = new SinAtaquePreparado();

        // Act
        sinAtaque.ejecutar(ataque, origen, tablero);

        // Assert
        assertEquals(redInicial, tablero.obtenerRedElectrica());
    }
}