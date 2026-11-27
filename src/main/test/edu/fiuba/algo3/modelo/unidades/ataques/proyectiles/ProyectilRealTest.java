
package edu.fiuba.algo3.modelo.unidades.ataques.proyectiles;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.direcciones.Este;
import edu.fiuba.algo3.modelo.unidades.ataques.efectos.EfectoEmpujar;
import edu.fiuba.algo3.modelo.unidades.ataques.efectos.EfectoNulo;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Luciernaga;

public class ProyectilRealTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";
    private static final String RUTA_MAPA_MONTANA = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_montana.txt";
    private static final String RUTA_MAPA_EDIFICIO = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_edificio.txt";

    @Test
    public void test01LanzarDesdeImpactaYDaniaAlPrimerOcupanteEnSuTrayectoria() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion origen = new Posicion(2, 0);
        Vek objetivo = new Luciernaga();
        objetivo.colocarseEn(new Posicion(2, 3), tablero);
        ProyectilReal proyectil = new ProyectilReal(1, new EfectoNulo(), new Este(), tablero);

        // Act
        proyectil.lanzarDesde(origen);

        // Assert
        assertEquals(2, objetivo.obtenerVida());
    }

    @Test
    public void test02LanzarDesdeAplicaElEfectoDeImpactoAlOcupante() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion origen = new Posicion(2, 0);
        Vek objetivo = new Luciernaga();
        objetivo.colocarseEn(new Posicion(2, 3), tablero);
        ProyectilReal proyectil = new ProyectilReal(1, new EfectoEmpujar(), new Este(), tablero);

        // Act
        proyectil.lanzarDesde(origen);

        // Assert
        assertEquals(new Posicion(2, 4), objetivo.obtenerPosicion());
    }

    @Test
    public void test03LanzarDesdeFrenaContraUnEdificioYLoDania() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        int redInicial = tablero.obtenerRedElectrica();
        Posicion origen = new Posicion(3, 0);
        ProyectilReal proyectil = new ProyectilReal(1, new EfectoNulo(), new Este(), tablero);

        // Act
        proyectil.lanzarDesde(origen);

        // Assert
        assertEquals(redInicial - 1, tablero.obtenerRedElectrica());
    }

    @Test
    public void test04LanzarDesdeFrenaContraUnaMontanaYNoDaniaAlOcupanteDeAtras() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_MONTANA);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion origen = new Posicion(2, 0);
        Vek detrasDeMontana = new Luciernaga();
        detrasDeMontana.colocarseEn(new Posicion(2, 4), tablero);
        ProyectilReal proyectil = new ProyectilReal(1, new EfectoNulo(), new Este(), tablero);

        // Act
        proyectil.lanzarDesde(origen);

        // Assert
        assertEquals(3, detrasDeMontana.obtenerVida());
    }

    @Test
    public void test05LanzarDesdeEnTrayectoriaDespejadaNoLanzaExcepcion() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion origen = new Posicion(2, 0);
        ProyectilReal proyectil = new ProyectilReal(1, new EfectoNulo(), new Este(), tablero);

        // Act & Assert
        assertDoesNotThrow(() -> proyectil.lanzarDesde(origen));
    }
}