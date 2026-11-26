package barberia;

/**
 * Hilo del barbero.
 * <p>
 * Pide el siguiente cliente a la sala, le corta el pelo durante 2000 ms y
 * repite. Si no hay clientes, {@link SalaEspera#siguienteCliente(Cliente)}
 * lo deja dormido. Termina cuando la sala cierra y no queda nadie.
 *
 * @author Sergio Cadena
 * @version 0.1
 */
public class Barbero extends Thread {

    /** Sala de espera de la que el barbero coge a los clientes. */
    private final SalaEspera sala;

    /**
     * Crea el barbero.
     *
     * @param sala sala de espera compartida con los clientes
     */
    public Barbero(SalaEspera sala) {
        this.sala = sala;
    }

    /**
     * Bucle de trabajo del barbero: pedir cliente, cortar y repetir hasta
     * que la sala cierre.
     */
    @Override
    public void run() {
        // Cliente al que acabamos de cortar el pelo. Al principio no hay
        // ninguno, así que empieza en null.
        Cliente anterior = null;

        try {
            while (true) {
                // Le pedimos el siguiente cliente a la sala. Si no hay nadie,
                // este método deja al barbero dormido (wait) hasta que llegue
                // alguien. Aquí NO se programa el dormir/despertar.
                Cliente cliente = sala.siguienteCliente(anterior);

                // null = la sala ha cerrado y no queda nadie: terminamos.
                if (cliente == null) {
                    break;
                }

                // Cortamos el pelo. Esto ocurre FUERA de siguienteCliente(),
                // o sea, sin tener el candado de la sala cogido.
                cortarPelo(cliente);

                // Guardamos a este cliente para pasárselo a la sala en la
                // siguiente vuelta del bucle.
                anterior = cliente;
            }
        } catch (InterruptedException e) {
            // Nos han interrumpido durante el corte: restauramos la marca de
            // interrupción y salimos del bucle, así que el hilo acaba.
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Corta el pelo a un cliente: dura 2000 ms. Se hace fuera del candado
     * de la sala para no bloquear a los clientes que llegan.
     *
     * @param c cliente al que se atiende
     * @throws InterruptedException si el barbero es interrumpido durante el corte
     */
    private void cortarPelo(Cliente c) throws InterruptedException {
        // Simulamos el corte con una pausa de 2 segundos. Como no hay
        // synchronized aquí, los clientes pueden seguir entrando a la sala
        // mientras el barbero duerme.
        Thread.sleep(2000);
    }
}
