package pl.umcs.api.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.umcs.generated.PurchaseOrderType;

@Endpoint
public class CountryEndpoint {
    private static final String NAMESPACE_URI = "http://localhost:8080/ws";

    private CountryRepository countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(localPart = "getCountryRequest")
    @ResponsePayload
    public PurchaseOrderType getCountry(@RequestPayload Long id) {
        return countryRepository.findOrder(id);
    }
}
