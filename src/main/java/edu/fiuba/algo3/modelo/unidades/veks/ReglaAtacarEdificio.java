package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.ResultadoAccion;

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
                        ResultadoAccion<Void> resultadoMovimiento = vek.moverA(destino, tablero);
                        if (!resultadoMovimiento.fold(ignorado -> true, motivo -> false)) {
                            return false;
                        }
                    }
                    ResultadoAccion<Void> resultadoAtaque = vek.prepararAtaque(direccion);
                    if (!resultadoAtaque.fold(ignorado -> true, motivo -> false)) {
                        return false;
                    }
                    return true;
                }
            }
        }
        return false;
    }
}