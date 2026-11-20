package edu.fiuba.algo3.modelo.unidades;

import java.util.Objects;
import java.util.function.Function;

public abstract class ResultadoAccion<T> {

    private ResultadoAccion() {
    }

    public static <T> ResultadoAccion<T> exito(T valor) {
        return new Exito<>(valor);
    }

    public static <T> ResultadoAccion<T> rechazo(MotivoRechazoAccion motivo) {
        return new Rechazo<>(motivo);
    }

    public abstract <U> ResultadoAccion<U> map(Function<? super T, ? extends U> transformacion);

    public abstract <R> R fold(
            Function<? super T, ? extends R> alExito,
            Function<? super MotivoRechazoAccion, ? extends R> alRechazo);

    private static final class Exito<T> extends ResultadoAccion<T> {
        private final T valor;

        private Exito(T valor) {
            this.valor = valor;
        }

        @Override
        public <U> ResultadoAccion<U> map(Function<? super T, ? extends U> transformacion) {
            return ResultadoAccion.exito(transformacion.apply(valor));
        }

        @Override
        public <R> R fold(
                Function<? super T, ? extends R> alExito,
                Function<? super MotivoRechazoAccion, ? extends R> alRechazo) {
            return alExito.apply(valor);
        }
    }

    private static final class Rechazo<T> extends ResultadoAccion<T> {
        private final MotivoRechazoAccion motivo;

        private Rechazo(MotivoRechazoAccion motivo) {
            this.motivo = Objects.requireNonNull(motivo);
        }

        @Override
        public <U> ResultadoAccion<U> map(Function<? super T, ? extends U> transformacion) {
            return ResultadoAccion.rechazo(motivo);
        }

        @Override
        public <R> R fold(
                Function<? super T, ? extends R> alExito,
                Function<? super MotivoRechazoAccion, ? extends R> alRechazo) {
            return alRechazo.apply(motivo);
        }
    }
}
