package barberia;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

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
 * @version 0.2
 */
public class SalaEspera {

    /**
     * Candado que protege todos los datos de la sala. Es justo ({@code true}):
     * los hilos lo consiguen por orden de llegada, así nadie se queda
     * esperándolo para siempre (sin inanición).
     */
    private final ReentrantLock lock = new ReentrantLock(true);

    /** Condición donde duerme el barbero cuando no hay clientes. */
    private final Condition hayClientes = lock.newCondition();

    /**
     * Cola de clientes sentados en la sala de espera. Los VIP se añaden al
     * principio y los demás al final; el barbero saca siempre del principio.
     * Solo se toca con el candado cogido.
     */
    private final Deque<Cliente> cola = new ArrayDeque<>();

    /** Número de sillas de espera (N). */
    private final int capacidad;

    /** Si el barbero está dormido esperando clientes. */
    private boolean barberoDormido = false;

    /**
     * Cliente que ha despertado al barbero y pasa directamente a la silla de
     * trabajo, sin ocupar silla de espera. Lo recoge
     * {@link #siguienteCliente(Cliente)}.
     */
    private Cliente clienteDirecto = null;

    /** Si la barbería ha cerrado y ya no entran clientes. */
    private boolean cerrada = false;

    /** Clientes atendidos por el barbero. */
    private int atendidos = 0;

    /** Clientes que se han marchado porque la sala estaba llena. */
    private int marchadosLleno = 0;

    /** Clientes impacientes que se han marchado sin ser atendidos. */
    private int desertores = 0;

    /**
     * Crea una sala de espera con un número fijo de sillas.
     *
     * @param numSillas número de sillas de espera (N)
     */
    public SalaEspera(int numSillas) {
        this.capacidad = numSillas;
    }

    /**
     * Intenta que un cliente entre en la barbería.
     * <p>
     * En un solo paso, con el candado cogido, decide si el cliente despierta
     * al barbero, se sienta (al final o en cabecera si es VIP) o se marcha
     * porque no hay silla libre. Al hacerlo todo con el candado cogido, dos
     * clientes no pueden ocupar a la vez la última silla (se evita la race
     * condition de «comprobar y después actuar»).
     *
     * @param c cliente que llega
     * @return {@link Resultado#AL_BARBERO}, {@link Resultado#SENTADO} o
     *         {@link Resultado#LLENO}
     */
    public Resultado entrar(Cliente c) {
        lock.lock();
        try {
            String nombre = "Cliente #" + c.getIdCliente() + " (" + c.getTipo() + ")";

            // 1. El barbero duerme: el cliente lo despierta y pasa directo a la silla de trabajo
            if (barberoDormido) {
                barberoDormido = false;
                clienteDirecto = c;
                Registro.log("LLEGADA", nombre + " entra y DESPIERTA al barbero.");
                hayClientes.signal();
                return Resultado.AL_BARBERO;
            }

            // 2. No queda silla libre: se marcha
            if (cola.size() >= capacidad) {
                marchadosLleno++;
                return Resultado.LLENO;
            }

            // 3. Hay silla libre: se sienta (el VIP en cabecera)
            c.atendido = false;
            c.turno = lock.newCondition();
            if (c.esVip()) {
                cola.addFirst(c);
                Registro.log("VIP", nombre + " entra y se coloca en CABECERA de la sala de espera."
                        + " (Ocupación: " + cola.size() + "/" + capacidad + ")");
            } else {
                cola.addLast(c);
                Registro.log("ESPERA", nombre + " se sienta en sala de espera."
                        + " (Ocupación: " + cola.size() + "/" + capacidad + ")");
            }
            return Resultado.SENTADO;
        } finally {
            lock.unlock();
        }
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
        // TODO Aldair (tarjeta: Sala: esperarTurno())
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
        // TODO Aldair (tarjeta: Sala: siguienteCliente())
        return null;
    }

    /**
     * Cierra la barbería: no entran más clientes y el barbero termina cuando
     * atiende a los que quedan.
     */
    public void cerrar() {
        // TODO Aldair (tarjeta: Sala: cerrar() y contadores)
    }

    /**
     * Devuelve el número de sillas de espera ocupadas.
     *
     * @return sillas ocupadas
     */
    public int getOcupacion() {
        lock.lock();
        try {
            return cola.size();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Devuelve el número total de sillas de espera.
     *
     * @return capacidad de la sala (N)
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Devuelve cuántos clientes han sido atendidos.
     *
     * @return clientes atendidos
     */
    public int getAtendidos() {
        // TODO Aldair (tarjeta: Sala: cerrar() y contadores)
        return 0;
    }

    /**
     * Devuelve cuántos clientes se han marchado porque la sala estaba llena.
     *
     * @return clientes marchados por sala llena
     */
    public int getMarchadosLleno() {
        // TODO Aldair (tarjeta: Sala: cerrar() y contadores)
        return 0;
    }

    /**
     * Devuelve cuántos clientes impacientes se han marchado sin ser atendidos.
     *
     * @return clientes desertores
     */
    public int getDesertores() {
        // TODO Aldair (tarjeta: Sala: cerrar() y contadores)
        return 0;
    }
}