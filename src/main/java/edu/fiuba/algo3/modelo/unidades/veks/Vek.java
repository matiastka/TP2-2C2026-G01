    package edu.fiuba.algo3.modelo.unidades.veks;

    import java.util.Optional;

    import edu.fiuba.algo3.modelo.tablero.Casilla;
    import edu.fiuba.algo3.modelo.tablero.Direccion;
    import edu.fiuba.algo3.modelo.tablero.Posicion;
    import edu.fiuba.algo3.modelo.tablero.Tablero;
    import edu.fiuba.algo3.modelo.unidades.Ataque;
    import edu.fiuba.algo3.modelo.unidades.Movimiento;
    import edu.fiuba.algo3.modelo.unidades.ResultadoAccion;
    import edu.fiuba.algo3.modelo.unidades.Unidad;

    public abstract class Vek extends Unidad {

        private EstadoTurnoVek estadoTurno;
        private AtaquePreparado ataquePreparado;
        private final Ataque ataque;

        protected Vek(int vida, int rango, Movimiento movimiento, Ataque ataque) {
            super(vida, rango, movimiento);
            this.estadoTurno = new EstadoVekDisponible();
            this.ataquePreparado = new SinAtaquePreparado();
            this.ataque = ataque;
        }

        @Override
        public ResultadoAccion<Void> moverA(Posicion destino, Tablero tablero) {
            return this.estadoTurno.mover(this, destino, tablero).map(nuevoEstado -> {
                this.estadoTurno = nuevoEstado;
                return null;
            });
        }

        public Optional<Casilla> simularImpactoDesde(Posicion origen, Direccion direccion, Tablero tablero) {
            return this.ataque.casillaImpactadaDesde(origen, direccion, tablero);
        }

        ResultadoAccion<Void> ejecutarMovimientoVek(Posicion destino, Tablero tablero) {
            return super.ejecutarMovimiento(destino, tablero);
        }

        public ResultadoAccion<Void> prepararAtaque(Direccion direccion) {
            return this.estadoTurno.prepararAtaque(this, direccion).map(nuevoEstado -> {
                this.estadoTurno = nuevoEstado;
                return null;
            });
        }

        public ResultadoAccion<Void> resolverAtaque(Tablero tablero) {
            return this.estadoTurno.resolverAtaque(this, tablero).map(nuevoEstado -> {
                this.estadoTurno = nuevoEstado;
                return null;
            });
        }

        void guardarDireccionAtaque(Direccion direccion) {
            this.ataquePreparado = new AtaqueEnDireccion(direccion);
        }

        void ejecutarAtaquePreparado(Tablero tablero) {
            this.ataquePreparado.ejecutar(this.ataque, obtenerPosicion(), tablero);
            this.ataquePreparado = new SinAtaquePreparado();
        }

        @Override
        protected void hundirse() {
            recibirDanio(obtenerVida());
        }

        public AtaqueAnunciado generarAtaqueAnunciado() {
            return new AtaqueAnunciado(this);
        }
}