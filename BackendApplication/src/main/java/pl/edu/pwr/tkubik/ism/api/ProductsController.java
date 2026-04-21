package pl.edu.pwr.tkubik.ism.api;

import pl.edu.pwr.tkubik.ism.aspect.LogExecutionTime;
import pl.edu.pwr.tkubik.ism.aspect.LogMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import pl.edu.pwr.tkubik.ism.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductsController implements ProductApi {

    @Autowired
    private NativeWebRequest nativeWebRequest = null;

    @Override
    @LogMethod
    @LogExecutionTime
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(nativeWebRequest);
    }

    @Override
    @LogMethod
    @LogExecutionTime
    public ResponseEntity<List<Product>> findAllProducts() {
        List<Product> products = new ArrayList<>();

        Product p1 = new Product();
        p1.setProductId(1L);
        p1.setName("Ceramic Mug");
        p1.setPrice(45.0);
        p1.setStockQuantity(10);
        p1.setStatus("Active");

        products.add(p1);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Override
    @LogMethod
    @LogExecutionTime
    public ResponseEntity<Product> addProduct(Product product) {
        product.setProductId(101L);
        product.setStatus("Active");
        System.out.println("Nouveau produit ajouté : " + product.getName());

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Override
    @LogMethod
    @LogExecutionTime
    public ResponseEntity<Product> findProductById(Long productId) {
        Product p = new Product();
        p.setProductId(productId);
        p.setName("Wrocław Art Print");
        p.setPrice(120.0);
        p.setStockQuantity(5);
        p.setStatus("Active");

        return new ResponseEntity<>(p, HttpStatus.OK);
    }
}