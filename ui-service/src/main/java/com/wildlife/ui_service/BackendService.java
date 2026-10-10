
package com.wildlife.ui_service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.ResourceAccessException;

@Service
public class BackendService {

    private final RestTemplate restTemplate;

    @Value("${backend.wildlife.url}")
    private String wildlifeUrl;

    @Value("${backend.rescue.url}")
    private String rescueUrl;

    @Value("${backend.field.url}")
    private String fieldUrl;

    @Value("${backend.conservation.url}")
    private String conservationUrl;

    public BackendService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, String> checkConnections() {
        Map<String, String> connections = new LinkedHashMap<>();

        connections.put("Wildlife Service", check(wildlifeUrl));
        connections.put("Incident and Rescue Service", check(rescueUrl));
        connections.put("Field Operations Service", check(fieldUrl));
        connections.put("Conservation Service", check(conservationUrl));

        return connections;
    }

    private String check(String url) {
        try {
            restTemplate.getForObject(url, String.class);
            return "Reachable";

        } catch (RestClientResponseException e) {
            // The server responded with an HTTP status, such as 404.
            return "Reachable";

        } catch (ResourceAccessException e) {
            // The server could not be contacted.
            return "Unavailable";

        } catch (Exception e) {
            e.printStackTrace();
            return "Unavailable";
        }
    }
}
