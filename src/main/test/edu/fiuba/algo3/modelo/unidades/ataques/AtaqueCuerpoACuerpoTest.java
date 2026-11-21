
package edu.fiuba.algo3.modelo.unidades.ataques;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.direcciones.Este;
import edu.fiuba.algo3.modelo.tablero.direcciones.Norte;
import edu.fiuba.algo3.modelo.unidades.ataques.efectos.EfectoEmpujar;
import edu.fiuba.algo3.modelo.unidades.ataques.efectos.EfectoNulo;
import edu.fiuba.algo3.modelo.unidades.veks.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtaqueCuerpoACuerpoTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";

    @Test
    public void test01EjecutarDaniaAlOcupanteEnLaCasillaAdyacente() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion origen = new Posicion(3, 3);
        Vek objetivo = new Luciernaga();
        objetivo.colocarseEn(new Posicion(3, 4), tablero);
        Ataque ataque = new AtaqueCuerpoACuerpo(2, new EfectoNulo());

        // Act
        ataque.ejecutar(origen, new Este(), tablero);

        // Assert
        assertEquals(1, objetivo.obtenerVida());
    }

    @Test
    public void test02EjecutarAplicaElEfectoDeImpactoAlOcupante() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion origen = new Posicion(3, 3);
        Vek objetivo = new Luciernaga();
        objetivo.colocarseEn(new Posicion(3, 4), tablero);
        Ataque ataque = new AtaqueCuerpoACuerpo(1, new EfectoEmpujar());

        // Act
        ataque.ejecutar(origen, new Este(), tablero);

        // Assert
        assertEquals(new Posicion(3, 5), objetivo.obtenerPosicion());
    }

    @Test
    public void test03EjecutarNoDaniaAUnOcupanteQueNoEstaAdyacente() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion origen = new Posicion(3, 3);
        Vek noAdyacente = new Luciernaga();
        noAdyacente.colocarseEn(new Posicion(3, 5), tablero);
        Ataque ataque = new AtaqueCuerpoACuerpo(2, new EfectoNulo());

        // Act
        ataque.ejecutar(origen, new Este(), tablero);

        // Assert
        assertEquals(3, noAdyacente.obtenerVida());
    }

    @Test
    public void test04EjecutarHaciaFueraDelTableroNoLanzaExcepcion() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion origen = new Posicion(0, 0);
        Ataque ataque = new AtaqueCuerpoACuerpo(2, new EfectoNulo());

        // Act & Assert
        assertDoesNotThrow(() -> ataque.ejecutar(origen, new Norte(), tablero));
    }

    @Test
    public void test05CasillaImpactadaDesdeDevuelveLaCasillaAdyacenteCuandoEstaEnRango() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion origen = new Posicion(3, 3);
        Posicion adyacente = new Posicion(3, 4);
        Ataque ataque = new AtaqueCuerpoACuerpo(2, new EfectoNulo());

        // Act
        Optional<Casilla> resultado = ataque.casillaImpactadaDesde(origen, new Este(), tablero);

        // Assert
        assertEquals(Optional.of(tablero.enCasilla(adyacente)), resultado);
    }

    @Test
    public void test06CasillaImpactadaDesdeDevuelveVacioCuandoApuntaFueraDelTablero() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion origen = new Posicion(0, 0);
        Ataque ataque = new AtaqueCuerpoACuerpo(2, new EfectoNulo());

        // Act
        Optional<Casilla> resultado = ataque.casillaImpactadaDesde(origen, new Norte(), tablero);

        // Assert
        assertEquals(Optional.empty(), resultado);
    }
}