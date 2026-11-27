package barberia;

/**
 * Resultado de que un cliente intente entrar en la barbería.
 * <p>
 * Lo devuelve {@link SalaEspera#entrar(Cliente)} para que el cliente sepa
 * qué hacer a continuación.
 *
 * @author Aldair
 * @version 0.1
 */
public enum Resultado {

    /** El barbero dormía: el cliente lo despierta y pasa directamente a la silla de trabajo. */
    AL_BARBERO,

    /** El cliente se ha sentado en la sala de espera (al final, o en cabecera si es VIP). */
    SENTADO,

    /** No había silla libre: el cliente se marcha. */
    LLENO
}
