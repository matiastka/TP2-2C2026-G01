package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.unidades.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.mechs.unidadesMech.Combate;
import edu.fiuba.algo3.modelo.unidades.veks.Vek;
import edu.fiuba.algo3.modelo.unidades.veks.unidadesVek.Saltador;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ReceptorDeOcupanteTest {

    @Test
    public void test01UnMechSeEntregaComoMech() {
        // Arrange
        ReceptorDeOcupante receptor = mock(ReceptorDeOcupante.class);
        Mech mech = new Combate();

        // Act
        mech.entregarseA(receptor);

        // Assert
        verify(receptor).recibirMech(mech);
    }

    @Test
    public void test02UnVekSeEntregaComoVek() {
        // Arrange
        ReceptorDeOcupante receptor = mock(ReceptorDeOcupante.class);
        Vek vek = new Saltador();

        // Act
        vek.entregarseA(receptor);

        // Assert
        verify(receptor).recibirVek(vek);
    }

    @Test
    public void test03UnaCasillaSinOcupanteSeEntregaVacia() {
        // Arrange
        ReceptorDeOcupante receptor = mock(ReceptorDeOcupante.class);
        Ocupante sinOcupante = new SinOcupante();

        // Act
        sinOcupante.entregarseA(receptor);

        // Assert
        verify(receptor).recibirSinOcupante();
    }
}