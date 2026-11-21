
package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.partida.Turno;
import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.ia.InteligenciaArtificialVek;
import edu.fiuba.algo3.modelo.unidades.veks.Saltador;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtaqueAnunciadoTest {

    private static final String[] MAPA_BASE = {
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS"
    };

    @Test
    public void test01DosVeksSeAtacanEntreSiConUnOrdenAnunciado() {
        // Arrange
        Tablero tablero = new ConstructorTablero().construir(MAPA_BASE);

        Posicion posVek1 = new Posicion(1, 1);
        Posicion posVek2 = new Posicion(1, 2);

        Saltador vek1 = new Saltador();
        Saltador vek2 = new Saltador();

        vek1.colocarseEn(posVek1, tablero);
        vek2.colocarseEn(posVek2, tablero);

        // Act
        vek1.prepararAtaque(posVek2);

        vek2.prepararAtaque(posVek1);

        new Turno(List.of(vek1, vek2), new InteligenciaArtificialVek()).faseResolucion(tablero);

        // Assert
        assertEquals(0, vek2.obtenerVida()); 
    }

    @Test
    public void test02VekMuertoDuranteLaFaseDeResolucionNoEjecutaSuAtaque() {
        // Arrange
        Tablero tablero = new ConstructorTablero().construir(MAPA_BASE);

        Posicion posVek1 = new Posicion(1, 1);
        Posicion posVek2 = new Posicion(1, 2);
        Posicion posMech = new Posicion(1, 3);

        Saltador vek1 = new Saltador(); 
        Saltador vek2 = new Saltador(); 
        Combate mech = new Combate();

        vek1.colocarseEn(posVek1, tablero);
        vek2.colocarseEn(posVek2, tablero);
        mech.colocarseEn(posMech, tablero);

        // Act
        vek1.prepararAtaque(posVek2);

        vek2.prepararAtaque(posMech);

        new Turno(List.of(vek1, vek2), new InteligenciaArtificialVek()).faseResolucion(tablero);

        // Assert
        assertEquals(3, mech.obtenerVida()); 
    }
}