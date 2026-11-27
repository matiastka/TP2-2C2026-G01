
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

public class AtaqueLineaRectaTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";
    private static final String RUTA_MAPA_MONTANA = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_montana.txt";

    @Test
    public void test01EjecutarImpactaYDaniaAlPrimerOcupanteEnLineaRecta() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion origen = new Posicion(2, 0);
        Vek objetivo = new Luciernaga();
        objetivo.colocarseEn(new Posicion(2, 3), tablero);
        Ataque ataque = new AtaqueLineaRecta(1, new EfectoNulo());

        // Act
        ataque.ejecutar(origen, new Este(), tablero);

        // Assert
        assertEquals(2, objetivo.obtenerVida());
    }

    @Test
    public void test02EjecutarFrenaEnElPrimerOcupanteYNoDaniaAlSegundo() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion origen = new Posicion(2, 0);
        Vek primerObjetivo = new Luciernaga();
        Vek segundoObjetivo = new Luciernaga();
        primerObjetivo.colocarseEn(new Posicion(2, 2), tablero);
        segundoObjetivo.colocarseEn(new Posicion(2, 5), tablero);
        Ataque ataque = new AtaqueLineaRecta(1, new EfectoNulo());

        // Act
        ataque.ejecutar(origen, new Este(), tablero);

        // Assert
        assertEquals(3, segundoObjetivo.obtenerVida());
    }

    @Test
    public void test03EjecutarAplicaElEfectoDeImpactoAlOcupanteImpactado() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion origen = new Posicion(2, 0);
        Vek objetivo = new Luciernaga();
        objetivo.colocarseEn(new Posicion(2, 3), tablero);
        Ataque ataque = new AtaqueLineaRecta(1, new EfectoEmpujar());

        // Act
        ataque.ejecutar(origen, new Este(), tablero);

        // Assert
        assertEquals(new Posicion(2, 4), objetivo.obtenerPosicion());
    }

    @Test
    public void test04EjecutarFrenaContraUnaMontanaYNoDaniaAlOcupanteDeAtras() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_MONTANA);
        Posicion origen = new Posicion(2, 0);
        Vek detrasDeMontana = new Luciernaga();
        detrasDeMontana.colocarseEn(new Posicion(2, 4), tablero);
        Ataque ataque = new AtaqueLineaRecta(1, new EfectoNulo());

        // Act
        ataque.ejecutar(origen, new Este(), tablero);

        // Assert
        assertEquals(3, detrasDeMontana.obtenerVida());
    }

    @Test
    public void test05CasillaImpactadaDesdeDevuelveLaCasillaDelPrimerOcupante() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion origen = new Posicion(2, 0);
        Posicion posObjetivo = new Posicion(2, 3);
        new Luciernaga().colocarseEn(posObjetivo, tablero);
        Ataque ataque = new AtaqueLineaRecta(1, new EfectoNulo());

        // Act
        Optional<Casilla> resultado = ataque.casillaImpactadaDesde(origen, new Este(), tablero);

        // Assert
        assertEquals(Optional.of(tablero.enCasilla(posObjetivo)), resultado);
    }

    @Test
    public void test06CasillaImpactadaDesdeDevuelveLaCasillaDeUnaMontanaEnLaTrayectoria() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_MONTANA);
        Posicion origen = new Posicion(2, 0);
        Posicion posMontana = new Posicion(2, 2);
        Ataque ataque = new AtaqueLineaRecta(1, new EfectoNulo());

        // Act
        Optional<Casilla> resultado = ataque.casillaImpactadaDesde(origen, new Este(), tablero);

        // Assert
        assertEquals(Optional.of(tablero.enCasilla(posMontana)), resultado);
    }

    @Test
    public void test07CasillaImpactadaDesdeDevuelveVacioSiLaLineaEstaDespejada() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Posicion origen = new Posicion(2, 0);
        Ataque ataque = new AtaqueLineaRecta(1, new EfectoNulo());

        // Act
        Optional<Casilla> resultado = ataque.casillaImpactadaDesde(origen, new Este(), tablero);

        // Assert
        assertEquals(Optional.empty(), resultado);
    }
}