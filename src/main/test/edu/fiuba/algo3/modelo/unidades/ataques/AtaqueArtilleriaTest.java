
package edu.fiuba.algo3.modelo.unidades.ataques;

import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.direcciones.Este;
import edu.fiuba.algo3.modelo.unidades.ataques.efectos.EfectoEmpujar;
import edu.fiuba.algo3.modelo.unidades.ataques.efectos.EfectoNulo;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Luciernaga;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtaqueArtilleriaTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";
    private static final String RUTA_MAPA_MONTANA = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_montana.txt";

    @Test
    public void test01EjecutarDaniaAlOcupanteALaDistanciaIndicada() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion origen = new Posicion(4, 0);
        Vek objetivo = new Luciernaga();
        objetivo.colocarseEn(new Posicion(4, 4), tablero);
        Ataque ataque = new AtaqueArtilleria(1, 4, new EfectoNulo());

        // Act
        ataque.ejecutar(origen, new Este(), tablero);

        // Assert
        assertEquals(2, objetivo.obtenerVida());
    }

    @Test
    public void test02EjecutarSalteaObstaculosIntermediosYDaniaAlObjetivo() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_MONTANA);
        Posicion origen = new Posicion(2, 0);
        Vek objetivo = new Luciernaga();
        objetivo.colocarseEn(new Posicion(2, 4), tablero);
        Ataque ataque = new AtaqueArtilleria(1, 4, new EfectoNulo());

        // Act
        ataque.ejecutar(origen, new Este(), tablero);

        // Assert
        assertEquals(2, objetivo.obtenerVida());
    }

    @Test
    public void test03EjecutarNoDaniaALosOcupantesAdyacentesAlPuntoDeImpacto() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion origen = new Posicion(4, 0);
        Vek adyacenteNorte = new Luciernaga();
        adyacenteNorte.colocarseEn(new Posicion(3, 4), tablero);
        Ataque ataque = new AtaqueArtilleria(1, 4, new EfectoEmpujar());

        // Act
        ataque.ejecutar(origen, new Este(), tablero);

        // Assert
        assertEquals(3, adyacenteNorte.obtenerVida());
    }

    @Test
    public void test04EjecutarAplicaEfectoAdyacenteHaciaElNorteDelImpacto() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion origen = new Posicion(4, 0);
        Vek adyacenteNorte = new Luciernaga();
        adyacenteNorte.colocarseEn(new Posicion(3, 4), tablero);
        Ataque ataque = new AtaqueArtilleria(1, 4, new EfectoEmpujar());

        // Act
        ataque.ejecutar(origen, new Este(), tablero);

        // Assert
        assertEquals(new Posicion(2, 4), adyacenteNorte.obtenerPosicion());
    }

    @Test
    public void test05EjecutarAplicaEfectoAdyacenteHaciaElSurDelImpacto() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion origen = new Posicion(4, 0);
        Vek adyacenteSur = new Luciernaga();
        adyacenteSur.colocarseEn(new Posicion(5, 4), tablero);
        Ataque ataque = new AtaqueArtilleria(1, 4, new EfectoEmpujar());

        // Act
        ataque.ejecutar(origen, new Este(), tablero);

        // Assert
        assertEquals(new Posicion(6, 4), adyacenteSur.obtenerPosicion());
    }

    @Test
    public void test06EjecutarAplicaEfectoAdyacenteHaciaElEsteDelImpacto() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion origen = new Posicion(4, 0);
        Vek adyacenteEste = new Luciernaga();
        adyacenteEste.colocarseEn(new Posicion(4, 5), tablero);
        Ataque ataque = new AtaqueArtilleria(1, 4, new EfectoEmpujar());

        // Act
        ataque.ejecutar(origen, new Este(), tablero);

        // Assert
        assertEquals(new Posicion(4, 6), adyacenteEste.obtenerPosicion());
    }

    @Test
    public void test07EjecutarAplicaEfectoAdyacenteHaciaElOesteDelImpacto() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion origen = new Posicion(4, 0);
        Vek adyacenteOeste = new Luciernaga();
        adyacenteOeste.colocarseEn(new Posicion(4, 3), tablero);
        Ataque ataque = new AtaqueArtilleria(1, 4, new EfectoEmpujar());

        // Act
        ataque.ejecutar(origen, new Este(), tablero);

        // Assert
        assertEquals(new Posicion(4, 2), adyacenteOeste.obtenerPosicion());
    }

    @Test
    public void test08CasillaImpactadaDesdeDevuelveLaCasillaALaDistanciaIndicada() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion origen = new Posicion(4, 0);
        Posicion destino = new Posicion(4, 4);
        Ataque ataque = new AtaqueArtilleria(1, 4, new EfectoNulo());

        // Act
        Optional<Casilla> resultado = ataque.casillaImpactadaDesde(origen, new Este(), tablero);

        // Assert
        assertEquals(Optional.of(tablero.enCasilla(destino)), resultado);
    }

    @Test
    public void test09CasillaImpactadaDesdeDevuelveVacioSiLaDistanciaExcedeElTablero() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion origen = new Posicion(4, 5);
        Ataque ataque = new AtaqueArtilleria(1, 4, new EfectoNulo());

        // Act
        Optional<Casilla> resultado = ataque.casillaImpactadaDesde(origen, new Este(), tablero);

        // Assert
        assertEquals(Optional.empty(), resultado);
    }
}