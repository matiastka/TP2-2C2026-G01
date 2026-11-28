package edu.fiuba.algo3.vistas.seleccion;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import edu.fiuba.algo3.modelo.terrenos.Suelo;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EstadoSeleccionTest {

    @Test
    public void test01SinSeleccionAlHacerClicEnUnMechLoSelecciona() {
        // Arrange
        SinSeleccion estado = new SinSeleccion();
        SeleccionJugador seleccion = mock(SeleccionJugador.class);
        ManejadorAccionesSeleccion manejador = mock(ManejadorAccionesSeleccion.class);
        Mech mech = new Combate();

        // Act
        estado.clicEnMech(mech, seleccion, manejador);

        // Assert
        verify(manejador).seleccionarMech(mech);
    }

    @Test
    public void test02SinSeleccionLaAccionSeEjecutaSinMech() {
        // Arrange
        SinSeleccion estado = new SinSeleccion();
        AccionSobreMech accion = mock(AccionSobreMech.class);

        // Act
        estado.ejecutar(accion);

        // Assert
        verify(accion).ejecutarSinMechSeleccionado();
    }

    @Test
    public void test03ConUnMechSeleccionadoElClicEnUnVekIntentaAtacarlo() {
        // Arrange
        Mech mech = new Combate();
        MechSeleccionado estado = new MechSeleccionado(mech);
        SeleccionJugador seleccion = mock(SeleccionJugador.class);
        ManejadorAccionesSeleccion manejador = mock(ManejadorAccionesSeleccion.class);
        Posicion posicion = new Posicion(1, 2);

        // Act
        estado.clicEnVek(posicion, seleccion, manejador);

        // Assert
        verify(seleccion).atacarSiEsObjetivoValido(mech, posicion, manejador);
    }

    @Test
    public void test04ConUnMechSeleccionadoElClicEnUnaCasillaVaciaSeProcesa() {
        // Arrange
        Mech mech = new Combate();
        MechSeleccionado estado = new MechSeleccionado(mech);
        SeleccionJugador seleccion = mock(SeleccionJugador.class);
        ManejadorAccionesSeleccion manejador = mock(ManejadorAccionesSeleccion.class);
        Casilla casilla = new Casilla(new Suelo(), new Posicion(1, 2));

        // Act
        estado.clicEnCasillaVacia(casilla, seleccion, manejador);

        // Assert
        verify(seleccion).procesarClicEnCasillaVacia(mech, casilla, manejador);
    }
}