package se.hegardt.api.models

class RadarForwardResponseModel {
    List<Address> addresses
}

class Address {
    String addressLabel
    String city
    String county
    String state
    String stateCode
    String country
    String countryCode
    Double distance
    String formattedAddress
    Double latitude
    Double longitude
}
