package barberia;

/**
 * Cliente normal: se sienta si hay silla libre; si está llena, se marcha.
 *
 * @author Fernando
 * @version 0.1
 */
public class ClienteNormal extends Cliente {

    /**
     * Crea un cliente de este tipo.
     *
     * @param id   número del cliente
     * @param sala sala de espera a la que llega
     */
    public ClienteNormal(int id, SalaEspera sala) {
        super(id, sala);
    }

    /** {@inheritDoc} */
    @Override
    public String getTipo() {
        return "Normal";
    }

    /** {@inheritDoc} */
    @Override
    public long getMaxEsperaMs() {
        return 0;
    }

    /** {@inheritDoc} */
    @Override
    public boolean esVip() {
        return false;
    }
}
