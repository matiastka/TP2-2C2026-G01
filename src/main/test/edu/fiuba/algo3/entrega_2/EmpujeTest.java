
package edu.fiuba.algo3.entrega_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Luciernaga;

public class EmpujeTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";

    @Test
    public void test01AlEmpujarUnaUnidadContraOtraNoSeMueve() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion origen = new Posicion(3, 2);
        Posicion objetivo = new Posicion(3, 3);
        Posicion bloqueo = new Posicion(3, 4);
        Mech mech = new Combate();
        Unidad objetivoVek = new Luciernaga();
        Unidad bloqueador = new Luciernaga();
        mech.colocarseEn(origen, tablero);
        objetivoVek.colocarseEn(objetivo, tablero);
        bloqueador.colocarseEn(bloqueo, tablero);

        // Act
        mech.atacarA(objetivo, tablero);

        // Assert
        assertSame(objetivoVek, tablero.enCasilla(objetivo).obtenerOcupante());
    }

    @Test
    public void test02AlEmpujarUnaUnidadContraOtraRecibeDanioDeColision() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion origen = new Posicion(3, 2);
        Posicion objetivo = new Posicion(3, 3);
        Posicion bloqueo = new Posicion(3, 4);
        Mech mech = new Combate();
        Unidad objetivoVek = new Luciernaga();
        Unidad bloqueador = new Luciernaga();
        mech.colocarseEn(origen, tablero);
        objetivoVek.colocarseEn(objetivo, tablero);
        bloqueador.colocarseEn(bloqueo, tablero);

        // Act
        mech.atacarA(objetivo, tablero);

        // Assert
        assertEquals(0, objetivoVek.obtenerVida());
    }

    @Test
    public void test03AlEmpujarUnaUnidadSobreSueloLibreCambiaDeCasilla() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion origen = new Posicion(3, 2);
        Posicion objetivo = new Posicion(3, 3);
        Posicion destino = new Posicion(3, 4);
        Mech mech = new Combate();
        Unidad objetivoVek = new Luciernaga();
        mech.colocarseEn(origen, tablero);
        objetivoVek.colocarseEn(objetivo, tablero);

        // Act
        mech.atacarA(objetivo, tablero);

        // Assert
        assertSame(objetivoVek, tablero.enCasilla(destino).obtenerOcupante());
    }
}