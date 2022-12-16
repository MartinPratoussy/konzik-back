package com.konzik.Ticketmaster.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
public class TicketmasterService {

    @Value("ticketmasterAPI.discoveryAPI.key")
    private String apikey;

    @Value("ticketmasterAPI.discoveryAPI.baseUrl")
    private String baseUrl;

    public String buildRequest(Map<String, String> filters) throws IOException {

        try {
            // Build the query string
            StringBuilder queryString = new StringBuilder();
            queryString.append("apikey=").append(apikey);
            for (Map.Entry<String, String> param : filters.entrySet()) {
                queryString.append("&").append(param.getKey()).append("=").append(param.getValue());
            }

            // Open a connection to the API
            URL url = new URL(baseUrl + "?" + queryString.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
