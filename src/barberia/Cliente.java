package barberia;

import java.util.concurrent.locks.Condition;

/**
 * Cliente de la barbería (clase base de los tres tipos).
 * <p>
 * Todos los clientes hacen lo mismo: entran en la sala y, si se sientan,
 * esperan su turno. Las subclases solo definen su tipo, su tiempo máximo de
 * espera y si son VIP.
 *
 * @author Fernando
 * @version 0.1
 */
public abstract class Cliente extends Thread {

    /** Número del cliente (#1, #2…). */
    private final int id;

    /** Sala de espera a la que llega el cliente. */
    protected final SalaEspera sala;

    /** Si el barbero ya lo ha llamado. Solo lo usa la sala, con su candado cogido. */
    boolean atendido;

    /** Condición donde espera el cliente a que el barbero lo llame. La crea y usa la sala. */
    Condition turno;

    /**
     * Crea un cliente.
     *
     * @param id   número del cliente
     * @param sala sala de espera a la que llega
     */
    public Cliente(int id, SalaEspera sala) {
        this.id = id;
        this.sala = sala;
    }

    /**
     * Devuelve el número del cliente.
     *
     * @return número del cliente
     */
    public int getIdCliente() {
        return id;
    }

    /**
     * Devuelve el tipo de cliente.
     *
     * @return "Normal", "Impaciente" o "VIP"
     */
    public abstract String getTipo();

    /**
     * Devuelve el tiempo máximo que el cliente espera a ser atendido.
     *
     * @return milisegundos de espera máxima; 0 significa sin límite
     */
    public abstract long getMaxEsperaMs();

    /**
     * Indica si el cliente se coloca en la cabecera de la cola.
     *
     * @return {@code true} si es VIP
     */
    public abstract boolean esVip();

    /**
     * Comportamiento común: entrar en la sala y, si se sienta, esperar turno.
     */
    @Override
    public void run() {
        Resultado resultado = sala.entrar(this);

        if (resultado == Resultado.SENTADO) {
            Registro.log(getTipo() + " " + id + " espera su turno");
            resultado = sala.esperarTurno(this);
        }

        if (resultado == Resultado.AL_BARBERO) {
            Registro.log(getTipo() + " " + id + " ha llegado al barbero");
        } else if (resultado == Resultado.LLENO) {
            Registro.log(getTipo() + " " + id + " se marcha (sin silla o tiempo agotado)");
        }
    }
}
