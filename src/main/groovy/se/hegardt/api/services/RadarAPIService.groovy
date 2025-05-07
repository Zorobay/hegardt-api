package se.hegardt.api.services

import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import se.hegardt.api.models.Address
import se.hegardt.api.models.LatLong
import se.hegardt.api.models.RadarForwardResponseModel
import se.hegardt.api.rest.RestClient

@CompileStatic
@Service
class RadarAPIService {

    @Value('${radar.api.baseurl}')
    String baseUrl
    @Value('${radar.api.apikey}')
    String apiKey

    @Autowired
    RestClient restClient

    /**
     * Returns a list of LatLong objects with latitudes and longitudes corresponding to the queried location.
     * @param country ISO country code
     * @param region name of region, f.ex. US state, Swedish län or Australian Territory
     * @param city name of city
     * @return a list of LatLong objects with latitudes and longitudes corresponding to the queried location.
     */
    List<LatLong> geocodeToCoordinates(String country, String region, String city) {
        Map<String, String> params = removeNullValues([country: country, query: [city, region].grep().join(',')])
        ResponseEntity response = restClient.get(geocodeForwardUrl, params, httpEntity, RadarForwardResponseModel)

        if (response.statusCode.is2xxSuccessful()) {
            RadarForwardResponseModel model = response.body as RadarForwardResponseModel
            return model.addresses.collect{
                Address address -> new LatLong(address.latitude, address.longitude)
            }
        } else {
            return []
        }
    }

    private String getGeocodeForwardUrl() {
        return "${baseUrl}/forward"
    }

    private HttpEntity getHttpEntity() {
        HttpHeaders headers = new HttpHeaders()
        headers.put(HttpHeaders.AUTHORIZATION, [apiKey])
        return new HttpEntity(headers)
    }

    private static Map<String, String> removeNullValues(Map<String, String> map) {
        return map.findAll { key, value -> value != null }
    }
}