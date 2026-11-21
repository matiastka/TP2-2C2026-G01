module edu.fiuba.algo3 {
    requires javafx.controls;
    requires json.simple;
    requires java.desktop;
    exports edu.fiuba.algo3;
    exports edu.fiuba.algo3.vistas;
    exports edu.fiuba.algo3.mapa;
    exports edu.fiuba.algo3.modelo;
    exports edu.fiuba.algo3.modelo.tablero;
    exports edu.fiuba.algo3.modelo.terrenos;
    exports edu.fiuba.algo3.modelo.unidades;
    exports edu.fiuba.algo3.modelo.unidades.mechs;
    exports edu.fiuba.algo3.modelo.unidades.veks;
}