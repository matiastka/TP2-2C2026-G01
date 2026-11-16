package edu.fiuba.algo3.entrega_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.mapa.ConstructorDeTablero;
import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.Artilleria;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.Avispon;
import edu.fiuba.algo3.modelo.unidades.veks.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;

public class ContaminacionLuciernagaTest {

    private static final String[] MAPA_LIBRE = {
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS"
    };

    private static final String[] MAPA_CON_ACIDO = {
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSCSSS",
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
    public void test01LuciernagaDaniaYContaminaAlMechImpactadoDuplicandoElSiguienteDanio() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_LIBRE);

        // Combate tiene 3 de vida
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(3, 3), tablero);
        int vidaInicial = mech.obtenerVida();

        // Luciérnaga dispara desde (3, 1) hacia el ESTE (3, 3): hace 1 de daño y contamina (vida: 3 - 1 = 2)
        Vek luciernaga = new Luciernaga();
        luciernaga.colocarseEn(new Posicion(3, 1), tablero);
        luciernaga.prepararAtaque(Direccion.ESTE);
        luciernaga.resolverAtaque(tablero);

        // Un segundo ataque de 1 de daño desde (1, 3) hacia el SUR se duplica a 2 por la contaminación (vida: 2 - 2 = 0)
        Vek otraLuciernaga = new Luciernaga();
        otraLuciernaga.colocarseEn(new Posicion(1, 3), tablero);
        otraLuciernaga.prepararAtaque(Direccion.SUR);
        otraLuciernaga.resolverAtaque(tablero);

        assertEquals(vidaInicial - 3, mech.obtenerVida());
    }

    @Test
    public void test02LaContaminacionNoSeAcumulaAlCombinarAcidoYLuciernaga() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_CON_ACIDO);

        // Combate tiene 3 de vida
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(3, 3), tablero);
        int vidaInicial = mech.obtenerVida();

        // 1. El Mech camina hacia (3, 4) donde hay Ácido ('A') y queda contaminado (vida sigue en 3)
        mech.moverA(new Posicion(3, 4), tablero);

        // 2. Luciérnaga dispara desde (3, 1) hacia el ESTE (3, 4): recibe 1 * 2 = 2 de daño (vida: 3 - 2 = 1) y vuelve a recibir efecto de contaminación[cite: 11, 14]
        Vek luciernaga = new Luciernaga();
        luciernaga.colocarseEn(new Posicion(3, 1), tablero);
        luciernaga.prepararAtaque(Direccion.ESTE);
        luciernaga.resolverAtaque(tablero);

        // Si se hubiera acumulado (x4), en el paso 2 habría recibido 4 de daño y quedado en 0; como NO se acumula, queda con 1 de vida
        assertEquals(vidaInicial - 2, mech.obtenerVida());
    }

    @Test
    public void test03LaContaminacionNoSeAcumulaAlRecibirDosImpactosDeLuciernaga() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_CON_ACIDO);

        // Combate tiene 3 de vida
        Mech mech = new Combate();
        mech.colocarseEn(new Posicion(3, 3), tablero);
        int vidaInicial = mech.obtenerVida();

        // 1. Luciérnaga 1 dispara desde (3, 1) hacia el ESTE: hace 1 de daño y contamina (vida: 3 - 1 = 2)
        Vek luciernaga1 = new Luciernaga();
        luciernaga1.colocarseEn(new Posicion(3, 1), tablero);
        luciernaga1.prepararAtaque(Direccion.ESTE);
        luciernaga1.resolverAtaque(tablero);

        // 2. El Mech pisa Ácido en (3, 4): si acumulara multiplicador pasaría a x4
        mech.moverA(new Posicion(3, 4), tablero);

        // 3. Luciérnaga 2 dispara desde (3, 6) hacia el OESTE: hace 1 * 2 = 2 de daño (vida: 2 - 2 = 0)
        Vek luciernaga2 = new Luciernaga();
        luciernaga2.colocarseEn(new Posicion(3, 6), tablero);
        luciernaga2.prepararAtaque(Direccion.OESTE);
        luciernaga2.resolverAtaque(tablero);

        assertEquals(vidaInicial - 3, mech.obtenerVida());
    }

    @Test
    public void test04AvisponEsInmuneALaContaminacionDeLuciernaga() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_LIBRE);

        // Avispón es un Vek con 4 de vida e inmune a la contaminación
        Vek avispon = new Avispon();
        avispon.colocarseEn(new Posicion(3, 3), tablero);
        int vidaInicial = avispon.obtenerVida();

        // 1er golpe: Luciérnaga hace 1 de daño, pero NO contamina al Avispón
        Vek luciernaga = new Luciernaga();
        luciernaga.colocarseEn(new Posicion(3, 1), tablero);
        luciernaga.prepararAtaque(Direccion.ESTE);
        luciernaga.resolverAtaque(tablero);

        // 2do golpe: Artillería hace 1 de daño normal (no se duplica)
        Mech artilleria = new Artilleria();
        artilleria.colocarseEn(new Posicion(0, 3), tablero);
        artilleria.atacarA(new Posicion(3, 3), tablero);

        assertEquals(vidaInicial - 2, avispon.obtenerVida());
    }
}