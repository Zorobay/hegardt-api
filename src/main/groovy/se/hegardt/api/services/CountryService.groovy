package se.hegardt.api.services

import org.springframework.stereotype.Service

@Service
class CountryService {

    private final Map<String, String> COUNTRY_CODE_INDEX = createCountryCodeIndex()

    private static Map<String, String> createCountryCodeIndex() {
        return Locale.getISOCountries().collectEntries {String code ->
            Locale locale = new Locale('', code)
            return [locale.getDisplayCountry().toUpperCase(), code]
        }
    }

    String getCountryCode(String countryName) {
        String countryNameUpper = countryName.toUpperCase()
        return COUNTRY_CODE_INDEX.get(countryNameUpper)
    }

}
