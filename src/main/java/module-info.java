module edu.fiuba.algo3 {
    requires javafx.controls;
    requires json.simple;
    requires java.desktop;
    exports edu.fiuba.algo3;
    exports edu.fiuba.algo3.modelo;
    exports edu.fiuba.algo3.controllers;
    exports edu.fiuba.algo3.vistas;
    exports edu.fiuba.algo3.modelo.mapa;
    exports edu.fiuba.algo3.modelo.partida;
    exports edu.fiuba.algo3.modelo.tablero;
    exports edu.fiuba.algo3.modelo.terrenos;
    exports edu.fiuba.algo3.modelo.unidades;
    exports edu.fiuba.algo3.modelo.unidades.mechs;
    exports edu.fiuba.algo3.modelo.unidades.veks;
    exports edu.fiuba.algo3.modelo.unidades.excepciones;
    exports edu.fiuba.algo3.modelo.unidades.movimientos;
    exports edu.fiuba.algo3.modelo.unidades.ataques;
    exports edu.fiuba.algo3.modelo.unidades.veks.ia;
    exports edu.fiuba.algo3.modelo.tablero.direcciones;
    exports edu.fiuba.algo3.modelo.unidades.ataques.proyectiles;
    exports edu.fiuba.algo3.modelo.unidades.ataques.efectos;
    exports edu.fiuba.algo3.modelo.unidades.veks.anuncios;
    exports edu.fiuba.algo3.modelo.tablero.emergencias;
}