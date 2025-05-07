package se.hegardt.api.helpers

import java.nio.charset.StandardCharsets

class RestHelper {

    static String urlEncode(String s) {
        return URLEncoder.encode(s, StandardCharsets.UTF_8.name())
    }

    /**
     * Url encodes the values (not keys) in a map of parameters.
     */
    static Map<String, String> urlEncode(Map<String, String> params) {
        return params.collectEntries { key, value -> [key, urlEncode(value)]} as Map<String, String>
    }


    static String buildParameters(Map<String, String> params, boolean encode = false, String separator = "&") {
        return params
                .collect { key, val -> "$key=${encode ? urlEncode(val) : val}" }
                .join(separator)
    }
}