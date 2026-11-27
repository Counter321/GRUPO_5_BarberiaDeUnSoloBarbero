package barberia;

/**
 * Salida por consola de la simulación con marca de tiempo.
 * <p>
 * Imprime cada evento con el formato {@code [mm:ss.mmm] [ETIQUETA] mensaje},
 * donde el tiempo es el transcurrido desde el inicio de la simulación. El
 * método {@link #log(String, String)} está sincronizado para que las líneas
 * de hilos distintos no se mezclen.
 *
 * @author Sergio Cadena
 * @version 0.1
 */
public class Registro {

    /** Clase de utilidad con métodos estáticos: no se instancia. */
    private Registro() {
    }

    // TODO Sergio: instante de inicio de la simulación.

    /**
     * Guarda el instante de inicio de la simulación. Lo llama el Main al
     * empezar.
     */
    public static void iniciar() {
        // TODO Sergio
    }

    /**
     * Imprime un evento con marca de tiempo.
     *
     * @param etiqueta tipo de evento (BARBERO, LLEGADA, ESPERA, VIP,
     *                 DESERCION, CORTE, LLENO)
     * @param mensaje  texto del evento
     */
    public static synchronized void log(String etiqueta, String mensaje) {
        // TODO Sergio: añadir la marca de tiempo [mm:ss.mmm]
        System.out.println("[" + etiqueta + "] " + mensaje);
    }
}
