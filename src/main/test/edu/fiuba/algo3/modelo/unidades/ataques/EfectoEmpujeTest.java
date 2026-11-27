
package edu.fiuba.algo3.modelo.unidades.ataques;

import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Canon;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Luciernaga;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EfectoEmpujeTest {

    private static final String RUTA_MAPA_OBSTACULOS = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_obstaculos.txt";

    @Test
    public void test01EmpujarUnidadContraOtraUnidadNoMueveALaUnidadEmpujada() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_OBSTACULOS);
        Posicion posVek1 = new Posicion(1, 2);
        Mech canon = new Canon();
        canon.colocarseEn(new Posicion(1, 0), tablero);
        Vek vekEmpujado = new Luciernaga();
        vekEmpujado.colocarseEn(posVek1, tablero);
        Vek vekObstaculo = new Luciernaga();
        vekObstaculo.colocarseEn(new Posicion(1, 3), tablero);

        // Act
        canon.atacarA(posVek1, tablero);

        // Assert
        assertEquals(posVek1, vekEmpujado.obtenerPosicion());
    }

    @Test
    public void test02EmpujarUnidadContraOtraUnidadAplicaDanioDeColisionALaEmpujada() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_OBSTACULOS);
        Posicion posVek1 = new Posicion(1, 2);
        Mech canon = new Canon();
        canon.colocarseEn(new Posicion(1, 0), tablero);
        Vek vekEmpujado = new Luciernaga();
        vekEmpujado.colocarseEn(posVek1, tablero);
        Vek vekObstaculo = new Luciernaga();
        vekObstaculo.colocarseEn(new Posicion(1, 3), tablero);

        // Act
        canon.atacarA(posVek1, tablero);

        // Assert
        assertEquals(1, vekEmpujado.obtenerVida());
    }

    @Test
    public void test03EmpujarUnidadContraOtraUnidadAplicaDanioDeColisionALaUnidadObstaculo() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_OBSTACULOS);
        Posicion posVek1 = new Posicion(1, 2);
        Mech canon = new Canon();
        canon.colocarseEn(new Posicion(1, 0), tablero);
        Vek vekEmpujado = new Luciernaga();
        vekEmpujado.colocarseEn(posVek1, tablero);
        Vek vekObstaculo = new Luciernaga();
        vekObstaculo.colocarseEn(new Posicion(1, 3), tablero);

        // Act
        canon.atacarA(posVek1, tablero);

        // Assert
        assertEquals(2, vekObstaculo.obtenerVida());
    }

    @Test
    public void test04EmpujarUnidadContraMontanaNoLaMueve() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_OBSTACULOS);
        Posicion posVek = new Posicion(3, 2);
        Mech canon = new Canon();
        canon.colocarseEn(new Posicion(3, 0), tablero);
        Vek vek = new Luciernaga();
        vek.colocarseEn(posVek, tablero);

        // Act
        canon.atacarA(posVek, tablero);

        // Assert
        assertEquals(posVek, vek.obtenerPosicion());
    }

    @Test
    public void test05EmpujarUnidadContraMontanaAplicaDanioDeColision() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_OBSTACULOS);
        Posicion posVek = new Posicion(3, 2);
        Mech canon = new Canon();
        canon.colocarseEn(new Posicion(3, 0), tablero);
        Vek vek = new Luciernaga();
        vek.colocarseEn(posVek, tablero);

        // Act
        canon.atacarA(posVek, tablero);

        // Assert
        assertEquals(1, vek.obtenerVida());
    }

    @Test
    public void test06EmpujarUnidadContraEdificioNoLaMueve() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_OBSTACULOS);
        Posicion posVek = new Posicion(3, 5);
        Mech canon = new Canon();
        canon.colocarseEn(new Posicion(3, 7), tablero);
        Vek vek = new Luciernaga();
        vek.colocarseEn(posVek, tablero);

        // Act
        canon.atacarA(posVek, tablero);

        // Assert
        assertEquals(posVek, vek.obtenerPosicion());
    }

    @Test
    public void test07EmpujarUnidadContraEdificioAplicaDanioDeColisionALaUnidad() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_OBSTACULOS);
        Posicion posVek = new Posicion(3, 5);
        Mech canon = new Canon();
        canon.colocarseEn(new Posicion(3, 7), tablero);
        Vek vek = new Luciernaga();
        vek.colocarseEn(posVek, tablero);

        // Act
        canon.atacarA(posVek, tablero);

        // Assert
        assertEquals(1, vek.obtenerVida());
    }

    @Test
    public void test08EmpujarUnidadContraBordeDelTableroNoLaMueve() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_OBSTACULOS);
        Posicion posVekEnBorde = new Posicion(0, 0);
        Mech canon = new Canon();
        canon.colocarseEn(new Posicion(0, 2), tablero);
        Vek vek = new Luciernaga();
        vek.colocarseEn(posVekEnBorde, tablero);

        // Act
        canon.atacarA(posVekEnBorde, tablero);

        // Assert
        assertEquals(posVekEnBorde, vek.obtenerPosicion());
    }

    @Test
    public void test09EmpujarUnidadContraBordeDelTableroNoAplicaDanioDeColision() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_OBSTACULOS);
        Posicion posVekEnBorde = new Posicion(0, 0);
        Mech canon = new Canon();
        canon.colocarseEn(new Posicion(0, 2), tablero);
        Vek vek = new Luciernaga();
        vek.colocarseEn(posVekEnBorde, tablero);

        // Act
        canon.atacarA(posVekEnBorde, tablero);

        // Assert
        assertEquals(2, vek.obtenerVida());
    }
}