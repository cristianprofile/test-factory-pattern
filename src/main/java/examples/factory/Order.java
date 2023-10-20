
package examples.factory;

import java.io.Serializable;
import java.net.URI;


/**
 * The type Order.
 */
public record Order(String reference, String name) implements Serializable {
}
