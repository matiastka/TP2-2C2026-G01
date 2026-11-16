package edu.fiuba.algo3.modelo.unidades.veks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.mapa.ConstructorDeTablero;
import edu.fiuba.algo3.modelo.tablero.Direccion;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.Combate;

public class EstadoTurnoVekTest {

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
    public void test01VekNoPuedeMoverseDosVecesEnElMismoTurno() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion origen = new Posicion(0, 0);
        Posicion primerDestino = new Posicion(0, 2);
        Posicion segundoDestino = new Posicion(0, 4);

        Vek vek = new Luciernaga();
        vek.colocarseEn(origen, tablero);

        vek.moverA(primerDestino, tablero);
        vek.moverA(segundoDestino, tablero); // Intento inválido: ya se movió

        assertEquals(primerDestino, vek.obtenerPosicion());
    }

    @Test
    public void test02VekPreparaAtaqueYNoHaceDanioInmediatoSinoAlResolver() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posVek = new Posicion(2, 2);
        Posicion posMech = new Posicion(2, 3);

        Vek vek = new Luciernaga();
        Mech mech = new Combate();

        vek.colocarseEn(posVek, tablero);
        mech.colocarseEn(posMech, tablero);

        int vidaInicialMech = mech.obtenerVida();

        // Fase 1: El Vek telegrafía/prepara el ataque
        vek.prepararAtaque(Direccion.ESTE);

        // El daño NO debe aplicarse todavía
        assertEquals(vidaInicialMech, mech.obtenerVida());

        // Fase 2: Al inicio del siguiente turno enemigo se resuelve el ataque
        vek.resolverAtaque(tablero);

        assertEquals(vidaInicialMech - 1, mech.obtenerVida());
    }

    @Test
    public void test03VekNoPuedeMoverseSiYaPreparoUnAtaque() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion origen = new Posicion(1, 1);
        Posicion destino = new Posicion(1, 2);

        Vek vek = new Luciernaga();
        vek.colocarseEn(origen, tablero);

        vek.prepararAtaque(Direccion.SUR);
        vek.moverA(destino, tablero); // Intento inválido: ya fijó su ataque

        assertEquals(origen, vek.obtenerPosicion());
    }

    @Test
    public void test04VekVuelveAEstarDisponibleLuegoDeResolverSuAtaque() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion origen = new Posicion(1, 1);
        Posicion nuevoDestino = new Posicion(1, 3);

        Vek vek = new Luciernaga();
        vek.colocarseEn(origen, tablero);

        // Turno 1: prepara ataque
        vek.prepararAtaque(Direccion.ESTE);

        // Turno 2: resuelve ataque y recupera capacidad de movimiento
        vek.resolverAtaque(tablero);
        vek.moverA(nuevoDestino, tablero);

        assertEquals(nuevoDestino, vek.obtenerPosicion());
    }

    @Test
    public void test05SiUnVekConAtaquePreparadoEsEmpujadoAtacaDesdeSuNuevaPosicion() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion posVekInicial = new Posicion(1, 1);
        Posicion posVekEmpujado = new Posicion(2, 1);
        Posicion posMech1 = new Posicion(1, 2);
        Posicion posMech2 = new Posicion(2, 2);

        Vek vek = new Luciernaga();
        Mech mech1 = new Combate();
        Mech mech2 = new Combate();

        vek.colocarseEn(posVekInicial, tablero);
        mech1.colocarseEn(posMech1, tablero);
        mech2.colocarseEn(posMech2, tablero);

        int vidaInicialMech1 = mech1.obtenerVida();
        int vidaInicialMech2 = mech2.obtenerVida();

        // El Vek prepara ataque hacia la derecha (apuntando originalmente a mech1)
        vek.prepararAtaque(Direccion.ESTE);

        // Un Mech empuja al Vek una casilla hacia abajo
        vek.serEmpujadoA(posVekEmpujado, tablero);

        // Al resolver, el Vek mantiene la dirección DERECHA desde (2,1), golpeando a mech2
        vek.resolverAtaque(tablero);

        assertEquals(vidaInicialMech1, mech1.obtenerVida()); // mech1 sale ileso
        assertEquals(vidaInicialMech2 - 1, mech2.obtenerVida()); // mech2 recibe el golpe
    }

    @Test
    public void test06ResolverAtaqueSinHaberPreparadoNadaNoCambiaEstado() {
        Tablero tablero = new ConstructorDeTablero().construir(MAPA);

        Posicion origen = new Posicion(0, 0);
        Posicion destino = new Posicion(0, 2);

        Vek vek = new Luciernaga();
        vek.colocarseEn(origen, tablero);

        // Llamar a resolver cuando está en EstadoVekDisponible no debe afectar su turno
        vek.resolverAtaque(tablero);
        vek.moverA(destino, tablero);

        assertEquals(destino, vek.obtenerPosicion());
    }
}