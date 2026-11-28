
package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.excepciones.CaminoBloqueadoException;
import edu.fiuba.algo3.modelo.partida.CasillasParaEmergencia;
import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.RedElectrica;
import edu.fiuba.algo3.modelo.unidades.movimientos.MovimientoTerrestre;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EdificioTest {

    @Test
    public void test01UnaUnidadTerrestreNoPuedeAtravesarEdificio() {
        // Arrange
        Casilla casilla = new Casilla(new Edificio(new RedElectrica()), new Posicion(0, 0));

        // Act & Assert
        assertThrows(CaminoBloqueadoException.class, () -> casilla.recibirTransito(new MovimientoTerrestre()));
    }

    @Test
    public void test02UnaCasillaConEdificioNoSeOfreceParaUnaEmergencia() {
        // Arrange
        Casilla casilla = new Casilla(new Edificio(new RedElectrica()), new Posicion(0, 0));
        CasillasParaEmergencia candidatas = new CasillasParaEmergencia();

        // Act
        casilla.ofrecerParaEmergencia(candidatas);

        // Assert
        assertEquals(0, candidatas.primeras(1).size());
    }

}