package cl.forus.postulacion.controladores;

import cl.forus.postulacion.entidades.Producto;
import cl.forus.postulacion.servicios.ProductoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class ProductoController {

  @Autowired
  ProductoService productoService;

  @GetMapping("/productos")
  public @ResponseBody
  ResponseEntity<List<Producto>> getProductos() {
    return ResponseEntity.ok(productoService.getAllProducts());
  }

  @GetMapping("/productos/{id}")
  public @ResponseBody
  ResponseEntity<Producto> getProducto(@PathVariable Integer id) {

    Optional<Producto> productoOptional = productoService.getProductoById(id);
    if (!productoOptional.isPresent()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(productoOptional.get());
  }

  @DeleteMapping("/productos/{id}")
  public ResponseEntity deleteProductById(@PathVariable Integer id) {
    return productoService.deleteProduct(id);
  }

  @PutMapping("/productos/{id}")
  public ResponseEntity updateProductById(@PathVariable Integer id,
      @RequestBody Producto producto) {
    return productoService.updateProduct(id, producto);
  }

  @PostMapping("/productos")
  public ResponseEntity createProduct(@RequestBody Producto producto) {
    return productoService.createProduct(producto);
  }
}
