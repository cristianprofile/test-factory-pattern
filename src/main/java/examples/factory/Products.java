package examples.factory;

import static org.instancio.settings.Keys.COLLECTION_MAX_SIZE;
import static org.instancio.settings.Keys.COLLECTION_MIN_SIZE;
import static org.instancio.settings.Keys.FAIL_ON_ERROR;
import static org.instancio.settings.Keys.MAX_DEPTH;

import java.net.URI;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;
import org.instancio.Instancio;
import org.instancio.Select;
import org.instancio.settings.Settings;

@NoArgsConstructor
@AllArgsConstructor
@With
public class Products extends  AbstractBuilder<Product> {

  private static Product product = Instancio.of(Product.class)
      .withSettings(Settings.create().set(FAIL_ON_ERROR, true))
      .withSettings(Settings.create().set(MAX_DEPTH, 5))
      .withSettings(Settings.create().set(COLLECTION_MIN_SIZE, 2))
      .withSettings(Settings.create().set(COLLECTION_MAX_SIZE, 2))
      .set(Select.field(Product::reference), "reference1")
      .set(Select.field(Product::name), "product1")
      .set(Select.field(Product::size), 12.0)
      .set(Select.field(Product::picture), URI.create("https://aaa/image-603.png"))
      .create();

  private String reference = product.reference();

  private String name = product.name();

  private Double size = product.size();
  

  private URI picture = product.picture();

  public static Products createProduct() {
    return new Products();
  }

  public Product build() {
    return new Product(this.reference, this.name, this.size, this.picture);
  }

}
