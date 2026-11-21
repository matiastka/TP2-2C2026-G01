    package edu.fiuba.algo3.modelo.unidades.veks;

    import java.util.Optional;

    import edu.fiuba.algo3.modelo.tablero.Casilla;
    import edu.fiuba.algo3.modelo.tablero.Direccion;
    import edu.fiuba.algo3.modelo.tablero.Posicion;
    import edu.fiuba.algo3.modelo.tablero.Tablero;
    import edu.fiuba.algo3.modelo.unidades.Ataque;
    import edu.fiuba.algo3.modelo.unidades.Movimiento;
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
        public void moverA(Posicion destino, Tablero tablero) {
            this.estadoTurno = this.estadoTurno.mover(this, destino, tablero);
        }

        public Optional<Casilla> simularImpactoDesde(Posicion origen, Direccion direccion, Tablero tablero) {
            return this.ataque.casillaImpactadaDesde(origen, direccion, tablero);
        }

        void ejecutarMovimientoVek(Posicion destino, Tablero tablero) {
            super.ejecutarMovimiento(destino, tablero);
        }

        public void prepararAtaque(Posicion objetivo) {
            this.estadoTurno = this.estadoTurno.prepararAtaque(this, objetivo);
        }

        public void resolverAtaque(Tablero tablero) {
            this.estadoTurno = this.estadoTurno.resolverAtaque(this, tablero);
        }

        void ejecutarAtaquePreparado(Tablero tablero) {
            this.ataquePreparado.ejecutar(this.ataque, obtenerPosicion(), tablero);
            this.ataquePreparado = new SinAtaquePreparado();
        }

        @Override
        protected void hundirse() {
            recibirDanio(obtenerVida());
        }

        @Override
        public void recibirDanio(int danio) {
            super.recibirDanio(danio);
            if (obtenerVida() <= 0) {
                this.estadoTurno = new EstadoVekMuerto();
            }
        }

        void apuntarA(Posicion objetivo) {
            validarObjetivo(objetivo);
            this.ataquePreparado = new AtaqueEnDireccion(obtenerPosicion().direccionHacia(objetivo));
        }

        protected abstract void validarObjetivo(Posicion objetivo);
}