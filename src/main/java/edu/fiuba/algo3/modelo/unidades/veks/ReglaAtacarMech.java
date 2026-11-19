package edu.fiuba.algo3.modelo.unidades.veks;

import edu.fiuba.algo3.modelo.tablero.Casilla;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.ResultadoAccion;

import java.util.List;
import java.util.Optional;

public class ReglaAtacarMech implements ReglaPrioridadVek {

    @Override
    public boolean intentarEjecutar(Vek vek, List<Posicion> posicionesAlcanzables, Tablero tablero) {
        for (Posicion destino : posicionesAlcanzables) {
            for (Direccion direccion : Direccion.cardinales()) {
                Optional<Casilla> impacto = vek.simularImpactoDesde(destino, direccion, tablero);
                if (impacto.isPresent() && impacto.get().obtenerOcupante().esMech()) {
                    if (!vek.obtenerPosicion().equals(destino)) {
                        ResultadoAccion<Void> resultadoMovimiento = vek.moverA(destino, tablero);
                        if (resultadoMovimiento instanceof ResultadoAccion.Rechazo<?>) {
                            return false;
                        }
                    }
                    ResultadoAccion<Void> resultadoAtaque = vek.prepararAtaque(direccion);
                    if (resultadoAtaque instanceof ResultadoAccion.Rechazo<?>) {
                        return false;
                    }
                    return true;
                }
            }
        }
        return false;
    }
}