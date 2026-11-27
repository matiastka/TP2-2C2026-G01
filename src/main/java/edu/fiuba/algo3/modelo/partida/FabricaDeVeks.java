package edu.fiuba.algo3.modelo.partida;

import java.util.List;
import java.util.function.Supplier;

import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Avispon;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Saltador;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;

public class FabricaDeVeks {

    private final List<Supplier<Vek>> orden;
    private int siguiente;

    public FabricaDeVeks() {
        this(List.of(Saltador::new, Avispon::new, Luciernaga::new));
    }

    public FabricaDeVeks(List<Supplier<Vek>> orden) {
        this.orden = List.copyOf(orden);
        this.siguiente = 0;
    }

    public Vek crearSiguiente() {
        Vek vek = this.orden.get(this.siguiente).get();
        this.siguiente = (this.siguiente + 1) % this.orden.size();
        return vek;
    }
}