
package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Artilleria;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.veks.Avispon;
import edu.fiuba.algo3.modelo.unidades.veks.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContaminacionLuciernagaTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";
    private static final String RUTA_MAPA_ACIDO_FILA3 = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_acido_fila3.txt";

    @Test
    public void test01LuciernagaDaniaYContaminaAlMechImpactadoDuplicandoElSiguienteDanio() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion posicionMech = new Posicion(3, 3);
        Posicion posicionLuciernaga1 = new Posicion(3, 1);
        Posicion posicionLuciernaga2 = new Posicion(1, 3);
        Posicion posicionLuciernaga1Ataque = new Posicion(3, 2);
        Posicion posicionLuciernaga2Ataque = new Posicion(2, 3);
        Mech mech = new Combate();
        Vek luciernaga = new Luciernaga();
        Vek otraLuciernaga = new Luciernaga();
        mech.colocarseEn(posicionMech, tablero);
        luciernaga.colocarseEn(posicionLuciernaga1, tablero);
        otraLuciernaga.colocarseEn(posicionLuciernaga2, tablero);
        int vidaInicial = mech.obtenerVida();

        // Act
        luciernaga.prepararAtaque(posicionLuciernaga1Ataque);
        luciernaga.resolverAtaque(tablero);
        otraLuciernaga.prepararAtaque(posicionLuciernaga2Ataque);
        otraLuciernaga.resolverAtaque(tablero);

        // Assert
        assertEquals(vidaInicial - 3, mech.obtenerVida());
    }

    @Test
    public void test02LaContaminacionNoSeAcumulaAlCombinarAcidoYLuciernaga() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_ACIDO_FILA3);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Mech mech = new Combate();
        Vek luciernaga = new Luciernaga();
        mech.colocarseEn(new Posicion(3, 3), tablero);
        luciernaga.colocarseEn(new Posicion(3, 1), tablero);
        mech.moverA(new Posicion(3, 4), tablero);
        int vidaInicial = mech.obtenerVida();

        // Act
        luciernaga.prepararAtaque(new Posicion(3, 2));
        luciernaga.resolverAtaque(tablero);

        // Assert
        assertEquals(vidaInicial - 2, mech.obtenerVida());
    }

    @Test
    public void test03LaContaminacionNoSeAcumulaAlRecibirDosImpactosDeLuciernaga() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_ACIDO_FILA3);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Mech mech = new Combate();
        Vek luciernaga1 = new Luciernaga();
        Vek luciernaga2 = new Luciernaga();
        mech.colocarseEn(new Posicion(3, 3), tablero);
        luciernaga1.colocarseEn(new Posicion(3, 1), tablero);
        luciernaga2.colocarseEn(new Posicion(3, 6), tablero);
        int vidaInicial = mech.obtenerVida();

        // Act
        luciernaga1.prepararAtaque(new Posicion(3, 2));
        luciernaga1.resolverAtaque(tablero);
        mech.moverA(new Posicion(3, 4), tablero);
        luciernaga2.prepararAtaque(new Posicion(3, 5));
        luciernaga2.resolverAtaque(tablero);

        // Assert
        assertEquals(vidaInicial - 3, mech.obtenerVida());
    }

    @Test
    public void test04AvisponEsInmuneALaContaminacionDeLuciernaga() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Vek avispon = new Avispon();
        Vek luciernaga = new Luciernaga();
        Mech artilleria = new Artilleria();
        avispon.colocarseEn(new Posicion(3, 3), tablero);
        int vidaInicial = avispon.obtenerVida();

        // Act
        luciernaga.colocarseEn(new Posicion(3, 1), tablero);
        luciernaga.prepararAtaque(new Posicion(3, 2));
        luciernaga.resolverAtaque(tablero);
        artilleria.colocarseEn(new Posicion(0, 3), tablero);
        artilleria.atacarA(new Posicion(3, 3), tablero);

        // Assert
        assertEquals(vidaInicial - 2, avispon.obtenerVida());
    }
}