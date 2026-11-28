package edu.fiuba.algo3.vistas.seleccion;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EstadoSeleccionTest {

    @Test
    public void sinSeleccionSeleccionaElMechClickeado() {
        SinSeleccion estado = new SinSeleccion();
        SeleccionJugador seleccion = mock(SeleccionJugador.class);
        ManejadorAccionesSeleccion manejador = mock(ManejadorAccionesSeleccion.class);
        Mech mech = mock(Mech.class);

        estado.clicEnMech(mech, seleccion, manejador);

        verify(manejador).seleccionarMech(mech);
    }

    @Test
    public void sinSeleccionEjecutaComportamientoSinMech() {
        SinSeleccion estado = new SinSeleccion();
        AccionSobreMech accion = mock(AccionSobreMech.class);

        estado.ejecutar(accion);

        verify(accion).ejecutarSinMechSeleccionado();
    }

    @Test
    public void seleccionActivaDelegaElClicEnVekAlGestorDeSeleccion() {
        Mech mech = mock(Mech.class);
        MechSeleccionado estado = new MechSeleccionado(mech);
        SeleccionJugador seleccion = mock(SeleccionJugador.class);
        ManejadorAccionesSeleccion manejador = mock(ManejadorAccionesSeleccion.class);
        Posicion posicion = new Posicion(1, 2);

        estado.clicEnVek(posicion, seleccion, manejador);

        verify(seleccion).atacarSiEsObjetivoValido(mech, posicion, manejador);
    }

    @Test
    public void seleccionActivaDelegaLaCasillaVaciaAlGestorDeSeleccion() {
        Mech mech = mock(Mech.class);
        MechSeleccionado estado = new MechSeleccionado(mech);
        SeleccionJugador seleccion = mock(SeleccionJugador.class);
        ManejadorAccionesSeleccion manejador = mock(ManejadorAccionesSeleccion.class);
        Casilla casilla = mock(Casilla.class);

        estado.clicEnCasillaVacia(casilla, seleccion, manejador);

        verify(seleccion).procesarClicEnCasillaVacia(mech, casilla, manejador);
    }
}
