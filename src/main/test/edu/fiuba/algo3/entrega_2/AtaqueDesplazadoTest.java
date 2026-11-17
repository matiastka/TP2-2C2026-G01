
package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.mapa.ConstructorDeTablero;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Canon;
import edu.fiuba.algo3.modelo.unidades.veks.Avispon;
import edu.fiuba.algo3.modelo.unidades.veks.GestorDeAtaques;
import edu.fiuba.algo3.modelo.unidades.veks.Saltador;
import org.junit.jupiter.api.Test;

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
        Tablero tablero = new ConstructorDeTablero().construir(MAPA_BASE);
        GestorDeAtaques gestor = new GestorDeAtaques();

        Posicion posCanon = new Posicion(1, 1);
        Posicion posVek1 = new Posicion(1, 2);
        Posicion posNuevoObjetivo = new Posicion(2, 3);
        
        Canon mechCanon = new Canon();
        Avispon vek1 = new Avispon();
        Saltador vek2 = new Saltador();

        mechCanon.colocarseEn(posCanon, tablero);
        vek1.colocarseEn(posVek1, tablero);
        vek2.colocarseEn(posNuevoObjetivo, tablero);

        // Act
        vek1.prepararAtaque(Direccion.SUR);
        gestor.encolar(vek1.generarAtaqueAnunciado());

        mechCanon.atacarA(posVek1, tablero);

        gestor.ejecutarFase(tablero);

        // Assert
        assertEquals(1, vek2.obtenerVida());
    }
}