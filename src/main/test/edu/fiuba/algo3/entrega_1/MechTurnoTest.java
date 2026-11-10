package edu.fiuba.algo3.entrega_1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Combate;
import edu.fiuba.algo3.modelo.Luciernaga;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.Unidad;

public class MechTurnoTest {

    private static final String[] MAPA = {
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS"
    };

    @Test
    public void test01MechNoPuedeMoverseDosVecesEnElMismoTurno() {
        Tablero tablero = new Tablero(MAPA);
        Unidad mech = new Combate();
        Posicion origen = new Posicion(0, 0);
        Posicion primerDestino = new Posicion(0, 2);
        Posicion segundoDestino = new Posicion(0, 4);

        tablero.ocuparCasilla(origen, mech);

        mech.moverA(primerDestino, tablero);
        mech.moverA(segundoDestino, tablero); // Intento invalido

        assertEquals(0, tablero.buscarPosicionDe(mech).getFila());
        assertEquals(2, tablero.buscarPosicionDe(mech).getColumna());
    }

    @Test
    public void test02MechNoPuedeAtacarDosVecesEnElMismoTurno() {
        Tablero tablero = new Tablero(MAPA);
        Unidad mech = new Combate();
        Unidad vek1 = new Luciernaga();
        Unidad vek2 = new Luciernaga();

        Posicion posMech = new Posicion(2, 2);
        Posicion posVek1 = new Posicion(2, 3);
        Posicion posVek2 = new Posicion(3, 2);

        tablero.ocuparCasilla(posMech, mech);
        tablero.ocuparCasilla(posVek1, vek1);
        tablero.ocuparCasilla(posVek2, vek2);

        mech.atacarA(posVek1, tablero);
        mech.atacarA(posVek2, tablero); // Segundo ataque en el mismo turno

        assertEquals(3, vek2.obtenerVida()); // No debe recibir daño
    }

    @Test
    public void test03AlReiniciarTurnoElMechPuedeVolverAMoverseYAtacar() {
        Tablero tablero = new Tablero(MAPA);
        Unidad mech = new Combate();
        Unidad vek = new Luciernaga();

        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 1);
        Posicion posVek = new Posicion(0, 2);

        tablero.ocuparCasilla(origen, mech);
        tablero.ocuparCasilla(posVek, vek);

        mech.moverA(destino, tablero);
        mech.atacarA(posVek, tablero);

        // Iniciar nuevo turno
        mech.reiniciarTurno();

        Posicion nuevoDestino = new Posicion(1, 1);
        mech.moverA(nuevoDestino, tablero);

        assertEquals(1, tablero.buscarPosicionDe(mech).getFila());
        assertEquals(1, tablero.buscarPosicionDe(mech).getColumna());
    }
    
    @Test
    public void test04DeshacerMovimientoDeMechQueTodaviaNoAtaco() {
        Tablero tablero = new Tablero(MAPA);
        Unidad mech = new Combate();

        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 1);

        tablero.ocuparCasilla(origen, mech);
        mech.moverA(destino, tablero);
        
        mech.deshacerMovimiento(tablero);
        
        assertEquals(0, tablero.buscarPosicionDe(mech).getFila());
        assertEquals(0, tablero.buscarPosicionDe(mech).getColumna());
    }
    
    @Test
    public void test05DeshacerMovimientoDeMechQueYaAtaco() {
        Tablero tablero = new Tablero(MAPA);
        Unidad mech = new Combate();

        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 1);

        tablero.ocuparCasilla(origen, mech);
        mech.moverA(destino, tablero);
        
        mech.deshacerMovimiento(tablero);
        
        assertEquals(0, tablero.buscarPosicionDe(mech).getFila());
        assertEquals(0, tablero.buscarPosicionDe(mech).getColumna());
    }
    
    @Test
    public void test06DeshacerDosMovimientosDeMechEnElMismoTurno() {
        Tablero tablero = new Tablero(MAPA);
        Unidad mech = new Combate();

        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 1);
        Posicion destino2 = new Posicion(0, 2);

        tablero.ocuparCasilla(origen, mech);
        mech.moverA(destino, tablero);
        
        mech.deshacerMovimiento(tablero);
        
        mech.moverA(destino2, tablero);
        
        mech.deshacerMovimiento(tablero);
        
        assertEquals(0, tablero.buscarPosicionDe(mech).getFila());
        assertEquals(2, tablero.buscarPosicionDe(mech).getColumna());
    }
    
}