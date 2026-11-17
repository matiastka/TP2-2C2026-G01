
package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.terrenos.Suelo;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.GestorDeAtaques;
import edu.fiuba.algo3.modelo.unidades.veks.Saltador;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtaqueAnunciadoTest {

    @Test
    public void testVeksAtacanEnElOrdenAnunciado() {
        // Arrange
        Map<Posicion, Casilla> casillas = new HashMap<>();
        Posicion posVek1 = new Posicion(1, 1);
        Posicion posVek2 = new Posicion(1, 2);
        
        casillas.put(posVek1, new Casilla(new Suelo(), posVek1));
        casillas.put(posVek2, new Casilla(new Suelo(), posVek2));
        
        Tablero tablero = new Tablero(casillas);
        GestorDeAtaques gestor = new GestorDeAtaques();

        Saltador vek1 = new Saltador();
        Saltador vek2 = new Saltador();

        vek1.colocarseEn(posVek1, tablero);
        vek2.colocarseEn(posVek2, tablero);

        // Act
        vek1.prepararAtaque(Direccion.ESTE);
        gestor.encolar(vek1.generarAtaqueAnunciado());

        vek2.prepararAtaque(Direccion.OESTE); 
        gestor.encolar(vek2.generarAtaqueAnunciado());

        gestor.ejecutarFase(tablero);

        // Assert
        assertEquals(0, vek2.obtenerVida()); 
    }

    @Test
    public void testVekMuertoDuranteLaFaseDeResolucionNoEjecutaSuAtaque() {
        // Arrange
        Map<Posicion, Casilla> casillas = new HashMap<>();
        Posicion posVek1 = new Posicion(1, 1);
        Posicion posVek2 = new Posicion(1, 2);
        Posicion posMech = new Posicion(1, 3);

        casillas.put(posVek1, new Casilla(new Suelo(), posVek1));
        casillas.put(posVek2, new Casilla(new Suelo(), posVek2));
        casillas.put(posMech, new Casilla(new Suelo(), posMech));
        
        Tablero tablero = new Tablero(casillas);
        GestorDeAtaques gestor = new GestorDeAtaques();

        Saltador vek1 = new Saltador(); 
        Saltador vek2 = new Saltador(); 
        Combate mech = new Combate();

        vek1.colocarseEn(posVek1, tablero);
        vek2.colocarseEn(posVek2, tablero);
        mech.colocarseEn(posMech, tablero);

        // Act
        vek1.prepararAtaque(Direccion.ESTE);
        gestor.encolar(vek1.generarAtaqueAnunciado());

        vek2.prepararAtaque(Direccion.ESTE);
        gestor.encolar(vek2.generarAtaqueAnunciado());

        gestor.ejecutarFase(tablero);

        // Assert
        assertEquals(3, mech.obtenerVida()); 
    }
}