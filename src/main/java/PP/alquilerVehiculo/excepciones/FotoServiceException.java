package PP.alquilerVehiculo.excepciones;

/**
 *
 * @author Matias Luca Soto
 */
public class FotoServiceException extends Exception {

    /**
     * Creates a new instance of <code>FotoServiceException</code> without detail message.
     */
    public FotoServiceException() {
    }


    /**
     * Constructs an instance of <code>FotoServiceException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public FotoServiceException(String msg) {
        super(msg);
    }
}
