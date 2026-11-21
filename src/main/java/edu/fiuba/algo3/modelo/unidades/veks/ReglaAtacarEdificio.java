package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

import java.util.List;
import java.util.Optional;

public class ReglaAtacarEdificio implements ReglaPrioridadVek {

    @Override
    public boolean intentarEjecutar(Vek vek, List<Posicion> posicionesAlcanzables, Tablero tablero) {
        for (Posicion destino : posicionesAlcanzables) {
            for (Direccion direccion : Direccion.cardinales()) {
                Optional<Casilla> impacto = vek.simularImpactoDesde(destino, direccion, tablero);
                if (impacto.isPresent() && impacto.get().obtenerTerreno().esEdificio()) {
                    if (!vek.obtenerPosicion().equals(destino)) {
                        vek.moverA(destino, tablero);
                    }
                    vek.prepararAtaque(destino.siguienteEn(direccion));
                    return true;
                }
            }
        }
        return false;
    }
}