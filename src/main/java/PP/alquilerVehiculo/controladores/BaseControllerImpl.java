package PP.alquilerVehiculo.controladores;


public abstract class BaseControllerImpl{//<E extends Base, S extends BaseServiceImpl<E, Long>> implements BaseControllerr<E, Long> {
//    @Autowired
//    protected S servicio;
//
//    @GetMapping("")
//    public ResponseEntity<?> getAll() {
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error\"}");
//        }
//    }
//
//    @GetMapping("/paged")
//    public ResponseEntity<?> getAll(Pageable pageable) {
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll(pageable));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error\"}");
//        }
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getOne(@PathVariable Long id) {
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(servicio.findById(id));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error\"}");
//        }
//    }
//
//    @PostMapping("")
//    public ResponseEntity<?> save(@RequestBody E entity) {
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(servicio.save(entity));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error\"}");
//        }
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody E entity) {
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(servicio.update(id, entity));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error\"}");
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable Long id) {
//        try {
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(servicio.delete(id));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error\"}");
//        }
//    }
}


