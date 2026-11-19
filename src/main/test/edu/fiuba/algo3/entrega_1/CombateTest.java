package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.mapa.ConstructorDeTablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.Luciernaga;
import edu.fiuba.algo3.modelo.unidades.Mech;
import edu.fiuba.algo3.modelo.unidades.MotivoRechazoAccion;
import edu.fiuba.algo3.modelo.unidades.ResultadoAccion;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CombateTest {
    private static final String[] MAPA = {
            "AMESSMSM",
            "AMSSECSA",
            "ASESSCSA",
            "ASSESCSA",
            "ASSSSCSA",
            "ASSSECSA",
            "ASSSSCSA",
            "AMMMSCSA"
    };

    @Test
    public void test01UnMechCombateSeMueve1ALaDerecha(){
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posicionMechOrigen = new Posicion(2,3);
        Posicion posicionMechDestino = new Posicion(2,4);

        Unidad mechCombate = new Combate();


        mechCombate.colocarseEn(posicionMechOrigen, tablero);

        // Act
        mechCombate.moverA(posicionMechDestino, tablero);

        // Assert
        assertSame(mechCombate, tablero.enCasilla(posicionMechDestino).obtenerOcupante());

    }

    @Test
    public void test02UnMechCombateSeMueve1Arriba(){
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posicionMechOrigen = new Posicion(2,3);
        Posicion posicionMechDestino = new Posicion(1,3);

        Unidad mechCombate = new Combate();

        mechCombate.colocarseEn(posicionMechOrigen, tablero);

        // Act
        mechCombate.moverA(posicionMechDestino,tablero);

        // Assert
        assertSame(mechCombate, tablero.enCasilla(posicionMechDestino).obtenerOcupante());

    }
    @Test
    public void test03AlMoverseElMechLaCasillaDeOrigenQuedaLibre() {
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posicionMechOrigen = new Posicion(2,3);
        Posicion posicionMechDestino = new Posicion(2,4);

        Unidad mechCombate = new Combate();

        mechCombate.colocarseEn(posicionMechOrigen, tablero);

        // Act
        mechCombate.moverA(posicionMechDestino, tablero);

        // Assert
        assertTrue(tablero.enCasilla(posicionMechOrigen).estaLibre());
    }

    @Test
    public void test04AlMoverseElMechQuedaEnLaCasillaDestino() {
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posicionMechOrigen = new Posicion(2,3);
        Posicion posicionMechDestino = new Posicion(2,4);

        Unidad mechCombate = new Combate();

        mechCombate.colocarseEn(posicionMechOrigen, tablero);

        // Act
        mechCombate.moverA(posicionMechDestino, tablero);

        // Assert
        assertSame(mechCombate, tablero.enCasilla(posicionMechDestino).obtenerOcupante());
    }

    @Test
    public void test05UnMechNoSeMueveFueraDeSuRango() {
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posicionMechDestino = new Posicion(4,5);
        Posicion posicionMechOrigen = new Posicion(4,1);

        Unidad mechCombate = new Combate();

        mechCombate.colocarseEn(posicionMechOrigen, tablero);

        // Act
        // Se intenta mover 4 casillas a la derecha, cuando su rango es 3
        assertRejected(mechCombate.moverA(posicionMechDestino, tablero),
                MotivoRechazoAccion.DESTINO_NO_ALCANZABLE);

        // Assert
        assertSame(mechCombate, tablero.enCasilla(posicionMechOrigen).obtenerOcupante());
    }

    @Test
    public void test06UnMechCombatePuedeMoverseExactamenteSuRangoMaximo() {
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posicionMechOrigen = new Posicion(4,1);
        Posicion posicionRangoDestino = new Posicion(4,4);

        Unidad mechCombate = new Combate();

        mechCombate.colocarseEn(posicionMechOrigen, tablero);

        // Act
        mechCombate.moverA(posicionRangoDestino, tablero);

        // Assert
        assertSame(mechCombate, tablero.enCasilla(posicionRangoDestino).obtenerOcupante());
    }

    @Test
    public void test07UnMechNoPuedeAtravesarAOtroMech() {
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posicionMech1Origen = new Posicion(4,1);
        Posicion posicionMech2Origen = new Posicion(4,2);
        Posicion posicionDestinoMech1 = new Posicion(4,3);

        Unidad mechCombate = new Combate();
        Unidad otroMech = new Combate();

        mechCombate.colocarseEn(posicionMech1Origen, tablero);
        otroMech.colocarseEn(posicionMech2Origen, tablero);

        // Act
        // mech intentar ir al (4,3) cuando tiene un mech en (4,2). Por ende no se moverá
        assertRejected(mechCombate.moverA(posicionDestinoMech1, tablero),
                MotivoRechazoAccion.CAMINO_BLOQUEADO);

        // Assert
        assertSame(mechCombate, tablero.enCasilla(posicionMech1Origen).obtenerOcupante());
    }

    @Test
    public void test08UnMechNoPuedeAtravesarUnEdificioYSeQuedaEnElMismoLugar() {
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posicionMechOrigen = new Posicion(2,1);
        Posicion posicionEdificio = new Posicion(2,3);

        Unidad mechCombate = new Combate();

        mechCombate.colocarseEn(posicionMechOrigen, tablero);

        // Act
        assertRejected(mechCombate.moverA(posicionEdificio, tablero),
                MotivoRechazoAccion.CAMINO_BLOQUEADO);

        // Assert
        assertSame(mechCombate, tablero.enCasilla(posicionMechOrigen).obtenerOcupante());
    }

    @Test
    public void test09ElPunioHace2DeDanioAUnaLucierganaAdyacente(){
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posicionMechOrigen = new Posicion(4,2);
        Posicion posicionVekOrigen = new Posicion(4,3);

        Mech mechCombate = new Combate(); // Duda mia (Mati) es correcto que usemos Mech en vez de Unidad. ¿Esto no viola el principio de "programe contra interfaces"?
        Unidad vekLuciernaga = new Luciernaga();

        int vidaVekEsperada = 1;

        mechCombate.colocarseEn(posicionMechOrigen, tablero);
        vekLuciernaga.colocarseEn(posicionVekOrigen, tablero);

        // Act
        mechCombate.atacarA(posicionVekOrigen, tablero);

        // Assert
        assertEquals(vidaVekEsperada, vekLuciernaga.obtenerVida());
    }

    @Test
    public void test10ElPunioEmpujaAlObjetivoUnaCasillaEnDireccionAlGolpe(){
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posicionMechOrigen = new Posicion(4,2);
        Posicion posicionVekOrigen = new Posicion(4,3);
        Posicion posicionTrasEmpuje = new Posicion(4,4);

        Mech mechCombate = new Combate();
        Unidad vekLuciernaga = new Luciernaga();

        mechCombate.colocarseEn(posicionMechOrigen, tablero);
        vekLuciernaga.colocarseEn(posicionVekOrigen, tablero);

        // Act
        mechCombate.atacarA(posicionVekOrigen, tablero);

        // Assert
        assertSame(vekLuciernaga, tablero.enCasilla(posicionTrasEmpuje).obtenerOcupante());
    }

    @Test
    public void test11TrasSerEmpujadoLaCasillaDondeEstabaQuedaLibre(){
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posicionMechOrigen = new Posicion(4,2);
        Posicion posicionVekOrigen = new Posicion(4,3);

        Mech mechCombate = new Combate();
        Unidad vekLuciernaga = new Luciernaga();


        mechCombate.colocarseEn(posicionMechOrigen, tablero);
        vekLuciernaga.colocarseEn(posicionVekOrigen, tablero);

        // Act
        mechCombate.atacarA(posicionVekOrigen, tablero);

        // Assert
        assertTrue(tablero.enCasilla(posicionVekOrigen).estaLibre());
    }

    @Test
    public void test12SeEmpujaAUnVekAlLimiteDelMapaElMismoQuedaEnElBorde(){
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posicionMechOrigen = new Posicion(6,4);
        Posicion posicionVekOrigen = new Posicion(7,4);

        Mech mechCombate = new Combate();
        Unidad vekLuciernaga = new Luciernaga();

        mechCombate.colocarseEn(posicionMechOrigen, tablero);
        vekLuciernaga.colocarseEn(posicionVekOrigen, tablero);

        // Act
        mechCombate.atacarA(posicionVekOrigen, tablero);

        // Assert
        assertSame(vekLuciernaga, tablero.enCasilla(posicionVekOrigen).obtenerOcupante());
    }

    @Test
    public void test13AtacarAUnVekNoAdyacenteNoLeQuitaVida(){
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posicionMechOrigen = new Posicion(4,1);
        Posicion posicionVekOrigen = new Posicion(4,3);

        Mech mechCombate = new Combate();
        Unidad vekLuciernaga = new Luciernaga();

        int vidaEsperada = 3;

        mechCombate.colocarseEn(posicionMechOrigen, tablero);
        vekLuciernaga.colocarseEn(posicionVekOrigen, tablero);

        // Act
        ResultadoAccion<Void> resultadoAtaque = mechCombate.atacarA(posicionVekOrigen, tablero);

        // Assert
        assertRejected(resultadoAtaque, MotivoRechazoAccion.DESTINO_NO_ALCANZABLE);
        assertEquals(vidaEsperada, vekLuciernaga.obtenerVida());
    }

    @Test
    public void test14AtacarAUnVekNoAdyacenteNoLoEmpuja(){
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posicionMechOrigen = new Posicion(4,1);
        Posicion posicionVekOrigen = new Posicion(4,3);

        Mech mechCombate = new Combate();
        Unidad vekLuciernaga = new Luciernaga();

        mechCombate.colocarseEn(posicionMechOrigen, tablero);
        vekLuciernaga.colocarseEn(posicionVekOrigen, tablero);

        // Act
        ResultadoAccion<Void> resultadoAtaque = mechCombate.atacarA(posicionVekOrigen, tablero);

        // Assert
        assertRejected(resultadoAtaque, MotivoRechazoAccion.DESTINO_NO_ALCANZABLE);
        assertSame(vekLuciernaga, tablero.enCasilla(posicionVekOrigen).obtenerOcupante());
    }

    @Test
    public void test15AtacarUnaCasillaVaciaNoDebeLanzarUnError(){ // Test polemico, por ahora lo dejo
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posicionMech = new Posicion(4,2);
        Posicion posicionVacia = new Posicion(4,3);

        Mech mechCombate = new Combate();

        mechCombate.colocarseEn(posicionMech, tablero);

        // Act & Assert
        assertEquals(ResultadoAccion.Exito.class, mechCombate.atacarA(posicionVacia, tablero).getClass());
    }

    @Test
    public void test16ElPunioNoEmpujaAUnaUnidadContraUnEdificio(){
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posicionMech = new Posicion(3,1);
        Posicion posicionVek = new Posicion(3,2); // en (3,3) hay un Edificio

        Mech mechCombate = new Combate();
        Unidad vekLuciernaga = new Luciernaga();

        mechCombate.colocarseEn(posicionMech, tablero);
        vekLuciernaga.colocarseEn(posicionVek, tablero);

        // Act
        mechCombate.atacarA(posicionVek, tablero);

        // Assert
        assertSame(vekLuciernaga, tablero.enCasilla(posicionVek).obtenerOcupante());

    }

    @Test
    public void test17ElPunioEmpujaAUnaUnidadQueYaSeMovioEnElTurno(){
        // Arrange
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posicionMech = new Posicion(4,2);
        Posicion posicionVekOrigen = new Posicion(4,4);
        Posicion posicionVekDestino = new Posicion(4,3);

        Mech mechCombate = new Combate();
        Unidad vekLuciernaga = new Luciernaga();

        mechCombate.colocarseEn(posicionMech, tablero);
        vekLuciernaga.colocarseEn(posicionVekOrigen, tablero);

        vekLuciernaga.moverA(posicionVekDestino, tablero);

        // Act
        mechCombate.atacarA(posicionVekDestino, tablero);

        // Assert
        assertSame(vekLuciernaga, tablero.enCasilla(posicionVekOrigen).obtenerOcupante());
    }
    @Test
    public void test18CombateEmpiezaCon3DeVida() {
        // Arrange
        Combate combate = new Combate();
        int vidaEsperada = 3;

        // Act
        int vidaActual = combate.obtenerVida();

        // Assert
        assertEquals(vidaEsperada, vidaActual);
    }

    @Test
    public void test19CombateTieneRango3() {
        // Arrange
        Combate combate = new Combate();
        int rangoEsperado = 3;

        // Act
        int rangoActual = combate.obtenerRango();

        // Assert
        assertEquals(rangoEsperado, rangoActual);
    }

    @Test
    public void test20Recibir2DeDanioLoDejaCon1DeVida() {
        // Arrange
        Combate combate = new Combate();
        int danioARecibir = 2;
        int vidaEsperada = 1;
        
        // Act
        combate.recibirDanio(danioARecibir);
        int vidaActual = combate.obtenerVida();
        
        // Assert
        assertEquals(vidaEsperada, vidaActual);
    }

    private void assertRejected(ResultadoAccion<?> resultado, MotivoRechazoAccion motivo) {
        assertEquals(ResultadoAccion.Rechazo.class, resultado.getClass());
        assertEquals(motivo, ((ResultadoAccion.Rechazo<?>) resultado).motivo());
    }
}
