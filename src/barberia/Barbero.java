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
        // TODO Sergio
    }

    /**
     * Corta el pelo a un cliente: dura 2000 ms. Se hace fuera del candado
     * de la sala para no bloquear a los clientes que llegan.
     *
     * @param c cliente al que se atiende
     * @throws InterruptedException si el barbero es interrumpido durante el corte
     */
    private void cortarPelo(Cliente c) throws InterruptedException {
        // TODO Sergio
    }
}
