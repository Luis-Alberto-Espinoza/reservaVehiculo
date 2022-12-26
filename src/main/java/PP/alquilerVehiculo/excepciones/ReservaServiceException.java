package PP.alquilerVehiculo.excepciones;

/**
 *
 * @author Matias Luca Soto
 */
public class ReservaServiceException extends Exception {

    /**
     * Creates a new instance of <code>MascotaServiceException</code> without detail message.
     */
    public ReservaServiceException() {
    }

    /**
     * Constructs an instance of <code>MascotaServiceException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ReservaServiceException(String msg) {
        super(msg);
    }
}
