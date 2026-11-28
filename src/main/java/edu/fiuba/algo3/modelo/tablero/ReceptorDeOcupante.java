package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;

public interface ReceptorDeOcupante {
    void recibirMech(Mech mech);
    void recibirVek(Vek vek);
    void recibirSinOcupante();
}