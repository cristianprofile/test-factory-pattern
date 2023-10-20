package examples.factory;


import org.instancio.Instancio;
import org.instancio.Select;
import org.instancio.settings.Settings;

import static org.instancio.settings.Keys.*;

public class Orders {

    private static final Order ORDER = Instancio.of(Order.class)
            .withSettings(Settings.create().set(FAIL_ON_ERROR, true))
            .withSettings(Settings.create().set(MAX_DEPTH, 5))
            .withSettings(Settings.create().set(COLLECTION_MIN_SIZE, 2))
            .withSettings(Settings.create().set(COLLECTION_MAX_SIZE, 2))
            .set(Select.field(Order::reference), "reference1")
            .set(Select.field(Order::name), "name1")
            .create();

    private String reference = ORDER.reference();

    private String name = ORDER.name();

    public static Orders createOrder() {
        return new Orders();
    }

    public Order build() {
        return new Order(this.reference, this.name);
    }

    public Orders withReference(String reference) {
         this.reference = reference ;
        return this;
    }

    public Orders withName(String name) {
         this.name = name ;
        return this;
    }

  
}
