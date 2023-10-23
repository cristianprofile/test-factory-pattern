package examples.factory;

import lombok.With;

import java.io.Serializable;
import java.net.URI;


/**
 * The type Product.
 */
@With
public record Product(String reference, String name, Double size, URI picture) implements Serializable {
}
