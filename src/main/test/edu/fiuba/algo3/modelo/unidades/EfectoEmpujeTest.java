package edu.fiuba.algo3.modelo.unidades;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.mapa.ConstructorDeTablero;
import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Canon;
import edu.fiuba.algo3.modelo.unidades.veks.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;

public class EfectoEmpujeTest {

    private static final String[] MAPA_OBSTACULOS = {
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSMESSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS"
    };

    @BeforeEach
    public void setUp() {
        RedElectrica.getInstancia().reiniciar();
    }

    @Test
    public void test01EmpujarUnidadContraOtraUnidadNoMueveALaUnidadEmpujada() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_OBSTACULOS);
        Posicion posVek1 = new Posicion(1, 2);

        Mech canon = new Canon();
        canon.colocarseEn(new Posicion(1, 0), tablero);

        Vek vekEmpujado = new Luciernaga();
        vekEmpujado.colocarseEn(posVek1, tablero);

        Vek vekObstaculo = new Luciernaga();
        vekObstaculo.colocarseEn(new Posicion(1, 3), tablero);

        canon.atacarA(posVek1, tablero);

        assertEquals(posVek1, vekEmpujado.obtenerPosicion());
    }

    @Test
    public void test02EmpujarUnidadContraOtraUnidadAplicaDanioDeColisionALaEmpujada() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_OBSTACULOS);
        Posicion posVek1 = new Posicion(1, 2);

        Mech canon = new Canon();
        canon.colocarseEn(new Posicion(1, 0), tablero);

        Vek vekEmpujado = new Luciernaga(); // 3 de vida
        vekEmpujado.colocarseEn(posVek1, tablero);

        Vek vekObstaculo = new Luciernaga();
        vekObstaculo.colocarseEn(new Posicion(1, 3), tablero);

        canon.atacarA(posVek1, tablero);

        // 1 de daño por el disparo + 1 de daño por colisión = 2 de daño (3 - 2 = 1)
        assertEquals(1, vekEmpujado.obtenerVida());
    }

    @Test
    public void test03EmpujarUnidadContraOtraUnidadAplicaDanioDeColisionALaUnidadObstaculo() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_OBSTACULOS);
        Posicion posVek1 = new Posicion(1, 2);

        Mech canon = new Canon();
        canon.colocarseEn(new Posicion(1, 0), tablero);

        Vek vekEmpujado = new Luciernaga();
        vekEmpujado.colocarseEn(posVek1, tablero);

        Vek vekObstaculo = new Luciernaga(); // 3 de vida
        vekObstaculo.colocarseEn(new Posicion(1, 3), tablero);

        canon.atacarA(posVek1, tablero);

        // 1 de daño por colisión (3 - 1 = 2)
        assertEquals(2, vekObstaculo.obtenerVida());
    }

    @Test
    public void test04EmpujarUnidadContraMontanaNoLaMueve() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_OBSTACULOS);
        Posicion posVek = new Posicion(3, 2);

        Mech canon = new Canon();
        canon.colocarseEn(new Posicion(3, 0), tablero);

        Vek vek = new Luciernaga();
        vek.colocarseEn(posVek, tablero);

        canon.atacarA(posVek, tablero);

        assertEquals(posVek, vek.obtenerPosicion());
    }

    @Test
    public void test05EmpujarUnidadContraMontanaAplicaDanioDeColision() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_OBSTACULOS);
        Posicion posVek = new Posicion(3, 2);

        Mech canon = new Canon();
        canon.colocarseEn(new Posicion(3, 0), tablero);

        Vek vek = new Luciernaga(); // 3 de vida
        vek.colocarseEn(posVek, tablero);

        canon.atacarA(posVek, tablero);

        // 1 de daño por el disparo + 1 de daño por colisión contra la montaña
        assertEquals(1, vek.obtenerVida());
    }

    @Test
    public void test06EmpujarUnidadContraEdificioNoLaMueve() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_OBSTACULOS);
        Posicion posVek = new Posicion(3, 5);

        Mech canon = new Canon();
        canon.colocarseEn(new Posicion(3, 7), tablero);

        Vek vek = new Luciernaga();
        vek.colocarseEn(posVek, tablero);

        canon.atacarA(posVek, tablero);

        assertEquals(posVek, vek.obtenerPosicion());
    }

    @Test
    public void test07EmpujarUnidadContraEdificioAplicaDanioDeColisionALaUnidad() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_OBSTACULOS);
        Posicion posVek = new Posicion(3, 5);

        Mech canon = new Canon();
        canon.colocarseEn(new Posicion(3, 7), tablero);

        Vek vek = new Luciernaga(); // 3 de vida
        vek.colocarseEn(posVek, tablero);

        canon.atacarA(posVek, tablero);

        // 1 de daño por el disparo + 1 de daño por colisión contra el edificio
        assertEquals(1, vek.obtenerVida());
    }

    @Test
    public void test08EmpujarUnidadContraBordeDelTableroNoLaMueve() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_OBSTACULOS);
        Posicion posVekEnBorde = new Posicion(0, 0);

        Mech canon = new Canon();
        canon.colocarseEn(new Posicion(0, 2), tablero);

        Vek vek = new Luciernaga();
        vek.colocarseEn(posVekEnBorde, tablero);

        canon.atacarA(posVekEnBorde, tablero);

        assertEquals(posVekEnBorde, vek.obtenerPosicion());
    }

    @Test
    public void test09EmpujarUnidadContraBordeDelTableroNoAplicaDanioDeColision() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_OBSTACULOS);
        Posicion posVekEnBorde = new Posicion(0, 0);

        Mech canon = new Canon();
        canon.colocarseEn(new Posicion(0, 2), tablero);

        Vek vek = new Luciernaga(); // 3 de vida
        vek.colocarseEn(posVekEnBorde, tablero);

        canon.atacarA(posVekEnBorde, tablero);

        // Solo recibe 1 de daño del disparo del Cañón, sin daño extra por colisión
        assertEquals(2, vek.obtenerVida());
    }
}