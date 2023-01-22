package PP.alquilerVehiculo.servicio;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import PP.alquilerVehiculo.entidad.Cliente;
import PP.alquilerVehiculo.excepciones.ClienteServiceException;
import PP.alquilerVehiculo.repositorios.ClienteRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class  ClienteServicio implements BaseService<Cliente> {
    public ClienteRepositorio clienteRepositorio;

    public  Cliente buscarXcorreo(String correo){
        return clienteRepositorio.buscarPorMail(correo);
    }
    @Transactional
    public  Cliente buscarXdni(long dni){
        return clienteRepositorio.buscarPorDNI(dni);
    }
    public ClienteServicio(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    @Transactional
    public  void registrar(String nombre, String apellido, String email, String clave1, String clave2, String direccion, Long edad, long telefono, long dni) throws ClienteServiceException {

        //Validación de los parámetros
        validar(nombre, apellido, email, clave1, clave2  ); //, direccion, edad, telefono, dni);

        //Creamos el Objeto Cliente
        Cliente cliente  = new Cliente();

        //Seteamos sus atributos con los parámetros recibidos
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setMail(email);
        cliente.setClave1(clave1);
        cliente.setAlta(new Date());
        cliente.setDni(dni);
        cliente.setDireccion(direccion);
        cliente.setTelefono(telefono);
        cliente.setEdad(edad);

        /* Invocamos al atributo repositorio (que es en realidad una interfaz que extiende
        de JpaRepository) y llamamos al método 'save' enviándole como argumento el Objeto
        Cliente para que lo guarde en la base de datos. */
        clienteRepositorio.save(cliente);
    }
    private void validar(String nombre, String apellido, String email, String clave1, String clave2 ) throws ClienteServiceException {// , String direccion, long edad, long telefono, Long dni ) throws ClienteServiceException {

        //Validaciones de los argumentos
        if (nombre == null || nombre.isEmpty()) {
            throw new ClienteServiceException("El nombre del cliente no puede ser nulo.");
        }

        if (apellido == null || apellido.isEmpty()) {
            throw new ClienteServiceException("El apellido del cliente no puede ser nulo.");
        }

        if (email == null || email.isEmpty()) {
            throw new ClienteServiceException("El email del cliente no puede ser nulo.");
        } else if (!email.contains("@")) {
            throw new ClienteServiceException("El email del cliente no es válido.");
        } else if (clienteRepositorio.buscarPorMail(email) .equals("cambia aca!!!!") ) {
            throw new ClienteServiceException("El mail ya está en uso.");
        }

        if (clave1 == null || clave1.isEmpty()) {
            throw new ClienteServiceException("La clave del cliente no puede ser nulo.");
        } else if (clave1.length() < 3) {
            throw new ClienteServiceException("La clave del cliente debe contener 3 o más caracteres.");
        }

        //Validamos que las dos claves recibidas sean iguales
        if (!clave1.equals(clave2)) {
            throw new ClienteServiceException("Las contraseñas no coinciden.");
        }

//        if (direccion == null || direccion.isEmpty()) {
//            throw new ClienteServiceException("La direccion del cliente no puede ser nulo.");
//        }
//
//        if (edad == 0 || edad < 18 ) {
//            throw new ClienteServiceException("El Edad del cliente no puede ser menor a 18 años.");
//        }
//        if (dni == null || dni.longValue() < 8) {
//            throw new ClienteServiceException("El DNI del cliente no fue bien colocado.");
//        }
//        if (telefono == 0 ) {
//            throw new ClienteServiceException("El telefono del cliente no puede ser nulo.");
//        }
    }
    //Modificar un Cliente ya existente en la base de datos
    @Transactional
    public void modificar(long id, String nombre, String apellido, String email, String clave1, String clave2 )throws ClienteServiceException { //  ,  String direccion, long edad, long telefono, Long dni ) throws ClienteServiceException {

        /* Llamamos a un método de ClienteRepositorio para buscar un registro por el Id,
        atrapamos el resultado usando el método get() y lo guardo en mi Objeto Cliente */
        // Cliente cliente = clienteRepositorio.findById(id).get();
        //
        //Validación de los parámetros
        System.out.println(" ClienteServicio 104 Modificar + mail "+email );
        //validar(nombre, apellido, email, clave1, clave2 ); // , direccion, edad, telefono, dni);
        //Validación del mail
       // validarMail(id, email);

        //Validamos que se encuentre un Cliente con el Id recibido
        Optional<Cliente> respuesta = clienteRepositorio.findById(id);
        System.out.println(" ClienteServicio 111 Modificar + respuesta "+respuesta.toString() );

        //Método que devuelve true si se encontró un registro en la base de datos
        if (respuesta.isPresent()) {

            //Guardamos el resultado de la búsqueda en el repositorio en el Objeto Cliente
            Cliente cliente = respuesta.get();

            //Seteamos los nuevos atributos al Objeto Cliente
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setMail(email);
            cliente.setClave1(clave1);


            //Actualizamos la entrada dentro del repositorio usando el mismo método save()
            clienteRepositorio.save(cliente);

        } else {
            //Si el método .isPresent() da false es porque no se encontró ningún Cliente
            throw new ClienteServiceException("No se encontró el cliente solicitado.");
        }
    }

    //Método para deshabilitar un Cliente
    @Transactional
    public void deshabilitar(long id) throws ClienteServiceException {

        //Validamos que se encuentre un Cliente con el Id recibido
        Optional<Cliente> respuesta = clienteRepositorio.findById(id);

        //Método que devuelve true si se encontró un registro en la base de datos
        if (respuesta.isPresent()) {

            //Guardamos el resultado de la búsqueda en el repositorio en el Objeto Cliente
            Cliente cliente = respuesta.get();

            //Seteamos la fecha actual de baja del Cliente
            cliente.setBaja(new Date());

            //Actualizamos la entrada dentro del repositorio usando el mismo método save()
            clienteRepositorio.save(cliente);

        } else {
            //Si el método .isPresent() da false es porque no se encontró ningún Cliente
            throw new ClienteServiceException("No se encontró el cliente solicitado.");
        }
    }

    //Método para rehabilitar un Cliente
    @Transactional
    public void rehabilitar(long id) throws ClienteServiceException {

        //Validamos que se encuentre un Cliente con el Id recibido
        Optional<Cliente> respuesta = clienteRepositorio.findById(id);

        //Método que devuelve true si se encontró un registro en la base de datos
        if (respuesta.isPresent()) {

            //Guardamos el resultado de la búsqueda en el repositorio en el Objeto Cliente
            Cliente cliente = respuesta.get();

            //Seteamos como null el atributo Baja del Cliente
            cliente.setBaja(null);

            //Actualizamos la entrada dentro del repositorio usando el mismo método save()
            clienteRepositorio.save(cliente);

        } else {
            //Si el método .isPresent() da false es porque no se encontró ningún Cliente
            throw new ClienteServiceException("No se encontró el cliente solicitado.");
        }
    }

    public Cliente buscarPorId(long id) {
        return clienteRepositorio.getReferenceById(id);
    }

    //Método para delegar la tarea de validar los parámetros
    private void validar(String nombre, String apellido, String email, String clave1, String clave2, String idZona, boolean newUser) throws ClienteServiceException {

        //Validaciones de los argumentos
        if (nombre == null || nombre.isEmpty()) {
            throw new ClienteServiceException("El nombre del cliente no puede ser nulo.");
        }

        if (apellido == null || apellido.isEmpty()) {
            throw new ClienteServiceException("El apellido del cliente no puede ser nulo.");
        }

        if (email == null || email.isEmpty()) {
            throw new ClienteServiceException("El email del cliente no puede ser nulo.");
        } else if (!email.contains("@")) {
            throw new ClienteServiceException("El email del cliente no es válido.");
        } else if (clienteRepositorio.buscarPorMail(email) != null && newUser) {
            throw new ClienteServiceException("El mail ya está en uso.");
        }

        if (clave1 == null || clave1.isEmpty()) {
            throw new ClienteServiceException("La clave del cliente no puede ser nulo.");
        } else if (clave1.length() < 8) {
            throw new ClienteServiceException("La clave del cliente debe contener 8 o más caracteres.");
        }

        //Validamos que las dos claves recibidas sean iguales
        if (!clave1.equals(clave2)) {
            throw new ClienteServiceException("Las contraseñas no coinciden.");
        }


    }

    /**
     * Validamos que el nuevo mail ingresado de un cliente ya registrado sea nuevo o no pertenezca a otro cliente
     * @param id
     * @param email
     * @throws ClienteServiceException
     */
    private void validarMail(long id, String email) throws ClienteServiceException {
        Cliente newUser = clienteRepositorio.buscarPorMail(email);
//        if (newUser != null && id != (newUser.getId())) {
//            throw new ClienteServiceException("El nuevo mail ya está en uso.");
//        }
    }
    public void GenerarReservas() {
    }

    public void CancelarReservas() {
    }

    public void consultarReservas() {
    }

    public void RegistraCleinte() {
    }

    @Override
    @Transactional
    public List<Cliente> findAll() throws Exception {
        try {
            List<Cliente> entities = clienteRepositorio.findAll();
            return entities;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Cliente findById(long id) throws Exception {
        try {
            Optional<Cliente> entityOptional = clienteRepositorio.findById(id);
            return entityOptional.get();

        } catch (Exception e) {
            throw new Exception(e.getMessage());

        }
    }

    @Override
    @Transactional
    public Cliente save(Cliente entity) throws Exception {
        try {
            entity = clienteRepositorio.save(entity);
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());

        }
    }

    @Override
    @Transactional
    public Cliente update(long id, Cliente entity) throws Exception {
        try {
            Optional<Cliente> entityoptional = clienteRepositorio.findById(id);
            Cliente cliente = entityoptional.get();
            cliente = clienteRepositorio.save(cliente);
            return cliente;
        } catch (Exception e) {
            throw new Exception(e.getMessage());

        }
    }

    @Override
    @Transactional
    public boolean delete(long id) throws Exception {

        try {
            if (clienteRepositorio.existsById(id)) {
                clienteRepositorio.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());

        }

    }
}
