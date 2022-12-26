package PP.alquilerVehiculo.excepciones;

/**
 *
 * @author Matias Luca Soto
 */
public class VotoServiceException extends Exception {

    /**
     * Creates a new instance of <code>VotoServiceException</code> without detail message.
     */
    public VotoServiceException() {
    }


    /**
     * Constructs an instance of <code>VotoServiceException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public VotoServiceException(String msg) {
        super(msg);
    }
}
