
package edu.fiuba.algo3.modelo.unidades.veks.anuncios;

import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.direcciones.Este;
import edu.fiuba.algo3.modelo.tablero.direcciones.Norte;
import edu.fiuba.algo3.modelo.unidades.ataques.Ataque;
import edu.fiuba.algo3.modelo.unidades.ataques.AtaqueCuerpoACuerpo;
import edu.fiuba.algo3.modelo.unidades.ataques.efectos.EfectoEmpujar;
import edu.fiuba.algo3.modelo.unidades.ataques.efectos.EfectoNulo;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtaqueEnDireccionTest {

    private static final String RUTA_MAPA_LIBRE = "src/main/test/edu/fiuba/algo3/modelo/mapa/mapa_libre.txt";

    @Test
    public void test01EjecutarDisparaElAtaqueEnLaDireccionConfiguradaYDaniaAlObjetivo() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion origen = new Posicion(3, 3);
        Mech objetivo = new Combate();
        objetivo.colocarseEn(new Posicion(3, 4), tablero);
        Ataque ataque = new AtaqueCuerpoACuerpo(2, new EfectoNulo());
        AtaquePreparado anuncio = new AtaqueEnDireccion(new Este());

        // Act
        anuncio.ejecutar(ataque, origen, tablero);

        // Assert
        assertEquals(1, objetivo.obtenerVida());
    }

    @Test
    public void test02EjecutarNoDaniaAUnObjetivoUbicadoEnOtraDireccion() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion origen = new Posicion(3, 3);
        Mech objetivoEnOtraDireccion = new Combate();
        objetivoEnOtraDireccion.colocarseEn(new Posicion(2, 3), tablero);
        Ataque ataque = new AtaqueCuerpoACuerpo(2, new EfectoNulo());
        AtaquePreparado anuncio = new AtaqueEnDireccion(new Este());

        // Act
        anuncio.ejecutar(ataque, origen, tablero);

        // Assert
        assertEquals(3, objetivoEnOtraDireccion.obtenerVida());
    }

    @Test
    public void test03EjecutarAplicaElEfectoDelAtaqueEnLaDireccionConfigurada() {
        // Arrange
        String[] mapa = new LectorDeMapa().leer(RUTA_MAPA_LIBRE);
        Tablero tablero = new ConstructorTablero().construir(mapa);
        Posicion origen = new Posicion(3, 3);
        Mech objetivo = new Combate();
        objetivo.colocarseEn(new Posicion(2, 3), tablero);
        Ataque ataque = new AtaqueCuerpoACuerpo(1, new EfectoEmpujar());
        AtaquePreparado anuncio = new AtaqueEnDireccion(new Norte());

        // Act
        anuncio.ejecutar(ataque, origen, tablero);

        // Assert
        assertEquals(new Posicion(1, 3), objetivo.obtenerPosicion());
    }
}