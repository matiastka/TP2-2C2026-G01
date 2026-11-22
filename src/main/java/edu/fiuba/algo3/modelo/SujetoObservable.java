package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class SujetoObservable {
    private final List<Observador> observadores = new ArrayList<>();

    public void agregarObservador(Observador observador) {
        if (!this.observadores.contains(observador)) {
            this.observadores.add(observador);
        }
    }

    public void quitarObservador(Observador observador) {
        this.observadores.remove(observador);
    }

    protected void notificarObservadores() {
        for (Observador observador : List.copyOf(this.observadores)) {
            observador.actualizar();
        }
    }
}
