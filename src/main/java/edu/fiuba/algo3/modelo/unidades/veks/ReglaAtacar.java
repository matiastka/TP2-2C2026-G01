package edu.fiuba.algo3.modelo.unidades.veks;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public abstract class ReglaAtacar implements ReglaPrioridadVek {

    @Override
    public boolean intentarEjecutar(Vek vek, List<Posicion> posicionesAlcanzables, Tablero tablero) {
        List<AtaquePosible> ataquesPosibles = new ArrayList<>();
        for (Posicion destino : posicionesAlcanzables) {
            for (Direccion direccion : Direccion.cardinales()) {
                AtaquePosible ataque = crearAtaque(destino, direccion, ataquesPosibles);
                vek.simularImpactoDesde(destino, direccion, tablero)
                        .ifPresent(casilla -> casilla.ofrecerComoObjetivo(ataque));
            }
        }
        if (ataquesPosibles.isEmpty()) {
            return false;
        }
        ataquesPosibles.get(0).ejecutarCon(vek, tablero);
        return true;
    }

    protected abstract AtaquePosible crearAtaque(Posicion destino, Direccion direccion, List<AtaquePosible> ataquesPosibles);
}
