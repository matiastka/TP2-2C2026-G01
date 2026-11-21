
package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.partida.Turno;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Sur;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Canon;
import edu.fiuba.algo3.modelo.unidades.veks.Avispon;
import edu.fiuba.algo3.modelo.unidades.veks.InteligenciaArtificialVek;
import edu.fiuba.algo3.modelo.unidades.veks.Saltador;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtaqueDesplazadoTest {

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
    public void testEmpujarVekDesplazaSuAtaqueAnunciadoYDanioAlNuevoObjetivo() {
        // Arrange
        Tablero tablero = new ConstructorTablero().construir(MAPA_BASE);

        Posicion posCanon = new Posicion(1, 1);
        Posicion posAvispon = new Posicion(1, 2);
        Posicion posSaltador = new Posicion(2, 3);
        Posicion posAtaqueAvispon = new Posicion(2,2);
        
        Canon mechCanon = new Canon();
        Avispon vek1 = new Avispon();
        Saltador vek2 = new Saltador();

        mechCanon.colocarseEn(posCanon, tablero);
        vek1.colocarseEn(posAvispon, tablero);
        vek2.colocarseEn(posSaltador, tablero);

        // Act
        vek1.prepararAtaque(posAtaqueAvispon);

        mechCanon.atacarA(posAvispon, tablero);

        new Turno(List.of(vek1), new InteligenciaArtificialVek()).faseResolucion(tablero);

        // Assert
        assertEquals(1, vek2.obtenerVida());
    }
}