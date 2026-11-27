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
        Registro.iniciar();

        SalaEspera sala = new SalaEspera(5);
        Barbero barbero = new Barbero(sala, 2000);
        barbero.start();

        java.util.ArrayList<Cliente> clientes = new java.util.ArrayList<>();
        for (int id = 1; id <= 15; id++) {
            Cliente cliente;
            if (id % 7 == 0) {
                cliente = new ClienteVIP(id, sala);
            } else if (id % 3 == 0) {
                cliente = new ClienteImpaciente(id, sala);
            } else {
                cliente = new ClienteNormal(id, sala);
            }

            cliente.start();
            clientes.add(cliente);
            Thread.sleep(250);
        }

        for (Cliente cliente : clientes) {
            cliente.join();
        }

        sala.cerrar();
        barbero.join();
        Registro.resumen(sala);
    }
}
