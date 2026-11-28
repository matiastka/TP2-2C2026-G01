
package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Canon;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Avispon;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Luciernaga;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContaminacionTest {

    private static final String RUTA_MAPA_ACIDO = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_acido.txt";

    @Test
    public void test01UnMechSeMueveAUnaCasillaConAcidoYAlRecibirDanioPierdeElDobleDeVida() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_ACIDO);
        Posicion posicionMech = new Posicion(2, 3);
        Posicion posicionAcido = new Posicion(2, 4);
        Mech mechCombate = new Combate();
        mechCombate.colocarseEn(posicionMech, tablero);
        int vidaEsperada = 1;

        // Act
        mechCombate.moverA(posicionAcido, tablero);
        mechCombate.recibirDanio(1);

        // Assert
        assertEquals(vidaEsperada, mechCombate.obtenerVida());
    }

    @Test
    public void test02UnMechQueNuncaPisaAcidoRecibeDanioNormal() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_ACIDO);
        Posicion posicionMech = new Posicion(2, 2);
        Posicion posicionSuelo = new Posicion(2, 3);
        Mech mechCombate = new Combate();
        mechCombate.colocarseEn(posicionMech, tablero);
        int vidaEsperada = 2;
        mechCombate.moverA(posicionSuelo, tablero);

        // Act
        mechCombate.recibirDanio(1);

        // Assert
        assertEquals(vidaEsperada, mechCombate.obtenerVida());
    }

    @Test
    public void test03UnMechQueSaleDelAcidoSigueContaminado() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_ACIDO);
        Posicion posicionMech = new Posicion(2, 2);
        Posicion posicionAcido = new Posicion(2, 4);
        Posicion posicionSuelo = new Posicion(2, 5);
        Mech mechCombate = new Combate();
        mechCombate.colocarseEn(posicionMech, tablero);
        int vidaEsperada = 1;
        mechCombate.moverA(posicionAcido, tablero);
        mechCombate.reiniciarTurno();
        mechCombate.moverA(posicionSuelo, tablero);

        // Act
        mechCombate.recibirDanio(1);

        // Assert
        assertEquals(vidaEsperada, mechCombate.obtenerVida());
    }

    @Test
    public void test04UnaLucierganaEmpujadaAlAcidoQuedaContaminada() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_ACIDO);
        Posicion posicionMech = new Posicion(2, 2);
        Posicion posicionVek = new Posicion(2, 3);
        Mech mechCanon = new Canon();
        Unidad vekLuciernaga = new Luciernaga();
        mechCanon.colocarseEn(posicionMech, tablero);
        vekLuciernaga.colocarseEn(posicionVek, tablero);
        mechCanon.atacarA(posicionVek, tablero);
        int vidaEsperada = 0;
        mechCanon.reiniciarTurno();

        // Act
        mechCanon.atacarA(posicionVek, tablero);

        // Assert
        assertEquals(vidaEsperada, vekLuciernaga.obtenerVida());
    }

    @Test
    public void test05UnAvisponQueTerminaSobreAcidoNoQuedaContaminado() {
        // Arrange
        Tablero tablero = new LectorDeMapa().leer(RUTA_MAPA_ACIDO);
        Posicion posicionVek = new Posicion(2, 3);
        Posicion posicionAcido = new Posicion(2, 4);
        Unidad vekAvispon = new Avispon();
        vekAvispon.colocarseEn(posicionVek, tablero);
        vekAvispon.moverA(posicionAcido, tablero);
        int vidaEsperada = 1;

        // Act
        vekAvispon.recibirDanio(1);

        // Assert
        assertEquals(vidaEsperada, vekAvispon.obtenerVida());
    }
}