package barberia;

/**
 * Cliente impaciente: se sienta, pero si pasan 3 segundos sin ser atendido, se marcha.
 *
 * @author Fernando
 * @version 0.1
 */
public class ClienteImpaciente extends Cliente {

    /**
     * Crea un cliente de este tipo.
     *
     * @param id   número del cliente
     * @param sala sala de espera a la que llega
     */
    public ClienteImpaciente(int id, SalaEspera sala) {
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
