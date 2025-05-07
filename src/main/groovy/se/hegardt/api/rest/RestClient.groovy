package se.hegardt.api.rest

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import se.hegardt.api.helpers.RestHelper

@Component
class RestClient {

    final static Logger log = LoggerFactory.getLogger(this.class)

    RestTemplate restTemplate = new RestTemplate()

    ResponseEntity get(String url, Map<String, String> params, HttpEntity httpEntity = null, Class deserializeType = String) {
        return call(url, HttpMethod.GET, params, httpEntity, deserializeType)
    }

    private ResponseEntity call(String url, HttpMethod method, Map<String, String> params, HttpEntity httpEntity = null, Class deserializeType = String) {
        String urlWithParams = buildUrl(url, params)
        String uuid = UUID.randomUUID().toString().takeRight(10)

        log.info("[START][${uuid}] ${urlWithParams}")
        ResponseEntity responseEntity = restTemplate.exchange(urlWithParams, method, httpEntity, deserializeType)
        log.info("[FINISH][${uuid}] ${urlWithParams}")

        return responseEntity
    }

    private static String buildUrl(String url, Map<String, String> params) {
        StringBuilder builder = new StringBuilder(url)

        if (!url.contains("?")) {
            builder.append("?")
        } else if (!url.endsWith("&")) {
            builder.append("&")
        }

        builder.append(RestHelper.buildParameters(params, false))
        return builder.toString()
    }
}