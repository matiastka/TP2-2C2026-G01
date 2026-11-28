package edu.fiuba.algo3.vistas.seleccion;

import edu.fiuba.algo3.modelo.unidades.mechs.Mech;

public interface AccionSobreMech {
    void ejecutar(Mech mech);
    void ejecutarSinMechSeleccionado();
}
