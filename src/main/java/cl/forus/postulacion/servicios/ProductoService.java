package cl.forus.postulacion.servicios;

import cl.forus.postulacion.entidades.Producto;
import cl.forus.postulacion.repos.ProductoRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

  @Autowired
  ProductoRepo productoRepo;

  public List<Producto> getAllProducts() {
    return productoRepo.findAll();
  }

  public Optional<Producto> getProductoById(Integer id) {
    return productoRepo.findById(id);
  }

  public ResponseEntity<Producto> deleteProduct(Integer id) {
    Optional<Producto> productoOptional = productoRepo.findById(id);

    if (!productoOptional.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    Producto producto = productoOptional.get();
    productoRepo.delete(producto);
    return ResponseEntity.ok(producto);
  }

  public ResponseEntity<Producto> updateProduct(Integer id, Producto producto) {
    Optional<Producto> productoOptional = productoRepo.findById(id);

    if (!productoOptional.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    Producto productoDb = productoOptional.get();
    productoDb.setNombre(producto.getNombre());
    productoDb.setDescripcion(producto.getDescripcion());
    productoDb.setCantidad(producto.getCantidad());
    productoDb.setUbicacion(producto.getUbicacion());

    productoRepo.save(productoDb);
    return ResponseEntity.ok(productoDb);
  }

  public ResponseEntity createProduct(Producto producto) {
    productoRepo.save(producto);

    return ResponseEntity.created(null).build();
  }
}
