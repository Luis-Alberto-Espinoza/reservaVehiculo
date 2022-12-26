package PP.alquilerVehiculo.excepciones;

/**
 *
 * @author Matias Luca Soto
 */
public class ClienteServiceException extends Exception {

    /**
     * Creates a new instance of <code>UsuarioServiceException</code> without detail message.
     */
    public ClienteServiceException() {
    }

    /**
     * Constructs an instance of <code>UsuarioServiceException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ClienteServiceException(String msg) {
        super(msg);
    }
}
