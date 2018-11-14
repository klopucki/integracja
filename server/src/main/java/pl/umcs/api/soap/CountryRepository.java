package pl.umcs.api.soap;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import pl.umcs.generated.ObjectFactory;
import pl.umcs.generated.PurchaseOrderType;
import pl.umcs.generated.USAddress;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CountryRepository {

    private static final Map<Integer, PurchaseOrderType> orders = new HashMap<>();

    @PostConstruct
    public void initData() {
        USAddress newYork = new ObjectFactory().createUSAddress();
        newYork.setCity("New York");
        newYork.setCountry("US");
        newYork.setStreet("Washington");
        newYork.setZip(BigInteger.valueOf(1));
        newYork.setName("Potatoes");

        PurchaseOrderType newYorkType = new ObjectFactory().createPurchaseOrderType();
        newYorkType.setBillTo(newYork);

        orders.put(1, newYorkType);

        USAddress sanfrancisco = new ObjectFactory().createUSAddress();
        sanfrancisco.setCity("Sanfrancisco");
        sanfrancisco.setCountry("US");
        sanfrancisco.setStreet("NONAME");
        sanfrancisco.setZip(BigInteger.valueOf(2));
        sanfrancisco.setName("TOMATOES");

        PurchaseOrderType sanfranciscoType = new ObjectFactory().createPurchaseOrderType();
        sanfranciscoType.setBillTo(newYork);

        orders.put(2, sanfranciscoType);
    }

    public PurchaseOrderType findOrder(Long id) {
        Assert.notNull(id, "The country's name must not be null");
        return orders.get(id);
    }
}
