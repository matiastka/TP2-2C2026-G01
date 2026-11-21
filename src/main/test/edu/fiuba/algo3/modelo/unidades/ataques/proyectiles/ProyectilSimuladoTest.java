
package edu.fiuba.algo3.modelo.unidades.ataques.proyectiles;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.direcciones.Este;
import edu.fiuba.algo3.modelo.unidades.veks.Luciernaga;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProyectilSimuladoTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";
    private static final String RUTA_MAPA_MONTANA = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_montana.txt";
    private static final String RUTA_MAPA_EDIFICIO = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_edificio.txt";

    @Test
    public void test01LanzarDesdeRegistraLaCasillaDelPrimerOcupanteEnLaTrayectoria() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion origen = new Posicion(2, 0);
        Posicion posObjetivo = new Posicion(2, 3);
        new Luciernaga().colocarseEn(posObjetivo, tablero);
        ProyectilSimulado proyectil = new ProyectilSimulado(new Este(), tablero);

        // Act
        proyectil.lanzarDesde(origen);
        Optional<Casilla> resultado = proyectil.casillaImpactada();

        // Assert
        assertEquals(Optional.of(tablero.enCasilla(posObjetivo)), resultado);
    }

    @Test
    public void test02LanzarDesdeFrenaEnElPrimerOcupanteYNoRegistraAlSegundo() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion origen = new Posicion(2, 0);
        Posicion posPrimero = new Posicion(2, 2);
        Posicion posSegundo = new Posicion(2, 5);
        new Luciernaga().colocarseEn(posPrimero, tablero);
        new Luciernaga().colocarseEn(posSegundo, tablero);
        ProyectilSimulado proyectil = new ProyectilSimulado(new Este(), tablero);

        // Act
        proyectil.lanzarDesde(origen);
        Optional<Casilla> resultado = proyectil.casillaImpactada();

        // Assert
        assertEquals(Optional.of(tablero.enCasilla(posPrimero)), resultado);
    }

    @Test
    public void test03LanzarDesdeRegistraLaCasillaDeUnaMontanaEnLaTrayectoria() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_MONTANA);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion origen = new Posicion(2, 0);
        Posicion posMontana = new Posicion(2, 2);
        ProyectilSimulado proyectil = new ProyectilSimulado(new Este(), tablero);

        // Act
        proyectil.lanzarDesde(origen);
        Optional<Casilla> resultado = proyectil.casillaImpactada();

        // Assert
        assertEquals(Optional.of(tablero.enCasilla(posMontana)), resultado);
    }

    @Test
    public void test04LanzarDesdeRegistraLaCasillaDeUnEdificioEnLaTrayectoria() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_EDIFICIO);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion origen = new Posicion(3, 0);
        Posicion posEdificio = new Posicion(3, 3);
        ProyectilSimulado proyectil = new ProyectilSimulado(new Este(), tablero);

        // Act
        proyectil.lanzarDesde(origen);
        Optional<Casilla> resultado = proyectil.casillaImpactada();

        // Assert
        assertEquals(Optional.of(tablero.enCasilla(posEdificio)), resultado);
    }

    @Test
    public void test05LanzarDesdeDevuelveVacioCuandoLaTrayectoriaEstaDespejada() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion origen = new Posicion(2, 0);
        ProyectilSimulado proyectil = new ProyectilSimulado(new Este(), tablero);

        // Act
        proyectil.lanzarDesde(origen);
        Optional<Casilla> resultado = proyectil.casillaImpactada();

        // Assert
        assertEquals(Optional.empty(), resultado);
    }
}