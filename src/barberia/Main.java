package barberia;

/**
 * Punto de entrada de la simulación de la barbería de un solo barbero.
 * <p>
 * Crea la sala y el barbero, genera clientes de los tres tipos, espera a que
 * terminen, cierra la barbería y muestra el resumen final.
 *
 * @author Fernando
 * @version 0.1
 */
public class Main {

    /** Clase de arranque: no se instancia. */
    private Main() {
    }

    /**
     * Lanza la simulación.
     *
     * @param args argumentos de la línea de comandos (no se usan)
     * @throws InterruptedException si el hilo main es interrumpido
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO Fernando:
        // 1. Registro.iniciar()
        // 2. Crear la SalaEspera y el Barbero, y arrancar el barbero
        // 3. Crear y arrancar clientes de los tres tipos, con pausas entre llegadas
        // 4. join() de los clientes, sala.cerrar() y join() del barbero
        // 5. Imprimir el resumen: atendidos, marchados por sala llena y desertores
    }
}
