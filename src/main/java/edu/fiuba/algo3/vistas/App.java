package edu.fiuba.algo3.vistas;

import java.util.List;

import edu.fiuba.algo3.controllers.ControladorPartida;
import edu.fiuba.algo3.modelo.mapa.ConstructorTablero;
import edu.fiuba.algo3.modelo.mapa.LectorDeMapa;
import edu.fiuba.algo3.modelo.partida.Partida;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Artilleria;
import edu.fiuba.algo3.modelo.unidades.mechs.Canon;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.veks.Avispon;
import edu.fiuba.algo3.modelo.unidades.veks.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.veks.Saltador;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        String rutaMapa = "src/main/java/edu/fiuba/algo3/modelo/mapa/mapa1.txt";
        String[] mapa = new LectorDeMapa().leer(rutaMapa);
        Tablero tablero = new ConstructorTablero().construir(mapa);

        Mech combate = new Combate();
        combate.colocarseEn(new Posicion(6, 1), tablero);
        Mech canon = new Canon();
        canon.colocarseEn(new Posicion(6, 2), tablero);
        Mech artilleria = new Artilleria();
        artilleria.colocarseEn(new Posicion(6, 3), tablero);

        Vek luciernaga = new Luciernaga();
        luciernaga.colocarseEn(new Posicion(1, 2), tablero);
        Vek saltador = new Saltador();
        saltador.colocarseEn(new Posicion(1, 3), tablero);
        Vek avispon = new Avispon();
        avispon.colocarseEn(new Posicion(1, 6), tablero);

        Partida partida = new Partida(
                tablero,
                List.of(combate, canon, artilleria),
                List.of(luciernaga, saltador, avispon)
        );

        VistaBarraEstado barraEstado = new VistaBarraEstado();
        Button terminarTurno = new Button("Terminar fase del jugador");
        ControladorPartida controlador = new ControladorPartida(partida, barraEstado);
        terminarTurno.setOnAction(evento -> controlador.terminarFaseJugador());

        VistaPanelSuperior panelSuperior = new VistaPanelSuperior(partida, terminarTurno);
        VistaPanelLateral panelLateral = new VistaPanelLateral(partida, barraEstado);
        VistaPrincipal vistaPrincipal = new VistaPrincipal(
                partida,
                panelSuperior,
                barraEstado,
                panelLateral
        );

        Scene scene = new Scene(vistaPrincipal, 620, 690);
        stage.setTitle("Into the Breach");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}