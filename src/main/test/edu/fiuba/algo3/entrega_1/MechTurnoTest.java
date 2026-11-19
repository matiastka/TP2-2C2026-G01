package edu.fiuba.algo3.entrega_1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.mapa.ConstructorDeTablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Mech;
import edu.fiuba.algo3.modelo.unidades.MotivoRechazoAccion;
import edu.fiuba.algo3.modelo.unidades.ResultadoAccion;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.Luciernaga;

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
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion origen = new Posicion(0, 0);
        Posicion primerDestino = new Posicion(0, 2);
        Posicion segundoDestino = new Posicion(0, 4);

        Mech mech = new Combate();

        mech.colocarseEn(origen, tablero);

        mech.moverA(primerDestino, tablero);
        assertRejected(mech.moverA(segundoDestino, tablero), MotivoRechazoAccion.MOVIMIENTO_YA_REALIZADO);

        assertEquals(mech.obtenerPosicion(), primerDestino);
    }

    @Test
    public void test02MovimientoInvalidoNoConsumeLaAccionDelMech() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);
        Posicion origen = new Posicion(0, 0);
        Mech mech = new Combate();
        mech.colocarseEn(origen, tablero);

        assertRejected(mech.moverA(new Posicion(0, 4), tablero), MotivoRechazoAccion.DESTINO_NO_ALCANZABLE);
        ResultadoAccion<Void> resultadoMovimiento = mech.moverA(new Posicion(0, 1), tablero);

        assertEquals(ResultadoAccion.Exito.class, resultadoMovimiento.getClass());
        assertEquals(new Posicion(0, 1), mech.obtenerPosicion());
    }

    @Test
    public void test03MechNoPuedeAtacarDosVecesEnElMismoTurno() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posMech = new Posicion(2, 2);
        Posicion posVek1 = new Posicion(2, 3);
        Posicion posVek2 = new Posicion(3, 2);

        Mech mech = new Combate();
        Unidad vek1 = new Luciernaga();
        Unidad vek2 = new Luciernaga();

        mech.colocarseEn(posMech, tablero);
        vek1.colocarseEn(posVek1, tablero);
        vek2.colocarseEn(posVek2, tablero);

        mech.atacarA(posVek1, tablero);
        assertRejected(mech.atacarA(posVek2, tablero), MotivoRechazoAccion.TURNO_AGOTADO);

        assertEquals(3, vek2.obtenerVida()); // No debe recibir daño
    }

    @Test
    public void test04AlReiniciarTurnoElMechPuedeVolverAMoverse() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 1);
        Posicion posVek = new Posicion(0, 2);

        Mech mech = new Combate();
        Unidad vek = new Luciernaga();

        mech.colocarseEn(origen, tablero);
        vek.colocarseEn(posVek, tablero);

        mech.moverA(destino, tablero);
        mech.atacarA(posVek, tablero);

        mech.reiniciarTurno();

        Posicion nuevoDestino = new Posicion(1, 1);
        mech.moverA(nuevoDestino, tablero);

        assertEquals(mech.obtenerPosicion(), nuevoDestino);
    }
    
    @Test
    public void test05DeshacerMovimientoDeMechQueTodaviaNoAtaco() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 1);

        Mech mech = new Combate();

        mech.colocarseEn(origen, tablero);
        mech.moverA(destino, tablero);
        
        mech.deshacerMovimiento(tablero);

        assertEquals(mech.obtenerPosicion(), origen);
    }
    
    @Test
    public void test06DeshacerDejaLaCasillaDestinoLibre() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 1);

        Mech mech = new Combate();

        mech.colocarseEn(origen, tablero);
        mech.moverA(destino, tablero);
        
        mech.deshacerMovimiento(tablero);

        assertTrue(tablero.enCasilla(destino).estaLibre());
    }
    
    @Test
    public void test07DeshacerDosMovimientosDeMechEnElMismoTurno() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 1);
        Posicion destino2 = new Posicion(0, 2);

        Mech mech = new Combate();

        mech.colocarseEn(origen, tablero);
        mech.moverA(destino, tablero);
        
        mech.deshacerMovimiento(tablero);
        
        mech.moverA(destino2, tablero);
        
        assertRejected(mech.deshacerMovimiento(tablero), MotivoRechazoAccion.ACCION_NO_DISPONIBLE);
        
        assertEquals(mech.obtenerPosicion(), destino2);
    }

    @Test
    public void test08NoSePuedeDeshacerElMovimientoDespuesDeAtacar() {
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 1);
        Posicion posicionVek = new Posicion(0, 2);

        Mech mech = new Combate();
        Unidad vek = new Luciernaga();

        mech.colocarseEn(origen, tablero);
        vek.colocarseEn(posicionVek, tablero);

        mech.moverA(destino, tablero);
        mech.atacarA(posicionVek, tablero);

        // Act
        assertRejected(mech.deshacerMovimiento(tablero), MotivoRechazoAccion.ACCION_NO_DISPONIBLE);

        // Assert
        assertEquals(destino, mech.obtenerPosicion());
    }

    @Test
    public void test09AtaqueInvalidoNoConsumeLaAccionDelMech() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);
        Posicion origen = new Posicion(0, 0);
        Posicion objetivoNoAdyacente = new Posicion(0, 2);
        Posicion destinoMovimiento = new Posicion(0, 1);
        Mech mech = new Combate();
        Unidad vek = new Luciernaga();
        mech.colocarseEn(origen, tablero);
        vek.colocarseEn(objetivoNoAdyacente, tablero);

        assertRejected(mech.atacarA(objetivoNoAdyacente, tablero),
                MotivoRechazoAccion.DESTINO_NO_ALCANZABLE);
        ResultadoAccion<Void> resultadoMovimiento = mech.moverA(destinoMovimiento, tablero);

        assertEquals(ResultadoAccion.Exito.class, resultadoMovimiento.getClass());
        assertEquals(destinoMovimiento, mech.obtenerPosicion());
        assertEquals(3, vek.obtenerVida());
    }

    private void assertRejected(ResultadoAccion<?> resultado, MotivoRechazoAccion motivo) {
        assertEquals(ResultadoAccion.Rechazo.class, resultado.getClass());
        assertEquals(motivo, ((ResultadoAccion.Rechazo<?>) resultado).motivo());
    }

}