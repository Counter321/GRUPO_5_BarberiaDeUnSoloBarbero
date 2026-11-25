package barberia;

/**
 * Cliente VIP: si hay silla libre, se coloca en la cabecera de la cola.
 *
 * @author Fernando
 * @version 0.1
 */
public class ClienteVIP extends Cliente {

    /**
     * Crea un cliente de este tipo.
     *
     * @param id   número del cliente
     * @param sala sala de espera a la que llega
     */
    public ClienteVIP(int id, SalaEspera sala) {
        super(id, sala);
    }

    /** {@inheritDoc} */
    @Override
    public String getTipo() {
        // TODO Fernando
        return "";
    }

    /** {@inheritDoc} */
    @Override
    public long getMaxEsperaMs() {
        // TODO Fernando
        return 0;
    }

    /** {@inheritDoc} */
    @Override
    public boolean esVip() {
        // TODO Fernando
        return false;
    }
}
