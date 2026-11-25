package barberia;

/**
 * Sala de espera de la barbería: el recurso compartido entre el barbero y
 * los clientes.
 * <p>
 * Todos los datos compartidos (la cola, las sillas ocupadas, si el barbero
 * duerme y los contadores) están aquí dentro y solo se modifican con un
 * {@code ReentrantLock(true)} (justo) cogido. El barbero espera en la
 * condición {@code hayClientes} y cada cliente en su propia condición
 * {@code turno}.
 *
 * @author Aldair
 * @version 0.1
 */
public class SalaEspera {

    // TODO Aldair: candado justo, condición hayClientes, cola ArrayDeque<Cliente>,
    // capacidad, barberoDormido, cerrada y contadores.

    /**
     * Crea una sala de espera con un número fijo de sillas.
     *
     * @param numSillas número de sillas de espera (N)
     */
    public SalaEspera(int numSillas) {
        // TODO Aldair
    }

    /**
     * Intenta que un cliente entre en la barbería.
     * <p>
     * En un solo paso, con el candado cogido, decide si el cliente despierta
     * al barbero, se sienta (al final o en cabecera si es VIP) o se marcha
     * porque no hay silla libre.
     *
     * @param c cliente que llega
     * @return {@link Resultado#AL_BARBERO}, {@link Resultado#SENTADO} o
     *         {@link Resultado#LLENO}
     */
    public Resultado entrar(Cliente c) {
        // TODO Aldair
        return Resultado.LLENO;
    }

    /**
     * Bloquea al cliente hasta que el barbero lo llame.
     * <p>
     * Si el cliente tiene tiempo máximo de espera (el impaciente) y se agota
     * antes de que lo llamen, lo saca de la cola y se marcha. La espera es
     * bloqueante, sin espera activa.
     *
     * @param c cliente que espera
     * @return {@code true} si lo atienden; {@code false} si se marcha por
     *         impaciencia
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public boolean esperarTurno(Cliente c) throws InterruptedException {
        // TODO Aldair
        return false;
    }

    /**
     * Imprime el corte del cliente anterior y devuelve el siguiente cliente
     * de la cola.
     * <p>
     * Si no hay nadie, el barbero se duerme hasta que llegue un cliente.
     * Lo usa el barbero.
     *
     * @param anterior cliente al que acaba de atender, o {@code null} la
     *                 primera vez
     * @return el siguiente cliente, o {@code null} si la barbería está
     *         cerrada y no queda nadie
     * @throws InterruptedException si el barbero es interrumpido mientras duerme
     */
    public Cliente siguienteCliente(Cliente anterior) throws InterruptedException {
        // TODO Aldair
        return null;
    }

    /**
     * Cierra la barbería: no entran más clientes y el barbero termina cuando
     * atiende a los que quedan.
     */
    public void cerrar() {
        // TODO Aldair
    }

    /**
     * Devuelve el número de sillas de espera ocupadas.
     *
     * @return sillas ocupadas
     */
    public int getOcupacion() {
        // TODO Aldair
        return 0;
    }

    /**
     * Devuelve el número total de sillas de espera.
     *
     * @return capacidad de la sala (N)
     */
    public int getCapacidad() {
        // TODO Aldair
        return 0;
    }

    /**
     * Devuelve cuántos clientes han sido atendidos.
     *
     * @return clientes atendidos
     */
    public int getAtendidos() {
        // TODO Aldair
        return 0;
    }

    /**
     * Devuelve cuántos clientes se han marchado porque la sala estaba llena.
     *
     * @return clientes marchados por sala llena
     */
    public int getMarchadosLleno() {
        // TODO Aldair
        return 0;
    }

    /**
     * Devuelve cuántos clientes impacientes se han marchado sin ser atendidos.
     *
     * @return clientes desertores
     */
    public int getDesertores() {
        // TODO Aldair
        return 0;
    }
}
