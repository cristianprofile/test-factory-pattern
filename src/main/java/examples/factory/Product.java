package examples.factory;

import java.io.Serializable;
import java.net.URI;


/**
 * The type Product.
 */
public record Product(String reference, String name, Double size, URI picture) implements Serializable {
}
