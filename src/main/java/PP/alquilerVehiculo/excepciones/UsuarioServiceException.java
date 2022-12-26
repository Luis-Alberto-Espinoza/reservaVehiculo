package PP.alquilerVehiculo.excepciones;

/**
 *
 * @author Matias Luca Soto
 */
public class UsuarioServiceException extends Exception {

    /**
     * Creates a new instance of <code>UsuarioServiceException</code> without detail message.
     */
    public UsuarioServiceException() {
    }

    /**
     * Constructs an instance of <code>UsuarioServiceException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public UsuarioServiceException(String msg) {
        super(msg);
    }
}
