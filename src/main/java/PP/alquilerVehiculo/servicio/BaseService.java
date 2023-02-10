package PP.alquilerVehiculo.servicio;

import PP.alquilerVehiculo.entidad.Vehiculo;

import java.util.List;

//public interface BaseService<E extends Base, ID extends Serializable> {
public interface BaseService<E> {
    public List<E> findAll() throws Exception;
  //  public Page<E> findAll(Pageable pageable) throws Exception;
    public E findById(long id) throws Exception;
    public E save(E entity) throws Exception;
    public E update(long id, E entity) throws Exception;
public void deleteById (long id)throws Exception;
}